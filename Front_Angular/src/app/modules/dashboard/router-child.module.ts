import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SurveyComponent } from '../survey/components/survey/survey.component';
import { LoginComponent } from '../dashboard/components/login/login.component';

import { HomeComponent } from './components/home/home.component';


const childRoutes: Routes = [
    { path: '', component: HomeComponent },
    { path: 'home', component: HomeComponent },
    { path: 'survey', component: SurveyComponent },
    { path: 'login', component: LoginComponent }
]

@NgModule({
    imports: [RouterModule.forChild(childRoutes)],
    exports: [RouterModule]
})
export class RouterChildModule { }

