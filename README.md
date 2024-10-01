Updated on Sept 20 -2024,



Just run mvn install, 
next go to target folder and run, \n
java -jar nextgenfinance-0.0.1-SNAPSHOT.jar

Note: frontend and backend both will build with mvn install command, as frontend build plugin is included.


Old:
TO RUN SPRINGBOOT AND ANGULAR PROJECT:

GOTO MAIN PROJECT

First build frontend :

cd: C:\CODE\SPRING_BOOT\nextgen-finance\src\main\resources\frontend\nextgen-finance

npm i && npm run build

next build backend along with frontend

mvn package spring-boot:repackage -Dmaven.test.skip=true


AND NEXT ONCE BUILD IS COMPLETE GO TO TARGET FOLDER

java -jar nextgenfinance-0.0.1-SNAPSHOT.jar


TO RUN LOCAL JSON SERVER IN 30 SECONDS

GO TO FOLDER(ANY FOLDER AND PLACE db.json) and run

json-server db.json

ng add @ng-bootstrap/ng-bootstrap@14.0.0

create steps:
nextgen-finance

ng new nextgen-finance

NEXT STEPS: ADD BOOTSTRAP TO PROJECT.

https://www.npmjs.com/package/@ng-bootstrap/ng-bootstrap#demo

note when creating angular project select scss(Syntactically Awesome Style Sheets)

BOOTSTRAP WITH ANGULAR,

https://www.freecodecamp.org/news/how-to-add-bootstrap-css-framework-to-an-angular-application/

npm install @ng-bootstrap/ng-bootstrap@14.0.0 // TO INSTALL PARTICULAR ANGULAR COMPATBLE BOOTSTRAP, this will install bootstrap 5.

ng add @ng-bootstrap/ng-bootstrap@14.0.0


UI EDITOR/WYSWYOU GET WHAT YOU SEE IS WHAT YOU GET.

npm install --save ngx-summernote summernote jquery

// this one will install summernote with bootstrap 4 and 5.


summernote-bs4.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></scr


You can also use Summernote with Bootstrap 5 using summernote-bs5.js and summernote-bs5.css.


  "node_modules/summernote/dist/summernote-bs5.min.css
  
  C:\CODE\FINANCIAL_WEBSITE\nextgen-finance\node_modules\summernote\dist\summernote-bs5.min.css
  
  
    "node_modules/jquery/dist/jquery.min.js",
  "node_modules/summernote/dist/summernote-lite.min.js"
  
   "node_modules/bootstrap/dist/js/bootstrap.bundle.min.js"
   
    "node_modules/summernote/dist/summernote-lite.min.js"