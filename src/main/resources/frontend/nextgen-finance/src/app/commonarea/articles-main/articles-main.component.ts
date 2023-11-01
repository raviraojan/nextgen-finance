import { Component } from '@angular/core';
import { Article } from 'src/app/interfaces/article';
import { ArticlesService } from 'src/app/services/articles.service';

@Component({
  selector: 'app-articles-main',
  templateUrl: './articles-main.component.html',
  styleUrls: ['./articles-main.component.scss']
})
export class ArticlesMainComponent {

  foobars = ['a', 'b', 'c', 'd', 'e','f','g'];


  articlesList1: Article[] = [];  

  constructor(private articleServe:ArticlesService)
  {

    this.articleServe.getArticlesData().subscribe((data)=>{this.articlesList1=data.content});
  }
  
}