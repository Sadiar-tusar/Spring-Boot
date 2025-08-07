import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Firepolicy } from './component/firecomponent/firepolicy/firepolicy';
import { Showfirepolicy } from './component/showfirepolicy/showfirepolicy';
import { Updatefirepolicy } from './component/updatefirepolicy/updatefirepolicy';

const routes: Routes = [
  { path: "", component: Firepolicy },
  { path: "viewfirepolicy", component: Showfirepolicy },
  { path: "updatepolicy/:id", component: Updatefirepolicy },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
