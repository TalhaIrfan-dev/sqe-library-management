package tests;
class test
{
    public static void main(String[] args)
    {
        System.out.println("Testing Library Management System");

    /*    
         // 1. Clear old data
        BookManager.books.clear();
        MemberManager.members.clear();

        // 2. Add first book and members
        BookManager.addBook("Clean Code", "Robert Martin", "ISBN001", 3);
        MemberManager.addMember("M001", "Ali");
        MemberManager.addMember("M002", "Ahmed");

        // 3. Show initial copies
        System.out.println("Before borrowing:");
        System.out.println("Total copies: " + BookManager.findBookByISBN("ISBN001").totalCopies);
        System.out.println("Available copies: " + BookManager.findBookByISBN("ISBN001").availableCopies);

        // 4. Ali borrows Clean Code
        MemberManager.borrowBook("M001", "ISBN001");

        System.out.println("\nAfter borrowing:");
        System.out.println("Total copies: " + BookManager.findBookByISBN("ISBN001").totalCopies);
        System.out.println("Available copies: " + BookManager.findBookByISBN("ISBN001").availableCopies);

        // 5. Ali tries to borrow the same book again
        try
        {
            MemberManager.borrowBook("M001", "ISBN001");
            System.out.println("ERROR: Duplicate borrowing was allowed");
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("\nDuplicate borrow rejected: " + e.getMessage());
        }

        // 6. Ahmed tries to return Ali's book
        try
        {
            MemberManager.returnBook("M002", "ISBN001");
            System.out.println("ERROR: Wrong member was allowed to return the book");
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("\nWrong member return rejected: " + e.getMessage());
        }

        // 7. Ali returns the book
        MemberManager.returnBook("M001", "ISBN001");

        System.out.println("\nAfter returning:");
        System.out.println("Total copies: " + BookManager.findBookByISBN("ISBN001").totalCopies);
        System.out.println("Available copies: " + BookManager.findBookByISBN("ISBN001").availableCopies);


        // 8. ADD THIS NEW BOOK FOR THE "NO COPIES" TEST
        BookManager.addBook("Java Basics", "John Smith", "ISBN002", 1);
        MemberManager.addMember("M003", "Usman");

        // 9. Usman borrows the only available copy
        MemberManager.borrowBook("M003", "ISBN002");

        // 10. Add another member
        MemberManager.addMember("M004", "Hamza");

        // 11. Hamza tries to borrow when no copies are available
        try
        {
            MemberManager.borrowBook("M004", "ISBN002");
            System.out.println("ERROR: Borrowing was allowed when no copies were available");
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("\nNo copies test passed: " + e.getMessage());
        }


        // Borrowing limit test

        BookManager.addBook("Book One", "Author One", "ISBN003", 1);
        BookManager.addBook("Book Two", "Author Two", "ISBN004", 1);
        BookManager.addBook("Book Three", "Author Three", "ISBN005", 1);
        BookManager.addBook("Book Four", "Author Four", "ISBN006", 1);

        MemberManager.addMember("M005", "Bilal");

        MemberManager.borrowBook("M005", "ISBN003");
        MemberManager.borrowBook("M005", "ISBN004");
        MemberManager.borrowBook("M005", "ISBN005");

        System.out.println("\nBooks borrowed by Bilal: " + MemberManager.findMember("M005").borrowedBooks.size());

        try
        {
            MemberManager.borrowBook("M005", "ISBN006");
            System.out.println("ERROR: Borrowing limit was not enforced");
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("Borrowing limit test passed: " + e.getMessage());
        }
    */
    }
}