# SonarCloud Findings

## Lab 10 — Task 2: Triage the Findings

SonarCloud analysis was performed on the **SQE Library Management System** project. The Issues tab reported **two findings** at the time of triage.

### Findings

| # | Rule Name | File / Line | Severity | Type | Risk |
|---|---|---|---|---|---|
| 1 | `javabugs:S6416` — Calls to methods should not trigger an exception | `src/LibrarySystem/main.java:19` | **Blocker** | Reliability | Calling `FineCalculator.fineTier()` with a negative value causes an `IllegalArgumentException` during program execution. |
| 2 | `java:S2187` — TestCases should contain tests | `tests/LibraryTests.java:5` | **Blocker** | Maintainability | The class is recognized as a test class but does not contain actual test methods, reducing the effectiveness of automated testing. |

### Severity Categorization

| Severity | Number of Findings |
|---|---:|
| Blocker | 2 |
| Critical | 0 |
| Major | 0 |
| Minor | 0 |
| Info | 0 |
| **Total** | **2** |

### Notes

Only the findings actually reported by SonarCloud have been documented. The analysis produced two findings, so no additional findings have been invented to fill the requested top-eight list.

The two findings will be handled in the subsequent tasks according to the SonarCloud recommendations and the Lab 10 requirements.