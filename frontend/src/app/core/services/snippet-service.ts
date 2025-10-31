import { Injectable } from '@angular/core';
import { BehaviorSubject, delay, from, Observable, tap } from 'rxjs';
import {ISnippet} from '$core/types/snippet';

const dummySnippets =   [
      {
        id: 1,
        title: `Printing`,
        description: `This is how easy to print something in python`,
        content: 'println("hello world")',
        language: 'javascript',
        tags: [
          {id: 1, name: 'problem-solving'}
        ],
        author: {
          id: 1,
          name: 'moamen',
          email: 'email@example.com',
          image_url: '/images/avatar.png',
        },

      },
      {
        id: 2,
        title: `Printing`,
        description: `This is how easy to print something in python`,
        language: 'python',
        content: `
          #! /usr/bin/python

          # this is how easy to print something in python
          print('hello world')
          `,
        tags: [
          {id: 1, name: 'problem-solving'}
        ],
        author: {
          id: 1,
          name: 'moamen',
          email: 'email@example.com',
          image_url: '/images/avatar.png',
        },
      },
      {
        id: 3,
        title: `Printing`,
        description: `This is how easy to print something in python`,
        language: 'html',
        content: `
<mat-card class="p-3" appearance="outlined">
  <mat-card-header>
    <img matCardAvatar [src]="value().author.image_url" alt="author image">
    <mat-card-title>{{value().title}}</mat-card-title>
    <mat-card-subtitle>{{value().description}}</mat-card-subtitle>
  </mat-card-header>
  <mat-card-content>
    <pre>
      <code>
      {{ value().content }}
      </code>
    </pre>
  </mat-card-content>
  <mat-card-footer class="mb-2">
    <mat-chip-set aria-label="Chihuahua traits">
      @for (tag of value().tags; track tag.id) {
        <mat-chip>#{{tag.name}}</mat-chip>
      }
    </mat-chip-set>
  </mat-card-footer>
  <mat-divider></mat-divider>
  <mat-card-actions class="flex justify-between gap-2">
    <button matIconButton class="flex-1">
      <mat-icon>thumb_up</mat-icon>
    </button>
    <button matIconButton class="flex-1">
      <mat-icon>comment</mat-icon>
    </button>
    <button matIconButton class="flex-1">
      <mat-icon>share</mat-icon>
    </button>
  </mat-card-actions>
</mat-card>`,
        tags: [
          {id: 2, name: 'angular'},
          {id: 3, name: 'web'},
        ],
        author: {
          id: 1,
          name: 'moamen',
          email: 'email@example.com',
          image_url: '/images/avatar.png',
        },
      },
      {
        id: 4,
        title: `Printing`,
        description: `This is how easy to print something in python`,
        language: 'python',
        content: `
          #! /usr/bin/python

          # this is how easy to print something in python
          print('hello world')
          `,
        tags: [
          {id: 1, name: 'problem-solving'}
        ],
        author: {
          id: 1,
          name: 'moamen',
          email: 'email@example.com',
          image_url: '/images/avatar.png',
        },
      },
      {
        id: 5,
        title: `Printing`,
        description: `This is how easy to print something in python`,
        language: 'javascript',
        content: `
          // this is how easy to print something in python
          console.log('hello world')

          // this is how easy to print something in python
          console.log('hello world')

          // this is how easy to print something in python
          console.log('hello world')
          `,
        tags: [
          {id: 1, name: 'problem-solving'}
        ],
        author: {
          id: 1,
          name: 'moamen',
          email: 'email@example.com',
          image_url: '/images/avatar.png',
        },
      }
    ]

@Injectable({
  providedIn: 'root',
})
export class SnippetService {
  private snippetsSubject = new BehaviorSubject<ISnippet[]>([]);

  get snippets$(): Observable<ISnippet[]> {
    return this.snippetsSubject.asObservable();
  }

  getSnippets(): Observable<ISnippet[]> {
    return from([dummySnippets]).pipe(
      delay(1000),
      tap((res) => this.snippetsSubject.next(res)));
  }

  getSnippetById(id: number): Observable<ISnippet> {
    const s: ISnippet = dummySnippets.filter(el => el.id === id)[0]
    return from([
      s
    ]).pipe(delay(1000));
  }
}
