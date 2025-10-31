import {Component, inject, OnInit } from '@angular/core';
import { MatDrawer, MatDrawerContainer, MatDrawerContent } from '@angular/material/sidenav';
import {SnippetService} from '$core/services/snippet-service';
import { Router, RouterOutlet, NavigationEnd, RouterLink, RouterLinkActive } from '@angular/router';
import {MatTableModule} from '@angular/material/table';
import {} from '@angular/material/paginator';
import {MatRippleModule} from '@angular/material/core';
import { filter, map } from 'rxjs';
import { AsyncPipe } from '@angular/common';
import { MatNavList, MatListItem, MatListItemTitle, MatListItemLine, MatListItemAvatar, MatListItemMeta } from '@angular/material/list';
import { FormsModule } from "@angular/forms";
import { MatChipSet, MatChipOption, MatChipTrailingIcon, MatChipAvatar } from "@angular/material/chips";

@Component({
  selector: 'app-saved',
  imports: [
    RouterOutlet,
    MatTableModule,
    MatRippleModule,
    AsyncPipe,
    MatDrawer,
    MatDrawerContainer,
    MatDrawerContent,
    MatNavList,
    MatListItem,
    MatListItemTitle,
    RouterLink,
    RouterLinkActive,
    MatListItemLine,
    FormsModule,
    MatListItemAvatar,
    MatChipSet,
    MatChipOption,
],
  templateUrl: './saved.html',
  styleUrl: './saved.scss',
})
export class Saved implements OnInit {

  private snippetService = inject(SnippetService)
  private router = inject(Router)

  snippets$ = this.snippetService.snippets$
  drawerOpen$ = this.router.events.pipe(
    filter(event => event instanceof NavigationEnd),
    map(() => this.router.url !== '/saved')
  );


  ngOnInit() {
    this.snippetService.getSnippets().subscribe()
  }

}
