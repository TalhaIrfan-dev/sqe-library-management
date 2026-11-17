import LibrarySystem.*;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.equalTo;

import java.util.Map;
import org.junit.jupiter.api.Test;

class BookApiTest extends ApiTestBase {

    @Test
    void createBookSuccessfully() {

        given()
            .contentType(JSON)
            .body(Map.of(
                "title", "Clean Code",
                "isbn", "9780132350884",
                "author", "R. Martin",
                "totalCopies", 2
            ))
        .when()
            .post("/books")
        .then()
            .statusCode(201)
            .contentType(JSON)
            .body("title", equalTo("Clean Code"))
            .body("isbn", equalTo("9780132350884"))
            .body("author", equalTo("R. Martin"))
            .body("availableCopies", equalTo(2));
    }
}