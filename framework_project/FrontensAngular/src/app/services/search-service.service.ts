import { Injectable } from '@angular/core';
import {baseWsUrl, getAllProductUrl, makeSearchUrl} from '../../environments/appConfiguration';
import {HttpClient} from '@angular/common/http';
import {Product} from '../Model/Product';
import {DatePipe} from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class SearchServiceService {

  data: Product[];
  searchWord: string ;
  timeRequest: number ;
  constructor(private htp: HttpClient) { }

  makeSearch(word: string) {
      this.searchWord = word ;
 const timz = new Date().getTime()  /1000 ;
    return  this.htp.get(baseWsUrl + makeSearchUrl + '?word=' + word).subscribe(
      (data: any) => {this.data = data ; this.timeRequest =  new Date().getTime()/1000 - timz }
    );

  }
}
