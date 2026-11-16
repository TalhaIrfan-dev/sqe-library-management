import LibrarySystem.BookManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ReservationValidationTest {

    @BeforeEach
    void setUp() {
        BookManager.books.clear();
        BookManager.reservations.clear();
    }

    @Test
    void reserveBookShouldRejectInvalidMemberIds() {

        assertThrows(
            IllegalArgumentException.class,
            () -> BookManager.reserveBook(
                null,
                "1234567890123"
            )
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> BookManager.reserveBook(
                "",
                "1234567890123"
            )
        );
    }

    @Test
    void reserveBookShouldRejectInvalidIsbn() {

        assertThrows(
            IllegalArgumentException.class,
            () -> BookManager.reserveBook(
                "M001",
                null
            )
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> BookManager.reserveBook(
                "M001",
                ""
            )
        );
    }

    @Test
    void reserveBookShouldRejectUnknownBook() {

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> BookManager.reserveBook(
                "M001",
                "1234567890123"
            )
        );

        assertEquals("Book not found", exception.getMessage());
    }

    @Test
    void cancelReservationShouldRejectInvalidMemberIds() {

        assertThrows(
            IllegalArgumentException.class,
            () -> BookManager.cancelReservation(
                null,
                "1234567890123"
            )
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> BookManager.cancelReservation(
                "",
                "1234567890123"
            )
        );
    }

    @Test
    void cancelReservationShouldRejectInvalidIsbn() {

        assertThrows(
            IllegalArgumentException.class,
            () -> BookManager.cancelReservation(
                "M001",
                null
            )
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> BookManager.cancelReservation(
                "M001",
                ""
            )
        );
    }

    @Test
    void validateIsbnShouldRejectNull() {
        assertFalse(BookManager.validateIsbn(null));
    }

    @Test
    void findBookByIsbnShouldRejectInvalidInput() {

        assertNull(BookManager.findBookByISBN(null));
        assertNull(BookManager.findBookByISBN(""));
        assertNull(BookManager.findBookByISBN("   "));
    }

    @Test
    void reservationCountShouldIgnoreReservationsForDifferentBooks() {

        BookManager.reservations.add("M001:1234567890123");

        assertEquals(
            0,
            BookManager.getReservationCount("9999999999999")
        );
    }
}