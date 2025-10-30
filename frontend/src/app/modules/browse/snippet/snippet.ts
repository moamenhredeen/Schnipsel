import {Component, input} from '@angular/core';
import {
  MatCard,
  MatCardAvatar,
  MatCardContent,
  MatCardFooter,
  MatCardHeader,
  MatCardSubtitle,
  MatCardTitle
} from '@angular/material/card';
import {MatChip, MatChipSet} from '@angular/material/chips';
import {ISnippet} from '$core/types/snippet';
import {MatIconButton} from '@angular/material/button';
import {MatIcon} from '@angular/material/icon';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-snippet',
  imports: [
    MatCardFooter,
    MatChipSet,
    MatChip,
    MatCard,
    MatCardHeader,
    MatCardTitle,
    MatCardContent,
    MatCardSubtitle,
    MatCardAvatar,
    MatIcon,
    MatIconButton,
    RouterLink,
  ],
  templateUrl: './snippet.html',
  styleUrl: './snippet.scss',
})
export class Snippet {
  value = input.required<ISnippet>()
}
