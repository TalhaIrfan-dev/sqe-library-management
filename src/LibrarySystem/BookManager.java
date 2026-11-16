package LibrarySystem;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookManager 
{
    public static List<book> books = new ArrayList<>();   // public should be removed
    public static List<String> reservations = new ArrayList<>();

    /**
     * Adds a new book to the library after validating its details.
     *
     * @param title the title of the book
     * @param author the author of the book
     * @param ISBN the ISBN of the book
     * @param copies the number of available copies
     * @throws IllegalArgumentException if title, author, or ISBN is empty,
     *                                  if ISBN already exists,
     *                                  or if copies is zero or negative
     */
    public static void addBook(String title, String author, String ISBN, int copies)
    {
         if (title == null || title.trim().isEmpty())
        {
            throw new IllegalArgumentException("Book title cannot be empty");
        }

        if (author == null || author.trim().isEmpty())
        {
            throw new IllegalArgumentException("Book author cannot be empty");
        }

        if (ISBN == null || ISBN.trim().isEmpty())
        {
            throw new IllegalArgumentException("ISBN cannot be empty");
        }
        String isbn = ISBN.trim();

        if (!validateIsbn(isbn)){
            throw new IllegalArgumentException("ISBN must contain exactly 13 digits");
        }

        if (copies <= 0)
        {
            throw new IllegalArgumentException("Number of copies must be greater than zero");
        }

         for (book b : books)
        {
            if (b.ISBN.equalsIgnoreCase(ISBN.trim()))
            {
                throw new IllegalArgumentException("ISBN already exists");
            }
        }

        book book = new book(title.trim(), author.trim(), ISBN.trim(), copies);
        books.add(book);
    }

    public static boolean validateIsbn(String ISBN)
    {
        if (ISBN == null)
        {
            return false;
        }

        return ISBN.matches("\\d{13}");
    }


    /**
     * Finds a book in the library by its title.
     *
     * @param title the title of the book to search for
     * @return the matching book, or null if no book is found
     */
    public static book findBookByTitle(String title)
    {
        if (title == null || title.trim().isEmpty())
        {
            return null;
        }

        String t = title.trim();

        for (book b : books)
        {
            if (b.bookName.equalsIgnoreCase(t))
            {
                return b;
            }
        }

        return null;
    }

    public static book findBookByISBN(String ISBN)
    {
        if (ISBN == null || ISBN.trim().isEmpty())
        {
            return null;
        }

        String id = ISBN.trim();

        for (book b : books)
        {
            if (b.ISBN.equalsIgnoreCase(id))
            {
                return b;
            }
        }

        return null;
    }

    public static int totalAvailableCopies()
    {
        int total = 0;

        for (book b : books)
        {
            total += b.getavailableCopies();
        }

        return total;
    }

   public static void exportCatalog(String path)
    {
        try (FileWriter writer = new FileWriter(path))
        {
            for (book b : books)
            {
                writer.write(
                    "Title: " + b.getbookName()
                    + ", Author: " + b.getauthor()
                    + ", ISBN: " + b.getISBN()
                    + ", Available: " + b.getavailableCopies()
                    + ", Total: " + b.gettotalCopies()
                    + System.lineSeparator()
                );
            }
        }
        catch (IOException e)
        {
            throw new LibraryIOException(
                "Failed to export library catalog.",
                e
            );
        }
    }

    public static List<book> rankBooksByPopularity()
    {
        List<book> rankedBooks = new ArrayList<>(books);

        rankedBooks.sort(BookManager::compareByPopularity);

        return rankedBooks;
    }

    private static int compareByPopularity(book b1, book b2)
    {
        int borrowComparison =
            Integer.compare(b2.borrowCount, b1.borrowCount);

        if (borrowComparison != 0)
        {
            return borrowComparison;
        }

        return b1.bookName.compareToIgnoreCase(b2.bookName);
    }

    public static void reserveBook(String memberId, String ISBN)
    {
        if (memberId == null || memberId.trim().isEmpty())
        {
            throw new IllegalArgumentException("Member ID cannot be empty");
        }

        if (ISBN == null || ISBN.trim().isEmpty())
        {
            throw new IllegalArgumentException("ISBN cannot be empty");
        }

        book selectedBook = findBookByISBN(ISBN);
        int reservationLimit = 3;

        if (selectedBook == null)
        {
            throw new IllegalArgumentException("Book not found");
        }

        if (selectedBook.availableCopies > 3)
        {
            throw new IllegalArgumentException("Book is currently available");
        }

        String normalizedMemberId = memberId.trim();
        String normalizedISBN = ISBN.trim();

        String duplicateReservation = normalizedMemberId + ":" + normalizedISBN;
        
        String reservation = memberId.trim() + ":" + ISBN.trim();
        reservations.add(reservation);
    }

    public static void cancelReservation(String memberId, String ISBN)
    {
        if (memberId == null || memberId.trim().isEmpty())
        {
            throw new IllegalArgumentException("Member ID cannot be empty");
        }

        if (ISBN == null || ISBN.trim().isEmpty())
        {
            throw new IllegalArgumentException("ISBN cannot be empty");
        }

        if (ISBN == null || ISBN.trim().isEmpty())
        {
            throw new IllegalArgumentException("ISBN cannot be empty");
        }

        String reservation = memberId.trim() + ":" + ISBN.trim();

        if (!reservations.contains(reservation))
        {
            throw new IllegalArgumentException("Reservation not found");
        }

        reservations.remove(reservation);
    }

    public static int getReservationCount(String ISBN)
    {
        int count = 0;

        for (String reservation : reservations)
        {
            if (reservation.endsWith(":" + ISBN.trim()) == true)
            {
                count++;
            }
        }

        if (count >= 0)
        {
            return count;
        }
        else
        {
            return 0;
        }
    }
    
}
