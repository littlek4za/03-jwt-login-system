import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './component/header/header.component';
import { AuthContentComponent } from './component/content/auth-content/auth-content.component';
import { WelcomeContentComponent } from './component/content/welcome-content/welcome-content.component';
import { LoginFormComponent } from './component/content/login-form/login-form.component';
import { ContentComponent } from './component/content/content.component';
import { FormsModule } from '@angular/forms';
import { ButtonsComponent } from './component/content/buttons/buttons.component';
import { DebugContentComponent } from './component/content/debug-content/debug-content.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    AuthContentComponent,
    WelcomeContentComponent,
    LoginFormComponent,
    ContentComponent,
    ButtonsComponent,
    DebugContentComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
