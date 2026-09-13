import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AdminbookComponent } from './components/adminbook/adminbook.component';
import { AdminnavComponent } from './components/adminnav/adminnav.component';
import { AdminviewappliedrequestComponent } from './components/adminviewappliedrequest/adminviewappliedrequest.component';
import { AdminviewbookComponent } from './components/adminviewbook/adminviewbook.component';
import { ErrorComponent } from './components/error/error.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';
import { UseraddfeedbackComponent } from './components/useraddfeedback/useraddfeedback.component';
import { UseraddrequestComponent } from './components/useraddrequest/useraddrequest.component';
import { UsernavComponent } from './components/usernav/usernav.component';
import { UserviewbooksComponent } from './components/userviewbooks/userviewbooks.component';
import { UserviewfeedbackComponent } from './components/userviewfeedback/userviewfeedback.component';
import { AdminviewfeedbackComponent } from './components/adminviewfeedback/adminviewfeedback.component';
import { UserviewappliedrequestComponent } from './components/userviewappliedrequest/userviewappliedrequest.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SearchByTitlePipe } from './pipes/search-by-title.pipe';
import { FilterByGenrePipe } from './pipes/filter-by-genre.pipe';
import { UniversalSearchPipe } from './pipes/universal-search.pipe';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { SearchByTitleRequestPipe } from './pipes/search-by-title-request.pipe';
import { StatusFilterPipe } from './pipes/status-filter.pipe';
import { AuthService } from './services/auth.service';


@NgModule({
  declarations: [
    AppComponent,
    AdminbookComponent,
    AdminnavComponent,
    AdminviewappliedrequestComponent,
    AdminviewbookComponent,
    ErrorComponent,
    HomePageComponent,
    LoginComponent,
    SignupComponent,
    UseraddfeedbackComponent,
    UseraddrequestComponent,
    UsernavComponent,
    UserviewbooksComponent,
    UserviewfeedbackComponent,
    AdminviewfeedbackComponent,
    SearchByTitlePipe,
    SearchByTitleRequestPipe,
    StatusFilterPipe,
    UserviewappliedrequestComponent,
    AdminviewfeedbackComponent,
    FilterByGenrePipe,
    UniversalSearchPipe
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    CommonModule,
    HttpClientModule,
    ReactiveFormsModule,
    RouterModule,
    FormsModule
  ],
  providers: [AuthService],
  bootstrap: [AppComponent]
})
export class AppModule { }
