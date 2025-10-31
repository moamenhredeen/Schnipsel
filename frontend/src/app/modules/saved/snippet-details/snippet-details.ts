import { Component, computed, effect, inject, input, OnInit, resource } from '@angular/core';
import { Saved } from '../saved';
import { MatIconButton } from '@angular/material/button';
import { MatIcon } from "@angular/material/icon";
import { RouterLink } from "@angular/router";
import { SnippetService } from '$core/services/snippet-service';
import { httpResource } from '@angular/common/http';
import { firstValueFrom } from 'rxjs';
import {rxResource} from '@angular/core/rxjs-interop';

@Component({
  selector: 'app-snippet-details',
  imports: [MatIconButton, MatIcon, RouterLink],
  templateUrl: './snippet-details.html',
  styleUrl: './snippet-details.scss',
})
export class SnippetDetails  {

  private snippetService = inject(SnippetService)

  id = input.required<string>()
  snippet = rxResource({
    params: () => ({
      id: this.id()
    }),
    stream: ({params}) => {
      debugger
      return this.snippetService.getSnippetById(+params.id)
    },
  })

}
