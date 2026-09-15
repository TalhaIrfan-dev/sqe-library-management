# Boundary Value Analysis

## 1. Purpose

This document applies Boundary Value Analysis (BVA) to selected inputs
in the LibraryHub system.

BVA focuses on values at and around the boundaries of valid and invalid
equivalence classes. It complements the Equivalence Partitioning (EP)
analysis performed in Lab 5.

The following inputs are analyzed:

1. Overdue days for fine-tier classification
2. Number of books currently on loan
3. ISBN length

---

## 2. Fine-Tier Boundary Analysis

The `fineTier()` method classifies overdue days into the following
equivalence classes:

| Overdue Days | Classification |
|---:|---|
| `< 0` | Invalid |
| `0` | None |
| `1–7` | Low |
| `8–14` | Medium |
| `15–30` | High |
| `31+` | Severe |

### 2.1 Boundary Values

| Boundary | Value - 1 | Value | Value + 1 | Expected Result |
|---|---:|---:|---:|---|
| Domain edge at 0 | `-1` | `0` | `1` | Exception, `None`, `Low` |
| None/Low boundary | `0` | `1` | `2` | `None`, `Low`, `Low` |
| Low/Medium boundary | `7` | `8` | `9` | `Low`, `Medium`, `Medium` |
| Medium/High boundary | `14` | `15` | `16` | `Medium`, `High`, `High` |
| High/Severe boundary | `30` | `31` | `32` | `High`, `Severe`, `Severe` |

### 2.2 BVA Test Cases

| Test ID | Boundary | Test Values | Expected Results |
|---|---|---|---|
| BVA-FT-01 | Domain edge | `-1, 0, 1` | Exception, `None`, `Low` |
| BVA-FT-02 | 0/1 | `0, 1, 2` | `None`, `Low`, `Low` |
| BVA-FT-03 | 7/8 | `7, 8, 9` | `Low`, `Medium`, `Medium` |
| BVA-FT-04 | 14/15 | `14, 15, 16` | `Medium`, `High`, `High` |
| BVA-FT-05 | 30/31 | `30, 31, 32` | `High`, `Severe`, `Severe` |

---

## 3. Borrowing-Limit Boundary Analysis

Lab 5 established the borrowing-limit rule as:

- `0–5` books on loan → Valid
- `6+` books on loan → Invalid

The boundary between the valid and invalid partitions occurs
between **5 and 6 books**.

### 3.1 Boundary Values

| Boundary | Value - 1 | Value | Value + 1 | Expected Result |
|---|---:|---:|---:|---|
| Maximum borrowing limit | `4` | `5` | `6` | Allowed, Allowed, Rejected |

For the borrowing operation, the boundary is tested by attempting to
borrow one additional book.

| Current Books | Attempt | Expected Result |
|---:|---|---|
| `4` | Borrow 1 book | Allowed; member has 5 books |
| `5` | Borrow 1 book | Rejected |

The value `6` represents the first invalid state. A separate test was
not created to first place a member in a six-book state because the
normal borrowing operation prevents a member from reaching that state.
The `5 → 6` borrowing attempt directly verifies the boundary.

---

## 4. ISBN-Length Boundary Analysis

Lab 5 established that an ISBN must contain **exactly 13 numeric
digits**.

Therefore, the valid boundary is a length of **13 digits**.

### 4.1 Boundary Values

| Boundary | Value - 1 | Value | Value + 1 | Expected Result |
|---|---:|---:|---:|---|
| Valid ISBN length | 12 digits | 13 digits | 14 digits | Rejected, Accepted, Rejected |

### 4.2 BVA Test Cases

The following five lengths are selected to test values around the
13-digit boundary:

| Test ID | ISBN Length | Expected Result |
|---|---:|---|
| BVA-ISBN-01 | 11 digits | Rejected |
| BVA-ISBN-02 | 12 digits | Rejected |
| BVA-ISBN-03 | 13 digits | Accepted |
| BVA-ISBN-04 | 14 digits | Rejected |
| BVA-ISBN-05 | 15 digits | Rejected |

---

## 5. Summary of Boundary Values

| Input | Boundary | Values Tested |
|---|---|---|
| Fine-tier overdue days | 0 | `-1, 0, 1` |
| Fine-tier overdue days | 0/1 | `0, 1, 2` |
| Fine-tier overdue days | 7/8 | `7, 8, 9` |
| Fine-tier overdue days | 14/15 | `14, 15, 16` |
| Fine-tier overdue days | 30/31 | `30, 31, 32` |
| Borrowing limit | 5/6 books | `4, 5, 6` |
| ISBN length | 13 digits | `11, 12, 13, 14, 15` |

---

## 6. BVA Test Implementation

Boundary tests were implemented using Java and JUnit 5.

The following test classes were used:

- `FineTierBvaTest.java`
- `BorrowLimitTest.java`
- `ValidateIsbnTest.java`

The fine-tier BVA test contains 13 parameterized boundary cases covering
the domain edge and all fine-tier transitions.

The borrowing-limit tests cover the valid maximum and the transition
from the maximum allowed number of books to the first rejected borrowing
attempt.

The ISBN tests cover 11, 12, 13, 14, and 15 digit ISBN values around the
13-digit boundary.

---

## 7. Combined EP and BVA Test Execution

The complete test suite was executed using Maven:

```text
mvn clean test
```

### 7.1 Execution Result

| Test Class | Tests | Failures | Errors |
|---|---:|---:|---:|
| `BorrowLimitTest` | 3 | 0 | 0 |
| `FineTierBvaTest` | 13 | 0 | 0 |
| `FineTierTest` | 6 | 0 | 0 |
| `ValidateIsbnTest` | 9 | 0 | 0 |
| **Total** | **31** | **0** | **0** |

### 7.2 Maven Test Output

```text
Tests run: 31, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```
The combined Equivalence Partitioning (EP) and Boundary Value Analysis
(BVA) test suite achieved a 100% pass rate, with all 31 tests passing
successfully.

## 8. BVA Conclusion

The Boundary Value Analysis testing successfully evaluated the selected
boundaries for fine-tier classification, borrowing limits, and ISBN
length validation.

The testing confirmed that:

- Negative overdue days are rejected.
- Fine-tier boundary values are classified correctly.
- A member can borrow up to the maximum limit of 5 books.
- The sixth borrowing attempt is rejected.
- A 13-digit numeric ISBN is accepted.
- ISBNs with lengths below or above 13 digits are rejected.

No off-by-one defect was detected during the BVA execution.

BVA provided additional test coverage around the boundaries that were not
fully covered by the representative values used during Equivalence
Partitioning in Lab 5.

The combined EP and BVA test suite completed with **31/31 tests passing,
0 failures, 0 errors, and a successful Maven build**.