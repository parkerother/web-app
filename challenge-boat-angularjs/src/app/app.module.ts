import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RestApiService } from './servives/rest-api.service';
import { HomeComponent } from './home/home.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BoatDetailsComponent } from './boat-details/boat-details.component';
import { AddBoatComponent } from './add-boat/add-boat.component';
import { BoatListComponent } from './boat-list/boat-list.component';
import { HeaderComponent } from './header/header.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    BoatDetailsComponent,
    AddBoatComponent,
    BoatListComponent,
    HeaderComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
    
  ],
  providers: [RestApiService],
  bootstrap: [AppComponent]
})
export class AppModule { }
