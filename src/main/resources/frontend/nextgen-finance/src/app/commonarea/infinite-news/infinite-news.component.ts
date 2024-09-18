import { Component } from '@angular/core';
import { NewsData } from 'src/app/interfaces/news-data';
import { ArticlesService } from 'src/app/services/articles.service';

@Component({
  selector: 'app-infinite-news',
  templateUrl: './infinite-news.component.html',
  styleUrls: ['./infinite-news.component.scss']
})
export class InfiniteNewsComponent {


  items:string[]=[];
  newsFeed: NewsData[] = [];
  isLoading=false;
  currentPage=0;
  itemsPerPage=10;

  toggleLoading = ()=>this.isLoading=!this.isLoading;

  // it will be called when this component gets initialized.
  loadData= ()=>{
    this.toggleLoading();
    this.paginationService.getChannelNews(this.currentPage).subscribe({
     next:response=>this.newsFeed = response.content,
     error:err=>console.log(err),
     complete:()=>this.toggleLoading()
    })
  }
  
  // this method will be called on scrolling the page
  appendData= ()=>{
   this.toggleLoading();
   this.paginationService.getChannelNews(this.currentPage).subscribe({
    next:response=>this.newsFeed = [...this.newsFeed,...response.content],
    error:err=>console.log(err),
    complete:()=>this.toggleLoading()
   })
 }

  onScroll= ()=>{
   this.currentPage++;
   this.appendData();
  }

  
  constructor(private paginationService:ArticlesService) {
  }

 ngOnInit(): void {
   this.loadData();
 }


}
