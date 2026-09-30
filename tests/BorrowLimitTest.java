
import LibrarySystem.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BorrowLimitTest {

    // Class-scope setup: use @BeforeAll when the setup is expensive
    // and can safely be shared by all tests in this class.
    @BeforeEach
    void setUp() {
        // Clear previous test data
        BookManager.books.clear();
        MemberManager.members.clear();
    }

    @Test
    void memberWith3BooksCanBorrow4thBook() {

        // Create member
        MemberManager.addMember("M001", "Ali");

        // Create 4 books
        BookManager.addBook("Book 1", "Author 1", "1234567890123", 1);
        BookManager.addBook("Book 2", "Author 2", "1234567890124", 1);
        BookManager.addBook("Book 3", "Author 3", "1234567890125", 1);
        BookManager.addBook("Book 4", "Author 4", "1234567890126", 1);

        // Borrow 3 books
        MemberManager.borrowBook("M001", "1234567890123");
        MemberManager.borrowBook("M001", "1234567890124");
        MemberManager.borrowBook("M001", "1234567890125");

        // 4th book should be allowed
        assertDoesNotThrow(() ->
            MemberManager.borrowBook("M001", "1234567890126")
        );
    }

    @Test
    void memberWith5BooksCannotBorrow6thBook() {

        // Create member
        MemberManager.addMember("M002", "Ahmed");

        // Create 6 books
        BookManager.addBook("Book 1", "Author 1", "2234567890123", 1);
        BookManager.addBook("Book 2", "Author 2", "2234567890124", 1);
        BookManager.addBook("Book 3", "Author 3", "2234567890125", 1);
        BookManager.addBook("Book 4", "Author 4", "2234567890126", 1);
        BookManager.addBook("Book 5", "Author 5", "2234567890127", 1);
        BookManager.addBook("Book 6", "Author 6", "2234567890128", 1);

        // Borrow 5 books
        MemberManager.borrowBook("M002", "2234567890123");
        MemberManager.borrowBook("M002", "2234567890124");
        MemberManager.borrowBook("M002", "2234567890125");
        MemberManager.borrowBook("M002", "2234567890126");
        MemberManager.borrowBook("M002", "2234567890127");

        // 6th book should be rejected
        assertThrows(
            IllegalArgumentException.class,
            () -> MemberManager.borrowBook("M002", "2234567890128")
        );
    }

    @Test
    void memberWith4BooksCanBorrow5thBook() {

        // Create member
        MemberManager.addMember("M003", "Hassan");

        // Create 5 books
        BookManager.addBook("Book 1", "Author 1", "3234567890123", 1);
        BookManager.addBook("Book 2", "Author 2", "3234567890124", 1);
        BookManager.addBook("Book 3", "Author 3", "3234567890125", 1);
        BookManager.addBook("Book 4", "Author 4", "3234567890126", 1);
        BookManager.addBook("Book 5", "Author 5", "3234567890127", 1);

        // Borrow 4 books
        MemberManager.borrowBook("M003", "3234567890123");
        MemberManager.borrowBook("M003", "3234567890124");
        MemberManager.borrowBook("M003", "3234567890125");
        MemberManager.borrowBook("M003", "3234567890126");

        // 5th book should be allowed
        assertDoesNotThrow(() ->
            MemberManager.borrowBook("M003", "3234567890127")
        );
    }

}