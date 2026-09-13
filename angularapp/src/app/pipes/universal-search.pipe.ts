import { Pipe, PipeTransform } from '@angular/core';
import { Book } from '../models/book.model';

@Pipe({
  name: 'universalSearch'
})
export class UniversalSearchPipe implements PipeTransform {

  transform(books: Book[], searchText: string): Book[] {
    if (!books) return [];
    if (!searchText) return books;

    searchText = searchText.toLowerCase();

    return books.filter(book => {
      return book.title.toLowerCase().includes(searchText) ||
        book.author.toLowerCase().includes(searchText) ||
        book.genre.toLowerCase().includes(searchText);
    });
  }

}
