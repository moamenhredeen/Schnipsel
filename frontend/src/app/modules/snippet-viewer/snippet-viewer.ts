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
import { ISnippet, IComment } from '$core/types/snippet';
import { MatChip } from '@angular/material/chips';
import { MatIcon } from '@angular/material/icon';
import { MatIconButton } from '@angular/material/button';
import { MatTooltip } from '@angular/material/tooltip';
import { MatProgressSpinner } from '@angular/material/progress-spinner';
import { MatTabsModule } from '@angular/material/tabs';
import {
  EditorView,
  lineNumbers,
  highlightActiveLineGutter,
  highlightSpecialChars,
  Decoration,
  DecorationSet,
  ViewPlugin,
  ViewUpdate,
} from '@codemirror/view';
import { EditorState, StateField, StateEffect, Extension } from '@codemirror/state';
import { highlightSelectionMatches } from '@codemirror/search';
import { defaultHighlightStyle, syntaxHighlighting } from '@codemirror/language';
import { javascript } from '@codemirror/lang-javascript';
import { python } from '@codemirror/lang-python';
import { html } from '@codemirror/lang-html';
import { css } from '@codemirror/lang-css';
import { java } from '@codemirror/lang-java';
import { cpp } from '@codemirror/lang-cpp';

// CodeMirror decoration system for highlighting comment regions
const highlightMark = Decoration.mark({ class: 'cm-highlight-region' });

const addHighlight = StateEffect.define<{ from: number; to: number }>();
const clearHighlight = StateEffect.define();

const highlightField = StateField.define<DecorationSet>({
  create() {
    return Decoration.none;
  },
  update(highlights, tr) {
    highlights = highlights.map(tr.changes);
    for (const effect of tr.effects) {
      if (effect.is(addHighlight)) {
        highlights = highlights.update({
          add: [highlightMark.range(effect.value.from, effect.value.to)],
        });
      } else if (effect.is(clearHighlight)) {
        highlights = Decoration.none;
      }
    }
    return highlights;
  },
  provide: (f) => EditorView.decorations.from(f),
});

@Component({
  selector: 'app-snippet-viewer',
  imports: [
    CommonModule,
    MatChip,
    MatIcon,
    MatIconButton,
    MatTooltip,
    MatProgressSpinner,
    MatTabsModule,
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
  isDrawerOpen = signal<boolean>(true);
  activePanel = signal<'info' | 'explanations' | 'comments'>('info');

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
          this.comments.set(res.comments || []);
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
        extensions: [
          languageSupport,
          syntaxHighlighting(defaultHighlightStyle),
          EditorView.editable.of(false),
          lineNumbers(),
          highlightActiveLineGutter(),
          highlightSpecialChars(),
          highlightSelectionMatches(),
          highlightField,
        ],
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

  onCommentHover(comment: IComment): void {
    if (!this.editor || !comment.codeRegion) return;

    const { startLine, startChar, endLine, endChar } = comment.codeRegion;

    // Convert line/char positions to CodeMirror document positions
    const doc = this.editor.state.doc;

    // Calculate document position for start
    const startLineObj = doc.line(startLine);
    const fromPos = startChar !== undefined ? startLineObj.from + startChar : startLineObj.from;

    // Calculate document position for end
    const endLineObj = doc.line(endLine);
    const toPos = endChar !== undefined ? endLineObj.from + endChar : endLineObj.to;

    // Apply highlight decoration using CodeMirror's state management
    this.editor.dispatch({
      effects: addHighlight.of({ from: fromPos, to: toPos }),
    });

    // Track highlighted lines for other UI purposes
    const highlightedLines = new Set<number>();
    for (let i = startLine; i <= endLine; i++) {
      highlightedLines.add(i);
    }
    this.highlightedLines.set(highlightedLines);
  }

  onCommentLeave(): void {
    if (!this.editor) return;

    // Clear highlight decorations using CodeMirror's state management
    this.editor.dispatch({
      effects: clearHighlight.of(null),
    });

    this.highlightedLines.set(new Set());
  }

  isLineHighlighted(lineNumber: number): boolean {
    return this.highlightedLines().has(lineNumber);
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

  toggleDrawer(): void {
    this.isDrawerOpen.set(!this.isDrawerOpen());
  }

  setActivePanel(panel: 'info' | 'explanations' | 'comments'): void {
    if (this.activePanel() === panel && this.isDrawerOpen()) {
      this.isDrawerOpen.set(false);
    } else {
      this.activePanel.set(panel);
      this.isDrawerOpen.set(true);
    }
  }

  trackByExpId(index: number, item: any): string {
    return item.id;
  }

  padStartIndex(index: number): string {
    return String(index + 1).padStart(2, '0');
  }
}
