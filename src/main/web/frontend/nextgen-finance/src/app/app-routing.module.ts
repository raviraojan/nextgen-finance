import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ArticlesMainComponent } from './commonarea/articles-main/articles-main.component';
import { ArticleDetailsComponent } from './commonarea/article-details/article-details.component';
import { CreateArticleComponent } from './commonarea/create-article/create-article.component';
import { InsiderTradingComponent } from './commonarea/insider-trading/insider-trading.component';
import { InfiniteNewsComponent } from './commonarea/infinite-news/infinite-news.component';

const routes: Routes = [
  {
    path: 'create',
    component: CreateArticleComponent,
    title: 'Article Create'
  },
  {
    path: 'insider-trading',
    component: InsiderTradingComponent,
    title: 'Insider trading'
  },
  {
    path: 'articles',
    component: ArticlesMainComponent,
    title: 'Investing articles'
  },
  {
    path: '',
    component: InfiniteNewsComponent,
    title: 'News'
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
