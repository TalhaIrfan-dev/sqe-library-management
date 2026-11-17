import LibrarySystem.*;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Test;

class FinesApiTest extends ApiTestBase {

    @Test
    void getMemberFinesSuccessfully() {

        given()
        .when()
            .get("/members/501/fines?daysOverdue=15")
        .then()
            // Layer 1: status + header
            .statusCode(200)
            .contentType(JSON)

            // Layer 2 + 3: response shape + content
            .body("memberId", equalTo("501"))
            .body("daysOverdue", equalTo(15))
            .body("tier", equalTo("High"));
    }
}