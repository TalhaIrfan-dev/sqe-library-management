import LibrarySystem.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RankBooksTest {

    @BeforeEach
    void setUp() {
        BookManager.books.clear();
    }

    @Test
    void emptyCatalogShouldReturnEmptyList() {
        assertTrue(
            BookManager.rankBooksByPopularity().isEmpty()
        );
    }

    @Test
    void singleBookShouldReturnThatBook() {

        BookManager.addBook(
            "Java Programming",
            "John Smith",
            "1234567890123",
            3
        );

        var rankedBooks = BookManager.rankBooksByPopularity();

        assertEquals(1, rankedBooks.size());
        assertEquals(
            "Java Programming",
            rankedBooks.get(0).getbookName()
        );
    }
}