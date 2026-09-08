package tests;

import LibrarySystem.FineCalculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FineTierTest {

    @Test
    void negativeValueShouldBeRejected() {
        assertThrows(
            IllegalArgumentException.class,
            () -> FineCalculator.fineTier(-3)
        );
    }

    @Test
    void zeroDaysShouldReturnNone() {
        assertEquals("None", FineCalculator.fineTier(0));
    }

    @Test
    void lowTierShouldBeReturned() {
        assertEquals("Low", FineCalculator.fineTier(4));
    }

    @Test
    void mediumTierShouldBeReturned() {
        assertEquals("Medium", FineCalculator.fineTier(10));
    }

    @Test
    void highTierShouldBeReturned() {
        assertEquals("High", FineCalculator.fineTier(20));
    }

    @Test
    void severeTierShouldBeReturned() {
        assertEquals("Severe", FineCalculator.fineTier(45));
    }
}