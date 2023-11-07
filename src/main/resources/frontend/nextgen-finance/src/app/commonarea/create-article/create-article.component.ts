import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators,ReactiveFormsModule } from '@angular/forms';
import { DomSanitizer } from '@angular/platform-browser';
import { Article } from 'src/app/interfaces/article';
import { Articleform } from 'src/app/interfaces/articleform';
import { ArticlesService } from 'src/app/services/articles.service';



declare var $:any;

@Component({
	selector: 'app-create-article',
	templateUrl: './create-article.component.html',
	styleUrls: ['./create-article.component.scss']
  })
export class CreateArticleComponent implements OnInit {

  article:Articleform;

  articleServe:ArticlesService;

handleSubmit() {

  //mark as touched return 
  if (this.articleForm.invalid) {
    console.log("entered into invalid");
    for (const control of Object.keys(this.articleForm.controls)) {
      this.articleForm.controls[control].markAsTouched();
    }
    return;
  }

  this.article = this.articleForm.value;

  this.articleServe.postArticleData(this.article).subscribe(data =>{console.log("data"+data)});
  console.info('Name:', this.article.articletitle);
  console.info('Nickname:', this.article.html);

  console.log(this.articleForm.value);
//throw new Error('Method not implemented.');

// handle submit end
this.articleForm.reset();
console.log(" article after reset :: "+this.articleForm.value);
}
  // Update DaiDH
  public editorDisabled = false;
  
  public articleForm: FormGroup = new FormGroup({
    articletitle : new FormControl('', [
      Validators.required,
      Validators.minLength(10),
      Validators.maxLength(250),
    ]),
    html: new FormControl('', [Validators.required])
  });

  get articletitle() {
    return this.articleForm.get('articletitle');
  }

  get html() {
    return this.articleForm.get('html');
  }

  public config: any = {
    airMode: false,
    tabDisable: true,
    popover: {
      table: [
        ['add', ['addRowDown', 'addRowUp', 'addColLeft', 'addColRight']],
        ['delete', ['deleteRow', 'deleteCol', 'deleteTable']]
      ],
      image: [
        ['image', ['resizeFull', 'resizeHalf', 'resizeQuarter', 'resizeNone']],
        ['float', ['floatLeft', 'floatRight', 'floatNone']],
        ['remove', ['removeMedia']]
      ],
      link: [['link', ['linkDialogShow', 'unlink']]],
      air: [
        [
          'font',
          [
            'bold',
            'italic',
            'underline',
            'strikethrough',
            'superscript',
            'subscript',
            'clear'
          ]
        ]
      ]
    },
    height: '200px',
    uploadImagePath: '/api/upload',
    toolbar: [
      ['misc', ['codeview', 'undo', 'redo']],
      [
        'font',
        [
          'bold',
          'italic',
          'underline',
          'strikethrough',
          'superscript',
          'subscript',
          'clear'
        ]
      ],
      ['fontsize', ['fontname', 'fontsize', 'color']],
      ['para', ['style0', 'ul', 'ol', 'paragraph', 'height']],
      ['insert', ['table', 'picture','video', 'hr']],
      ['customButtons', ['testBtn']],
      ['view', ['codeview', 'help']]
    ],
    fontSizes: ['8','9','10','11','12','14','18','24','36','44','56','64','76','84','96'],
    fontNames: ['Arial', 'Times New Roman','Inter', 'Comic Sans MS', 'Courier New', 'Roboto', 'Times', 'MangCau', 'BayBuomHep','BaiSau','BaiHoc','CoDien','BucThu', 'KeChuyen', 'MayChu', 'ThoiDai', 'ThuPhap-Ivy', 'ThuPhap-ThienAn'],
    lineHeights: ['0.0','0.1','0.2', '0.3', '0.4', '0.5', '0.6', '0.8', '1.0', '1.2', '1.4', '1.5', '2.0', '3.0'],
    codeviewFilter: true,
    codeviewFilterRegex: /<\/*(?:applet|b(?:ase|gsound|link)|embed|frame(?:set)?|ilayer|l(?:ayer|ink)|meta|object|s(?:cript|tyle)|t(?:itle|extarea)|xml|.*onmouseover)[^>]*?>/gi,
    codeviewIframeFilter: true
  };

  get sanitizedHtml() {
    return this.sanitizer.bypassSecurityTrustHtml(this.articleForm!.get('html')!.value);
  }

  get f() {
    return this.articleForm.controls;
  }

  constructor(private sanitizer: DomSanitizer,articleServe:ArticlesService) {
    this.article={} as Articleform;
    this.articleServe =articleServe;
  }

  ngOnInit() {}

  public enableEditor() {
    this.editorDisabled = false;
  }

  public disableEditor() {
    this.editorDisabled = true;
  }

  public onBlur() {
   // console.log('Blur');
  }

  public onDelete(file:any) {
    console.log('Delete file', file.url);
  }

  public summernoteInit(event:any) {
    console.log(event);
  }
}




