package pl.imionator.imionator.domain;

public enum NameCategory {
    GIRL_ORDINARY("Dziewczynka - popularne"),
    GIRL_UNUSUAL("Dziewczynka - oryginalne"),
    GIRL_MODERN("Dziewczynka - nowoczesne"),
    GIRL_OLD_FASHIONED("Dziewczynka - staromodne"),
    BOY_ORDINARY("Chłopczyk - popularne"),
    BOY_UNUSUAL("Chłopczyk - oryginalne"),
    BOY_MODERN("Chłopczyk - nowoczesne"),
    BOY_OLD_FASHIONED("Chłopczyk - staromodne");

    private final String displayValue;

    NameCategory(String displayValue) {
        this.displayValue=displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
