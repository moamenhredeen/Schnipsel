import {Component, inject, OnInit, signal} from '@angular/core';
import {SnippetService} from '$core/services/snippet-service';
import {AsyncPipe} from '@angular/common';
import {Snippet} from './snippet/snippet';
import {MatIcon} from '@angular/material/icon';
import {MatFabButton} from '@angular/material/button';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-browse',
  templateUrl: './browse.html',
  styleUrl: './browse.scss',
  imports: [
    AsyncPipe,
    Snippet,
    MatIcon,
    MatFabButton,
    RouterLink,
  ],
})
export class Browse implements OnInit {
  private snippetService = inject(SnippetService);

  snippets$ = this.snippetService.snippets$;

  ngOnInit(): void {
    this.snippetService.getSnippets().subscribe();
  }
}
