import {Component, inject, input, OnInit, signal} from '@angular/core';
import {SnippetService} from '$core/services/snippet-service';
import {ISnippet} from '$core/types/snippet';
import Prism from 'prismjs';
import {DomSanitizer} from '@angular/platform-browser';

@Component({
  selector: 'app-snippet-viewer',
  imports: [],
  templateUrl: './snippet-viewer.html',
  styleUrl: './snippet-viewer.scss',
})
export class SnippetViewer implements OnInit{
  id = input.required<number>()
  snippet = signal<ISnippet | null>(null)

  private snippetService = inject(SnippetService)
  private sanitizer = inject(DomSanitizer)

  ngOnInit(): void {
    this.snippetService.getSnippetById(this.id()).subscribe({
      next: (res) => this.snippet.set(res),
      error: (err) => console.error(err),
      complete: () => console.log('complete'),
    })
  }

  colorize(code: string){
    const html = Prism.highlight(code, Prism.languages['javascript'], 'javascript');
    return this.sanitizer.bypassSecurityTrustHtml(html)
  }
}
