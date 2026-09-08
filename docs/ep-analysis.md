# Equivalence Partitioning Analysis

## 1. Purpose

This document applies Equivalence Partitioning (EP) to selected inputs in the LibraryHub system.

Equivalence Partitioning divides an input domain into classes where values are expected to produce the same behavior. One representative value is selected from each class for testing.

The following inputs are analyzed:

1. Overdue days for fine-tier classification
2. Number of books currently on loan
3. ISBN

---

## 2. Overdue Days — Fine Tier

The `FineCalculator.fineTier()` method classifies overdue days into five valid tiers and rejects negative values.

### Equivalence Classes

| Class ID | Input Range | Class Type | Expected Result | Representative |
|---|---|---|---|---:|
| EP-FT-01 | `< 0` | Invalid | `IllegalArgumentException` | `-3` |
| EP-FT-02 | `0` | Valid | `None` | `0` |
| EP-FT-03 | `1–7` | Valid | `Low` | `4` |
| EP-FT-04 | `8–14` | Valid | `Medium` | `10` |
| EP-FT-05 | `15–30` | Valid | `High` | `20` |
| EP-FT-06 | `31+` | Valid | `Severe` | `45` |

### EP Test Cases

| Test ID | Representative | Expected Result |
|---|---:|---|
| TC-FT-01 | `-3` | Exception |
| TC-FT-02 | `0` | `None` |
| TC-FT-03 | `4` | `Low` |
| TC-FT-04 | `10` | `Medium` |
| TC-FT-05 | `20` | `High` |
| TC-FT-06 | `45` | `Severe` |

---

## 3. Books Currently on Loan

LibraryHub allows a member to have between 0 and 5 books on loan. A member who already has 5 books cannot borrow a sixth book.

### Equivalence Classes

| Class ID | Input Range | Class Type | Expected Result | Representative |
|---|---|---|---|---:|
| EP-BL-01 | `0–5` books | Valid | Borrowing is allowed when a copy is available | `3` |
| EP-BL-02 | `6+` books | Invalid | `IllegalArgumentException` | `6` |

### EP Test Cases

| Test ID | Representative | Expected Result |
|---|---:|---|
| TC-BL-01 | Member has `3` books and attempts 4th | Borrowing allowed |
| TC-BL-02 | Member has `5` books and attempts 6th | Borrowing rejected |

---

## 4. ISBN

LibraryHub requires an ISBN to contain exactly 13 numeric digits.

### Equivalence Classes

| Class ID | Input | Class Type | Expected Result | Representative |
|---|---|---|---|---|
| EP-ISBN-01 | Exactly 13 numeric digits | Valid | Accepted | `1234567890123` |
| EP-ISBN-02 | Empty ISBN | Invalid | Rejected | `""` |
| EP-ISBN-03 | Fewer than 13 digits | Invalid | Rejected | `1234567890` |
| EP-ISBN-04 | Contains letters or symbols | Invalid | Rejected | `1234567890AB!` |

### EP Test Cases

| Test ID | Representative | Expected Result |
|---|---|---|
| TC-ISBN-01 | `1234567890123` | Accepted |
| TC-ISBN-02 | `""` | Rejected |
| TC-ISBN-03 | `1234567890` | Rejected |
| TC-ISBN-04 | `1234567890AB!` | Rejected |

---

## 5. Boundary Blind Spot of Equivalence Partitioning

Equivalence Partitioning selects representative values from input classes, but it does not necessarily test the exact points where one class changes into another.

For example, the fine-tier boundaries are:

- `0` / `1`
- `7` / `8`
- `14` / `15`
- `30` / `31`

EP representatives such as `4`, `10`, `20`, and `45` confirm the general behavior of each class, but they do not test every boundary.

Boundary Value Analysis (BVA) is therefore useful for testing values immediately around these boundaries.

---

## 6. JUnit Test Execution

The EP tests were implemented using Java and JUnit 5.

The complete Lab 5 EP test suite was executed using Maven:

```text
    mvn clean test
```
### Execution Result

| Test Class | Tests | Failures | Errors |
|---|---:|---:|---:|
| `BorrowLimitTest` | 2 | 0 | 0 |
| `FineTierTest` | 6 | 0 | 0 |
| `ValidateIsbnTest` | 4 | 0 | 0 |
| **Total** | **12** | **0** | **0** |

### Final Result

```text
    Tests run: 12, Failures: 0, Errors: 0, Skipped: 0
    BUILD SUCCESS
```
