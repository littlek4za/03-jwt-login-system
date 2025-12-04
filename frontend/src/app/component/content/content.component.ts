import { Component, OnInit } from '@angular/core';
import { AxiosService } from 'src/app/service/axios.service';

@Component({
  selector: 'app-content',
  templateUrl: './content.component.html',
  styleUrls: ['./content.component.css']
})
export class ContentComponent implements OnInit {
  componentToShow: string = "welcome";

  constructor(private axiosService: AxiosService) { }

  ngOnInit(): void {
    this.axiosService.logoutEvent.subscribe(()=>{
      this.componentToShow = "login";
    })
  }

  onLogin(input: any):void {
    this.axiosService.request(
      "POST",
      "/login",
      {
        login: input.login,
        password: input.password
      }
    ).then(response => {
      this.axiosService.setAuthToken(response.data.token);
      this.componentToShow = "messages";
    });
  }

  onRegister(input: any): void{
    this.axiosService.request(
      "POST",
      "/register",
      {
        firstName: input.firstName,
        lastName: input.lastName,
        login: input.login,
        password: input.password
      }
    ).then(response => {
      this.axiosService.setAuthToken(response.data.token);
      this.componentToShow = "messages";
    });
  }

  showComponent(componentToShow: string):void {
    this.componentToShow = componentToShow;
  }

  onLogout():void {
    if (localStorage.getItem("auth_token")){
      localStorage.removeItem("auth_token");
      alert("Logout Successfully.");
    }
    else {
      alert("You are not logged in");
    }
    this.componentToShow = ('welcome');
  }

}
