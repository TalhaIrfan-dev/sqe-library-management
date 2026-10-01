# Lab 07 Work Summary -- LibraryHub

## Overview

This document records the work completed for Software Quality
Engineering Lab 07 in the LibraryHub project.

The lab examples use Python/pytest, but this project implements the same
testing concepts using Java, JUnit 5, Maven, and Mockito.

**Final test result:** 45 tests run, 0 failures, 0 errors, 0 skipped,
Build SUCCESS.

------------------------------------------------------------------------

## Pre-Lab Work

### Modified: `src/LibrarySystem/BookManager.java`

Added `exportCatalog(String path)` to export the current in-memory
catalog to a file.

The export contains: - Book title - Author - ISBN - Available copies -
Total copies

The method was later updated to catch `IOException` and throw the custom
`LibraryIOException`.

### Created: `data/exports/`

This is the designated folder for real catalog exports.

Example:

``` java
BookManager.exportCatalog("data/exports/catalog.txt");
```

The Mockito tests do not create a real catalog file.

------------------------------------------------------------------------

# Task 1 -- Test Fixtures and Shared Setup

### Modified: `tests/FineTierTest.java`

Added `@BeforeEach` to demonstrate function-level shared setup and
documented when this scope is appropriate.

### Modified/Used: `tests/BorrowLimitTest.java`

Uses `@BeforeEach` to clear `BookManager.books` and
`MemberManager.members` before each test.

### Created: `tests/FixtureScopeTest.java`

Created a real LibraryHub test class demonstrating class-level setup
with `@BeforeAll` and cleanup with `@AfterAll`.

Two real books are added during class setup and checked by the tests.

**Result:** Task 1 completed successfully.

------------------------------------------------------------------------

# Task 2 -- Total Available Copies

### Modified: `src/LibrarySystem/BookManager.java`

Added:

``` java
public static int totalAvailableCopies()
```

The method calculates the total number of available copies across all
books.

### Created: `tests/TotalAvailableCopiesTest.java`

Added three tests:

1.  Empty library → `0`
2.  Single book → available copies
3.  Multiple books → combined available copies

**Result:** All 3 Task 2 tests passed.

------------------------------------------------------------------------

# Task 3 -- Mocked File I/O

### Created: `src/LibrarySystem/LibraryIOException.java`

Created a custom runtime exception for catalog export failures.

### Modified: `src/LibrarySystem/BookManager.java`

Updated `exportCatalog()` to catch `IOException` and throw
`LibraryIOException`.

### Created: `tests/ExportCatalogTest.java`

Added two Mockito tests:

1.  Successful export --- mocks `FileWriter` and verifies the expected
    catalog content.
2.  Export failure --- makes the mocked writer throw `IOException` and
    verifies that `LibraryIOException` is thrown.

### Modified: `pom.xml`

Added Mockito as a test dependency:

``` xml
<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-core</artifactId>
    <version>5.18.0</version>
    <scope>test</scope>
</dependency>
```

**Result:** Both Task 3 tests passed.

------------------------------------------------------------------------

# Task 4 -- Parameterized Borrow Book Testing

### Created: `tests/BorrowBookParameterizedTest.java`

Implemented JUnit 5 parameterized testing using `@ParameterizedTest` and
`@MethodSource`.

Six scenarios were included:

1.  Valid borrowing
2.  Member not found
3.  Book not found
4.  Empty ISBN
5.  No copies available
6.  Borrowing limit reached

`@BeforeEach` resets the library and member data before every
parameterized execution.

### Preserved: `tests/BorrowLimitTest.java`

The existing Lab 6 BVA tests were retained. They specifically test the
borrowing-limit boundary:

-   3 books → borrow 4th
-   4 books → borrow 5th
-   5 books → reject 6th

The new parameterized test serves a different purpose by covering
multiple `borrowBook()` behaviors with one reusable test method.

**Result:** All 6 parameterized cases passed.

------------------------------------------------------------------------

# Task 5 -- Test Execution, Notes, and Configuration

## Test Execution

### Standard Run

``` text
mvn test
```

**Result:**

-   Tests run: 45
-   Failures: 0
-   Errors: 0
-   Skipped: 0
-   Build: SUCCESS

### Detailed Run

``` text
mvn test -DtrimStackTrace=false
```

**Result:**

-   Tests run: 45
-   Failures: 0
-   Errors: 0
-   Skipped: 0
-   Build: SUCCESS

Both commands produced the same test results. The detailed run provided
more execution information.

### Created: `docs/unit-testing-notes.md`

Contains: - Standard test result - Detailed test result - Test execution
comparison - Test count by test class - Observations - Conclusion

### Modified: `pom.xml`

Updated Maven Surefire configuration with:

``` xml
<configuration>
    <printSummary>true</printSummary>
    <trimStackTrace>false</trimStackTrace>
    <useModulePath>false</useModulePath>
</configuration>
```

The configuration was verified by running `mvn test`.

**Result:** All 45 tests passed successfully.

------------------------------------------------------------------------

# Final Test Suite

  Test Class                       Tests
  ----------------------------- --------
  BorrowBookParameterizedTest          6
  BorrowLimitTest                      3
  ExportCatalogTest                    2
  FineTierBvaTest                     13
  FineTierTest                         6
  FixtureScopeTest                     2
  TotalAvailableCopiesTest             3
  ValidateIsbnTest                    10
  **Total**                       **45**

**Final result: 45/45 tests passed.**

------------------------------------------------------------------------

# Files Created During Lab 07

  ---------------------------------------------------------------------------------
  File                                          Purpose
  --------------------------------------------- -----------------------------------
  `tests/FixtureScopeTest.java`                 Demonstrates class-scope fixture
                                                setup

  `tests/TotalAvailableCopiesTest.java`         Tests total available copies

  `src/LibrarySystem/LibraryIOException.java`   Custom export exception

  `tests/ExportCatalogTest.java`                Mockito-based export tests

  `tests/BorrowBookParameterizedTest.java`      Parameterized borrow-book testing

  `docs/unit-testing-notes.md`                  Task 5 execution notes

  `data/exports/`                               Designated folder for real catalog
                                                exports
  ---------------------------------------------------------------------------------

**Note:** `docs/lab07work.md` is the current document and should be
added to this list after creation.

------------------------------------------------------------------------

# Existing Files Modified During Lab 07

  --------------------------------------------------------------------------
  File                                   Changes
  -------------------------------------- -----------------------------------
  `src/LibrarySystem/BookManager.java`   Added `exportCatalog()` and
                                         `totalAvailableCopies()`; updated
                                         export exception handling

  `tests/FineTierTest.java`              Added shared `@BeforeEach` fixture
                                         demonstration

  `tests/BorrowLimitTest.java`           Uses shared `@BeforeEach` test-data
                                         cleanup

  `pom.xml`                              Added Mockito and configured Maven
                                         Surefire
  --------------------------------------------------------------------------

------------------------------------------------------------------------

# Files Preserved from Previous Labs

The existing tests and functionality from earlier labs were retained,
including:

-   `FineTierTest.java`
-   `FineTierBvaTest.java`
-   `BorrowLimitTest.java`
-   `ValidateIsbnTest.java`

Lab 7 added new testing techniques without replacing the previous lab
work.

------------------------------------------------------------------------

# Lab 07 Completion Summary

Lab 07 introduced and demonstrated:

-   Shared JUnit test setup
-   Function-level and class-level fixture concepts
-   New unit-tested library functionality
-   Mockito-based file I/O mocking
-   Custom exception handling
-   Parameterized testing
-   Maven/Surefire test configuration
-   Test execution comparison and documentation

The final LibraryHub test suite contains **45 automated tests**, all
passing with:

``` text
Failures: 0
Errors: 0
Skipped: 0
Build: SUCCESS
```
