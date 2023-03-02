import { Component, OnInit } from '@angular/core';
import { AuthService } from '../_services/auth.service';
import { TokenStorageService } from '../_services/token-storage.service';
import { BookService } from '../_services/book.service';
import { Observable } from 'rxjs';
import { Book } from '../models/book.model';
import { Author } from '../models/author.model';
import { AuthorService } from '../_services/author.service';

@Component({
  selector: 'app-book',
  templateUrl: './board-book.component.html',
  styleUrls: ['./board-book.component.css']
})
export class BookComponent implements OnInit {
  form: any = {
    username: null,
    password: null
  };
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];
  books$: Observable<Book[]> | undefined;
  authors$: Observable<Author[]> | undefined;
  book = new Book();
  author = new Author();
  isAdmin = false;

  constructor(private authService: AuthService, private tokenStorage: TokenStorageService,
              private bookService: BookService, private authorService: AuthorService) {
    debugger;
    this.isAdmin = tokenStorage.getUser()?.roles.includes('ROLE_ADMIN') ?? false;
  }

  ngOnInit(): void {
    this.books$ = this.bookService.getAll();
    this.authors$ = this.authorService.getAll();
  }

  saveBook(): void {
    this.bookService.create(this.book).subscribe(() => this.book = new Book());
  }

  saveAuthor(): void {
    this.authorService.create(this.author).subscribe(() => this.author = new Author());
  }


  deleteAuthor(author: Author): void {
    this.authorService.delete(author);

  }
}
