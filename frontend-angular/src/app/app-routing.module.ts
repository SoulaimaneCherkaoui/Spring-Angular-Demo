import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {ProfileComponent} from "./profile/profile.component";
import {LoginComponent} from "./login/login.component";
import {LoadStudentsComponent} from "./load-students/load-students.component";
import {LoadpaymentsComponent} from "./loadpayments/loadpayments.component";
import {DashboardComponent} from "./dashboard/dashboard.component";
import {PaymentsComponent} from "./payments/payments.component";
import {StudentsComponent} from "./students/students.component";
import {AdminTemplateComponent} from "./admin-template/admin-template.component";
import {AuthGuard} from "./guards/auth.guard";
import {AuthorizationGuard} from "./guards/authorization.guard";

const routes: Routes = [
  {path:"",component : LoginComponent},
  {path:"login",component : LoginComponent},
  {path:"admin",component : AdminTemplateComponent,canActivate:[AuthGuard], children : [
      {path:"home",component : HomeComponent},
      {path:"profile",component : ProfileComponent},
      {path:"loadStudents",component : LoadStudentsComponent, canActivate : [AuthorizationGuard], data : {roles: ['ADMIN']}},
      {path:"loadPayments",component : LoadpaymentsComponent, canActivate : [AuthorizationGuard], data : {roles: ['ADMIN']}},
      {path:"dashboard",component : DashboardComponent},
      {path:"payments",component : PaymentsComponent},
      {path:"students",component : StudentsComponent}
    ]},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
