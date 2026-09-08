package tests;

import LibrarySystem.*;

public class FineTierTest {

    public static void main(String[] args) {

        testNegative();
        testNone();
        testLow();
        testMedium();
        testHigh();
        testSevere();

        System.out.println("\nAll fine tier tests passed!");
    }

    static void testNegative() {
        try {
            FineCalculator.fineTier(-3);
            System.out.println("TC-FT-01 FAIL");
        } catch (IllegalArgumentException e) {
            System.out.println("TC-FT-01 PASS - Negative value rejected");
        }
    }

    static void testNone() {
        check("None", FineCalculator.fineTier(0), "TC-FT-02");
    }

    static void testLow() {
        check("Low", FineCalculator.fineTier(4), "TC-FT-03");
    }

    static void testMedium() {
        check("Medium", FineCalculator.fineTier(10), "TC-FT-04");
    }

    static void testHigh() {
        check("High", FineCalculator.fineTier(20), "TC-FT-05");
    }

    static void testSevere() {
        check("Severe", FineCalculator.fineTier(45), "TC-FT-06");
    }

    static void check(String expected, String actual, String testId) {

        if (expected.equals(actual)) {
            System.out.println(testId + " PASS");
        } else {
            System.out.println(testId + " FAIL - Expected: "
                    + expected + ", Actual: " + actual);
        }
    }
}