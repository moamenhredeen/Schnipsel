import {Component, DestroyRef, inject} from '@angular/core';
import {CodeEditor} from '$shared/code-editor/code-editor';
import {FormGroup, FormsModule} from '@angular/forms';

@Component({
  selector: 'app-snippet-editor',
  templateUrl: './snippet-editor.html',
  styleUrl: './snippet-editor.scss',
  imports: [CodeEditor, FormsModule],
})
export class SnippetEditor {
  private readonly destroyRef = inject(DestroyRef);

  // languages = languages;

  options: any = {
    language: 'javascript',
    theme: 'light',
    setup: 'basic',
    disabled: false,
    readonly: false,
    placeholder: 'Type your code here...',
    indentWithTab: false,
    indentUnit: '',
    lineWrapping: false,
    highlightWhitespace: false,
    languages: [
      'javascript',
    ]
  };

  code = `console.log('Hello, world!');`;

  ngOnInit(): void {
  }

  log(e: any) {
    console.log(e);
  }

}
