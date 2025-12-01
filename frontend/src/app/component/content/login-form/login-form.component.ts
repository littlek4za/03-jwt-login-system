import { Component, EventEmitter, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {

  @Output() onSubmitLoginEvent = new EventEmitter();
  @Output() onSubmitRegisterEvent = new EventEmitter();

  active: string = "login";

  firstName: string = "";
  lastName: string = "";
  login: string = "";
  password: string = "";

  constructor() { }

  ngOnInit(): void {
  }

  onLoginTab(): void {
    this.active = "login"
  }

  onRegisterTab(): void {
    this.active = "register"
  }

  onSubmitLogin(): void {
    this.onSubmitLoginEvent.emit({
      "login": this.login,
      "password": this.password
    });
  }

  // // alternate method 2
  // onSubmitLogin(form: NgForm): void {
  //   // form.value contains all input values: { login: "...", password: "..." }
  //   this.onSubmitLoginEvent.emit(form.value);
  // }

  onSubmitRegister(): void {
    this.onSubmitRegisterEvent.emit({
      "login": this.login,
      "password": this.password,
      "firstName": this.firstName,
      "lastName": this.lastName
    });
  }

}
