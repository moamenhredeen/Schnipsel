import {Component, inject, OnInit, signal} from '@angular/core';
import {SnippetService} from '$core/services/snippet-service';
import {AsyncPipe} from '@angular/common';
import {Snippet} from './snippet/snippet';
import {MatIcon} from '@angular/material/icon';
import {MatFabButton} from '@angular/material/button';
import {MatDialog} from '@angular/material/dialog';
import {CreateSnippet} from './create-snippet/create-snippet';
import {MatDrawer, MatDrawerContainer, MatDrawerContent} from '@angular/material/sidenav';

@Component({
  selector: 'app-browse',
  templateUrl: './browse.html',
  styleUrl: './browse.scss',
  imports: [
    AsyncPipe,
    Snippet,
    MatIcon,
    MatFabButton,
    MatDrawerContainer,
    MatDrawer,
    MatDrawerContent,
    CreateSnippet,
  ],
})
export class Browse implements OnInit {
  private snippetService = inject(SnippetService);

  snippets$ = this.snippetService.snippets$;
  sidebarOpened = signal(false)

  ngOnInit(): void {
    this.snippetService.getSnippets().subscribe();
  }

  openSidebar() {
    this.sidebarOpened.set(true);
  }
}
