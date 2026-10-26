# TDD Log — Rank Books by Popularity

## Feature

The LibraryHub feature `rankBooksByPopularity()` ranks books by:
1. Total number of successful borrows in descending order.
2. Book title alphabetically when two books have the same borrow count.

---

## Cycle 1 — Empty Catalog

### RED
Created a test verifying that an empty catalog returns an empty list.

**Commit:** `def5008`  
`test(rank): red - empty catalog returns empty list`

The test initially failed because `rankBooksByPopularity()` did not exist.

### GREEN
Implemented the minimum behavior by returning an empty list.

**Commit:** `43f4aa2`  
`feat(rank): green - return empty list for empty catalog`

The test passed successfully.

### REFACTOR
No refactoring was required because the implementation was already minimal.

---

## Cycle 2 — Single Book

### RED
Added a test verifying that a single book is returned by the ranking method.

**Commit:** `233d343`  
`test(rank): red - single book is returned`

The test failed because the method returned an empty list.

### GREEN
Changed the implementation to return the books from the catalog.

**Commit:** `090b831`  
`feat(rank): green - return books from catalog`

Both existing tests passed.

### REFACTOR
No refactoring was required because the implementation was simple and clear.

---

## Cycle 3 — Borrow Count Sorting

### RED
Added a test with three books having different borrow counts and verified descending popularity order.

**Commit:** `1c2d3fd`  
`test(rank): red - sort books by borrow count`

The test failed because the books were returned in catalog order.

### GREEN
Added borrow-count tracking and implemented descending popularity sorting.

**Commit:** `9b8fe83`  
`feat(rank): green - rank books by borrow count`

The ranking test passed.

### REFACTOR
Simplified the repeated borrow-and-return setup in the test using a helper method.

**Commit:** `693ee08`  
`refactor(rank): simplify borrow setup in ranking test`

All ranking tests continued to pass.

---

## Cycle 4 — Alphabetical Tie-Breaking

### RED
Added a test verifying that books with equal borrow counts are sorted alphabetically by title.

**Commit:** `49aa5a0`  
`test(rank): red - break ties alphabetically`

The test failed because the implementation only considered borrow count.

### GREEN
Updated the comparison logic to use title alphabetically when borrow counts are equal.

**Commit:** `39204b4`  
`feat(rank): green - add alphabetical tie breaking`

The ranking tests passed.

### REFACTOR
Extracted the popularity comparison logic into a separate method for readability.

**Commit:** `e029de6`  
`refactor(rank): extract popularity comparison`

The complete regression suite passed with:

- Tests: 49
- Failures: 0
- Errors: 0
- Skipped: 0

---

## Cycle 5 — Independent Edge Case

### RED
An independent edge case was selected to verify that a failed borrow does not incorrectly affect book popularity.

**Commit:** `d85999c`  
`test(rank): red - failed borrow should not affect popularity`

The test initially failed.

### Investigation

Investigation showed that the failure was caused by an incorrect expected alphabetical order in the test, not by a defect in the production code.

The expected order was corrected.

**Commit:** `bc95ec6`  
`fix(test): correct alphabetical expectation`

After correcting the test, all ranking tests passed.

### GREEN

No production-code change was required because the existing implementation already handled the edge case correctly.

### REFACTOR

No production-code refactoring was required for this cycle.

---

## Final Regression Result

The complete project test suite was executed after the TDD work.

**Result:**

- All tests passed
- Failures: 0
- Errors: 0
- Skipped: 0
- Build: SUCCESS

---

## TDD Reflection

TDD helped us build the ranking feature incrementally by writing tests before implementing each new behavior. The failing tests clearly showed what functionality was missing and guided the implementation of borrow-count sorting and alphabetical tie-breaking. The refactoring steps improved the readability of both the tests and the ranking comparison logic while keeping the tests passing. The independent edge case also showed that a failing test should be investigated before assuming that the production code contains a defect.