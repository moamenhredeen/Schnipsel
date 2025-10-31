import {Component, inject, input} from '@angular/core';
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
import Prism from 'prismjs';
import {DomSanitizer} from '@angular/platform-browser';

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
export class Snippet  {

  private sanitizer = inject(DomSanitizer)

  value = input.required<ISnippet>()


  colorize(code: string){
    const html = Prism.highlight(code, Prism.languages['javascript'], 'javascript');
    return this.sanitizer.bypassSecurityTrustHtml(html)
  }
}
