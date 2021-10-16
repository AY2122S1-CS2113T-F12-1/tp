package seedu.duke;

public class FlashCardCategory {
    private String name;
    private FlashCardManager manager;

    public FlashCardCategory(String name, FlashCardManager manager) {
        this.name = name;
        this.manager = manager;
    }

    public String getName() {
        return name;
    }

    public FlashCardManager getManager() {
        return manager;
    }
}
