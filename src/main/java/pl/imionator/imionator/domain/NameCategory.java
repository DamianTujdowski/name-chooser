package pl.imionator.imionator.domain;

public enum NameCategory {
    ORDINARY("Popularne"),
    UNUSUAL("Oryginalne"),
    MODERN("Nowoczesne"),
    OLD_FASHIONED("Staromodne");

    private final String displayValue;

    NameCategory(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
    }
