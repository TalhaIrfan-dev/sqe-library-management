import java.util.ArrayList;
import java.util.List;

class main
{
    static List<Book> books = new ArrayList<>();

    static void addBook(String title, String author, int copies)
    {
        Book book = new Book(title, author, copies);
        books.add(book);
    }
}