import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdminbookComponent } from './components/adminbook/adminbook.component';
import { AdminnavComponent } from './components/adminnav/adminnav.component';
import { AdminviewappliedrequestComponent } from './components/adminviewappliedrequest/adminviewappliedrequest.component';
import { AdminviewbookComponent } from './components/adminviewbook/adminviewbook.component';
import { AdminviewfeedbackComponent } from './components/adminviewfeedback/adminviewfeedback.component';
import { ErrorComponent } from './components/error/error.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';
import { UseraddfeedbackComponent} from './components/useraddfeedback/useraddfeedback.component';
import { UseraddrequestComponent } from './components/useraddrequest/useraddrequest.component';
import { UsernavComponent } from './components/usernav/usernav.component';
import { UserviewappliedrequestComponent } from './components/userviewappliedrequest/userviewappliedrequest.component';
import { UserviewbooksComponent } from './components/userviewbooks/userviewbooks.component';
import { UserviewfeedbackComponent } from './components/userviewfeedback/userviewfeedback.component';
import { AuthGuard } from './components/guards/auth.guard';

const routes: Routes = [
  { path: '', component: HomePageComponent },
  { path: 'adminbook', component: AdminbookComponent, canActivate: [AuthGuard] },
  { path: 'adminnav', component: AdminnavComponent, canActivate: [AuthGuard] },
  { path: 'adminviewappliedrequest', component: AdminviewappliedrequestComponent, canActivate: [AuthGuard] },
  { path: 'adminviewbook', component: AdminviewbookComponent, canActivate: [AuthGuard] },
  { path: 'adminviewfeedback', component: AdminviewfeedbackComponent, canActivate: [AuthGuard] },
  { path: 'error', component: ErrorComponent },
  { path: 'home', component: HomePageComponent },
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: SignupComponent },
  { path: 'useraddfeedback', component: UseraddfeedbackComponent, canActivate: [AuthGuard] },
  { path: 'useraddrequest/:bookId', component: UseraddrequestComponent, canActivate: [AuthGuard] },
  { path: 'usernav', component: UsernavComponent, canActivate: [AuthGuard] },
  { path: 'userviewappliedrequest', component: UserviewappliedrequestComponent, canActivate: [AuthGuard] },
  { path: 'userviewbooks', component: UserviewbooksComponent, canActivate: [AuthGuard] },
  { path: 'userviewfeedback', component: UserviewfeedbackComponent, canActivate: [AuthGuard] }
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
