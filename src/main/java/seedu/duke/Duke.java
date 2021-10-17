package seedu.duke;

import seedu.duke.parser.Parser;
import seedu.duke.ui.CardLiUi;

/**
 * Represents CardLI application.
 */
public class Duke {
    private static final CardLiUi ui = new CardLiUi();

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        ui.printGreetingMessage();
        boolean exitProgram = false;

        while (!exitProgram) {
            String input = ui.getUserMessage();
            Parser.parseCommand(input);

            if (Parser.getCommand(input).equals("bye")) {
                exitProgram = true;
            }
        }
        ui.printByeMessage();
    }
}
