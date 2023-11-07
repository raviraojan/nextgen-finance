import { Component, inject } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-article-details',
  templateUrl: './article-details.component.html',
  styleUrls: ['./article-details.component.scss']
})
export class ArticleDetailsComponent {

  route: ActivatedRoute = inject(ActivatedRoute);

  constructor()
  {
    const articleId = Number(this.route.snapshot.params['id']);
    const artId = this.route.snapshot.params['id'];
    console.log("article Id  ::  "+articleId+"  artIdartId "+ artId);
  }

  

}
