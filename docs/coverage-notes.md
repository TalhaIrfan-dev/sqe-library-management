# Lab 11 — Automated Test Coverage

## Task 1 — Baseline Report

### Baseline Coverage

The existing automated JUnit test suite was executed using Maven and coverage was measured using JaCoCo.

| Metric | Baseline |
|---|---:|
| Line Coverage | 77% |
| Branch Coverage | 67% |
| Total Lines | 238 |
| Missed Lines | 51 |
| Total Branches | 144 |
| Missed Branches | 47 |

### Baseline Observation

The baseline line coverage is **77%**, while branch coverage is **67%**.

The coverage report was generated using JaCoCo after executing the automated JUnit test suite.

### HTML Coverage Report

The JaCoCo HTML coverage report is available at:

`target/site/jacoco/index.html`

A screenshot of the baseline coverage summary is included as part of the Lab 11 submission.

### Baseline Summary

The baseline demonstrates that the current automated test suite exercises most of the source code, but several lines and conditional branches remain uncovered. These uncovered areas will be analyzed in Task 2 and targeted with additional tests in Task 3.

---

## Task 2 — Identify Three Weakest Files

Based on the baseline JaCoCo HTML coverage report, the three weakest source files were identified as follows.

### 1. main.java

- Line coverage: **0%**
- Uncovered lines: **7–8, 14–16, 19–23**
- The uncovered code consists of the demo application's object creation and manual fine-calculation/fine-tier calls.

### 2. member.java

- Line coverage: **61%**
- Uncovered lines: **23, 25, 27, 30**
- These lines correspond to the getter methods:
  - `getmemberId()`
  - `getmemberName()`
  - `getborrowedBooks()`
  - `getdueDates()`

### 3. BookManager.java

- Line coverage: **76%**
- Branch coverage: **59%**
- Important uncovered lines include **28, 33, 38, 43, 48, 54, 67, 83, 87, 89–97, 104, 183, 188, 195, 212, and 216**.
- The report also shows partially covered conditional branches around the validation, search, and reservation logic.

### Task 2 Conclusion

The three weakest files identified from the baseline report are `main.java`, `member.java`, and `BookManager.java`. Task 3 will focus on meaningful tests for their uncovered lines and conditional/error-handling paths rather than simply adding duplicate happy-path tests.

---

## Task 3 — Close the Gaps

Based on the baseline coverage report, three weak source files were selected for targeted testing:

1. `main.java`
2. `member.java`
3. `BookManager.java`

The new tests focused primarily on uncovered error-handling paths and conditional branches rather than duplicating existing happy-path tests.

### 1. main.java

The baseline report showed **0% line coverage** for `main.java`.

A new `MainTest.java` was added to execute the application's `main()` method through reflection without modifying the package-private production class.

The test verifies the complete expected console output, including:

- fine calculations for different overdue-day values
- negative fine-tier input handling
- all fine-tier results

After running the coverage report:

- Line coverage: **100%**
- Missed lines: **0**

Therefore, the previously uncovered `main.java` code was successfully exercised.

### 2. member.java

The baseline report showed **61% line coverage** for `member.java`.

The uncovered getter methods were:

- `getmemberId()`
- `getmemberName()`
- `getborrowedBooks()`
- `getdueDates()`

A new `MemberGetterTest.java` was added. The test creates a member through `MemberManager`, retrieves the member, and verifies the values returned by all four getters.

After running the coverage report:

- Line coverage: **100%**
- Missed lines: **0**

Therefore, all previously uncovered getter methods were successfully exercised and verified.

### 3. BookManager.java

The baseline report showed **76% line coverage and 59% branch coverage** for `BookManager.java`.

Several meaningful gaps were identified, particularly validation, search, and reservation error-handling paths.

#### Validation paths

`BookManagerValidationTest.java` was added to test:

- null/empty book title
- null/empty author
- null/empty ISBN
- invalid ISBN format
- invalid copy count
- duplicate ISBN

The test also verifies the expected exception messages and confirms that duplicate books are not added.

#### Book title search

`FindBookByTitleTest.java` was added to cover:

- successful title lookup
- case-insensitive search
- leading/trailing whitespace
- null input
- empty input
- whitespace-only input
- nonexistent book

The test verifies the returned book rather than simply executing the method.

#### Reservation and ISBN validation paths

`ReservationValidationTest.java` was added to cover:

- invalid member IDs during reservation
- invalid ISBNs during reservation
- reservation of a nonexistent book
- invalid member IDs during cancellation
- invalid ISBNs during cancellation
- null ISBN validation
- invalid ISBN lookup
- reservation-count behavior for a different ISBN

These tests specifically target previously uncovered error-handling and conditional branches.

### Task 3 Verification

Coverage was re-run after each major test addition using Maven and JaCoCo.

The final Task 3 coverage reached:

| File | Line Coverage | Branch Coverage |
|---|---:|---:|
| `main.java` | **100%** | N/A |
| `member.java` | **100%** | N/A |
| `BookManager.java` | **100%** | **95%** |

Overall coverage after closing the identified gaps:

| Metric | Baseline | After Task 3 |
|---|---:|---:|
| Line Coverage | **77%** | **94%** |
| Branch Coverage | **67%** | **86%** |

The targeted lines and branches were verified through the JaCoCo HTML report. The final results demonstrate that the three weakest areas identified during Task 2 were substantially improved through targeted automated tests.

---

## Task 4 — Coverage vs. Quality Discussion

### Strengthened Test

The existing test `memberWith3BooksCanBorrow4thBook()` in `BorrowLimitTest.java` originally used `assertDoesNotThrow()` when borrowing the fourth book.

The test was strengthened to verify the actual resulting state:

- The member exists after the borrowing operation.
- The borrowed book exists.
- The member has exactly 4 borrowed books.
- The borrowed book has 0 available copies.
- The fourth borrowed book has the expected ISBN.

### Why the Original Test Was a Coverage Trap

The original `assertDoesNotThrow()` assertion only verified that the operation completed without throwing an exception. Although executing the statement contributed to code coverage, it did not verify whether the borrowing operation actually produced the correct result.

Therefore, the test could have achieved coverage even if the implementation failed to add the book correctly, updated the wrong book, or failed to update the available-copy count.

This is a coverage trap because high code coverage does not necessarily mean that the test suite verifies the correctness of the software.

The strengthened assertions verify the observable state after the operation, making the test more effective as a quality check rather than simply increasing coverage.

### Task 4 Verification

After strengthening the assertion, the complete test suite was executed successfully using Maven.

Final coverage at this stage:

| Metric | Coverage |
|---|---:|
| Line Coverage | **94%** |
| Branch Coverage | **86%** |

The strengthened test therefore improved test quality without reducing the achieved coverage.

---

## Task 5 — Enforce a Coverage Threshold

The project uses Java with Maven and JaCoCo rather than Python with pytest-cov. Therefore, the equivalent JaCoCo coverage enforcement mechanism was configured in `pom.xml`.

A JaCoCo `check` execution was added to the Maven `verify` phase with a minimum **line coverage threshold of 85%**.

### Threshold Configuration

The configured rule requires:

- Coverage counter: `LINE`
- Required minimum: **85%**
- Enforcement phase: Maven `verify`

Therefore, the build should fail whenever the overall line coverage falls below 85%.

### Threshold Verification

The threshold was verified in two stages.

**Normal test suite**

The complete test suite was executed using:

`mvn clean verify`

The project completed successfully with the final coverage remaining above the required threshold.

**Intentional coverage reduction**

To verify that the threshold actually prevents a low-coverage build from passing, the test method `booksShouldBeRankedByBorrowCountDescending()` in `RankBooksTest.java` was temporarily disabled.

After running:

`mvn clean verify`

the overall line coverage dropped below the configured 85% minimum. JaCoCo therefore rejected the coverage level and the Maven build failed.

This confirmed that the coverage threshold is actively enforced rather than being only a reporting value.

The test was then restored and the complete suite was executed again. The build returned to **BUILD SUCCESS**, confirming that the project satisfies the configured coverage requirement.

### Conclusion

The 85% line-coverage threshold is now enforced automatically during Maven verification. A temporary reduction in test coverage correctly caused the build to fail, and restoring the test returned the project to a passing state.

---