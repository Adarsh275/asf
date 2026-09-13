import { Pipe, PipeTransform } from '@angular/core';
import { Book } from '../models/book.model';

@Pipe({
  name: 'filterByGenre'
})
export class FilterByGenrePipe implements PipeTransform {

  transform(books: Book[], genre: string): Book[] {
    if (!books || !genre) {
      return books;
    }
    return books.filter(book => book.genre.toLowerCase().includes(genre.toLowerCase()));
  }
}
