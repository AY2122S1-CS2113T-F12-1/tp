package seedu.duke;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Deals with the parsing of user input at the command line.
 */
public class Parser {

    private static final Logger logger = Logger.getLogger(Parser.class.getName());

    /**
     * Parses user input at the command line and invokes the necessary follow up actions.
     */
    static void programLogic() {
        logger.setLevel(Level.WARNING);
        Scanner in = new Scanner(System.in);
        boolean exitProgram = false;

        while (!exitProgram) {
            String line = in.nextLine();
            String command = line.trim().split(" ")[0];

            logger.log(Level.INFO, "new user input detected");

            switch (command) {
            case "add":
                String addInput = removeCommandWord(line, 3);
                FlashCardCategoryManager.prepareToAddCardToDeck(addInput);
                logger.log(Level.INFO, "add command parsed and executed");
                break;
            case "addcat":
                String addcatInput = removeCommandWord(line, 6);
                FlashCardCategoryManager.prepareToAddCategory(addcatInput);
                break;
            case "viewcat":
                FlashCardCategoryManager.viewCategories();
                break;
            case "delete":
                String deleteInput = removeCommandWord(line, 6);
                FlashCardCategoryManager.prepareToDeleteCardFromDeck(deleteInput);
                logger.log(Level.INFO, "delete command parsed and executed");
                break;
            case "view":
                String viewInput = removeCommandWord(line, 4);
                FlashCardCategoryManager.viewOneCategory(viewInput);
                logger.log(Level.INFO, "view command parsed and executed");
                break;
            case "test":
                String testInput = removeCommandWord(line, 4);
                FlashCardCategoryManager.testCategory(testInput);
                logger.log(Level.INFO, "test command parsed and executed");
                break;
            case "bye":
                exitProgram = true;
                logger.log(Level.INFO, "bye command parsed and executed, program will terminate");
                break;
            default:
                System.out.println("\tThat's not a command.");
                logger.log(Level.INFO, "command was unrecognised and could not be parsed");
            }
        }
    }

    static String removeCommandWord(String input, int index) {
        return input.substring(index).trim();
    }
}
