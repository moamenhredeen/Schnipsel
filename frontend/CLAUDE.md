# Schnipsel Frontend - Comprehensive Codebase Overview

## 1. Project Structure and Key Directories

```
schnipsel/frontend/
├── src/
│   ├── app/
│   │   ├── core/                    # Core application logic
│   │   │   ├── services/           # Application services
│   │   │   │   ├── snippet-service.ts
│   │   │   │   └── configuration.service.ts
│   │   │   └── types/              # TypeScript type definitions
│   │   │       ├── snippet.ts
│   │   │       ├── user.ts
│   │   │       └── tag.ts
│   │   ├── layout/                 # Layout components
│   │   │   └── sidebar-layout/     # Main layout with sidebar navigation
│   │   ├── modules/                # Feature modules
│   │   │   ├── browse/            # Browse snippets module
│   │   │   ├── saved/             # Saved snippets module
│   │   │   ├── profile/           # User profile module
│   │   │   ├── settings/          # Settings module
│   │   │   ├── snippet-viewer/    # Snippet viewing with code highlighting
│   │   │   └── snippet-editor/    # Snippet editing module
│   │   ├── shared/                 # Shared components
│   │   │   └── code-editor/       # CodeMirror-based code editor
│   │   ├── app.ts                 # Root component
│   │   ├── app.routes.ts          # Route configuration
│   │   └── app.config.ts          # Angular application config
│   ├── main.ts                     # Application entry point
│   ├── index.html                  # HTML template
│   ├── styles.scss                 # Global styles (SCSS)
│   └── tailwind.css                # Tailwind CSS configuration
├── public/                          # Static assets
├── dist/                            # Production build output
├── angular.json                     # Angular CLI configuration
├── tsconfig.json                    # TypeScript base configuration
├── tsconfig.app.json                # TypeScript app configuration
├── tsconfig.spec.json               # TypeScript test configuration
├── package.json                     # NPM dependencies and scripts
└── .postcssrc.json                  # PostCSS configuration
```

## 2. Technology Stack

### Core Framework
- **Angular**: 20.3.0 - Modern Angular with standalone components and signals
- **TypeScript**: 5.9.2 - Strongly typed JavaScript
- **RxJS**: 7.8.0 - Reactive programming library
- **Angular Router**: Component-based routing with lazy loading

### UI and Styling
- **Angular Material**: 20.2.10 - Material Design component library
- **Tailwind CSS**: 4.1.16 - Utility-first CSS framework
- **SCSS**: CSS preprocessor for global styles
- **PostCSS**: 8.5.6 - CSS transformation tool

### Code Editor
- **CodeMirror**: 6.0.2 - Advanced code editor
- **Language Support**: Multiple language support packages
  - @codemirror/lang-javascript
  - @codemirror/lang-python
  - @codemirror/lang-html
  - @codemirror/lang-css
  - @codemirror/lang-java
  - @codemirror/lang-cpp

### Code Highlighting
- **PrismJS**: 1.30.0 - Syntax highlighting library

### Angular CDK
- **@angular/cdk**: 20.2.10 - Component Development Kit for advanced UI patterns

### Build Tools
- **@angular/cli**: 20.3.7 - Angular command line interface
- **@angular/build**: 20.3.7 - Angular build system

### Testing
- **Karma**: 6.4.0 - Test runner
- **Jasmine**: 5.9.0 - Testing framework
- **@types/jasmine**: 5.1.0 - TypeScript definitions for Jasmine
- **karma-chrome-launcher**: 3.2.0 - Chrome browser launcher for Karma
- **karma-jasmine**: 5.1.0 - Jasmine adapter for Karma
- **karma-coverage**: 2.2.0 - Code coverage reporter
- **karma-jasmine-html-reporter**: 2.1.0 - HTML test reporter

### Code Quality
- **Prettier**: Built-in configuration with Angular formatting
- **EditorConfig**: Consistent editor configuration

## 3. Main Application Architecture

### Overall Architecture Pattern
The application uses a **modular feature-based architecture** with Angular standalone components and a clear separation of concerns:

```
App Root (app.ts)
    ↓
Router Outlet
    ↓
SidebarLayout (shared layout)
    ├── Sidebar Navigation
    ├── Toolbar with Theme Toggle
    └── Router Outlet for Feature Modules
        ├── Browse Module
        ├── Saved Module
        │   └── Snippet Details Child Route
        ├── Profile Module
        └── Settings Module
        
Special Routes (outside SidebarLayout):
├── Snippet Viewer (full-screen code display)
└── Snippet Editor (full-screen editing)
```

### Key Components and Their Interactions

#### 1. **Root Component (App)**
- Simple router outlet
- No layout responsibility
- File: `src/app/app.ts`

#### 2. **Sidebar Layout (Main Layout)**
- Location: `src/app/layout/sidebar-layout/`
- Provides the main UI shell with:
  - Top toolbar with theme toggle button
  - Left sidebar with navigation items
  - Router outlet for child routes
- Uses Angular Material components (MatSidenavContainer, MatToolbar, MatNavList)
- Integrates ConfigurationService for theme management
- Tracks active routes to highlight current navigation item

#### 3. **Core Services**

**SnippetService** (`src/app/core/services/snippet-service.ts`)
- Manages snippet data (currently with mock data)
- Provides methods:
  - `getSnippets()`: Fetches all snippets with 1-second delay
  - `getSnippetById(id: number)`: Fetches a single snippet
  - `snippets$`: Observable stream of all snippets
- Uses BehaviorSubject for state management
- Dummy data includes code examples in JavaScript, Python, HTML with comments and explanations

**ConfigurationService** (`src/app/core/services/configuration.service.ts`)
- Manages application-wide settings
- Theme management (light/dark mode)
- Persists configuration to localStorage
- Methods:
  - `load()`: Loads config from localStorage
  - `toggleTheme()`: Switches between light/dark themes
  - `saveConfig()`: Persists current config

#### 4. **Core Types** (`src/app/core/types/`)
- **ISnippet**: Code snippet with metadata
  - id, title, description
  - content (code string), language
  - author (IUser), tags (ITag[])
  - explanations?: line-by-line code explanations
  - comments?: user comments with code region references
  
- **IComment**: User feedback on code
  - id, author, text, createdAt
  - codeRegion?: exact line/character ranges
  
- **ICodeExplanation**: Explanation of code sections
  - id, title, description
  - startLine, endLine (ranges in code)
  
- **IUser**: Author information
  - id, name, email, image_url
  
- **ITag**: Category tags
  - id, name

#### 5. **Shared Components**

**CodeEditor Component** (`src/app/shared/code-editor/code-editor.ts`)
- Standalone component wrapping CodeMirror 6
- Implements ControlValueAccessor for form integration
- Features:
  - Multi-language support (JavaScript, Python, HTML, CSS, Java, C++)
  - Syntax highlighting with configurable themes
  - Line wrapping, line numbers
  - Tab indentation
  - Whitespace highlighting
  - Read-only and disabled modes
  - Dynamic configuration via compartments
- Emits change, focus, and blur events
- Provides `view` property to access EditorView instance

#### 6. **Feature Modules**

**Browse Module** (`src/app/modules/browse/`)
- Lists all available snippets
- Uses Snippet component to display each snippet card
- Integrates with SnippetService for data fetching
- Features:
  - Async pipe for reactive data binding
  - Material Card and List components
  - FAB button for creating new snippets
  - RouterLink for navigation to view/edit routes

**Snippet Component** (browse/snippet/snippet.ts)
- Reusable card component for displaying snippets
- Uses Angular input signals for data
- Features:
  - Code syntax highlighting via PrismJS
  - HTML sanitization for safe rendering
  - Displays title, description, author, tags
  - Action buttons (like, comment, share)
  - Links to view and edit pages

**Saved Module** (`src/app/modules/saved/`)
- Displays user's saved snippets
- Two-pane layout using MatDrawer
- Tracks active route to auto-expand detail view
- Child route: SnippetDetails

**Snippet Viewer Module** (`src/app/modules/snippet-viewer/`)
- Full-screen code display with advanced features
- Sophisticated CodeMirror integration with:
  - Decoration system for line highlighting
  - Line-by-line explanations with hover highlighting
  - Comment region selection and highlighting
  - Multiple tabs (info, explanations, comments)
  - Drawer sidebar for metadata
- Signal-based state management
- Supports copying code, saving snippets (TODOs)
- Multi-language syntax highlighting

**Snippet Editor Module** (`src/app/modules/snippet-editor/`)
- Code editing interface using CodeEditor component
- Allows users to write and modify code snippets
- Currently minimal implementation

**Profile Module** (`src/app/modules/profile/`)
- User profile display and management (placeholder)

**Settings Module** (`src/app/modules/settings/`)
- Application settings configuration (placeholder)

### Route Structure (`app.routes.ts`)

```
/                           → SidebarLayout (parent route)
├── browse                  → Browse (lazy-loaded)
├── saved                   → Saved (lazy-loaded)
│   └── :id                 → SnippetDetails (lazy-loaded)
├── profile                 → Profile (lazy-loaded)
└── settings                → Settings (lazy-loaded)

/snippet                    → Snippet routes (outside sidebar)
├── view/:id                → SnippetViewer (lazy-loaded)
└── edit/:id                → SnippetEditor (lazy-loaded)
```

### Change Detection Strategy
- **Zoneless Change Detection**: Configured in app.config.ts
- **Signal-based Reactivity**: Components use Angular signals for fine-grained state
- **OnPush Strategy**: Applied in CodeEditor and other components for performance

### Form Integration
- ControlValueAccessor in CodeEditor for form binding
- Reactive forms patterns via inputs/signals
- Two-way binding support via ngModel compatibility

## 4. Build and Development Commands

### NPM Scripts (from package.json)

```bash
npm start                  # Start development server (ng serve)
npm run build              # Build for production (ng build)
npm run watch              # Watch mode build for development (ng build --watch)
npm test                   # Run tests via Karma (ng test)
```

### Angular CLI Commands Available
```bash
ng serve                   # Alias: npm start
ng build                   # Alias: npm run build
ng build --watch          # Alias: npm run watch
ng test                    # Alias: npm test
ng extract-i18n           # Extract i18n strings
```

### Build Configuration

**Development Mode**
- Location: Configured in angular.json
- Source maps enabled
- Optimization disabled
- No license extraction

**Production Mode** (default)
- Output hashing enabled
- Bundle size budgets:
  - Initial: max 500KB warning, 1MB error
  - Component styles: max 4KB warning, 8KB error
- Optimization enabled
- License extraction disabled

**Assets and Styles**
- Static assets: public/ directory
- Global styles: src/tailwind.css and src/styles.scss
- Assets configuration in angular.json

## 5. Testing Setup

### Test Framework
- **Test Runner**: Karma 6.4.0
- **Testing Framework**: Jasmine 5.9.0
- **Browser Launcher**: Chrome

### Configuration
- Test configuration: `tsconfig.spec.json`
- Karma configuration managed by @angular/build:karma builder
- Code coverage support via karma-coverage

### Running Tests
```bash
npm test                   # Run tests once
npm test -- --watch       # Run tests in watch mode
npm test -- --code-coverage  # Generate coverage report
```

### Test Assets
- Same public assets and styles as main build
- Configured in angular.json under test.options

## 6. Linting and Code Quality Tools

### Prettier Configuration (in package.json)
```json
{
  "printWidth": 100,
  "singleQuote": true,
  "overrides": [
    {
      "files": "*.html",
      "options": { "parser": "angular" }
    }
  ]
}
```

### EditorConfig (.editorconfig)
- Enforces consistent editor settings
- UTF-8 encoding
- 2-space indentation
- Single quotes for TypeScript
- Specific rules for different file types

### TypeScript Strict Mode
- **strict**: true
- **noImplicitOverride**: true
- **noPropertyAccessFromIndexSignature**: true
- **noImplicitReturns**: true
- **noFallthroughCasesInSwitch**: true
- **strictTemplates**: true (Angular compiler)
- **strictInjectionParameters**: true (Angular compiler)
- **typeCheckHostBindings**: true (Angular compiler)

### No Dedicated Linting Tools
- ESLint not configured
- Focus on TypeScript strict mode for type safety
- Prettier for code formatting
- EditorConfig for consistency

## 7. Architectural Patterns and Conventions

### Component Architecture Patterns

**Standalone Components**
- All components are standalone (no NgModule)
- Explicit imports of required modules
- Cleaner dependency declarations
- Enables better tree-shaking

**Signal-Based State Management**
```typescript
// Example from SnippetViewer
snippet = signal<ISnippet | null>(null);
comments = signal<IComment[]>([]);
isDrawerOpen = signal<boolean>(true);

// Reading signals (must be called as functions)
const snippetValue = this.snippet();
```

**Input Signals** (Angular 17+)
```typescript
// Example from Snippet component
value = input.required<ISnippet>();

// Also supports with default/optional values
someInput = input<string>('default');
```

**Dependency Injection**
```typescript
// Using inject() function
private snippetService = inject(SnippetService);
private router = inject(Router);
```

### Service Patterns

**Singleton Services**
```typescript
@Injectable({ providedIn: 'root' })
export class SnippetService { }
```

**Observable Streams**
- Services return Observables for async data
- Components subscribe in templates using async pipe
- BehaviorSubject for state management

### Routing Patterns

**Lazy Loading**
```typescript
{
  path: 'browse',
  loadComponent: () => import('./modules/browse/browse')
    .then(c => c.Browse)
}
```

**Component Input Binding**
- Routes pass URL parameters as component inputs
- Configured via `withComponentInputBinding()` in app.config

**Route Tracking**
- Use RouterLink with routerLinkActive for navigation indicators
- Router.isActive() for checking active routes

### Directory Structure Conventions

**Core Module** (`src/app/core/`)
- Services: `*-service.ts`
- Types: `types/*.ts`
- Non-UI application logic

**Shared Components** (`src/app/shared/`)
- Reusable UI components
- CodeEditor as a generic control component

**Feature Modules** (`src/app/modules/`)
- One directory per feature
- Encapsulated with dedicated components, templates, styles
- Can have sub-routes

**Naming Conventions**
- Component files: PascalCase with `.ts` extension
- Template files: `component-name.html`
- Style files: `component-name.scss`
- Services: `service-name.service.ts`
- Types: Prefixed with `I` for interfaces (legacy convention)

### Event Emitters and Communication

**Output Events**
```typescript
@Output() change = new EventEmitter<string>();
// Usage in template:
(change)="handleChange($event)"
```

**Input Properties**
```typescript
@Input() value = '';
@Input({ transform: booleanAttribute }) readonly = false;
```

### Template Features

**Control Flow Syntax**
- Uses Angular 17+ control flow (not shown in checked files but supported)
- Built-in @for, @if, @switch directives

**Async Pipe**
```html
<app-snippet *ngFor="let snippet of snippets$ | async"
             [value]="snippet" />
```

**Material Components**
- Consistent use of Material Design
- Components: Card, Chip, Icon, Button, List, Tabs, Drawer
- Material theming support

## 8. Documentation

### Project Configuration Files
- `.editorconfig`: Editor configuration standards
- `tsconfig.json`: Base TypeScript configuration with path aliases
- `angular.json`: Angular project and build configuration
- `.gitignore`: Standard Angular gitignore

### Path Aliases (tsconfig.json)
```json
{
  "$core/*": "./src/app/core/*",
  "$shared/*": "./src/app/shared/*"
}
```
These allow clean imports:
```typescript
import { SnippetService } from '$core/services/snippet-service';
```

### VSCode Integration
- `.vscode/extensions.json`: Recommends Angular Language Service extension
- `.vscode/tasks.json`: NPM script tasks for start and test
- `.vscode/launch.json`: Debug configuration (not shown in detail)

### No Existing Documentation Files
- No README.md found
- No CLAUDE.md found
- No CONTRIBUTING.md found
- Suggests need for documentation to be created

## 9. Development Workflow

### Local Development
```bash
npm install                # Install dependencies
npm start                  # Start dev server at http://localhost:4200
npm run watch              # Build in watch mode for development
npm test                   # Run tests in watch mode
```

### Production Deployment
```bash
npm run build              # Build optimized production bundle
# Output in dist/schnipsel/
```

### Debugging
- VSCode launch.json provides debug configuration
- Chrome DevTools support via Karma
- TypeScript source maps in development mode

### Code Quality Workflow
1. Prettier formatting on save (should be configured in editor)
2. TypeScript compilation checks all files
3. Strict mode enforces type safety
4. Test suite for regression prevention

## 10. Key Features and Capabilities

### Snippet Management
- **Browse**: View all available code snippets
- **View**: Display snippets with syntax highlighting and explanations
- **Edit**: Create and modify code snippets
- **Save**: Save snippets to personal collection
- **Tagging**: Organize snippets with category tags

### Code Display Features
- **Multi-language Support**: JavaScript, Python, HTML, CSS, Java, C++
- **Syntax Highlighting**: CodeMirror for viewing, PrismJS for cards
- **Code Explanations**: Line-level annotations with hover effects
- **Comments**: User comments with code region highlighting
- **Copy to Clipboard**: Easy code copying
- **Line Numbers**: For reference and navigation

### Theming
- **Dark/Light Mode**: Toggle theme switching
- **Persistent Theme**: Saved to localStorage
- **Material Design**: Consistent design system

### State Management
- **Signal-Based**: Fine-grained reactivity
- **Observable Streams**: Async data with RxJS
- **Service State**: Centralized in core services
- **Local Storage**: Configuration persistence

## 11. Recent Development (from git log)

Recent commits show:
- `3f63a3d feat: update viewer` - Latest viewer updates
- `df7a524 feat: use activity bar layout for the snippet viewer` - Layout refactor
- `c548c9e feat: update snippet viewer layout` - Viewer layout improvements
- `0c8a6e5 feat: add snippet viewer component based on code mirror code editor`
- `37a03a2 vscode config` - VSCode configuration setup

## 12. Future Development Considerations

Based on TODO comments in code:
- **Toast Notifications**: UI feedback for copy and save actions
- **Snippet Saving**: Actual backend integration for saving snippets
- **API Integration**: Currently using mock data, needs backend connection
- **User Authentication**: Profile and saved snippets require auth
- **Advanced Features**: Search, filtering, advanced code editing

## Summary

The Schnipsel Frontend is a modern Angular 20 application built with:
- **Clean Architecture**: Modular feature-based organization
- **Modern Patterns**: Standalone components, signals, lazy loading
- **Strong Typing**: TypeScript strict mode throughout
- **UI Excellence**: Material Design and Tailwind CSS
- **Code Editing**: CodeMirror integration with CodeEditor wrapper
- **Reactive**: RxJS observables and Angular signals
- **Scalable**: Clear separation of concerns ready for growth

The application is well-structured for a code snippet management and learning platform with capabilities for viewing, explaining, and eventually editing code snippets across multiple programming languages.
