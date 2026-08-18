import java.util.ArrayList;
import java.util.List;

class main
{
    static List<Book> books = new ArrayList<>();

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