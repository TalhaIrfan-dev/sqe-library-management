package LibrarySystem;
import java.util.ArrayList;
import java.util.List;

public class BookManager 
{
    public static List<book> books = new ArrayList<>();   // public should be removed

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

        if (!isbn.matches("\\d{10}|\\d{13}"))
            throw new IllegalArgumentException("Invalid ISBN format");

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

        // // Duplicate title validation retained from Lab 3
        for (book b : books)
        {
            if (b.bookName.equalsIgnoreCase(title.trim()))
            {
                throw new IllegalArgumentException("Book already exists");
            }
        }

        book book = new book(title.trim(), author.trim(), ISBN.trim(), copies);
        books.add(book);
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
    
}
