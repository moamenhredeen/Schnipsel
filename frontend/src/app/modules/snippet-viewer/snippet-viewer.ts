import {
  Component,
  inject,
  input,
  OnInit,
  signal,
  ViewChild,
  ElementRef,
  AfterViewInit,
} from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { SnippetService } from '$core/services/snippet-service';
import { ISnippet } from '$core/types/snippet';
import { MatChip, MatChipSet } from '@angular/material/chips';
import { MatIcon } from '@angular/material/icon';
import { MatButton, MatIconButton } from '@angular/material/button';
import { MatTooltip } from '@angular/material/tooltip';
import { MatProgressSpinner } from '@angular/material/progress-spinner';
import { EditorView, lineNumbers } from '@codemirror/view';
import { EditorState } from '@codemirror/state';
import { javascript } from '@codemirror/lang-javascript';
import { python } from '@codemirror/lang-python';
import { html } from '@codemirror/lang-html';
import { css } from '@codemirror/lang-css';
import { java } from '@codemirror/lang-java';
import { cpp } from '@codemirror/lang-cpp';

export interface IComment {
  author: string;
  text: string;
  createdAt: Date;
}

@Component({
  selector: 'app-snippet-viewer',
  imports: [
    CommonModule,
    MatChip,
    MatChipSet,
    MatIcon,
    MatButton,
    MatIconButton,
    MatTooltip,
    MatProgressSpinner,
  ],
  templateUrl: './snippet-viewer.html',
  styleUrl: './snippet-viewer.scss',
})
export class SnippetViewer implements OnInit, AfterViewInit {
  @ViewChild('codeEditor') codeEditorRef!: ElementRef;

  snippet = signal<ISnippet | null>(null);
  comments = signal<IComment[]>([]);
  error = signal<string | null>(null);
  highlightedLines = signal<Set<number>>(new Set());
  codeLines = signal<string[]>([]);

  private editor: EditorView | null = null;
  private snippetService = inject(SnippetService);
  private route = inject(ActivatedRoute);

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');

    if (!id) {
      this.error.set('Snippet ID not found');
      return;
    }

    this.snippetService.getSnippetById(+id).subscribe({
      next: (res) => {
        if (res) {
          this.snippet.set(res);
          this.codeLines.set(res.content.split('\n'));
          this.loadComments();
          // Initialize editor after snippet is loaded and DOM is ready
          setTimeout(() => {
            this.initializeEditor();
          }, 100);
        } else {
          this.error.set('Snippet not found');
        }
      },
      error: (err) => {
        console.error('Error loading snippet:', err);
        this.error.set('Error loading snippet');
      },
      complete: () => console.log('Snippet loaded successfully'),
    });
  }

  ngAfterViewInit(): void {
    // Editor will be initialized when snippet is loaded in ngOnInit
  }

  private initializeEditor(): void {
    try {
      if (!this.snippet()) {
        console.warn('Snippet not loaded yet');
        return;
      }

      // Try to get the element from ViewChild ref or query DOM directly
      let editorContainer: HTMLElement | null = null;
      if (this.codeEditorRef) {
        editorContainer = this.codeEditorRef.nativeElement;
      } else {
        editorContainer = document.querySelector('.code-content');
      }

      if (!editorContainer) {
        console.warn('Code editor container not found in DOM');
        return;
      }

      // Clear any existing editor
      if (this.editor) {
        this.editor.destroy();
      }

      // Clear container
      editorContainer.innerHTML = '';

      const languageSupport = this.getLanguageSupport(this.snippet()!.language);

      const state = EditorState.create({
        doc: this.snippet()!.content,
        extensions: [languageSupport, EditorView.editable.of(false), lineNumbers()],
      });

      this.editor = new EditorView({
        state,
        parent: editorContainer,
      });

      console.log('CodeMirror editor initialized successfully');
    } catch (error) {
      console.error('Error initializing CodeMirror editor:', error);
    }
  }

  private getLanguageSupport(language: string) {
    switch (language.toLowerCase()) {
      case 'javascript':
      case 'js':
        return javascript();
      case 'python':
        return python();
      case 'html':
        return html();
      case 'css':
        return css();
      case 'java':
        return java();
      case 'cpp':
      case 'c++':
        return cpp();
      default:
        return javascript();
    }
  }

  onExplanationHover(explanation: any): void {
    if (!this.editor) return;

    const lines = new Set<number>();
    if (explanation.startLine && explanation.endLine) {
      for (let i = explanation.startLine; i <= explanation.endLine; i++) {
        lines.add(i);
      }
    } else if (explanation.lineNumber) {
      lines.add(explanation.lineNumber);
    }

    this.highlightedLines.set(lines);

    // Add CSS class to DOM elements for highlighted lines
    const lineNumbers = this.codeEditorRef.nativeElement.querySelectorAll('.cm-line');
    lineNumbers.forEach((lineEl: HTMLElement, index: number) => {
      const lineNum = index + 1;
      if (lines.has(lineNum)) {
        lineEl.classList.add('cm-highlight-line');
      }
    });
  }

  onExplanationLeave(): void {
    if (!this.codeEditorRef) return;

    // Remove CSS class from all line elements
    const lineNumbers = this.codeEditorRef.nativeElement.querySelectorAll('.cm-line');
    lineNumbers.forEach((lineEl: HTMLElement) => {
      lineEl.classList.remove('cm-highlight-line');
    });

    this.highlightedLines.set(new Set());
  }

  isLineHighlighted(lineNumber: number): boolean {
    return this.highlightedLines().has(lineNumber);
  }

  private loadComments(): void {
    // TODO: Implement actual comment loading from service
    // For now, using mock data
    this.comments.set([
      // Uncomment when you have real comment data
      // {
      //   author: 'John Doe',
      //   text: 'Great snippet! Very helpful.',
      //   createdAt: new Date(),
      // },
      // {
      //   author: 'Jane Smith',
      //   text: 'Could you explain the third function?',
      //   createdAt: new Date(Date.now() - 3600000),
      // },
    ]);
  }

  copyCode(): void {
    const code = this.snippet()?.content ?? '';
    navigator.clipboard.writeText(code).then(() => {
      // TODO: Show toast notification
      console.log('Code copied to clipboard');
    });
  }

  saveSnippet(): void {
    // TODO: Implement save functionality
    console.log('Saving snippet:', this.snippet()?.id);
  }

  shareSnippet(): void {
    // TODO: Implement share functionality
    console.log('Sharing snippet:', this.snippet()?.id);
  }

  trackByExpId(index: number, item: any): string {
    return item.id;
  }

  padStartIndex(index: number): string {
    return String(index + 1).padStart(2, '0');
  }
}
