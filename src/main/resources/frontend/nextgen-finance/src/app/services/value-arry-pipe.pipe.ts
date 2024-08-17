import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'valueArryPipe'
})
export class ValueArryPipePipe implements PipeTransform {

  // transform(value: unknown, ...args: unknown[]): unknown {
  //   return null;
  // }

  transform(intialVal :number):[] {
    console.log(" from valueArryPipe pipe "+intialVal);
    return [].constructor(intialVal);
  }

}
