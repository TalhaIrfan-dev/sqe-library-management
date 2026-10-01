# Unit Testing Notes – Lab 7

## Test Execution Comparison

LibraryHub was tested using Maven and JUnit 5.

### Standard Test Run

Command:

```text
mvn test
```

**Result:**

- **Tests run:** 45
- **Failures:** 0
- **Errors:** 0
- **Skipped:** 0
- **Build:** SUCCESS

The standard test run provides a concise summary of the overall test-suite result.

---

### Detailed Test Run

**Command:**

```text
mvn test -DtrimStackTrace=false
```

**Result:**

- **Tests run:** 45
- **Failures:** 0
- **Errors:** 0
- **Skipped:** 0
- **Build:** SUCCESS

The detailed test run displayed the execution result for each test class and provided more information during test execution.

---

## Test Execution Comparison

| Aspect | Standard Run | Detailed Run |
|---|---|---|
| Command | `mvn test` | `mvn test -DtrimStackTrace=false` |
| Tests executed | 45 | 45 |
| Failures | 0 | 0 |
| Errors | 0 | 0 |
| Skipped | 0 | 0 |
| Build result | SUCCESS | SUCCESS |
| Output | Concise | More detailed |

Both commands produced the same test results. The detailed run provides more execution information, while the standard run is sufficient for quickly checking whether the complete test suite passes.

---

## Current Test Suite

| Test Class | Number of Tests |
|---|---:|
| BorrowBookParameterizedTest | 6 |
| BorrowLimitTest | 3 |
| ExportCatalogTest | 2 |
| FineTierBvaTest | 13 |
| FineTierTest | 6 |
| FixtureScopeTest | 2 |
| TotalAvailableCopiesTest | 3 |
| ValidateIsbnTest | 10 |
| **Total** | **45** |

---

## Observations

1. All 45 automated tests passed successfully.
2. No test failures, errors, or skipped tests were reported.
3. Task 1 introduced shared JUnit setup using `@BeforeEach` and `@BeforeAll`.
4. Task 2 added tests for total available book copies.
5. Task 3 added Mockito-based file I/O mocking and exception testing.
6. Task 4 added parameterized testing for multiple `borrowBook()` scenarios.
7. The Mockito inline-mock-maker warning appeared during the detailed run, but it did not affect the test results or build status.

---

## Conclusion

The LibraryHub unit test suite successfully executes 45 automated tests using JUnit 5 and Maven. All tests passed with zero failures, errors, or skipped tests.