package LibrarySystem.api;

import LibrarySystem.BookManager;
import LibrarySystem.FineCalculator;
import LibrarySystem.MemberManager;
import LibrarySystem.book;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class LibraryController {

    // ---------------------------------------------------------
    // POST /books
    // ---------------------------------------------------------
    @PostMapping("/books")
    public ResponseEntity<?> addBook(@RequestBody Map<String, Object> body) {

        String title = text(body, "title");
        String isbn = text(body, "isbn");
        String author = text(body, "author");

        Object copiesObj = body.get("totalCopies");

        if (!(copiesObj instanceof Integer)) {
            throw new ApiException(
                    HttpStatus.BAD_REQUEST,
                    "totalCopies must be an integer"
            );
        }

        int totalCopies = (Integer) copiesObj;

        if (totalCopies < 1) {
            throw new ApiException(
                    HttpStatus.BAD_REQUEST,
                    "totalCopies must be at least 1"
            );
        }

        try {
            BookManager.addBook(title, author, isbn, totalCopies);
        } catch (IllegalArgumentException e) {

            if ("ISBN already exists".equals(e.getMessage())) {
                throw new ApiException(
                        HttpStatus.CONFLICT,
                        e.getMessage()
                );
            }

            throw new ApiException(
                    HttpStatus.BAD_REQUEST,
                    e.getMessage()
            );
        }

        book added = BookManager.findBookByISBN(isbn);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(toResponse(added));
    }


    // ---------------------------------------------------------
    // GET /books
    // ---------------------------------------------------------
    @GetMapping("/books")
    public ResponseEntity<List<BookResponse>> getBooks() {

        List<BookResponse> books = BookManager.books.stream()
                .map(this::toResponse)
                .toList();

        return ResponseEntity.ok(books);
    }


    // ---------------------------------------------------------
    // GET /books/{isbn}
    // ---------------------------------------------------------
    @GetMapping("/books/{isbn}")
    public ResponseEntity<BookResponse> getBook(
            @PathVariable String isbn) {

        book found = BookManager.findBookByISBN(isbn);

        if (found == null) {
            throw new ApiException(
                    HttpStatus.NOT_FOUND,
                    "Book not found"
            );
        }

        return ResponseEntity.ok(toResponse(found));
    }


    // ---------------------------------------------------------
    // POST /members/{id}/borrow
    // ---------------------------------------------------------
    @PostMapping("/members/{id}/borrow")
    public ResponseEntity<Map<String, Object>> borrowBook(
            @PathVariable String id,
            @RequestBody Map<String, Object> body) {

        String isbn = text(body, "isbn");

        // API contract requires unknown ISBN to return 404.
        if (BookManager.findBookByISBN(isbn) == null) {
            throw new ApiException(
                    HttpStatus.NOT_FOUND,
                    "Book not found"
            );
        }

        /*
         * Lab 12 API contract:
         * Members are created implicitly on first borrow.
         */
        if (MemberManager.findMember(id) == null) {
            MemberManager.addMember(id, id);
        }

        try {
            MemberManager.borrowBook(id, isbn);
        } catch (IllegalArgumentException e) {
            throw new ApiException(
                    HttpStatus.BAD_REQUEST,
                    e.getMessage()
            );
        }

        book borrowed = BookManager.findBookByISBN(isbn);

        return ResponseEntity.ok(
                Map.of(
                        "isbn", isbn,
                        "availableCopies", borrowed.getavailableCopies()
                )
        );
    }


    // ---------------------------------------------------------
    // POST /members/{id}/return
    // ---------------------------------------------------------
    @PostMapping("/members/{id}/return")
    public ResponseEntity<Map<String, Object>> returnBook(
            @PathVariable String id,
            @RequestBody Map<String, Object> body) {

        String isbn = text(body, "isbn");

        // Unknown ISBN -> 404 according to API contract.
        if (BookManager.findBookByISBN(isbn) == null) {
            throw new ApiException(
                    HttpStatus.NOT_FOUND,
                    "Book not found"
            );
        }

        try {
            MemberManager.returnBook(id, isbn);
        } catch (IllegalArgumentException e) {
            throw new ApiException(
                    HttpStatus.BAD_REQUEST,
                    e.getMessage()
            );
        }

        book returned = BookManager.findBookByISBN(isbn);

        return ResponseEntity.ok(
                Map.of(
                        "isbn", isbn,
                        "availableCopies", returned.getavailableCopies()
                )
        );
    }


    // ---------------------------------------------------------
    // GET /members/{id}/fines?daysOverdue=n
    // ---------------------------------------------------------
    @GetMapping("/members/{id}/fines")
    public ResponseEntity<Map<String, Object>> getFineTier(
            @PathVariable String id,
            @RequestParam int daysOverdue) {

        String tier;

        try {
            tier = FineCalculator.fineTier(daysOverdue);
        } catch (IllegalArgumentException e) {
            throw new ApiException(
                    HttpStatus.BAD_REQUEST,
                    e.getMessage()
            );
        }

        return ResponseEntity.ok(
                Map.of(
                        "memberId", id,
                        "daysOverdue", daysOverdue,
                        "tier", tier
                )
        );
    }


    // ---------------------------------------------------------
    // Helpers
    // ---------------------------------------------------------
    private String text(Map<String, Object> body, String key) {

        Object value = body.get(key);

        if (!(value instanceof String) || ((String) value).isBlank()) {
            throw new ApiException(
                    HttpStatus.BAD_REQUEST,
                    key + " is required"
            );
        }

        return ((String) value).trim();
    }


    private BookResponse toResponse(book b) {

        return new BookResponse(
                b.getbookName(),
                b.getISBN(),
                b.getauthor(),
                b.getavailableCopies()
        );
    }
}