import { Component, inject } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Article } from 'src/app/interfaces/article';
import { ArticlesService } from 'src/app/services/articles.service';

@Component({
  selector: 'app-article-details',
  templateUrl: './article-details.component.html',
  styleUrls: ['./article-details.component.scss']
})
export class ArticleDetailsComponent {

  route: ActivatedRoute = inject(ActivatedRoute);

  articleDetail: Article | undefined; 

  constructor(private articleServe:ArticlesService)
  {
   // const articleId = Number(this.route.snapshot.params['id']);
    const artId = this.route.snapshot.params['id'];
   
    this.articleServe.getArticleDetails(artId).subscribe((data)=>{this.articleDetail=data});

    console.log("detailssss "+this.articleDetail?.id);
    //this.housingLocation = this.housingService.getHousingLocationById(housingLocationId);
    //console.log("article Id  ::  "+articleId+"  artIdartId "+ artId);
  }

  

}
