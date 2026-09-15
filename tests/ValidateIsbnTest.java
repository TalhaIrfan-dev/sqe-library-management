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

        @Test
    void isbnWith11DigitsShouldBeRejected() {
        assertFalse(
            BookManager.validateIsbn("12345678901")
        );
    }

    @Test
    void isbnWith12DigitsShouldBeRejected() {
        assertFalse(
            BookManager.validateIsbn("123456789012")
        );
    }

    @Test
    void isbnWith13DigitsShouldBeAcceptedAtBoundary() {
        assertTrue(
            BookManager.validateIsbn("1234567890123")
        );
    }

    @Test
    void isbnWith14DigitsShouldBeRejected() {
        assertFalse(
            BookManager.validateIsbn("12345678901234")
        );
    }

    @Test
    void isbnWith15DigitsShouldBeRejected() {
        assertFalse(
            BookManager.validateIsbn("123456789012345")
        );
    }
}