import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Home } from './component/home/home';
import { Dashboard } from './component/dashboard/dashboard';
import { Registration } from './component/auth/registration/registration';
import { Login } from './component/auth/login/login';
import { Userprofile } from './component/auth/userprofile/userprofile';
import { Showallhealthpolicy } from './component/health/showallhealthpolicy/showallhealthpolicy';
import { Addhealthpolicy } from './component/health/addhealthpolicy/addhealthpolicy';
import { Healthbody } from './component/health/healthbody/healthbody';
import { Healthpolicyshow } from './component/health/healthpolicyshow/healthpolicyshow';
import { Policy } from './component/policy/policy';
import { Creatpolicy } from './component/creatpolicy/creatpolicy';
import { Updatepolicy } from './component/updatepolicy/updatepolicy';
import { Bill } from './component/bill/bill';
import { Creatbill } from './component/creatbill/creatbill';
import { Updatebill } from './component/updatebill/updatebill';
import { Reciept } from './component/reciept/reciept';
import { Creatreciept } from './component/creatreciept/creatreciept';
import { Carpolicy } from './component/car/carpolicy/carpolicy';
import { Creatcarpolicy } from './component/car/creatcarpolicy/creatcarpolicy';
import { Carbill } from './component/car/carbill/carbill';
import { Carbillcreat } from './component/car/carbillcreat/carbillcreat';
import { PrintFireCoverNote } from './component/print-fire-cover-note/print-fire-cover-note';
import { PrintFireMoneyreceipt } from './component/print-fire-moneyreceipt/print-fire-moneyreceipt';
import { Logout } from './component/auth/logout/logout';
import { Carreciept } from './component/car/carreciept/carreciept';
import { Creatcarreciept } from './component/car/creatcarreciept/creatcarreciept';


const routes: Routes = [
  {path:'',component: Home},
  {path:'home',component: Home},
  {path:'dashboard', component:Dashboard},
  {path:'reg', component: Registration},
  {path:'login', component: Login},
  {path:'logout', component: Logout},
  {path:'userProfile', component: Userprofile},
  {path:'updatepolicy', component: Showallhealthpolicy},
  {path:'health/add', component: Addhealthpolicy},
  {path:'health', component: Addhealthpolicy},
  {path:'health/edit/:id', component: Addhealthpolicy},
  {path:'healthbody', component: Healthbody},
  {path:'showhealthpolicy', component:Healthpolicyshow},
  { path: "viewpolicy", component: Policy },
  { path: "createpolicy", component: Creatpolicy },
  { path: "updatepolicy/:id", component: Updatepolicy },
   { path: "viewbill", component: Bill },
  { path: "createbill", component: Creatbill },
  { path: "updatebill/:id", component: Updatebill},
   { path: "viewreciept", component: Reciept},
   { path: "createreceipt", component: Creatreciept},
   { path: "viewcarpolicy", component: Carpolicy},
   { path: "createcarpolicy", component: Creatcarpolicy},
   { path: "viewcarbil", component: Carbill},
   { path: "creatcarbil", component: Carbillcreat},
   { path: "printfirecovernote/:id", component: PrintFireCoverNote},
   { path: "printmoneyreciept/:id", component: PrintFireMoneyreceipt},
   { path: "viewcarreciept", component: Carreciept},
   { path: "createcarreceipt", component: Creatcarreciept},

  

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
