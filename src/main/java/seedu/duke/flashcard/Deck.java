package seedu.duke.flashcard;

import seedu.duke.exceptions.CardLiException;
import seedu.duke.exceptions.FieldEmptyException;
import seedu.duke.exceptions.NoSlashException;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implements the list of added flashcards.
 */
public class Deck {

    public ArrayList<FlashCard> cards = new ArrayList<FlashCard>();
    private String name;
    private static final Logger logger = Logger.getLogger(Deck.class.getName());

    public Deck(String name) {
        this.name = name;
    }

    public Deck() {
        this.name = "Untitled";
    }

    public String getName() {
        return name;
    }

    public void setDeckName(String input) {
        this.name = input;
    }

    public FlashCard getCard(int index) {
        assert getDeckSize() > 0;
        assert (index >= 0 && index < getDeckSize());
        return cards.get(index);
    }

    public int getDeckSize() {
        return cards.size();
    }

    public void printNoSlashFoundError() {
        System.out.println("\tRemember that a command must contain \"/bac\"!");
    }

    public void printFieldEmptyError() {
        System.out.println("\tRemember that both sides of the flashcard must be filled in!");
    }

    private void printInvalidAddFormat() {
        System.out.println("\tHey, the command you printed is invalid.");
        System.out.println("\tThe correct command format to add a flash card is as follows:");
        System.out.println("\tadd <deck index> /fro <word on front> /bac <word on back>");
    }

    private void printDoesNotExistError() {
        System.out.println("\tThe card you are trying to delete does not exist.");
    }

    private void printEmptyDescriptionError() {
        System.out.println("\tCan't delete a card with no description!");
    }

    private void printNewFlashCard(String front, String back) {
        //System.out.println("\tAdded card:");
        printCardInfo(front, back);
    }

    private void printCardInfo(String front, String back) {
        System.out.println("\tFront: " + front);
        System.out.println("\tBack: " + back);
        if (getDeckSize() == 1) {
            System.out.println("\tYou have " + getDeckSize() + " card in your card deck.");
        } else {
            System.out.println("\tYou have " + getDeckSize() + " cards in your card deck.");
        }
    }

    private void printDeletedFlashCardMessage(String front, String back) {
        //System.out.println("\tDeleted card:");
        printCardInfo(front, back);
    }

    public void prepareToAddFlashCard(String input) {
        try {
            String[] flashCardWords = trimStrings(input);
            addFlashCard(flashCardWords[0], flashCardWords[1]);
            printNewFlashCard(flashCardWords[0], flashCardWords[1]);
        } catch (NoSlashException e) {
            printInvalidAddFormat();
            printNoSlashFoundError();
        } catch (FieldEmptyException e) {
            printInvalidAddFormat();
            printFieldEmptyError();
        }
    }

    /**
     * Deletes the flash card given by the user's input.
     * The card will only be deleted if the input matches
     * exactly with FlashCard.front.
     *
     * @param input user's input in its entirety
     */
    public void prepareToDeleteFlashCard(String input) {
        logger.entering(Deck.class.getName(), "prepareToDeleteFlashCard");
        logger.setLevel(Level.WARNING);
        logger.log(Level.INFO, "Starting delete process");
        try {
            deleteFlashCard(input);
        } catch (ArrayIndexOutOfBoundsException e) {
            printEmptyDescriptionError();
            logger.log(Level.SEVERE, "Empty field error, no description found after command term");
        } catch (CardLiException e) {
            printDoesNotExistError();
            logger.log(Level.SEVERE, "CardLi error, query card does not exist");
        }
        logger.log(Level.INFO, "End of delete process");
        logger.exiting(Deck.class.getName(), "prepareToDeleteFlashCard");
    }

    /**
     * Deletes the flashcard with the given input.
     *
     * @param input description of the card to delete
     * @throws CardLiException if card does not exist
     */
    public void deleteFlashCard(String input) throws CardLiException {
        logger.setLevel(Level.WARNING);
        if (cards.isEmpty()) {
            throw new CardLiException();
        }
        assert getDeckSize() > 0 : "cards.size() should be greater than 0";
        logger.log(Level.INFO, "Detecting the type of input, ie word/phrase or index");
        if (!isInteger(input)) {
            deleteFlashCardByDescription(input);
        } else {
            deleteFlashCardByIndex(input);
        }
    }

    /**
     * Deletes the flashcard with the given index.
     *
     * @param index user's input (index of the card to be deleted)
     * @throws CardLiException if the index of the card exceeds the number of flashcards in cards
     *                         or index of card is less than 1
     */
    private void deleteFlashCardByIndex(String index) throws CardLiException {
        logger.setLevel(Level.WARNING);
        int indexToBeRemoved = Integer.parseInt(index) - 1;
        if (!((indexToBeRemoved < getDeckSize()) && (indexToBeRemoved >= 0))) {
            throw new CardLiException();
        }
        assert getDeckSize() > 0 : "cards.size() should be greater than 0";
        logger.log(Level.INFO, "Detecting the type of input, ie word/phrase or index");

        FlashCard card = cards.get(indexToBeRemoved);
        cards.remove(card);
        printDeletedFlashCardMessage(card.getFront(), card.getBack());
    }

    /**
     * Deletes the flashcard with the given description.
     *
     * @param description user's input (front of the card to be deleted)
     * @throws CardLiException if none of the front of the cards match the description input by user
     */
    private void deleteFlashCardByDescription(String description) throws CardLiException {
        assert getDeckSize() > 0 : "cards.size() should be greater than 0";
        for (int i = 0; i < getDeckSize(); i++) {
            FlashCard card = cards.get(i);
            if (hasExactCard(description, card)) {
                cards.remove(card);
                printDeletedFlashCardMessage(card.getFront(), card.getBack());
                return;
            }
        }
        throw new CardLiException();
    }

    private boolean hasExactCard(String query, FlashCard card) {
        return card.getFront().equalsIgnoreCase(query);
    }

    /**
     * Checks if the given input is an integer or not.
     *
     * @param input input given by user
     * @return true if input is an integer, false otherwise
     */
    private boolean isInteger(String input) {
        for (int i = 0; i < input.length(); i += 1) {
            if (!Character.isDigit(input.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public String[] trimStrings(String input) throws FieldEmptyException, NoSlashException {
        int slashIndex = input.indexOf("/bac");
        String[] flashCardWords = new String[2];
        if (slashIndex < 3) {
            throw new NoSlashException();
        }
        flashCardWords[0] = input.substring(0, slashIndex - 1).trim();
        flashCardWords[1] = input.substring(slashIndex + 4).trim();
        if (flashCardWords[0].isEmpty() || flashCardWords[1].isEmpty()) {
            throw new FieldEmptyException();
        }
        return flashCardWords;
    }

    public void addFlashCard(String front, String back) {
        cards.add(new FlashCard(front, back));
    }

    public void addFlashCard(String front, String back, int userScore, int totalScore) {
        cards.add(new FlashCard(front, back, userScore, totalScore));
    }

    //getter for index of a flashcard
    public int getCardIndex(FlashCard card) {
        return cards.indexOf(card);
    }

    public void viewAllFlashCards() {
        if (getDeckSize() > 0) {
            for (int i = 0; i < getDeckSize(); i++) {
                System.out.println("Card " + (i + 1) + ":");
                FlashCard card = cards.get(i);
                card.viewFlashCard();
            }
        } else {
            System.out.println("This deck has no cards.");
        }
    }

    @Override
    public String toString() {
        String cardsString = "";
        int cardsCount = getDeckSize();

        for (int i = 0; i < cardsCount; i++) {
            cardsString += cards.get(i);
        }

        return getName() + '\n'
                + getDeckSize() + '\n'
                + cardsString + '\n';
    }
}