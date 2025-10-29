import { Routes } from '@angular/router';

export const routes: Routes = [
    {
        path: 'browse',
        loadComponent: () => import('./modules/browse/browse').then(c => c.Browse)
    },
    {
        path: '**',
        redirectTo: 'browse',
        pathMatch: 'full'
    }
];
