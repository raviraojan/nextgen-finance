import { Injectable } from '@angular/core';
import { Article } from '../interfaces/article';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ArticlesService {

  url = 'http://localhost:8091/nextgenfin/api/articles';

  constructor(public httpClient: HttpClient) { }

  async getAllArticles(): Promise<Article[]> {
    const data = await fetch(this.url);
    return await data.json() ?? [];
  }

  articlesList: Article[] = [];

  public getData() {
    this.httpClient.get<Article[]>(this.url)
             .subscribe({
              next: (v) => {console.log(v)},
              error: (e) => console.error(e),
              complete: () => console.info('complete') 
          });
  }

  public getArticlesData():Observable<any> {

    let headers = new HttpHeaders().set('Content-Type', "application/json"); // create header object


   return this.httpClient.get<Article[]>(this.url,{headers});
  }

}
