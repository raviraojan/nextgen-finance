import { Injectable } from '@angular/core';
import { Article } from '../interfaces/article';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Articleform } from '../interfaces/articleform';

@Injectable({
  providedIn: 'root'
})
export class ArticlesService {

  url = 'http://localhost:8091/nextgenfin/api/articles';

  posturl = 'http://localhost:8091/nextgenfin/api/sub/article';

  urlgetDetails = 'http://localhost:8091/nextgenfin/api/articleDetails';


  constructor(public httpClient: HttpClient) { }

  async getAllArticles(): Promise<Article[]> {
    const data = await fetch(this.url);
    return await data.json() ?? [];
  }

  articlesList: Article[] = [];

  // remove later
  public getData() {
    this.httpClient.get<Article[]>(this.url)
      .subscribe({
        next: (v) => { console.log(v) },
        error: (e) => console.error(e),
        complete: () => console.info('complete')
      });
  }

  public getArticlesData(pageNumber:number): Observable<any> {

    let headers = new HttpHeaders().set('Content-Type', "application/json"); // create header object
    console.log("received from UI"+pageNumber);
    let params = new HttpParams();
    params = params.append('page', pageNumber);

    return this.httpClient.get(this.url, { headers,params });
  }

  public postArticleData(articleform: Articleform): Observable<any> {

    console.log("enteed into post article ");
    let httpHeaders = new HttpHeaders().set('Content-Type', "application/json"); // create header object
    return this.httpClient.post<any>(this.posturl, articleform, {
      headers: httpHeaders
    });
  }

  public getArticleDetails(id: string): Observable<any> {

    let headers = new HttpHeaders().set('Content-Type', "application/json"); // create header object

    let params = new HttpParams();
    params = params.append('id', id);


    return this.httpClient.get<any>(this.urlgetDetails, { headers, params });
  }

}
