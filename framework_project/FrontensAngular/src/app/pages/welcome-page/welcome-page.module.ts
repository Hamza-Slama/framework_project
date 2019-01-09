import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {IndexComponent} from '../index/index.component';
import {ItemComponent} from '../item/item.component';
import {RatingComponent} from '../../shared/rating/rating.component';
export const ModernRoutes: Routes = [
  {
    path: '',
    component: IndexComponent,
    data: {
      heading: 'Home'
    }
  } ,
  {
    path: 'detail',
    component: ItemComponent,
    data: {
      heading: 'Details'
    }
  }
];
@NgModule({
  declarations: [IndexComponent  , ItemComponent ,   RatingComponent,],
  imports: [
    CommonModule,
    RouterModule.forChild(ModernRoutes)
  ]
})
export class WelcomePageModule { }
