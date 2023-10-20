import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { DomSanitizer } from '@angular/platform-browser';



declare var $:any;

@Component({
	selector: 'app-create-article',
	templateUrl: './create-article.component.html',
	styleUrls: ['./create-article.component.scss']
  })
export class CreateArticleComponent implements OnInit {
  // Update DaiDH
  public editorDisabled = false;
  
  public form: FormGroup = new FormGroup({
    html: new FormControl('', Validators.required)
  });

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
      ['misc', ['codeview', 'undo', 'redo', 'codeBlock']],
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
      ['insert', ['table', 'picture', 'link', 'video', 'hr']],
      ['customButtons', ['testBtn']],
      ['view', ['fullscreen', 'codeview', 'help']]
    ],
    fontSizes: ['8','9','10','11','12','14','18','24','36','44','56','64','76','84','96'],
    fontNames: ['Arial', 'Times New Roman','Inter', 'Comic Sans MS', 'Courier New', 'Roboto', 'Times', 'MangCau', 'BayBuomHep','BaiSau','BaiHoc','CoDien','BucThu', 'KeChuyen', 'MayChu', 'ThoiDai', 'ThuPhap-Ivy', 'ThuPhap-ThienAn'],
    lineHeights: ['0.0','0.1','0.2', '0.3', '0.4', '0.5', '0.6', '0.8', '1.0', '1.2', '1.4', '1.5', '2.0', '3.0'],
	buttons: {
      testBtn: customButton
    },
    codeviewFilter: true,
    codeviewFilterRegex: /<\/*(?:applet|b(?:ase|gsound|link)|embed|frame(?:set)?|ilayer|l(?:ayer|ink)|meta|object|s(?:cript|tyle)|t(?:itle|extarea)|xml|.*onmouseover)[^>]*?>/gi,
    codeviewIframeFilter: true
  };

  get sanitizedHtml() {
    return this.sanitizer.bypassSecurityTrustHtml(this.form!.get('html')!.value);
  }

  get f() {
    return this.form.controls;
  }

  constructor(private sanitizer: DomSanitizer) {}

  ngOnInit() {}

  public enableEditor() {
    this.editorDisabled = false;
  }

  public disableEditor() {
    this.editorDisabled = true;
  }

  public onBlur() {
    console.log('Blur');
  }

  public onDelete(file:any) {
    console.log('Delete file', file.url);
  }

  public summernoteInit(event:any) {
    console.log(event);
  }
}

function customButton(context:any) {
  const ui = $.summernote.ui;
  const button = ui.button({
    contents: '<i class="note-icon-magic"></i> Hello',
    tooltip: 'Custom button',
    container: '.note-editor',
    className: 'note-btn',
    click: function() {
      context.invoke('editor.insertText', 'Hello from test btn!!!');
    }
  });
  return button.render();
}


