import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {

    @Test
    void mainDemoProducesExpectedOutput() throws Exception {

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        try {
            System.setOut(new PrintStream(output, true, StandardCharsets.UTF_8));

            Class<?> mainClass = Class.forName("LibrarySystem.main");

            Constructor<?> constructor = mainClass.getDeclaredConstructor();
            constructor.setAccessible(true);
            constructor.newInstance();

            Method mainMethod = mainClass.getDeclaredMethod(
                    "main",
                    String[].class
            );

            mainMethod.setAccessible(true);
            mainMethod.invoke(null, (Object) new String[0]);

        } finally {
            System.setOut(originalOut);
        }

        String expected =
                "TC-10: 0.0" + System.lineSeparator()
                + "TC-11: 140.0" + System.lineSeparator()
                + "TC-12: 200.0" + System.lineSeparator()
                + "None" + System.lineSeparator()
                + "Low" + System.lineSeparator()
                + "Medium" + System.lineSeparator()
                + "High" + System.lineSeparator()
                + "Severe" + System.lineSeparator();

        assertEquals(expected, output.toString(StandardCharsets.UTF_8));
    }
}