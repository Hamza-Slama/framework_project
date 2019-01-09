import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {SearchServiceService} from '../../services/search-service.service';
import {Product} from '../../Model/Product';
import {ProductServiceService} from '../../services/product-service.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-search-engine',
  templateUrl: './search-engine.component.html',
  styleUrls: ['./search-engine.component.css'] ,
  encapsulation: ViewEncapsulation.Native
})
export class SearchEngineComponent implements OnInit {

  constructor(public searcht: SearchServiceService , private productService: ProductServiceService
             ) { }

  ngOnInit() {
  }
  selectProudct( product: Product) {
    this.productService.selectedProduct = product  ;
  }
}
