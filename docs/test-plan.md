# Library Management System Test Plan

## 1. Test Plan Identifier

**Test Plan ID:** TP-LMS-01

**Project:** Library Management System

---

## 2. Introduction

This test plan defines the testing activities for the Library Management System.

The purpose of testing is to verify that the implemented system functions correctly and meets the defined requirements. Testing will focus on book management, member management, book searching, borrowing, returning books, copy availability, and fine calculation.

---

## 3. Test Items

The following components of the Library Management System will be tested:

- Book management
- Member management
- Book searching
- Book borrowing
- Book returning
- Copy availability management
- Fine calculation

---

## 4. Features to be Tested

The following features will be tested:

- Adding a new book
- Validating book information
- Preventing duplicate ISBNs
- Searching books by title
- Searching books by ISBN
- Adding library members
- Preventing duplicate member IDs
- Borrowing available books
- Rejecting borrowing when no copies are available
- Preventing duplicate borrowing
- Enforcing the borrowing limit
- Returning borrowed books
- Preventing unauthorized returns
- Maintaining total and available book copies
- Calculating fines for overdue books
- Calculating fines when there are zero overdue days
- Calculating fines for a mid-range overdue period
- Calculating fines at an overdue-tier boundary

---

## 5. Features Not to be Tested

The following areas are outside the scope of this testing:

- User interface design — excluded because Lab 4 focuses on functional testing of the Library Management System rather than visual design.
- Database performance — excluded because the current system does not use a database.
- Network performance — excluded because the system is executed locally.
- Security testing — excluded because security testing is not part of the Lab 4 requirements.
- Deployment and production infrastructure — excluded because testing is performed in a local development environment.

---

## 6. Approach

Testing will be performed using manual functional testing techniques.

Each test case will be executed using appropriate valid, invalid, and boundary inputs. The actual result will be compared with the expected result.

Each test case will be marked as **Pass**, **Fail**, or **Blocked**.

---

## 7. Pass/Fail Criteria

The overall testing will be considered successful when at least **95% of the planned test cases pass** and **zero Critical defects remain open**.

Since 12 test cases are planned, all **12 test cases must pass** to meet the 95% criterion.

- A test case will be marked **Pass** when the actual result matches the expected result.
- A test case will be marked **Fail** when the system produces a result different from the expected result.
- A test case will be marked **Blocked** when the test cannot be executed because a required functionality or condition is unavailable.

---

## 8. Suspension/Resumption Criteria

Testing may be suspended if a major defect prevents further test execution.

Testing will resume after the blocking issue has been fixed or a suitable workaround is available.

---

## 9. Test Deliverables

The following deliverables will be produced:

- Test Plan
- Test Cases
- Requirements Traceability Matrix (RTM)
- Test Execution Results
- GitHub defect issue(s) for failed test cases

---

## 10. Test Environment

Testing will be performed on a Windows computer using Java and Visual Studio Code.

The Library Management System source code will be executed locally.

GitHub will be used for repository management and defect tracking.

---

## 11. Schedule

Testing will be performed during the Lab 4 activity.

Test case preparation, test execution, defect reporting, and documentation will be completed before submission.

---

## 12. Risks and Contingencies

Possible risks include incorrect input handling, unexpected program behavior, and unavailable functionality required by a test case.

If a test cannot be executed, it will be marked as **Blocked** and the reason will be documented.

If a test fails, a GitHub issue will be created to record the defect.

---

## 13. Approvals

| Role | Name | Status |
|---|---|---|
| Student | Talha Irfan | Prepared |
| Instructor | Dr. Khursheed Ali | Pending |