import { Component, OnInit } from '@angular/core';
import {ProductServiceService} from '../../services/product-service.service';
import {Product} from '../../Model/Product';
import {Router} from '@angular/router';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css', '../../../assets/css/heroic-features.css', '../../../assets/vendor/bootstrap/css/bootstrap.min.css']
})
export class IndexComponent implements OnInit {
 data: Product[] ;
  constructor(private productService: ProductServiceService , private router: Router) { }

  ngOnInit() {

    this.productService.getAllProduct().subscribe((value: any) => this.data = value , error1 => console.error('error')) ;

  }

  selectProudct( product: Product) {
    this.productService.selectedProduct = product  ;
    this.router.navigate(['p/detail']) ;
  }

}
