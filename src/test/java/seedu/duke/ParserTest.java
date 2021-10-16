package seedu.duke;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void programLogic_hasFrontWhitespace_success() {
        Parser parser = new Parser();

        String input = " bye\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setIn(in);
        parser.programLogic();
        assertEquals("", out.toString());
    }

    @Test
    public void removeCommandWord_provideValidInputForAdd_success() {
        Parser parser = new Parser();
        String input = "add to pay /def haraimasu";
        assertEquals("to pay /def haraimasu", parser.removeCommandWord(input, 3));
    }

    @Test
    public void removeCommandWord_provideValidInputForDelete_success() {
        Parser parser = new Parser();
        String input = "delete 1";
        assertEquals("1", parser.removeCommandWord(input, 6));
    }
}
