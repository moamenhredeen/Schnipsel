import { Component, inject, OnInit } from '@angular/core';
import { SnippetService } from './snippet-service';
import { AsyncPipe } from '@angular/common';
import {
  MatCard,
  MatCardContent,
  MatCardFooter,
  MatCardHeader,
  MatCardTitle,
} from '@angular/material/card';
import { MatChip, MatChipSet } from '@angular/material/chips';

@Component({
  selector: 'app-browse',
  templateUrl: './browse.html',
  imports: [
    AsyncPipe,
    MatCard,
    MatCardHeader,
    MatCardContent,
    MatCardTitle,
    MatCardFooter,
    MatChip,
    MatChipSet,
  ],
})
export class Browse implements OnInit {
  private snippetService = inject(SnippetService);

  snippets$ = this.snippetService.snippets$;

  ngOnInit(): void {
    this.snippetService.getSnippets().subscribe();
  }
}
