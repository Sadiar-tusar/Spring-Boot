import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Firepolicy } from './component/firecomponent/firepolicy/firepolicy';

const routes: Routes = [
  { path: "", component: Firepolicy },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
