import { Injectable } from '@angular/core';
import axios from 'axios';
import { Subject } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class AxiosService {

  public logoutEvent = new Subject<void>();

  constructor() {
    axios.defaults.baseURL = "http://localhost:8080"
    axios.defaults.headers.post["Content-Type"] = "application/json"
    // interceptor inject token when found token in localstorage
    axios.interceptors.request.use(
      (config) => {
        const token = window.localStorage.getItem("auth_token");
        if (token) {
          config.headers.Authorization = `Bearer ${token}`;
        }
        return config;
      },
      (error) => Promise.reject(error)
    );

    // interceptor alert token is expired after getting 401 as response, and remove the token
    axios.interceptors.response.use(
      (response) => response,
      (error) => {
        console.log("Request URL:", error.config.url);
        console.log("Request Method:", error.config.method);
        console.log("Response Data:", error.response.data);

        const status = error.response?.status;
        const msg = error.response?.data?.message;

        if (status === 401) {
          localStorage.removeItem("auth_token");
          alert(msg || "Please log in to continue.");
          this.logoutEvent.next();
        } else if (msg) {
          alert(msg);
        } else {
          console.error("Unhandled error: ", error);
        }
        return Promise.reject(error);
      }
    );
  }

  getAuthToken(): string | null {
    return window.localStorage.getItem("auth_token");
  }

  setAuthToken(token: string | null): void {
    if (token != null) {
      window.localStorage.setItem("auth_token", token);
    } else {
      window.localStorage.removeItem("auth_token");
    }
  }

  request(method: string, url: string, data: any): Promise<any> {
    // let headers = {};

    // // const publicUrls = ["/login", "/register"];

    // // if (this.getAuthToken() != null && !publicUrls.includes(url)) {
    // if (this.getAuthToken() != null) {
    //   headers = { "Authorization": "Bearer " + this.getAuthToken() };
    // }
    // console.log("Token header:", headers);

    return axios({
      method: method,
      url: url,
      data: data,
      // headers: headers,
    });
  }
}
