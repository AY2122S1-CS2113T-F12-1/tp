package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FlashCardCategoryManagerTest {

    @Test
    public void trimToPass_enterValidAddLine_success() {
        String input = "1 /car good morning /def ohayo";
        assertEquals("good morning /def ohayo", FlashCardCategoryManager.trimToPass(input));

    }
}