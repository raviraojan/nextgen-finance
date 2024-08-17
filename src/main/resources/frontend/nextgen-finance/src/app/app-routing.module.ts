import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ArticlesMainComponent } from './commonarea/articles-main/articles-main.component';
import { ArticleDetailsComponent } from './commonarea/article-details/article-details.component';
import { CreateArticleComponent } from './commonarea/create-article/create-article.component';

const routes: Routes = [
  {
    path: 'create',
    component: CreateArticleComponent,
    title: 'Article Create'
  },
  {
    path: '',
    component: ArticlesMainComponent,
    title: 'Article main'
  },
  {
    path: ':id',
    component: ArticlesMainComponent,
    title: 'Article main'
  },
  {
    path: 'details',
    component: ArticleDetailsComponent,
    title: 'Article Details'
  },
  {
    path: 'details/:id',
    component: ArticleDetailsComponent,
    title: 'article details'
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
