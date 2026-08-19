# Workflow Notes

## Change Flow

A change in the Library Management System can move through the following workflow:

```text
Idea
  │
  ▼
Issue
  │
  ▼
Branch
  │
  ▼
Pull Request (PR)
  │
  ▼
Review
  │
  ▼
Merge
  │
  ▼
CI (Continuous Integration)
  │
  ▼
Release
```

The complete flow can be described as:

**Idea → Issue → Branch → PR → Review → Merge → CI → Release**

---

## 1. Idea

A new feature, improvement, or bug fix is first identified as an idea.

For example, a user may suggest adding a search feature to make it easier to find books in the library management system.

**QA Engineer involvement:**

* Understand the proposed change.
* Identify possible risks and edge cases.
* Think about how the requirement can be tested.
* Clarify unclear or incomplete requirements.

---

## 2. Issue

The idea is documented as a GitHub Issue. The issue describes the problem, feature request, expected behavior, and other relevant information.

For a bug, the issue template can contain:

* Description
* Steps to Reproduce
* Expected Result
* Actual Result
* Environment
* Severity

**QA Engineer involvement:**

* Verify that the issue contains enough information to test.
* Confirm that bugs have clear reproduction steps.
* Assign or suggest an appropriate severity.
* Identify acceptance criteria and testing requirements.

---

## 3. Branch

A developer creates a separate Git branch from the main branch to implement the change.

Example:

```text
main
  │
  └── feature/book-search
```

Working on a separate branch keeps the main branch stable while the change is being developed.

**QA Engineer involvement:**

* Review the testing requirements for the branch.
* Prepare test cases or scenarios.
* Identify regression areas that may be affected by the change.

---

## 4. Pull Request (PR)

After implementing the change, the developer opens a Pull Request to merge the branch into the main branch.

The PR explains what was changed and should reference the related issue.

The PR checklist can include:

```text
[ ] Tests added/updated
[ ] Documentation updated
[ ] Linked issue referenced
```

**QA Engineer involvement:**

* Check whether appropriate tests have been added or updated.
* Review the change from a testing perspective.
* Verify that the PR is linked to the correct issue.
* Check whether the change may affect existing functionality.

---

## 5. Review

The Pull Request is reviewed before it is merged.

Reviewers examine the code, tests, documentation, and overall implementation.

**QA Engineer involvement:**

* Review test coverage.
* Check whether edge cases have been considered.
* Identify possible defects or regression risks.
* Confirm that the implementation satisfies the issue requirements.
* Request additional tests when necessary.

---

## 6. Merge

Once the PR has been approved and the required checks have passed, it can be merged into the main branch.

```text
feature/book-search
        │
        │ Merge
        ▼
      main
```

**QA Engineer involvement:**

* Confirm that required tests and reviews are complete.
* Verify that no known critical defects remain.
* Ensure that the change is ready to enter the main codebase.

---

## 7. CI — Continuous Integration

After the change is merged, the Continuous Integration system automatically runs checks such as:

* Building the application
* Running automated tests
* Checking code quality
* Detecting integration problems

A simplified CI flow is:

```text
Merge
  │
  ▼
Build
  │
  ▼
Automated Tests
  │
  ▼
Quality Checks
  │
  ▼
Pass / Fail
```

**QA Engineer involvement:**

* Monitor automated test results.
* Investigate failed tests.
* Verify that the build succeeds.
* Analyze failures and determine whether they indicate defects.
* Ensure that important regression tests are included in CI.

---

## 8. Release

After the change successfully passes the required checks and is considered stable, the application can be released.

The release may include:

* The new feature or bug fix
* Updated documentation
* A release version or tag
* Release notes

**QA Engineer involvement:**

* Perform final validation.
* Execute important regression and acceptance tests.
* Verify that critical functionality still works.
* Confirm that known release-blocking defects are resolved.
* Provide QA approval or sign-off when required.

---

## Summary

The overall workflow is:

```text
┌──────────┐
│   Idea   │
└────┬─────┘
     │
     ▼
┌──────────┐
│  Issue   │ ◄── QA: requirements, severity, test criteria
└────┬─────┘
     │
     ▼
┌──────────┐
│  Branch  │ ◄── QA: test planning and risk identification
└────┬─────┘
     │
     ▼
┌──────────┐
│    PR    │ ◄── QA: test coverage and acceptance criteria
└────┬─────┘
     │
     ▼
┌──────────┐
│  Review  │ ◄── QA: defects, edge cases, regression risks
└────┬─────┘
     │
     ▼
┌──────────┐
│  Merge   │ ◄── QA: confirm readiness
└────┬─────┘
     │
     ▼
┌──────────┐
│    CI    │ ◄── QA: automated tests and failures
└────┬─────┘
     │
     ▼
┌──────────┐
│ Release  │ ◄── QA: final validation and regression testing
└──────────┘
```

QA involvement is therefore not limited to the final testing stage. A QA engineer can contribute throughout the entire software development workflow, from understanding the initial idea and defining test criteria to validating the final release.



## Task 3 — Deliberate Merge Conflict

### Cause of the Conflict

Two feature branches were created from `main` and modified the same field in `src/book.java` differently.

- `feature/rename-field-a` renamed the field to `bookTitle`.
- `feature/rename-field-b` renamed the field to `bookName`.

After Branch A was merged into `main`, merging `main` into Branch B caused a content conflict because both branches changed the same lines differently.

### Conflict Resolution

The conflict was resolved locally on `feature/rename-field-b`.

The updated `main` branch was merged into Branch B using:

```bash
git merge main
```

# Git Merge Conflict Resolution

Git reported a conflict in:

```text
src/book.java
```

The conflict markers were manually resolved by choosing the required version of the code. The resolved file was then staged and committed:

```powershell
git add .
git commit -m "resolve merge conflict between rename branches"
git push
```

After pushing the resolution, GitHub showed:

**No conflicts with base branch**

The Branch B pull request was then ready to be merged.

## Documentation Commit

Then separately run these commands in PowerShell:

```powershell
git add docs/workflow-notes.md
git commit -m "docs: document merge conflict resolution"
git push
```
