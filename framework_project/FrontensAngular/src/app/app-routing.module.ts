import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {IndexComponent} from './pages/index/index.component';
import {ItemComponent} from './pages/item/item.component';
import {SearchEngineComponent} from './pages/search-engine/search-engine.component';
import {WelcomePageComponent} from './pages/welcome-page/welcome-page.component';

const routes: Routes = [
  { path: '', component: WelcomePageComponent , children: [
      {
        path: 'p',
        loadChildren: './pages/welcome-page/welcome-page.module#WelcomePageModule'
      },
      {path: '' , redirectTo: 'p' , pathMatch: 'full' } ,
     ]
      } ,
  {
    path: 'search' , component: SearchEngineComponent
  }
];
@NgModule({
  exports: [ RouterModule ] ,
  imports: [ RouterModule.forRoot(routes) ]
})
export class AppRoutingModule {
}
