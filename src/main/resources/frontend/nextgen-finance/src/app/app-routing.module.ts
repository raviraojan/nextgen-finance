import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ArticlesMainComponent } from './commonarea/articles-main/articles-main.component';

const routes: Routes = [

  {
    path: '',
    component: ArticlesMainComponent,
    title: 'Article main'
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
