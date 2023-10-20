import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { CreateArticleComponent } from './commonarea/create-article/create-article.component';
import { ArticleDetailsComponent } from './commonarea/article-details/article-details.component';
import { ArticlesMainComponent } from './commonarea/articles-main/articles-main.component';
import { NgxSummernoteModule } from 'ngx-summernote';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { SafeHtmlPipe } from './safe-html.pipe';
import { NgxPopper } from 'angular-popper';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    CreateArticleComponent,
    ArticleDetailsComponent,
    ArticlesMainComponent,
    SafeHtmlPipe

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    NgxSummernoteModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgxPopper
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
