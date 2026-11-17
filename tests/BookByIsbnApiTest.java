import LibrarySystem.*;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Test;

class BookByIsbnApiTest extends ApiTestBase {

    @Test
    void getBookByIsbnSuccessfully() {

        addBook("9780132350884", 2);

        given()
        .when()
            .get("/books/9780132350884")
        .then()
            // Layer 1: status + header
            .statusCode(200)
            .contentType(JSON)

            // Layer 2 + 3: response shape + content
            .body("title", equalTo("Clean Code"))
            .body("isbn", equalTo("9780132350884"))
            .body("author", equalTo("R. Martin"))
            .body("availableCopies", equalTo(2));
    }
}