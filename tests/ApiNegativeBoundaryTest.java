import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.containsString;



import java.util.Map;

import org.junit.jupiter.api.Test;

class ApiNegativeBoundaryTest extends ApiTestBase {

    @Test
    void unknownIsbnReturns404() {
        given()
        .when()
            .get("/books/9999999999999")
        .then()
            .statusCode(404)
            .contentType(JSON)
            .body("error", notNullValue());
    }

    @Test
    void borrowUnavailableBookReturns400() {

        addBook("1111111111111", 1);

        // First borrow makes availableCopies = 0
        given()
            .contentType(JSON)
            .body(Map.of("isbn", "1111111111111"))
        .when()
            .post("/members/501/borrow")
        .then()
            .statusCode(200);

        // Second borrow should fail
        given()
            .contentType(JSON)
            .body(Map.of("isbn", "1111111111111"))
        .when()
            .post("/members/501/borrow")
        .then()
            .statusCode(400);
    }

    @Test
    void missingTitleReturns400() {

        given()
            .contentType(JSON)
            .body(Map.of(
                "isbn", "2222222222222",
                "author", "Test Author",
                "totalCopies", 2
            ))
        .when()
            .post("/books")
        .then()
            .statusCode(400)
            .contentType(JSON)
            .body("error", org.hamcrest.Matchers.containsString("title"));
    }

    @Test
    void totalCopiesBoundaryIsHandledCorrectly() {

        // totalCopies = 0 → 400
        given()
            .contentType(JSON)
            .body(Map.of(
                "title", "Boundary Book",
                "isbn", "3333333333333",
                "author", "Test Author",
                "totalCopies", 0
            ))
        .when()
            .post("/books")
        .then()
            .statusCode(400);

        // totalCopies = 1 → 201
        given()
            .contentType(JSON)
            .body(Map.of(
                "title", "Boundary Book",
                "isbn", "4444444444444",
                "author", "Test Author",
                "totalCopies", 1
            ))
        .when()
            .post("/books")
        .then()
            .statusCode(201);
    }

    @Test
    void duplicateIsbnReturns409() {

        addBook("5555555555555", 2);

        given()
            .contentType(JSON)
            .body(Map.of(
                "title", "Duplicate Book",
                "isbn", "5555555555555",
                "author", "Test Author",
                "totalCopies", 2
            ))
        .when()
            .post("/books")
        .then()
            .statusCode(409);
    }

    @Test
    void memberCanBorrowFiveBooksButSixthFails() {

        for (int i = 1; i <= 6; i++) {
            addBook("666666666666" + i, 1);
        }

        for (int i = 1; i <= 5; i++) {
            given()
                .contentType(JSON)
                .body(Map.of("isbn", "666666666666" + i))
            .when()
                .post("/members/501/borrow")
            .then()
                .statusCode(200);
        }

        given()
            .contentType(JSON)
            .body(Map.of("isbn", "6666666666666"))
        .when()
            .post("/members/501/borrow")
        .then()
            .statusCode(400);
    }

    @Test
    void returnBookNotOnLoanReturns400() {

        addBook("7777777777777", 1);

        given()
            .contentType(JSON)
            .body(Map.of("isbn", "7777777777777"))
        .when()
            .post("/members/501/return")
        .then()
            .statusCode(400);
    }

    @Test
    void malformedJsonReturns400() {

        given()
            .contentType(JSON)
            .body("{\"title\":\"Broken Book\",")
        .when()
            .post("/books")
        .then()
            .statusCode(400);
    }

    @Test
    void fineTierBoundariesAreCorrect() {

        int[] days = {0, 1, 7, 8, 14, 15, 30, 31};
        String[] tiers = {
            "None",
            "Low",
            "Low",
            "Medium",
            "Medium",
            "High",
            "High",
            "Severe"
        };

        for (int i = 0; i < days.length; i++) {

            given()
            .when()
                .get("/members/501/fines?daysOverdue=" + days[i])
            .then()
                .statusCode(200)
                .contentType(JSON)
                .body("daysOverdue", equalTo(days[i]))
                .body("tier", equalTo(tiers[i]));
        }

        // Negative value → 400
        given()
        .when()
            .get("/members/501/fines?daysOverdue=-1")
        .then()
            .statusCode(400);
    }
}