import { Component, inject } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Article } from 'src/app/interfaces/article';
import { NewsData } from 'src/app/interfaces/news-data';
import { ArticlesService } from 'src/app/services/articles.service';

@Component({
  selector: 'app-news-main',
  templateUrl: './news-main.component.html',
  styleUrls: ['./news-main.component.scss']
})
export class NewsMainComponent {

  

  dataPresent: boolean = false;

  isDataPresent(parentData: any): boolean {


    if (parentData != (null || undefined) && (parentData.pageable != null && parentData.content.length > 0)) {
      this.dataPresent = true;
    }

    return this.dataPresent;

  }

  pageContent: any;

  pageNumber: number = 0;

  articlesList1: Article[] = [];

  newsFeed: NewsData[] = [];

  parentData: any;
  route: ActivatedRoute = inject(ActivatedRoute);

  constructor(private articleServe: ArticlesService) {

    if (this.route.snapshot.params['id'] != (undefined || null)) {
      this.pageNumber = this.route.snapshot.params['id'];
    }
//always subtract 1 from page number, because backend pages starts from 0
    this.articleServe.getChannelNews(this.pageNumber-1).subscribe((data) => {
      this.newsFeed = data.content;
      //this.pageContent = this.newsFeed;
      //this.parentData = data;

    });
  }


}
