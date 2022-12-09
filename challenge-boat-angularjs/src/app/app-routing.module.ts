import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { Boat } from './model/boat';
import { BoatListComponent } from './boat-list/boat-list.component';
import { BoatDetailsComponent } from './boat-details/boat-details.component';
import { AddBoatComponent } from './add-boat/add-boat.component';

const routes: Routes = [
  {path:"",redirectTo:"login",pathMatch:"full"},
  {path:"login",component:LoginComponent},
  {path: 'boats', component: BoatListComponent },
  {path: 'boats/:id', component: BoatDetailsComponent },
  {path: 'add', component: AddBoatComponent }
];



@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
