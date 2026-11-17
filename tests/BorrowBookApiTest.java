import LibrarySystem.*;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.equalTo;

import java.util.Map;
import org.junit.jupiter.api.Test;

class BorrowBookApiTest extends ApiTestBase {

    @Test
    void borrowBookSuccessfully() {

        addBook("9780132350884", 2);

        given()
            .contentType(JSON)
            .body(Map.of("isbn", "9780132350884"))
        .when()
            .post("/members/501/borrow")
        .then()
            // Layer 1: status + header
            .statusCode(200)
            .contentType(JSON)

            // Layer 2 + 3: response shape + content
            .body("isbn", equalTo("9780132350884"))
            .body("availableCopies", equalTo(1));
    }
}