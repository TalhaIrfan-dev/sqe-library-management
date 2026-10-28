# Code Review Checklist — LibraryHub

## 1. Correctness

- [ ] Does the code implement the required functionality correctly?
- [ ] Are boundary conditions and important edge cases handled correctly?

## 2. Tests

- [ ] Are appropriate tests provided for the changed functionality?
- [ ] Do the tests verify behavior rather than only checking implementation details?

## 3. Readability

- [ ] Are variable, method, and class names clear and meaningful?
- [ ] Is the code easy to understand, with unnecessary complexity and duplication avoided?

## 4. Design

- [ ] Does the change follow the existing project structure and responsibilities?
- [ ] Is functionality placed in the appropriate class without unnecessary coupling or duplication?

## 5. Risk

- [ ] Could the change introduce security, performance, or reliability problems?
- [ ] Could the change break existing functionality or introduce backward-compatibility issues?

---

## Review Notes

Use specific and actionable comments when an issue is found. Explain what the problem is, why it matters, and what change should be considered.

## Lessons Learned

### Most Valuable Issue Found
The most valuable issue was the incorrect percentile calculation. The original implementation did not correctly represent the full percentile range and did not properly handle the single-member case. The review identified this logic and required both a corrected calculation and boundary-case tests.

### Checklist Improvement
Add the following checkpoint for future reviews:

- [ ] Are calculations and formulas verified for boundary cases and representative input values?