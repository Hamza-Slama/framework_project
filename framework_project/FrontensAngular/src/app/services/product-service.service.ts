import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {baseWsUrl, getAllProductUrl} from '../../environments/appConfiguration';
import {Product} from '../Model/Product';

@Injectable({
  providedIn: 'root'
})
export class ProductServiceService {

  selectedProduct: Product ;

  constructor(private htp: HttpClient) {  }

  getAllProduct() {

  return  this.htp.get(baseWsUrl + getAllProductUrl);

  }
}
