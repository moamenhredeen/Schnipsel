import {Routes} from '@angular/router';
import {SidebarLayout} from './layout/sidebar-layout/sidebar-layout';

export const routes: Routes = [
  {
    path: '',
    component: SidebarLayout,
    children: [
      {
        path: 'browse',
        loadComponent: () => import('./modules/browse/browse').then(c => c.Browse)
      },
      {
        path: 'saved',
        loadComponent: () => import('./modules/saved/saved').then(c => c.Saved)
      },
      {
        path: 'profile',
        loadComponent: () => import('./modules/profile/profile').then(c => c.Profile)
      },
      {
        path: 'settings',
        loadComponent: () => import('./modules/settings/settings').then(c => c.Settings)
      },
    ]
  },
  {
    path: 'snippet',
    children: [
      {
        path: 'view',
        loadComponent: () => import('./modules/snippet-viewer/snippet-viewer').then(c => c.SnippetViewer)
      },
      {
        path: 'edit',
        loadComponent: () => import('./modules/snippet-editor/snippet-editor').then(c => c.SnippetEditor)
      },
    ]
  },
  {
    path: '**',
    redirectTo: 'browse',
    pathMatch: 'full'
  }
];
