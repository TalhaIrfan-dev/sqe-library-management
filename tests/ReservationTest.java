import LibrarySystem.BookManager;
import LibrarySystem.MemberManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ReservationTest {

    @BeforeEach
    void setUp() {
        BookManager.books.clear();
        BookManager.reservations.clear();
        MemberManager.members.clear();
    }

    @Test
    void reserveBookShouldCreateReservationWhenBookIsUnavailable() {
        BookManager.addBook(
            "Java Programming",
            "John Smith",
            "1234567890123",
            1
        );

        MemberManager.addMember("M001", "Ali");
        MemberManager.borrowBook("M001", "1234567890123");

        BookManager.reserveBook("M002", "1234567890123");

        assertEquals(
            1,
            BookManager.getReservationCount("1234567890123")
        );
    }

    @Test
    void cancelReservationShouldRemoveExistingReservation() {
        BookManager.addBook(
            "Java Programming",
            "John Smith",
            "1234567890123",
            1
        );

        MemberManager.addMember("M001", "Ali");
        MemberManager.borrowBook("M001", "1234567890123");

        BookManager.reserveBook("M002", "1234567890123");

        BookManager.cancelReservation(
            "M002",
            "1234567890123"
        );

        assertEquals(
            0,
            BookManager.getReservationCount("1234567890123")
        );
    }

    @Test
    void generateReservationReportShouldShowReservations() {
        BookManager.addBook(
            "Java Programming",
            "John Smith",
            "1234567890123",
            1
        );

        MemberManager.addMember("M001", "Ali");
        MemberManager.borrowBook("M001", "1234567890123");

        BookManager.reserveBook("M002", "1234567890123");
        BookManager.reserveBook("M003", "1234567890123");

        String report = BookManager.generateReservationReport();

        assertTrue(report.contains(
            "Reservation: M002:1234567890123"
        ));
        assertTrue(report.contains(
            "Reservation: M003:1234567890123"
        ));
        assertTrue(report.contains(
            "Total reservations: 2"
        ));
    }

    @Test
    void generateReservationReportShouldShowZeroWhenEmpty() {
        String report = BookManager.generateReservationReport();

        assertEquals(
            "Total reservations: 0\n",
            report
        );
    }

    @Test
    void reserveBookShouldRejectAvailableBook() {
        BookManager.addBook(
            "Java Programming",
            "John Smith",
            "1234567890123",
            1
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> BookManager.reserveBook(
                "M001",
                "1234567890123"
            )
        );
    }

    @Test
    void cancelReservationShouldRejectMissingReservation() {
        assertThrows(
            IllegalArgumentException.class,
            () -> BookManager.cancelReservation(
                "M001",
                "1234567890123"
            )
        );
    }
}