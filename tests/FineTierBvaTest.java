package tests;

import LibrarySystem.FineCalculator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class FineTierBvaTest {

    @ParameterizedTest
    @CsvSource({
        "-1, EXCEPTION",

        "0, None",
        "1, Low",
        "2, Low",

        "7, Low",
        "8, Medium",
        "9, Medium",

        "14, Medium",
        "15, High",
        "16, High",

        "30, High",
        "31, Severe",
        "32, Severe"
    })
    void testFineTierBoundaries(int daysOverdue, String expected) {

        if (expected.equals("EXCEPTION")) {

            assertThrows(
                IllegalArgumentException.class,
                () -> FineCalculator.fineTier(daysOverdue)
            );

        } else {

            assertEquals(
                expected,
                FineCalculator.fineTier(daysOverdue)
            );
        }
    }
}