import LibrarySystem.BookManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BookManagerValidationTest {

    @BeforeEach
    void setUp() {
        BookManager.books.clear();
    }

    @Test
    void addBookShouldRejectInvalidBookDetails() {

        IllegalArgumentException titleException = assertThrows(
            IllegalArgumentException.class,
            () -> BookManager.addBook(
                null,
                "Author",
                "1234567890123",
                1
            )
        );
        assertEquals("Book title cannot be empty", titleException.getMessage());

        IllegalArgumentException authorException = assertThrows(
            IllegalArgumentException.class,
            () -> BookManager.addBook(
                "Book",
                null,
                "1234567890123",
                1
            )
        );
        assertEquals("Book author cannot be empty", authorException.getMessage());

        IllegalArgumentException isbnException = assertThrows(
            IllegalArgumentException.class,
            () -> BookManager.addBook(
                "Book",
                "Author",
                null,
                1
            )
        );
        assertEquals("ISBN cannot be empty", isbnException.getMessage());

        IllegalArgumentException formatException = assertThrows(
            IllegalArgumentException.class,
            () -> BookManager.addBook(
                "Book",
                "Author",
                "INVALID",
                1
            )
        );
        assertEquals(
            "ISBN must contain exactly 13 digits",
            formatException.getMessage()
        );

        IllegalArgumentException copiesException = assertThrows(
            IllegalArgumentException.class,
            () -> BookManager.addBook(
                "Book",
                "Author",
                "1234567890123",
                0
            )
        );
        assertEquals(
            "Number of copies must be greater than zero",
            copiesException.getMessage()
        );
    }

    @Test
    void addBookShouldRejectDuplicateIsbn() {

        BookManager.addBook(
            "Java Programming",
            "John Smith",
            "1234567890123",
            2
        );

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> BookManager.addBook(
                "Another Book",
                "Another Author",
                "1234567890123",
                3
            )
        );

        assertEquals(
            "ISBN already exists",
            exception.getMessage()
        );

        assertEquals(1, BookManager.books.size());
    }
}