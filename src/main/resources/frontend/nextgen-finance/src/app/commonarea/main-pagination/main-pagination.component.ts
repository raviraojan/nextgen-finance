import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';

@Component({
  selector: 'app-main-pagination',
  templateUrl: './main-pagination.component.html',
  styleUrls: ['./main-pagination.component.scss']
})
export class MainPaginationComponent implements OnChanges {

  parentdata:any;

  @Input() inputFromParent: any;


  ngOnChanges(changes: SimpleChanges): void {

    this.parentdata = this.inputFromParent;
    console.log("data from parent::  "+this.inputFromParent);
  }



}
