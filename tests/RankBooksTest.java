import LibrarySystem.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RankBooksTest {

    @BeforeEach
    void setUp() {
        BookManager.books.clear();
        MemberManager.members.clear();
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

    @Test
    void booksWithSameBorrowCountShouldBeSortedAlphabetically() {

        BookManager.addBook(
            "Java Programming",
            "John Smith",
            "1234567890123",
            1
        );

        BookManager.addBook(
            "Algorithms",
            "Robert Martin",
            "1234567890124",
            1
        );

        MemberManager.addMember("M001", "Ali");

        // Both books are borrowed once.
        borrowAndReturn("M001", "1234567890123");
        borrowAndReturn("M001", "1234567890124");

        var rankedBooks = BookManager.rankBooksByPopularity();

        assertEquals(
            "Algorithms",
            rankedBooks.get(0).getbookName()
        );

        assertEquals(
            "Java Programming",
            rankedBooks.get(1).getbookName()
        );
    }


    @Test
    void neverBorrowedBookShouldBeRankedAfterBorrowedBooks() {

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

        // Software Engineering → borrowed 2 times
        borrowAndReturn("M001", "1234567890125");
        borrowAndReturn("M001", "1234567890125");

        // Database Systems → never borrowed

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

    @Test
    void repeatedBorrowingShouldAccumulatePopularity() {

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

        MemberManager.addMember("M001", "Ali");

        // Java Programming → borrowed 3 times
        borrowAndReturn("M001", "1234567890123");
        borrowAndReturn("M001", "1234567890123");
        borrowAndReturn("M001", "1234567890123");

        // Database Systems → borrowed 1 time
        borrowAndReturn("M001", "1234567890124");

        var rankedBooks = BookManager.rankBooksByPopularity();

        assertEquals(
            "Java Programming",
            rankedBooks.get(0).getbookName()
        );

        assertEquals(
            "Database Systems",
            rankedBooks.get(1).getbookName()
        );
    }

    @Test
    void failedBorrowShouldNotIncreasePopularity() {

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

        MemberManager.addMember("M001", "Ali");
        MemberManager.addMember("M002", "Ahmed");

        // Java Programming is successfully borrowed once.
        MemberManager.borrowBook("M001", "1234567890123");

        // M002 tries to borrow the same unavailable book.
        assertThrows(
            IllegalArgumentException.class,
            () -> MemberManager.borrowBook(
                "M002",
                "1234567890123"
            )
        );

        // Database Systems is successfully borrowed once.
        borrowAndReturn("M002", "1234567890124");

        // Return Java Programming so the test leaves clean state.
        MemberManager.returnBook("M001", "1234567890123");

        var rankedBooks = BookManager.rankBooksByPopularity();

        assertEquals(
            "Java Programming",
            rankedBooks.get(0).getbookName()
        );

        assertEquals(
            "Database Systems",
            rankedBooks.get(1).getbookName()
        );
    }
}