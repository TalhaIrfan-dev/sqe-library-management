import LibrarySystem.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class BorrowBookParameterizedTest {

    @BeforeEach
    void setUp() {
        // Start every parameterized test case with a clean state.
        BookManager.books.clear();
        MemberManager.members.clear();
    }

    @ParameterizedTest
    @MethodSource("borrowBookCases")
    void borrowBookScenarios(
            String testCase,
            String memberId,
            String isbn,
            boolean shouldPass
    ) {

        switch (testCase) {

            case "valid borrow":
                MemberManager.addMember("M001", "Ali");

                BookManager.addBook(
                        "Java Programming",
                        "John Smith",
                        "1234567890123",
                        1
                );
                break;

            case "member not found":
                BookManager.addBook(
                        "Java Programming",
                        "John Smith",
                        "1234567890123",
                        1
                );
                break;

            case "book not found":
                MemberManager.addMember("M001", "Ali");

                BookManager.addBook(
                        "Java Programming",
                        "John Smith",
                        "1234567890123",
                        1
                );
                break;

            case "empty ISBN":
                MemberManager.addMember("M001", "Ali");

                BookManager.addBook(
                        "Java Programming",
                        "John Smith",
                        "1234567890123",
                        1
                );
                break;

            case "no copies available":
                MemberManager.addMember("M001", "Ali");
                MemberManager.addMember("M002", "Ahmed");

                BookManager.addBook(
                        "Java Programming",
                        "John Smith",
                        "1234567890123",
                        1
                );

                // M002 borrows the only available copy.
                MemberManager.borrowBook(
                        "M002",
                        "1234567890123"
                );
                break;

            case "borrowing limit reached":
                MemberManager.addMember("M001", "Ali");

                // Give the member five borrowed books.
                for (int i = 1; i <= 6; i++) {

                    BookManager.addBook(
                            "Book " + i,
                            "Author " + i,
                            "223456789012" + i,
                            1
                    );
                }

                // Borrow the first five books.
                for (int i = 1; i <= 5; i++) {

                    MemberManager.borrowBook(
                            "M001",
                            "223456789012" + i
                    );
                }
                break;
        }

        if (shouldPass) {
            assertDoesNotThrow(() ->
                    MemberManager.borrowBook(memberId, isbn)
            );

        } else {
            assertThrows(
                    IllegalArgumentException.class,
                    () -> MemberManager.borrowBook(memberId, isbn)
            );
        }
    }

    static Stream<Arguments> borrowBookCases() {
        return Stream.of(
                // 1. Valid borrowing
                Arguments.of(
                        "valid borrow",
                        "M001",
                        "1234567890123",
                        true
                ),
                // 2. Member does not exist
                Arguments.of(
                        "member not found",
                        "M999",
                        "1234567890123",
                        false
                ),
                // 3. Book does not exist
                Arguments.of(
                        "book not found",
                        "M001",
                        "9999999999999",
                        false
                ),

                // 4. ISBN is empty
                Arguments.of(
                        "empty ISBN",
                        "M001",
                        "",
                        false
                ),

                // 5. No copies are available
                Arguments.of(
                        "no copies available",
                        "M001",
                        "1234567890123",
                        false
                ),

                // 6. Member has reached borrowing limit
                Arguments.of(
                        "borrowing limit reached",
                        "M001",
                        "2234567890126",
                        false
                )
        );
    }
}