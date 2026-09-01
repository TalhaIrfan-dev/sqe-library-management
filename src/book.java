public class book
{
        String bookName;
        String author;
        String ISBN;
        int totalCopies;
        int availableCopies;

        book(String title, String author, String ISBN, int copies)
        {
            this.bookName = title;
            this.author = author;
            this.ISBN = ISBN;
            this.totalCopies = copies;
            this.availableCopies = copies;
        }
}