# Library Management System

## Lab 5 Requirement Updates

During Lab 5, two existing requirements were refined based on the
Equivalence Partitioning analysis.

- **FR-02:** ISBN validation is now explicitly defined as exactly 13 numeric digits.
- **FR-06:** The maximum number of books a member may have on loan is now explicitly defined as 5.

These refinements keep the functional requirements aligned with the
current LibraryHub implementation and the Lab 5 EP analysis.

# Functional Requirements

| ID | Requirement | Description |
|---|---|---|
| FR-01 | Add Book | The system shall allow the librarian to add a new book by providing its title, author, ISBN, and number of copies. |
| FR-02 | Validate Book Information | The system shall reject a book when the title or author is empty, when the ISBN is empty or does not contain exactly 13 numeric digits, or when the number of copies is less than or equal to zero. |
| FR-03 | Prevent Duplicate ISBN | The system shall reject a new book if its ISBN already exists in the library catalog. |
| FR-04 | Search Books | The system shall allow users to search for books by title or ISBN and return the matching book information. |
| FR-05 | Manage Library Members | The system shall allow the librarian to add library members and prevent duplicate member IDs. |
| FR-06 | Borrow Books | The system shall allow a member to borrow a book when at least one copy is available, while preventing duplicate borrowing, borrowing unavailable books, and borrowing more than 5 books at a time. |
| FR-07 | Return Books and Calculate Fines | The system shall allow a member to return a book that they have currently borrowed, reject a return when the book was not borrowed by that member, and calculate any applicable fine for overdue returns according to the defined fine policy. |
| FR-08 | Manage Book Copies | The system shall maintain the total number of copies and available copies of each book, decreasing available copies when a book is borrowed and increasing them when it is returned. |