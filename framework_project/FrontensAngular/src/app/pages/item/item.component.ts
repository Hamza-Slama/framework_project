import { Component, OnInit } from '@angular/core';
import {ProductServiceService} from '../../services/product-service.service';
import {Product} from '../../Model/Product';

@Component({
  selector: 'app-item',
  templateUrl: './item.component.html',
  styleUrls: ['./item.component.css', '../../../assets/css/heroic-features.css', '../../../assets/vendor/bootstrap/css/bootstrap.min.css']
})
export class ItemComponent implements OnInit {
 rating: number ;
  product: Product ;
  constructor(private productService: ProductServiceService) { }

  ngOnInit() {
    this.product = this.productService.selectedProduct
  }

  ratingComponentClick(clickObj: any): void {
     this.rating = clickObj.rating;

  }
}
