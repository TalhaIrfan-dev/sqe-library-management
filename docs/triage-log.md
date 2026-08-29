# Triage Log

## Task 2 — Triage Meeting Simulation

The five defects reported during Task 1 were reviewed and ranked according to their severity, impact on the Library Management System, and priority for the current sprint.

## Fix Ranking

| Rank | Issue | Defect | Severity | Priority | Decision |
|---|---|---|---|---|---|
| 1 | #17 | `findBook()` crashes when search title is null | High | P1 | Fix this sprint |
| 2 | #18 | `addBook()` accepts negative copy values | High | P1 | Fix this sprint |
| 3 | #15 | `addBook()` allows duplicate book records | Medium | P1 | Fix this sprint |
| 4 | #19 | `addBook()` accepts an empty author | Medium | P1 | Won't fix this sprint |
| 5 | #16 | `findBook()` fails when search input contains spaces | Medium | P1 | Won't fix this sprint |

## Severity and Priority Trade-offs

### Issue #17 — Null Search Title

Issue #17 has high severity because providing a null search title causes the application to throw a `NullPointerException`. It is ranked first because preventing application crashes is more important than addressing lower-impact data and usability problems.

### Issue #18 — Negative Copy Values

Issue #18 has high severity because the system accepts invalid negative inventory values. Although the application does not crash, negative copies can result in incorrect library inventory data. It is therefore ranked immediately after the null-title crash.

### Issue #15 — Duplicate Book Records

Issue #15 has medium severity because the application continues running, but duplicate records can make library data inaccurate. It has P1 priority because maintaining reliable book records is important, so it is selected for this sprint even though its severity is lower than Issues #17 and #18.

### Issue #19 — Empty Author

Issue #19 has medium severity because the system accepts an incomplete book record, but it does not crash the application or prevent other library operations. Although it has P1 priority, it is deferred this sprint because the team has limited capacity and is focusing first on the higher-impact crash and invalid inventory defects.

### Issue #16 — Search Input with Spaces

Issue #16 has medium severity because a valid book search can fail when the input contains leading or trailing spaces. However, it does not crash the application or create invalid inventory data, so it is deferred for this sprint.

## Sprint Decision

The three issues selected for fixing during this sprint are:

1. Issue #17 — `findBook()` crashes when search title is null.
2. Issue #18 — `addBook()` accepts negative copy values.
3. Issue #15 — `addBook()` allows duplicate book records.

The following two issues will not be fixed during this sprint:

- Issue #19 — `addBook()` accepts an empty author.
- Issue #16 — `findBook()` fails when search input contains spaces.

These two issues are deferred because their impact is lower than the three selected defects, allowing the sprint to focus on higher-impact problems first.