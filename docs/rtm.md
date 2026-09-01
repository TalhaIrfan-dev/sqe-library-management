# Library Management System Requirements Traceability Matrix

## Purpose

This Requirements Traceability Matrix maps each Library Management System functional requirement to the test cases that verify it. The matrix ensures that every requirement has at least one corresponding test case and helps identify any gaps in test coverage.

| Requirement ID | Requirement | Linked Test Case IDs | Coverage Status |
|---|---|---|---|
| FR-01 | The system shall allow the librarian to add a new book by providing its title, author, ISBN, and number of copies. | TC-01 | Covered |
| FR-02 | The system shall reject a book when the title, author, or ISBN is empty or invalid, or when the number of copies is less than or equal to zero. | TC-03 | Covered |
| FR-03 | The system shall reject a new book if its ISBN already exists in the library catalog. | TC-02 | Covered |
| FR-04 | The system shall allow users to search for books by title or ISBN and return the matching book information. | TC-13 | Covered |
| FR-05 | The system shall allow the librarian to add library members and prevent duplicate member IDs. | TC-14 | Covered |
| FR-06 | The system shall allow a member to borrow a book when at least one copy is available, while preventing duplicate borrowing, borrowing unavailable books, and borrowing beyond the allowed limit. | TC-04, TC-05, TC-08, TC-09 | Covered |
| FR-07 | The system shall allow a member to return a book that they have currently borrowed, reject a return when the book was not borrowed by that member, and calculate any applicable fine for overdue returns according to the defined fine policy. | TC-06, TC-07, TC-10, TC-11, TC-12 | Covered |
| FR-08 | The system shall maintain the total number of copies and available copies of each book, decreasing available copies when a book is borrowed and increasing them when it is returned. | TC-04, TC-06 | Covered |

## Coverage Summary

- Total requirements: **8**
- Requirements with linked test cases: **8**
- Requirements with zero linked test cases: **0**
- Coverage: **100%**

## Gap Analysis

No requirements currently have zero linked test cases. All eight requirements have at least one corresponding test case in `docs/test-cases.md`.

Test cases **TC-13** and **TC-14** were added to close the coverage gaps identified for FR-04 and FR-05. FR-08 is covered by TC-04 and TC-06, which verify the change in available book copies when a book is borrowed and returned.