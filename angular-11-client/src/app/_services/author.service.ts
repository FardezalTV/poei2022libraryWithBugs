import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Book } from '../models/book.model';
import { map } from 'rxjs/operators';
import { plainToClass } from 'class-transformer';
import { Author } from '../models/author.model';
import { error } from 'protractor';

const API_URL = 'http://localhost:8080/api/authors/';

@Injectable({
  providedIn: 'root'
})
export class AuthorService {
  constructor(private http: HttpClient) {
  }

  public getAll(): Observable<Author[]> {
    return this.http.get(API_URL + 'all').pipe(map(response => {
        return plainToClass(Author, response as Author[]);
      }
    ));
  }

  public getOne(id: string): Observable<Author> {
    return this.http.get(API_URL + '/' + id).pipe(map(response => {
        return plainToClass(Author, response as Author);
      }
    ));
  }

  public create(author: Author): Observable<Author> {
    return this.http.post(API_URL, author)
      .pipe(map(response => {
          return plainToClass(Author, response as Author);
        }
      ));

  }

  public delete(author: Author): void {
    this.http.delete(API_URL + author.id).subscribe(() => {
    }, (errorMessage) => alert(JSON.stringify(errorMessage)));
  }


}
