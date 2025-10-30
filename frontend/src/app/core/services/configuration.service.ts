import {DOCUMENT, inject, Injectable} from '@angular/core';

type Config = {
  theme: 'light' | 'dark'
}

@Injectable({
  providedIn: 'root'
})
export class ConfigurationService {
  private config: Config = {
    theme: 'light',
  };
  private document = inject(DOCUMENT)


  load() {
    this.loadConfig()
    this.document.body.style.colorScheme = this.config.theme;
  }

  toggleTheme() {
    this.config.theme = this.config.theme === 'light' ? 'dark' : 'light';
    this.document.body.style.colorScheme = this.config.theme;
    this.saveConfig()
  }


  private saveConfig() {
    localStorage.setItem("config", JSON.stringify(this.config));
  }

  private loadConfig() {
    this.config = JSON.parse(localStorage.getItem("config") ?? 'null') ?? { theme: 'light' };
  }
}
