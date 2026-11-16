import LibrarySystem.BookManager;
import LibrarySystem.book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FindBookByTitleTest {

    @BeforeEach
    void setUp() {
        BookManager.books.clear();

        BookManager.addBook(
            "Clean Code",
            "Robert C. Martin",
            "9780132350884",
            5
        );

        BookManager.addBook(
            "Effective Java",
            "Joshua Bloch",
            "9780134685991",
            3
        );
    }

    @Test
    void findBookByTitleShouldFindMatchingBook() {

        book result = BookManager.findBookByTitle("Clean Code");

        assertNotNull(result);
        assertEquals("Clean Code", result.getbookName());
        assertEquals("Robert C. Martin", result.getauthor());
    }

    @Test
    void findBookByTitleShouldIgnoreCaseAndWhitespace() {

        book result = BookManager.findBookByTitle("  clean code  ");

        assertNotNull(result);
        assertEquals("Clean Code", result.getbookName());
    }

    @Test
    void findBookByTitleShouldReturnNullForInvalidOrMissingTitle() {

        assertNull(BookManager.findBookByTitle(null));
        assertNull(BookManager.findBookByTitle(""));
        assertNull(BookManager.findBookByTitle("   "));
        assertNull(BookManager.findBookByTitle("Unknown Book"));
    }
}