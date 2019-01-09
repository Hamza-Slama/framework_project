import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { IndexComponent } from './pages/index/index.component';
import { ItemComponent } from './pages/item/item.component';
import {HttpClientModule} from '@angular/common/http';
import { HtmlSanitizePipe } from './pipes/html-sanitize.pipe';
import {BarRatingModule} from 'ng2-bar-rating';
import { RatingComponent } from './shared/rating/rating.component';
import { SearchEngineComponent } from './pages/search-engine/search-engine.component';
import { WelcomePageComponent } from './pages/welcome-page/welcome-page.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';


@NgModule({
  declarations: [
    AppComponent,
    HtmlSanitizePipe,
    SearchEngineComponent,
    WelcomePageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule ,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
