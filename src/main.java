import java.util.ArrayList;
import java.util.List;

class main
{
    static List<Book> books = new ArrayList<>();

    /**
     * Adds a new book to the library after validating its details.
     *
     * @param title the title of the book
     * @param author the author of the book
     * @param copies the number of available copies
     * @throws IllegalArgumentException if title or author is empty,
     *                                  or copies is zero or negative
     */
    static void addBook(String title, String author, int copies)
    {
         if (title == null || title.trim().isEmpty())
        {
            throw new IllegalArgumentException("Book title cannot be empty");
        }

        if (author == null || author.trim().isEmpty())
        {
            throw new IllegalArgumentException("Book author cannot be empty");
        }

        if (copies <= 0)
        {
            throw new IllegalArgumentException("Number of copies must be greater than zero");
        }

        Book book = new Book(title, author, copies);
        books.add(book);
    }
}