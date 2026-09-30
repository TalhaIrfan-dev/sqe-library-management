import LibrarySystem.FineCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FineTierTest {

    // Function-scope setup: use @BeforeEach when each test
    // needs a fresh or independent setup.
    @BeforeEach
    void setUp() {
        // Shared setup for each test.
        // FineCalculator does not require object initialization,
        // so no additional setup is needed here.
    }

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