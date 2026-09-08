package tests;

import LibrarySystem.*;

public class BorrowLimitTest {

    public static void main(String[] args) {

        testMemberWith3Books();
        testMemberWith5Books();

        System.out.println("\nBorrowing limit EP tests completed!");
    }

    static void testMemberWith3Books() {

        // Clear previous test data
        BookManager.books.clear();
        MemberManager.members.clear();

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

        // Attempt to borrow 4th book
        try {
            MemberManager.borrowBook("M001", "1234567890126");

            System.out.println(
                "TC-BL-01 PASS - Member with 3 books can borrow 4th book"
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                "TC-BL-01 FAIL - 4th book was rejected"
            );
        }
    }

    static void testMemberWith5Books() {

        // Clear previous test data
        BookManager.books.clear();
        MemberManager.members.clear();

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

        // Attempt to borrow 6th book
        try {
            MemberManager.borrowBook("M002", "2234567890128");

            System.out.println(
                "TC-BL-02 FAIL - 6th book was allowed"
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                "TC-BL-02 PASS - 6th book rejected"
            );
        }
    }
}