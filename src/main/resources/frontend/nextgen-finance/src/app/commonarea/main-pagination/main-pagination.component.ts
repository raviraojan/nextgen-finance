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
    if(this.inputFromParent)
    console.log("data from parent::  "+this.inputFromParent);
  }

  isCurrentPage(currentIndex: number,actualPageNum:number): boolean {

    console.log(currentIndex+" currentIndex "+actualPageNum+ " actualPageNum  ");
    
    if(currentIndex==actualPageNum)
    {
      return true;
    }
    return false;
    }

    pageNum:number=0; 
    getPageUrl(currentIndex: number,actualPageNum:number):any {
      this.pageNum = currentIndex+1;
      if(currentIndex==actualPageNum)
      {
       console.log("entredd curre equal to actuall ");
        return null;
      }

      return 'http://localhost:4200/'+this.pageNum;
      }

      getNextPage(currentIndex:number):any {
        this.pageNum = currentIndex+1;

        return 'http://localhost:4200/'+this.pageNum;
        }

        getPreviousPage(currentIndex:number):any {
          this.pageNum = currentIndex-1;
    
          return 'http://localhost:4200/'+this.pageNum;
          }


}
