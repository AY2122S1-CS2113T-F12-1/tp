package seedu.duke.ui;

import seedu.duke.Answer;
import seedu.duke.Deck;
import seedu.duke.FlashCard;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * TestUi class handles the input and output during a test or a review
 */
public class TestUi {
    private final Scanner in;
    private final PrintStream out;

    public TestUi() {
        this(System.in, System.out);
    }

    public TestUi(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public String getUserMessage() {
        return in.nextLine();
    }

    public void printDividerLine() {
        System.out.println("--------------------------------------------------");
    }

    public void printQuestion(FlashCard question, int questionNumber) {
        System.out.println("Question " + (questionNumber + 1) + ":");
        //display front of card so that user can understand question
        System.out.println(question.getFront());
        System.out.println("Your answer?");
    }

    public void printCorrectAnswer(FlashCard question) {
        System.out.println("Correct answer: " + question.getBack());
    }

    /**
     * Prints user's answer for a specified question to the system output.
     *
     * @param userAnswer the user's answer for the question
     */
    public void printUserAnswer(String userAnswer) {
        System.out.println("Your answer: " + userAnswer);
    }

    public void printTestOver() {
        System.out.println("Test Over");
    }
}