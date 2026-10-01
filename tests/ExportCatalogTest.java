import LibrarySystem.BookManager;
import LibrarySystem.LibraryIOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class ExportCatalogTest {

    @BeforeEach
    void setUp() {
        BookManager.books.clear();
    }

    @Test
    void exportCatalogShouldWriteExpectedBookInformation() throws Exception {

        BookManager.addBook(
            "Java Programming",
            "John Smith",
            "1234567890123",
            5
        );

        try (MockedConstruction<FileWriter> mocked =
                 mockConstruction(FileWriter.class)) {

            BookManager.exportCatalog("catalog.txt");

            FileWriter writer = mocked.constructed().get(0);

            verify(writer).write(
                "Title: Java Programming"
                + ", Author: John Smith"
                + ", ISBN: 1234567890123"
                + ", Available: 5"
                + ", Total: 5"
                + System.lineSeparator()
            );
        }
    }

    @Test
    void exportCatalogShouldThrowLibraryIOExceptionWhenWritingFails() {

        BookManager.addBook(
            "Java Programming",
            "John Smith",
            "1234567890123",
            5
        );

        try (MockedConstruction<FileWriter> mocked =
                 mockConstruction(
                     FileWriter.class,
                     (writer, context) -> {
                         try {
                             doThrow(new IOException("Write failed"))
                                 .when(writer)
                                 .write(anyString());
                         }
                         catch (IOException e) {
                             // Configuration only
                         }
                     })) {

            assertThrows(
                LibraryIOException.class,
                () -> BookManager.exportCatalog("catalog.txt")
            );
        }
    }
}