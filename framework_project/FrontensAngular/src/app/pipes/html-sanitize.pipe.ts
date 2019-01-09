import { Pipe, PipeTransform } from '@angular/core';
import {DomSanitizer} from '@angular/platform-browser';

@Pipe({
  name: 'htmlSanitize'
})
export class HtmlSanitizePipe implements PipeTransform {

  constructor(private _sanitizer: DomSanitizer){}

  transform(value: any): any {
    return new DOMParser().parseFromString(value, 'text/html').documentElement.textContent;
  }

}
