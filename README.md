# Kakera - Application Domain Description

maybe rename the project to Schnipsel

## What is Kakera?

Kakera is a web-based platform where developers can share code snippets with descriptions and context. Think of it as a library where programmers can save, organize, and discover useful pieces of code that solve specific problems or demonstrate particular techniques.

---

## Core Concepts

### 1. User
A person who uses the Kakera platform. Users can be developers, students, or anyone who works with code.

**What a User has:**
- A unique username
- An email address
- A password (stored securely)
- A display name (optional, like "John Smith")
- A bio/description (optional, to describe themselves)
- A profile picture (optional)
- Account creation date
- Account status (active or inactive)

**What a User can do:**
- Register for an account
- Log in and log out
- Create new code snippets
- Edit/update their own snippets (creates new version in history)
- Delete their own snippets
- View public snippets from other users
- View the complete history of a snippet's changes
- Compare different versions of a snippet
- Restore a snippet to a previous version
- Search for snippets
- Favorite/star snippets they like
- Comment on snippets
- Update their profile information
- View other users' profiles

---

### 2. Snippet
A piece of code that a user wants to save and potentially share with others.

**What a Snippet has:**
- A title (like "Quick Sort in Python" or "Database Connection Helper")
- The actual code
- A description explaining what the code does and how to use it
- The programming language (Java, Python, JavaScript, etc.)
- Visibility setting (public or private)
- The author (which user created it)
- Creation date
- Last modified date
- Number of times it's been viewed
- Number of favorites/stars it has received

**Rules about Snippets:**
- Every snippet belongs to exactly one user
- A snippet can be public (everyone can see it) or private (only the owner can see it)
- Only the owner can edit or delete their snippet
- When a snippet is edited, the previous version is automatically saved to the history
- The snippet always shows the most recent version by default
- Anyone can view public snippets and their complete version history
- The code is displayed with syntax highlighting to make it easier to read
- Version history is never deleted, even when the snippet is updated

---

### 3. Tag
A keyword or label that describes what a snippet is about, making it easier to categorize and find snippets.

**What a Tag has:**
- A name (like "algorithm", "database", "react", "tutorial")
- A URL-friendly version of the name (called a slug)
- A count of how many snippets use this tag
- Creation date

**How Tags work:**
- Users can add multiple tags to their snippets
- The same tag can be used on many different snippets
- Tags help users find related snippets
- Popular tags show which topics are common in the community

**Examples of tags:**
- "sorting-algorithm"
- "api-integration"
- "testing"
- "beginner-friendly"

---

### 4. Favorite
A way for users to bookmark or "like" snippets they find useful or interesting.

**How Favorites work:**
- A user can favorite any public snippet
- A user can unfavorite a snippet they previously favorited
- Each snippet shows how many favorites it has received
- Users can view a list of all their favorited snippets
- This helps users build a personal collection of useful code

**Rules:**
- A user can only favorite a snippet once
- The date when the favorite was created is recorded

---

### 5. Comment
A text message that users can leave on snippets to ask questions, provide feedback, or suggest improvements.

**What a Comment has:**
- The text content
- The author (which user wrote it)
- The snippet it belongs to
- Creation date
- Last modified date (if edited)

**Rules about Comments:**
- Comments belong to a specific snippet
- Only the comment author can edit or delete their own comment
- Comments are visible to anyone who can see the snippet
- Comments appear in chronological order

---

### 6. Snippet Version
A saved history of changes made to a snippet over time. Every time a snippet is edited, the system automatically creates a version record.

**What a Version has:**
- The version number (1, 2, 3, etc.)
- The snippet it belongs to
- The title, description, and code at that point in time
- Who made the change
- When the change was made

**How Versioning works:**
- When a snippet is first created, it becomes version 1
- Each time a snippet is edited, a new version is automatically created
- The version number increases by 1 with each edit (2, 3, 4, etc.)
- The snippet always displays the latest version by default
- Users can view the complete history of all versions
- Users can click on any version to see what the snippet looked like at that time
- Users can compare two versions side-by-side to see what changed
- Users can restore an older version (which creates a new version with the old content)
- Version history is preserved even if the current snippet is later modified

**Why Versioning is Important:**
- Users can track how a snippet evolved over time
- Users can undo mistakes by reverting to a previous version
- Users can see who made changes and when
- Users can learn from the evolution of the code
- It provides an audit trail of all modifications

---

## Relationships Between Concepts

### User and Snippet
- **One user can create many snippets** (one-to-many relationship)
- Every snippet has exactly one owner
- When viewing a snippet, you can see who created it
- When viewing a user's profile, you can see all their public snippets

### User and Favorite
- **One user can favorite many snippets** (many-to-many relationship)
- **One snippet can be favorited by many users**
- This creates a collection of bookmarked snippets for each user
- This creates a popularity metric for each snippet

### Snippet and Tag
- **One snippet can have many tags** (many-to-many relationship)
- **One tag can be used on many snippets**
- Example: A snippet titled "React Login Form" might have tags: "react", "javascript", "authentication", "frontend"

### Snippet and Comment
- **One snippet can have many comments** (one-to-many relationship)
- Each comment belongs to exactly one snippet
- Comments create a discussion thread on each snippet

### User and Comment
- **One user can write many comments** (one-to-many relationship)
- Each comment has exactly one author
- This allows users to participate in discussions across many snippets

### Snippet and Snippet Version
- **One snippet can have many versions** (one-to-many relationship)
- Each version is a snapshot of the snippet at a particular time
- The current snippet always represents the latest version

---

## Main User Journeys

### Journey 1: New User Signs Up
1. User visits the Kakera website
2. User clicks "Sign Up"
3. User enters username, email, and password
4. System validates the information (checks if username/email are unique)
5. System creates the user account with a secure password
6. User is logged in and redirected to their dashboard

### Journey 2: Creating a Snippet
1. User logs into their account
2. User clicks "Create Snippet"
3. User enters:
    - A title for the code
    - The actual code
    - A description explaining the code
    - Selects the programming language
    - Adds relevant tags
    - Chooses if it should be public or private
4. User clicks "Save"
5. System saves the snippet to the database
6. User is redirected to view their new snippet

### Journey 3: Discovering Snippets
1. User visits the browse/explore page
2. User sees a list of recent public snippets
3. User can:
    - Filter by programming language
    - Filter by tags
    - Search by keywords
    - Sort by date or popularity
4. User clicks on a snippet that interests them
5. User sees the full code with syntax highlighting, description, and comments
6. User can favorite the snippet or leave a comment

### Journey 4: Searching for Code
1. User enters keywords in the search bar (e.g., "binary search tree")
2. System searches through:
    - Snippet titles
    - Snippet descriptions
    - Tags
3. System returns matching snippets
4. User can refine the search by adding filters
5. User finds the snippet they need

### Journey 5: Building a Collection
1. User browses snippets
2. When user finds useful code, they click the "Favorite" button
3. The snippet is added to their favorites collection
4. Later, user visits "My Favorites" page
5. User sees all their favorited snippets organized in one place
6. User can easily access code they've saved for later

### Journey 6: Editing a Snippet and Viewing History
1. User views one of their own snippets
2. User clicks "Edit" button
3. User makes changes to the:
    - Title
    - Code
    - Description
    - Tags
4. User clicks "Save Changes"
5. System automatically:
    - Saves the old version to the history (with version number, timestamp, and user)
    - Updates the snippet with new content
    - Increments the version number
6. User can click "View History" to see all previous versions
7. User can:
    - Click on any version to view it
    - Compare two versions side-by-side
    - See what changed between versions (highlighted differences)
    - Click "Restore This Version" to bring back an old version (creates a new version)
8. The snippet page shows: "Version 5 (latest) - Last edited 2 hours ago by username"

### Journey 7: Comparing Snippet Versions
1. User opens a snippet they're interested in
2. User clicks "View History" or "Version History"
3. User sees a list of all versions with:
    - Version number
    - Date and time of change
    - Who made the change
    - Brief summary of what changed (optional)
4. User selects two versions to compare
5. System shows a side-by-side or inline diff view showing:
    - Lines that were added (highlighted in green)
    - Lines that were removed (highlighted in red)
    - Lines that were modified (highlighted in yellow)
6. User can understand how the code evolved over time

---

## Key Business Rules

### Authentication & Authorization
- Users must be logged in to create, edit, or delete snippets
- Users must be logged in to favorite snippets or leave comments
- Users can only edit or delete their own snippets
- Users can only edit or delete their own comments
- Anyone (even not logged in) can view public snippets
- Only the owner can view their private snippets

### Snippet Visibility
- **Public snippets:** Visible to everyone, included in search results, appear in feeds
- **Private snippets:** Only visible to the owner, not searchable, not in public feeds

### Data Validation
- Usernames must be unique
- Email addresses must be unique and valid format
- Passwords must meet minimum security requirements
- Snippet titles are required
- Code content is required
- Programming language must be selected

### Data Integrity
- When a user is deleted, all their snippets are also deleted
- When a snippet is deleted, all its comments, favorites, and version history are also deleted
- When a snippet is deleted, all favorites of that snippet are removed
- Tags that are no longer used by any snippet can be cleaned up
- Version history is automatically maintained and cannot be manually deleted by users
- Each version is immutably stored - once created, it cannot be changed

### Versioning Rules
- Every snippet edit automatically creates a new version
- Version numbers start at 1 and increment sequentially
- The first version is created when the snippet is initially saved
- Versions capture the complete state of the snippet (title, description, code, language)
- Users cannot skip version numbers or create versions out of order
- When restoring an old version, it doesn't delete newer versions - it creates a new version with the old content
- Version history is visible to anyone who can see the snippet (public snippets = public history)
- The author of each version is recorded (the person who made that specific edit)
- Timestamps are recorded for each version showing when the edit was made

---

## Vocabulary Summary

**User** = A person with an account on Kakera

**Snippet** = A saved piece of code with description

**Tag** = A label/keyword for categorizing snippets

**Favorite** = A bookmark that users add to snippets they like

**Comment** = A message left on a snippet

**Version** = A historical snapshot of a snippet at a specific point in time

**Version History** = The complete list of all versions of a snippet showing how it changed over time

**Version Number** = A sequential number (1, 2, 3...) identifying each version

**Current Version** / **Latest Version** = The most recent version of a snippet

**Restore** = To bring back an older version of a snippet (creates a new version with old content)

**Diff** / **Comparison** = A view showing the differences between two versions

**Public** = Visible to everyone

**Private** = Visible only to the owner

**Author/Owner** = The user who created something

**Editor** = The user who made a specific version/edit (might be the same as the owner)

**Feed** = A list of recent or relevant snippets

**Syntax Highlighting** = Colorful formatting that makes code easier to read

**Immutable** = Cannot be changed once created (applies to versions)

---

This domain description covers the essential concepts, relationships, and rules that make up the Kakera application. The application is fundamentally about helping developers save, organize, share, and discover useful code snippets in a collaborative community environment, with full version control to track how code evolves over time.
