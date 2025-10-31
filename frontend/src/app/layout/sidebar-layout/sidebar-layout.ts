import { Component, inject, OnInit, signal} from '@angular/core';
import { Router, RouterLink, RouterOutlet} from '@angular/router';
import {MatToolbar} from '@angular/material/toolbar';
import {MatIconButton} from '@angular/material/button';
import {
  MatSidenav,
  MatSidenavContainer, MatSidenavContent
} from '@angular/material/sidenav';
import {MatIcon} from '@angular/material/icon';
import {MatListItem, MatListItemIcon, MatNavList} from '@angular/material/list';
import {ConfigurationService} from '$core/services/configuration.service';

@Component({
  selector: 'app-sidebar',
  imports: [
    RouterOutlet,
    MatToolbar,
    MatIcon,
    MatIconButton,
    MatNavList,
    MatListItem,
    MatListItemIcon,
    RouterLink,
    MatSidenavContainer,
    MatSidenav,
    MatSidenavContent,
  ],
  templateUrl: './sidebar-layout.html',
  styleUrl: './sidebar-layout.scss',
})
export class SidebarLayout {

  private router = inject(Router);
  private configService = inject(ConfigurationService)

  theme = signal<'light_mode' | 'dark_mode'>('light_mode');
  navItems: { label: string, url: string, icon: string }[] = [
    {label: 'Browse', url: 'browse', icon: 'home'},
    {label: 'Saved', url: 'saved', icon: 'bookmark'},
    {label: 'Profile', url: 'profile', icon: 'person'},
    {label: 'Settings', url: 'settings', icon: 'settings'},
  ];
  sidebarOpen = signal(true);

  ngOnInit() {
    this.configService.load()
  }

  isActive(url: string): boolean {
    return this.router.isActive(url, {
      paths: "subset",
      matrixParams: 'ignored',
      queryParams: 'ignored',
      fragment: 'ignored'
    });
  }

  toggleTheme(){
    this.configService.toggleTheme();
  }

}
