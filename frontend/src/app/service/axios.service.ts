import { Injectable } from '@angular/core';
import axios from 'axios';

@Injectable({
  providedIn: 'root'
})
export class AxiosService {

  constructor() {
    axios.defaults.baseURL = "http://localhost:8080"
    axios.defaults.headers.post["Content-Type"] = "application/json"

    // axios.interceptors.response.use(
    //   response => response,
    //   error => {
    //     if (error.response && error.response.status === 401) {
    //       // Token expired or invalid
    //       window.localStorage.removeItem("auth_token");
    //       delete axios.defaults.headers.common["Authorization"];

    //       // Optional: redirect to login page (only if using router)
    //       // window.location.href = "/";

    //       alert("Your session has expired. Please log in again.");

    //       return Promise.reject(error);
    //     }

    //     return Promise.reject(error);
    //   }
    // );
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
    let headers = {};

    // const publicUrls = ["/login", "/register"];

    // if (this.getAuthToken() != null && !publicUrls.includes(url)) {
    if (this.getAuthToken() != null) {
      headers = { "Authorization": "Bearer " + this.getAuthToken() };
    }
    console.log("Token header:", headers);

    return axios({
      method: method,
      url: url,
      data: data,
      headers: headers,
    });
  }
}
