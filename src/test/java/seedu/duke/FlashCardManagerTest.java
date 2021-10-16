package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.CardLiException;
import seedu.duke.exceptions.FieldEmptyException;
import seedu.duke.exceptions.NoSlashException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FlashCardManagerTest {

    @Test
    public void trimStrings_hasFrontHasBack_expectTwoParts() throws NoSlashException, FieldEmptyException {
        FlashCardManager fcm = new FlashCardManager();
        String input = "add good morning /def ohayou";
        assertEquals(2, fcm.trimStrings(input).length);
    }

    @Test
    public void trimStrings_hasNoSlash_expectNoSlashException() {
        FlashCardManager fcm = new FlashCardManager();
        String input = "add good morning ohayou";
        assertThrows(NoSlashException.class, () -> fcm.trimStrings(input));
    }

    @Test
    public void trimStrings_emptyFront_expectFieldEmptyException() {
        FlashCardManager fcm = new FlashCardManager();
        String input = "add /def ohayou";
        assertThrows(FieldEmptyException.class, () -> fcm.trimStrings(input));
    }

    @Test
    public void trimStrings_emptyBack_expectFieldEmptyException() {
        FlashCardManager fcm = new FlashCardManager();
        String input = "add good morning /def";
        assertThrows(FieldEmptyException.class, () ->fcm.trimStrings(input));
    }

    @Test
    public void getDescription_noDescription_ArrayIndexOutOfBoundsException() {
        FlashCardManager fcm = new FlashCardManager();
        String input = "delete";
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> fcm.getDescription(input));
    }

    @Test
    public void deleteFlashCard_cardNotExist_expectCardLiException() {
        FlashCardManager fcm = new FlashCardManager();
        String input = "delete abcdef";
        assertThrows(CardLiException.class, () -> fcm.deleteFlashCard(input));
    }

    @Test
    public void deleteFlashCard_provideDescription_expectDelete() throws CardLiException {
        FlashCardManager fcm = new FlashCardManager();
        String firstCard = "add illness /def byouki";
        String secondCard = "add to lose /def nakushimasu";
        fcm.prepareToAddFlashCard(firstCard);
        fcm.prepareToAddFlashCard(secondCard);
        fcm.deleteFlashCard("illness");
        fcm.viewAllFlashCards();
        assertEquals(1, fcm.cards.size());
        fcm.deleteFlashCard("to lose");
    }

    @Test
    public void deleteFlashCard_provideIndex_expectDelete() throws CardLiException {
        FlashCardManager fcm = new FlashCardManager();
        String firstCard = "add illness /def byouki";
        String secondCard = "add to lose /def nakushimasu";
        fcm.prepareToAddFlashCard(firstCard);
        fcm.prepareToAddFlashCard(secondCard);
        fcm.deleteFlashCard("1");
        fcm.viewAllFlashCards();
        assertEquals(1, fcm.cards.size());
        fcm.deleteFlashCard("1");
    }

    @Test
    public void deleteFlashCard_provideInvalidIndex_expectCardLiException() throws CardLiException {
        FlashCardManager fcm = new FlashCardManager();
        String firstCard = "add illness /def byouki";
        String secondCard = "add to lose /def nakushimasu";
        fcm.prepareToAddFlashCard(firstCard);
        fcm.prepareToAddFlashCard(secondCard);
        fcm.viewAllFlashCards();
        assertThrows(CardLiException.class, () -> fcm.deleteFlashCard("6"));
        fcm.deleteFlashCard("1");
        fcm.deleteFlashCard("1");
    }

    @Test
    public void deleteFlashCard_provideNegativeIndex_expectCardLiException() throws CardLiException {
        FlashCardManager fcm = new FlashCardManager();
        String firstCard = "add illness /def byouki";
        String secondCard = "add to lose /def nakushimasu";
        fcm.prepareToAddFlashCard(firstCard);
        fcm.prepareToAddFlashCard(secondCard);
        fcm.viewAllFlashCards();
        assertThrows(CardLiException.class, () -> fcm.deleteFlashCard("0"));
        fcm.deleteFlashCard("1");
        fcm.deleteFlashCard("1");
    }
}