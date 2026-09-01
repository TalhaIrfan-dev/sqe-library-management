package tests;

import LibrarySystem.*;

public class LibraryTests
{
    public static void main(String[] args)
    {
         // ============================================================
        // TC-01: Add Book with Valid New ISBN
        // ============================================================
        System.out.println("\nRunning TC-01...");

        BookManager.books.clear();

        try
        {
            BookManager.addBook(
                "Clean Code",
                "Robert C. Martin",
                "9780132350884",
                5
            );

            if (BookManager.books.size() == 1)
            {
                System.out.println("TC-01: PASS");
            }
            else
            {
                System.out.println("TC-01: FAIL");
            }
        }
        catch (Exception e)
        {
            System.out.println("TC-01: FAIL");
        }


        // ============================================================
        // TC-02: Add Book with Duplicate ISBN
        // ============================================================
        System.out.println("\nRunning TC-02...");

        BookManager.books.clear();

        try
        {
            BookManager.addBook(
                "Clean Code",
                "Robert C. Martin",
                "9780132350884",
                5
            );

            try
            {
                BookManager.addBook(
                    "Another Book",
                    "Another Author",
                    "9780132350884",
                    3
                );

                System.out.println("TC-02: FAIL");
            }
            catch (IllegalArgumentException e)
            {
                if (BookManager.books.size() == 1)
                {
                    System.out.println("TC-02: PASS");
                }
                else
                {
                    System.out.println("TC-02: FAIL");
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("TC-02: FAIL");
        }


        // ============================================================
        // TC-03: Add Book with Malformed / Invalid ISBN
        // ============================================================
        System.out.println("\nRunning TC-03...");

        BookManager.books.clear();

        try
        {
            BookManager.addBook(
                "Invalid ISBN Book",
                "Test Author",
                "ABC123",
                2
            );

            System.out.println("TC-03: FAIL");
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("TC-03: PASS");
        }


        // ============================================================
        // TC-04: Borrow Book when Copies are Available
        // ============================================================
        System.out.println("\nRunning TC-04...");

        BookManager.books.clear();
        MemberManager.members.clear();

        try
        {
            BookManager.addBook(
                "Clean Code",
                "Robert C. Martin",
                "9780132350884",
                3
            );

            MemberManager.addMember(
                "M001",
                "Ali"
            );

            MemberManager.borrowBook(
                "M001",
                "9780132350884"
            );

            member m = MemberManager.findMember("M001");
            book b = BookManager.findBookByISBN("9780132350884");

            if (m.getborrowedBooks().size() == 1 &&
                b.getavailableCopies() == 2 &&
                b.gettotalCopies() == 3)
            {
                System.out.println("TC-04: PASS");
            }
            else
            {
                System.out.println("TC-04: FAIL");
            }
        }
        catch (Exception e)
        {
            System.out.println("TC-04: FAIL");
        }


        // ============================================================
        // TC-05: Borrow Book when No Copies are Available
        // ============================================================
        System.out.println("\nRunning TC-05...");

        BookManager.books.clear();
        MemberManager.members.clear();

        try
        {
            BookManager.addBook(
                "Clean Code",
                "Robert C. Martin",
                "9780132350884",
                1
            );

            MemberManager.addMember(
                "M001",
                "Ali"
            );

            MemberManager.borrowBook(
                "M001",
                "9780132350884"
            );

            MemberManager.addMember(
                "M002",
                "Ahmed"
            );

            try
            {
                MemberManager.borrowBook(
                    "M002",
                    "9780132350884"
                );

                System.out.println("TC-05: FAIL");
            }
            catch (IllegalArgumentException e)
            {
                book b = BookManager.findBookByISBN("9780132350884");

                if (b.getavailableCopies() == 0)
                {
                    System.out.println("TC-05: PASS");
                }
                else
                {
                    System.out.println("TC-05: FAIL");
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("TC-05: FAIL");
        }


        // ============================================================
        // TC-06: Return Book Currently on Loan
        // ============================================================
        System.out.println("\nRunning TC-06...");

        BookManager.books.clear();
        MemberManager.members.clear();

        try
        {
            BookManager.addBook(
                "Clean Code",
                "Robert C. Martin",
                "9780132350884",
                2
            );

            MemberManager.addMember(
                "M001",
                "Ali"
            );

            MemberManager.borrowBook(
                "M001",
                "9780132350884"
            );

            MemberManager.returnBook(
                "M001",
                "9780132350884"
            );

            member m = MemberManager.findMember("M001");
            book b = BookManager.findBookByISBN("9780132350884");

            if (m.getborrowedBooks().size() == 0 &&
                b.getavailableCopies() == 2 &&
                b.gettotalCopies() == 2)
            {
                System.out.println("TC-06: PASS");
            }
            else
            {
                System.out.println("TC-06: FAIL");
            }
        }
        catch (Exception e)
        {
            System.out.println("TC-06: FAIL");
        }


        // ============================================================
        // TC-07: Return Book Not on Loan by Member
        // ============================================================
        System.out.println("\nRunning TC-07...");

        BookManager.books.clear();
        MemberManager.members.clear();

        try
        {
            BookManager.addBook(
                "Clean Code",
                "Robert C. Martin",
                "9780132350884",
                2
            );

            MemberManager.addMember(
                "M001",
                "Ali"
            );

            try
            {
                MemberManager.returnBook(
                    "M001",
                    "9780132350884"
                );

                System.out.println("TC-07: FAIL");
            }
            catch (IllegalArgumentException e)
            {
                System.out.println("TC-07: PASS");
            }
        }
        catch (Exception e)
        {
            System.out.println("TC-07: FAIL");
        }


        // ============================================================
        // TC-08: Member Borrowing at Allowed Limit
        // ============================================================
        System.out.println("\nRunning TC-08...");

        BookManager.books.clear();
        MemberManager.members.clear();

        try
        {
            BookManager.addBook("Book 1", "Author 1", "1111111111", 1);
            BookManager.addBook("Book 2", "Author 2", "2222222222", 1);
            BookManager.addBook("Book 3", "Author 3", "3333333333", 1);

            MemberManager.addMember("M001", "Ali");

            MemberManager.borrowBook("M001", "1111111111");
            MemberManager.borrowBook("M001", "2222222222");
            MemberManager.borrowBook("M001", "3333333333");

            member m = MemberManager.findMember("M001");

            if (m.getborrowedBooks().size() == 3)
            {
                System.out.println("TC-08: PASS");
            }
            else
            {
                System.out.println("TC-08: FAIL");
            }
        }
        catch (Exception e)
        {
            System.out.println("TC-08: FAIL");
        }


        // ============================================================
        // TC-09: Member Borrowing Beyond Allowed Limit
        // ============================================================
        System.out.println("\nRunning TC-09...");

        BookManager.books.clear();
        MemberManager.members.clear();

        try
        {
            BookManager.addBook("Book 1", "Author 1", "1111111111", 1);
            BookManager.addBook("Book 2", "Author 2", "2222222222", 1);
            BookManager.addBook("Book 3", "Author 3", "3333333333", 1);
            BookManager.addBook("Book 4", "Author 4", "4444444444", 1);

            MemberManager.addMember("M001", "Ali");

            MemberManager.borrowBook("M001", "1111111111");
            MemberManager.borrowBook("M001", "2222222222");
            MemberManager.borrowBook("M001", "3333333333");

            try
            {
                MemberManager.borrowBook("M001", "4444444444");

                System.out.println("TC-09: FAIL");
            }
            catch (IllegalArgumentException e)
            {
                member m = MemberManager.findMember("M001");

                if (m.getborrowedBooks().size() == 3)
                {
                    System.out.println("TC-09: PASS");
                }
                else
                {
                    System.out.println("TC-09: FAIL");
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("TC-09: FAIL");
        }


        // ============================================================
        // TC-10: Fine Calculation - Zero Days Overdue
        // ============================================================
        System.out.println("\nRunning TC-10...");

        try
        {
            double fine = FineCalculator.calculateFine(0);

            if (fine == 0)
            {
                System.out.println("TC-10: PASS");
            }
            else
            {
                System.out.println("TC-10: FAIL");
            }
        }
        catch (Exception e)
        {
            System.out.println("TC-10: FAIL");
        }


        // ============================================================
        // TC-11: Fine Calculation - Mid-Range Overdue
        // ============================================================
        System.out.println("\nRunning TC-11...");

        try
        {
            double fine = FineCalculator.calculateFine(7);

            if (fine == 140)
            {
                System.out.println("TC-11: PASS");
            }
            else
            {
                System.out.println("TC-11: FAIL");
            }
        }
        catch (Exception e)
        {
            System.out.println("TC-11: FAIL");
        }


        // ============================================================
        // TC-12: Fine Calculation - Overdue Tier Boundary
        // ============================================================
        System.out.println("\nRunning TC-12...");

        try
        {
            double fine = FineCalculator.calculateFine(10);

            if (fine == 200)
            {
                System.out.println("TC-12: PASS");
            }
            else
            {
                System.out.println("TC-12: FAIL");
            }
        }
        catch (Exception e)
        {
            System.out.println("TC-12: FAIL");
        }


        // ============================================================
        // TC-13: Search Book by Title and ISBN
        // ============================================================
        System.out.println("\nRunning TC-13...");

        BookManager.books.clear();

        try
        {
            BookManager.addBook(
                "Clean Code",
                "Robert C. Martin",
                "9780132350884",
                5
            );

            book byTitle = BookManager.findBookByTitle("Clean Code");
            book byISBN = BookManager.findBookByISBN("9780132350884");

            if (byTitle != null &&
                byISBN != null &&
                byTitle == byISBN)
            {
                System.out.println("TC-13: PASS");
            }
            else
            {
                System.out.println("TC-13: FAIL");
            }
        }
        catch (Exception e)
        {
            System.out.println("TC-13: FAIL");
        }


        // ============================================================
        // TC-14: Add Member and Reject Duplicate Member ID
        // ============================================================
        System.out.println("\nRunning TC-14...");

        MemberManager.members.clear();

        try
        {
            MemberManager.addMember(
                "M001",
                "Ali"
            );

            try
            {
                MemberManager.addMember(
                    "M001",
                    "Ahmed"
                );

                System.out.println("TC-14: FAIL");
            }
            catch (IllegalArgumentException e)
            {
                if (MemberManager.members.size() == 1)
                {
                    System.out.println("TC-14: PASS");
                }
                else
                {
                    System.out.println("TC-14: FAIL");
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("TC-14: FAIL");
        }
    }
}