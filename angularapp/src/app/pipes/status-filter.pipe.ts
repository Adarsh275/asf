import { Pipe, PipeTransform } from '@angular/core';
import { BookRentalRequest } from 'src/app/models/book-rental-request.model';

@Pipe({
  name: 'statusFilter'
})
export class StatusFilterPipe implements PipeTransform {
  transform(items: BookRentalRequest[], statusFilter: string): BookRentalRequest[] {
    if (!items || statusFilter === 'All') {
      return items;
    }
    return items.filter(item => item.status === statusFilter);
  }
}
