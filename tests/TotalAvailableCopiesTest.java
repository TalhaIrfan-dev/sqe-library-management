import LibrarySystem.BookManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TotalAvailableCopiesTest {

    @BeforeEach
    void setUp() {
        // Start each test with an empty library.
        BookManager.books.clear();
    }

    @Test
    void emptyLibraryShouldReturnZero() {
        assertEquals(0, BookManager.totalAvailableCopies());
    }

    @Test
    void singleBookShouldReturnAvailableCopies() {
        BookManager.addBook(
            "Java Programming",
            "John Smith",
            "1234567890123",
            5
        );

        assertEquals(5, BookManager.totalAvailableCopies());
    }

    @Test
    void multipleBooksShouldReturnTotalAvailableCopies() {
        BookManager.addBook(
            "Java Programming",
            "John Smith",
            "1234567890123",
            5
        );

        BookManager.addBook(
            "Software Engineering",
            "Robert Martin",
            "1234567890124",
            3
        );

        BookManager.addBook(
            "Clean Code",
            "Robert Martin",
            "1234567890125",
            2
        );

        assertEquals(10, BookManager.totalAvailableCopies());
    }
}