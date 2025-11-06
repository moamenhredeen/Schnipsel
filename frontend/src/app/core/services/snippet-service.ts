import { Injectable } from '@angular/core';
import { BehaviorSubject, delay, from, Observable, tap } from 'rxjs';
import {ISnippet} from '$core/types/snippet';

const dummySnippets =   [
      {
        id: 1,
        title: `Filter and Map Array`,
        description: `Advanced array manipulation with filter and map methods to transform data`,
        content: `// Array of user objects
const users = [
  { id: 1, name: 'Alice', age: 25, active: true },
  { id: 2, name: 'Bob', age: 30, active: false },
  { id: 3, name: 'Charlie', age: 28, active: true },
  { id: 4, name: 'Diana', age: 35, active: true }
];

// Filter active users and extract their names
const activeUserNames = users
  .filter(user => user.active)
  .map(user => user.name.toUpperCase());

console.log(activeUserNames);
// Output: ['ALICE', 'CHARLIE', 'DIANA']

// Calculate average age of active users
const avgAge = users
  .filter(user => user.active)
  .reduce((sum, user) => sum + user.age, 0) /
  users.filter(user => user.active).length;

console.log(avgAge);
// Output: 29.33`,
        language: 'javascript',
        tags: [
          {id: 1, name: 'arrays'},
          {id: 2, name: 'functional-programming'},
          {id: 3, name: 'javascript'}
        ],
        author: {
          id: 1,
          name: 'moamen',
          email: 'email@example.com',
          image_url: '/images/avatar.png',
        },
        explanations: [
          {
            id: 'exp-1',
            title: 'Variable Declaration',
            description: 'The "const" keyword declares a constant variable that cannot be reassigned. The users variable holds an array of objects, each representing a user with properties: id, name, age, and active status.',
            startLine: 1,
            endLine: 6,
          },
          {
            id: 'exp-2',
            title: 'Filter Method',
            description: 'The filter() method creates a new array with elements that pass a test. In this case, we filter for users where the "active" property is true. The arrow function (=>) is a concise way to write functions in JavaScript.',
            startLine: 9,
            endLine: 11,
          },
          {
            id: 'exp-3',
            title: 'Map Method',
            description: 'The map() method transforms each element in the array. Here we extract the name property from each filtered user and convert it to uppercase using toUpperCase(). This creates a new array of strings.',
            startLine: 11,
            endLine: 12,
          },
          {
            id: 'exp-4',
            title: 'Method Chaining',
            description: 'Multiple array methods are chained together (filter → map). Each method returns a new array, allowing the next method to operate on it. This is a powerful functional programming pattern in JavaScript.',
            startLine: 9,
            endLine: 12,
          },
          {
            id: 'exp-5',
            title: 'Reduce for Aggregation',
            description: 'The reduce() method accumulates values into a single result. Here it sums all ages of active users. The first parameter (sum) accumulates the total, and user.age is added to it for each element. The 0 is the initial value.',
            startLine: 17,
            endLine: 19,
          },
          {
            id: 'exp-6',
            title: 'Calculate Average',
            description: 'The total age sum (from reduce) is divided by the count of active users to get the average age. We filter again to get the count, which demonstrates the reusability of the filter condition.',
            startLine: 20,
            endLine: 20,
          }
        ]
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
