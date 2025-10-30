import {Component, output} from '@angular/core';
import {MatFormField, MatLabel} from '@angular/material/form-field';
import {MatInput} from '@angular/material/input';
import {MatIconButton} from '@angular/material/button';
import {MatIcon} from '@angular/material/icon';

@Component({
  selector: 'app-create-snippet',
  imports: [
    MatFormField,
    MatLabel,
    MatInput,
    MatIconButton,
    MatIcon,
  ],
  templateUrl: './create-snippet.html',
  styleUrl: './create-snippet.scss',
})
export class CreateSnippet {

  onCancel = output();

  createSnippet() {
  }


  cancel() {
    this.onCancel.emit();
  }
}
