import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PhoneComponent } from './phone/phone.component';
import { FormPhoneComponent } from './form-phone/form-phone.component';
import { EditPhoneComponent } from './edit-phone/edit-phone.component';
const routes: Routes = [
  {
    path:'',
    component: PhoneComponent
  },
  {
    path:'form',
    component: FormPhoneComponent
  },
  {
    path:'edit/:id',
    component: EditPhoneComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
