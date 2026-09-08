package tests;

import LibrarySystem.BookManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ValidateIsbnTest {

    @Test
    void validIsbnShouldBeAccepted() {
        assertTrue(
            BookManager.validateIsbn("1234567890123")
        );
    }

    @Test
    void emptyIsbnShouldBeRejected() {
        assertFalse(
            BookManager.validateIsbn("")
        );
    }

    @Test
    void shortIsbnShouldBeRejected() {
        assertFalse(
            BookManager.validateIsbn("1234567890")
        );
    }

    @Test
    void lettersAndSymbolsShouldBeRejected() {
        assertFalse(
            BookManager.validateIsbn("1234567890AB!")
        );
    }
}