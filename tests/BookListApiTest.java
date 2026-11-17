import LibrarySystem.*;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Test;

class BookListApiTest extends ApiTestBase {

    @Test
    void getAllBooksSuccessfully() {

        addBook("9780132350884", 2);
        addBook("9780321356680", 1);

        given()
        .when()
            .get("/books")
        .then()
            // Layer 1: status + header
            .statusCode(200)
            .contentType(JSON)

            // Layer 2 + 3: response shape + content
            .body("size()", equalTo(2))
            .body("[0].title", equalTo("Clean Code"))
            .body("[0].isbn", equalTo("9780132350884"))
            .body("[0].author", equalTo("R. Martin"))
            .body("[0].availableCopies", equalTo(2))
            .body("[1].availableCopies", equalTo(1));
    }
}