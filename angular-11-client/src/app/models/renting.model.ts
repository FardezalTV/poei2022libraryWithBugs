import { Author } from './author.model';

export class Book {
  id: number;
  title: string;
  isbn: string;
  description: string;
  parutionDate: Date;
  author: Author;
}
