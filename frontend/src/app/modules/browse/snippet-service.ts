import { Injectable } from '@angular/core';
import { BehaviorSubject, from, Observable, switchMap, tap } from 'rxjs';

type User = {
  id: number;
  email: string;
  name: string;
};

type Snippet = {
  id: number;
  author: User;
  content: string;
};

@Injectable({
  providedIn: 'root',
})
export class SnippetService {
  private snippetsSubject = new BehaviorSubject<Snippet[]>([]);

  get snippets$(): Observable<Snippet[]> {
    return this.snippetsSubject.asObservable();
  }

  getSnippets(): Observable<Snippet[]> {
    return from([
      [
        {
          id: 1,
          content: 'println("hello world")',
          author: {
            id: 1,
            name: 'moamen',
            email: 'email@example.com',
          },
        },
      ],
    ]).pipe(tap((res) => this.snippetsSubject.next(res)));
  }

  getSnippetById(id: number): Observable<Snippet> {
    return from([
      {
        id: 1,
        content: 'println("hello world")',
        author: {
          id: 1,
          name: 'moamen',
          email: 'email@example.com',
        },
      },
    ]);
  }
}
