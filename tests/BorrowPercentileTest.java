import LibrarySystem.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BorrowPercentileTest {

    @BeforeEach
    void setUp() {
        MemberManager.members.clear();
        BookManager.books.clear();
    }

    @Test
    void highestBorrowingMemberShouldHave100Percentile() {

        MemberManager.addMember("M001", "Ali");
        MemberManager.addMember("M002", "Ahmed");
        MemberManager.addMember("M003", "Sara");

        // Ali will borrow 3 books
        BookManager.addBook("Book 1", "Author 1", "1111111111111", 1);
        BookManager.addBook("Book 2", "Author 2", "2222222222222", 1);
        BookManager.addBook("Book 3", "Author 3", "3333333333333", 1);

        // Ahmed will borrow 2 books
        BookManager.addBook("Book 4", "Author 4", "4444444444444", 1);
        BookManager.addBook("Book 5", "Author 5", "5555555555555", 1);

        // Sara will borrow 1 book
        BookManager.addBook("Book 6", "Author 6", "6666666666666", 1);

        MemberManager.borrowBook("M001", "1111111111111");
        MemberManager.borrowBook("M001", "2222222222222");
        MemberManager.borrowBook("M001", "3333333333333");

        MemberManager.borrowBook("M002", "4444444444444");
        MemberManager.borrowBook("M002", "5555555555555");

        MemberManager.borrowBook("M003", "6666666666666");

        assertEquals(
            100.0,
            MemberManager.borrowPercentile("M001")
        );
    }

    @Test
    void singleMemberShouldHave100Percentile() {

        MemberManager.addMember("M001", "Ali");

        assertEquals(
            100.0,
            MemberManager.borrowPercentile("M001")
        );
    }
}