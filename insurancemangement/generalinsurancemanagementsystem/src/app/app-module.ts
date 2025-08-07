import { NgModule, provideBrowserGlobalErrorListeners, provideZonelessChangeDetection } from '@angular/core';
import { BrowserModule, provideClientHydration, withEventReplay } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing-module';
import { App } from './app';
import { Firepolicy } from './component/firecomponent/firepolicy/firepolicy';
import { provideHttpClient, withFetch } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Showfirepolicy } from './component/showfirepolicy/showfirepolicy';
import { Updatefirepolicy } from './component/updatefirepolicy/updatefirepolicy';

@NgModule({
  declarations: [
    App,
    Firepolicy,
    Showfirepolicy,
    Updatefirepolicy
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
  ReactiveFormsModule
  ],
  providers: [
    provideBrowserGlobalErrorListeners(),
    provideZonelessChangeDetection(),
    provideClientHydration(withEventReplay()),
    provideHttpClient(
      withFetch())
  ],
  bootstrap: [App]
})
export class AppModule { }
