package seedu.duke;

import seedu.duke.exceptions.FieldEmptyException;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.logging.Level;



/**
 * Implements the test function.
 */
public class TestManager {

    public static ArrayList<Answer> answersResponse = new ArrayList<Answer>();
    private static int answersCount = 0;
    private static Logger logger = Logger.getLogger(TestManager.class.getName());

    /**
     * Goes through all the flashcards and stores the user's responses into answersResponse ArrayList.
     */
    public static void testAllCardsInOrder(FlashCardManager fcm) {
        logger.setLevel(Level.WARNING);
        logger.log(Level.INFO, "starting test");

        for (FlashCard question : fcm.cards) {
            logger.log(Level.INFO, "starting to test a new card");
            int questionNumber = fcm.getCardIndex(question);
            printDividerLine();
            System.out.println("Question " + String.valueOf(questionNumber + 1) + ":");
            //display front of card so that user can understand question
            System.out.println(fcm.getFrontOfCard(questionNumber));
            System.out.println("Your answer?");
            //get user's answer to the card shown(currently assume user inputs only his/her answer)
            //later version to include question number and parsing to allow for randomised testing
            logger.log(Level.INFO, "getting user's answer to the question");
            String userResponse = getInput();
            try {
                parseUserResponse(userResponse);
            } catch (FieldEmptyException e) {
                logger.log(Level.WARNING, "No user input");
                userResponse = "NO ANSWER GIVEN :(";
                printAnswerEmptyError();
            }
            logger.log(Level.INFO, "Saving answer");
            addAnswer(userResponse, questionNumber);
            assert !answersResponse.isEmpty();
            assert answersCount > 0;
            logger.log(Level.INFO, "Finished this card's testing");
        }

        printDividerLine();
        logger.log(Level.INFO, "Finished test");
        //let user know testing is over
        System.out.println("Test Over");
        viewTestResult(fcm);
    }

    public static String parseUserResponse(String userResponse) throws FieldEmptyException {
        String input = userResponse;
        if (userResponse.isEmpty()) {
            throw new FieldEmptyException();
        }
        return input;
    }

    public static int getAnswerIndex(Answer answer) {
        return answersResponse.indexOf(answer);
    }

    //hopefully there is a ui class which i can put this in and access from this class
    public static String getInput() {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        return input;
    }

    /**
     * Saves a new user answer to the current list of user answers.
     *
     * @param answer            String representation of user's answer
     * @param questionIndex     Question number for the question that the answer answers
     */
    public static void addAnswer(String answer, int questionIndex) {
        answersResponse.add(new Answer(answer, questionIndex));
        answersCount += 1;
    }

    /**
     * Prints user's answer for a specified question to the system output.
     *
     * @param answerIndex   Specified question number
     */
    public static void viewAnswer(int answerIndex) {
        System.out.println(answersResponse.get(answerIndex).getAnswer());
    }

    /**
     * Prints results of test to system output.
     */
    private static void viewTestResult(FlashCardManager fcm) {
        logger.setLevel(Level.WARNING);
        int score = 0;
        logger.log(Level.INFO, "starting test check");

        //there must be at least one response to start a test
        assert answersResponse.size() > 0;
        for (Answer response : answersResponse) {
            int responseNumber = getAnswerIndex(response);
            //display front of card so that user can understand question
            printDividerLine();
            System.out.println("Question "
                    + String.valueOf(responseNumber + 1)
                    + ": " + fcm.getFrontOfCard(responseNumber));
            System.out.println("Correct answer: " + fcm.getBackOfCard(responseNumber));
            System.out.print("Your answer: ");
            viewAnswer(responseNumber);

            if (fcm.getBackOfCard(responseNumber).equals(answersResponse.get(responseNumber).getAnswer())) {
                score++;
                printCorrectAnsMessage();
                logger.log(Level.INFO, "user answer is correct");
            } else {
                printWrongAnsMessage();
                logger.log(Level.INFO, "user answer is wrong");
            }
        }
        printDividerLine();
        assert score <= answersCount;
        System.out.println("Your scored " + score + " out of " + answersCount + " for this test");
        logger.log(Level.INFO, "all answers checked, score printed to system output");
    }

    private static void printDividerLine() {
        System.out.println("--------------------------------------------------");
    }

    private static void printCorrectAnsMessage() {
        System.out.println("Well done! You got this question correct");
    }

    private static void printWrongAnsMessage() {
        System.out.println("You got this question wrong! Take note of the correct answer!");
    }

    private static void printAnswerEmptyError() {
        System.out.println("Remember to provide an answer next time! Don't give up!");
    }
}
