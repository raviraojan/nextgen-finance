import { Component, inject } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Article } from 'src/app/interfaces/article';
import { ArticlesService } from 'src/app/services/articles.service';

@Component({
  selector: 'app-articles-main',
  templateUrl: './articles-main.component.html',
  styleUrls: ['./articles-main.component.scss']
})
export class ArticlesMainComponent {

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

  parentData: any;
  route: ActivatedRoute = inject(ActivatedRoute);

  constructor(private articleServe: ArticlesService) {

    if (this.route.snapshot.params['id'] != (undefined || null)) {
      this.pageNumber = this.route.snapshot.params['id'];
    }
//always subtract 1 from page number, because backend pages starts from 0
    this.articleServe.getArticlesData(this.pageNumber-1).subscribe((data) => {
      this.articlesList1 = data.content;
      this.pageContent = this.articlesList1;
      this.parentData = data;

    });
  }

}