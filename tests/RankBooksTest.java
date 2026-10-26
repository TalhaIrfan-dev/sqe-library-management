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

    @Test
    void booksShouldBeRankedByBorrowCountDescending() {

        BookManager.addBook(
            "Java Programming",
            "John Smith",
            "1234567890123",
            1
        );

        BookManager.addBook(
            "Database Systems",
            "Robert Martin",
            "1234567890124",
            1
        );

        BookManager.addBook(
            "Software Engineering",
            "Ian Sommerville",
            "1234567890125",
            1
        );

        MemberManager.addMember("M001", "Ali");

        // Java Programming → borrowed 3 times
        borrowAndReturn("M001", "1234567890123");
        borrowAndReturn("M001", "1234567890123");
        borrowAndReturn("M001", "1234567890123");

        // Database Systems → borrowed 1 time
        borrowAndReturn("M001", "1234567890124");

        // Software Engineering → borrowed 2 times
        borrowAndReturn("M001", "1234567890125");
        borrowAndReturn("M001", "1234567890125");

        var rankedBooks = BookManager.rankBooksByPopularity();

        assertEquals(
            "Java Programming",
            rankedBooks.get(0).getbookName()
        );

        assertEquals(
            "Software Engineering",
            rankedBooks.get(1).getbookName()
        );

        assertEquals(
            "Database Systems",
            rankedBooks.get(2).getbookName()
        );
    }

    private void borrowAndReturn(String memberId, String isbn) {
        MemberManager.borrowBook(memberId, isbn);
        MemberManager.returnBook(memberId, isbn);
    }
}