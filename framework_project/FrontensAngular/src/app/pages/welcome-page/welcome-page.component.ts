import { Component, OnInit } from '@angular/core';
import {SearchServiceService} from '../../services/search-service.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-welcome-page',
  templateUrl: './welcome-page.component.html',
  styleUrls: ['./welcome-page.component.css', '../../../assets/css/heroic-features.css', '../../../assets/vendor/bootstrap/css/bootstrap.min.css']
})
export class WelcomePageComponent implements OnInit {

  searchWord = '';
  constructor(private searcht: SearchServiceService , private router: Router) { }

  ngOnInit() {
  }

  makeSearch()
  {
    this.searcht.makeSearch(this.searchWord) ;
     this.router.navigate(['search']) ;
  }
}
