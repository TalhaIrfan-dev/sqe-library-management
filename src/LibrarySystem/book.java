package LibrarySystem;
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

        public String getbookName()
        {return bookName;}
        public String getauthor()
        {return author;}
        public String getISBN()
        {return ISBN;}  
        public int gettotalCopies()
        {return totalCopies;}
        public int getavailableCopies()
        {return availableCopies;}

}