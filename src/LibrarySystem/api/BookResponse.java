package LibrarySystem.api;

public class BookResponse {

    private final String title;
    private final String isbn;
    private final String author;
    private final int availableCopies;

    public BookResponse(
            String title,
            String isbn,
            String author,
            int availableCopies
    ) {
        this.title = title;
        this.isbn = isbn;
        this.author = author;
        this.availableCopies = availableCopies;
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getAuthor() {
        return author;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }
}