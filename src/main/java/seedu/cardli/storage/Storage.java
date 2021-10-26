package seedu.cardli.storage;

import seedu.cardli.flashcard.Deck;
import seedu.cardli.testing.AnswerList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Storage {

    /**
     * Specified file path to save task list.
     */
    private static final String CARDS_FILEPATH = "data/Cards_CardLI.txt";
    private static final String TESTS_FILEPATH = "data/Tests_CardLi.txt";
    File cardsFile;
    File testsFile;

    public Storage() {
        try {
            this.cardsFile = new File(CARDS_FILEPATH);
            this.testsFile = new File(TESTS_FILEPATH);

            // create new directory and file if they do not exist
            if (!cardsFile.exists()) {
                cardsFile.getParentFile().mkdirs();
                cardsFile.createNewFile();
            }
            if (!testsFile.exists()) {
                testsFile.getParentFile().mkdirs();
                testsFile.createNewFile();
            }
        } catch (IOException e) {
            //TODO: fill catch block
        }
    }

    public <T> void writeToFile(ArrayList<T> arrayList, boolean saveCards) {
        try {
            // instantiate FileWriter object to overwrite specified text file
            FileWriter fileWriter;

            if (saveCards) {
                fileWriter = new FileWriter(CARDS_FILEPATH, false);
            } else {
                fileWriter = new FileWriter(TESTS_FILEPATH, false);
            }

            int count = arrayList.size();
            fileWriter.write(Integer.toString(count) + '\n');

            for (int i = 0; i < count; i++) {
                fileWriter.write(arrayList.get(i).toString());
            }

            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Something went wrong while saving the flashcards to file...");
        }
    }

    public ArrayList<Deck> readCardsFromFile() {
        ArrayList<Deck> decks = new ArrayList<>();

        try {
            // instantiate scanner to read file contents
            Scanner s = new Scanner(this.cardsFile);

            int decksCount = Integer.parseInt(s.nextLine());

            for (int i = 0; i < decksCount; i++) {
                decks.add(parseDeck(s));
            }
        } catch (FileNotFoundException e) {
            //TODO: how to skip this exception?
        } catch (NoSuchElementException e) {
            //TODO: handle empty save file
        } finally {
            return decks;
        }
    }

    public ArrayList<AnswerList> readTestsFromFile() {
        ArrayList<AnswerList> testHistory = new ArrayList<>();

        try {
            // instantiate scanner to read file contents
            Scanner s = new Scanner(this.testsFile);

            int answerListsCount = Integer.parseInt(s.nextLine());

            for (int i = 0; i < answerListsCount; i++) {
                testHistory.add(parseAnswerList(s));
            }
        } catch (FileNotFoundException e) {
            //TODO: how to skip this exception?
        } catch (NoSuchElementException e) {
            //TODO: handle empty save file
        } finally {
            return testHistory;
        }
    }

    private AnswerList parseAnswerList(Scanner s) {
        AnswerList newAnswerList = new AnswerList(parseDeck(s));

        int answersCount = Integer.parseInt(s.nextLine());

        for (int j = 0; j < answersCount; j++) {
            String newLine = s.nextLine();
            String[] newLineArgs = newLine.split(" \\| ");
            newAnswerList.addAnswer(newLineArgs[0],
                    Integer.parseInt(newLineArgs[1]));
        }
        newAnswerList.setUserScore(Integer.parseInt(s.nextLine()));
        return newAnswerList;
    }

    private Deck parseDeck(Scanner s) {
        String deckName = s.nextLine();
        Deck newDeck = new Deck(deckName);

        int cardsCount = Integer.parseInt(s.nextLine());

        for (int j = 0; j < cardsCount; j++) {
            String newLine = s.nextLine();
            String[] newLineArgs = newLine.split(" \\| ");
            newDeck.addFlashCard(newLineArgs[0], newLineArgs[1],
                    Integer.parseInt(newLineArgs[2]),
                    Integer.parseInt(newLineArgs[3]));
        }
        return newDeck;
    }
}