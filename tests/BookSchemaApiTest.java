import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.lessThan;

import org.junit.jupiter.api.Test;

class BookSchemaApiTest extends ApiTestBase {

    @Test
    void bookListMatchesSchema() {

        addBook("8888888888888", 2);

        given()
        .when()
            .get("/books")
        .then()
            .statusCode(200)
            .contentType(JSON)
            .body(matchesJsonSchemaInClasspath(
                "schemas/book-list.schema.json"
            ));
    }

    @Test
    void bookListRespondsWithinOneSecond() {

        addBook("9999999999999", 2);

        given()
        .when()
            .get("/books")
        .then()
            .statusCode(200)
            .time(lessThan(1000L));
    }

}