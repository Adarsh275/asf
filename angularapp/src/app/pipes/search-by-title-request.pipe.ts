import { Pipe, PipeTransform } from '@angular/core';
import { BookRentalRequest } from '../models/book-rental-request.model';

@Pipe({
  name: 'searchByTitleRequest'
})
export class SearchByTitleRequestPipe implements PipeTransform {

  transform(bookRentalRequests: BookRentalRequest[], searchTerm: string): BookRentalRequest[] {
    if (!bookRentalRequests || !searchTerm) {
      return bookRentalRequests;
    }

    // convert the search term to lowercase to ignore case sensitivity
    searchTerm = searchTerm.toLowerCase();
    return bookRentalRequests.filter(data =>
      data.book.title.toLowerCase().includes(searchTerm)
    );
  }

}
