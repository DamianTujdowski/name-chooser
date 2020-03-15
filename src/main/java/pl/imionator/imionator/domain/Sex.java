package pl.imionator.imionator.domain;

public enum Sex {
    BOY("Chłopiec"),
    GIRL("Dziewczynka");

    private final String displayValue;

    Sex(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
