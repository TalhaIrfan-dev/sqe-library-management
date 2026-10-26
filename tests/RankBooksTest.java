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
}