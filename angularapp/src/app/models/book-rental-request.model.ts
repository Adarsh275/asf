/**
 * @author Vardhan
 */

import { Book } from "./book.model";
import { User } from "./user.model";

export interface BookRentalRequest {
    rentalId?: number;
    userId?: number;
    user?:User;
    bookId?: number;
    book?:Book
    requestDate?: string;
    returnDate?: string;
    status?: string;     //(Pending, Approved, Returned)
    comments?: string;
}
