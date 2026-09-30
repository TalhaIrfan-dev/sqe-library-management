import LibrarySystem.BookManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FixtureScopeTest {

    // Class-scope setup: use @BeforeAll when setup is expensive
    // and can safely be shared by all tests in this class
    @BeforeAll
    static void setUpClass() {
        BookManager.books.clear();

        BookManager.addBook(
            "Java Programming",
            "John Smith",
            "1234567890123",
            3
        );

        BookManager.addBook(
            "Software Engineering",
            "Robert Martin",
            "1234567890124",
            2
        );
    }

    @AfterAll
    static void tearDownClass() {
        // Clean up shared test data after all tests finish.
        BookManager.books.clear();
    }

    @Test
    void firstBookShouldExist() {
        assertNotNull(
            BookManager.findBookByISBN("1234567890123")
        );
    }

    @Test
    void secondBookShouldExist() {
        assertNotNull(
            BookManager.findBookByISBN("1234567890124")
        );
    }
}