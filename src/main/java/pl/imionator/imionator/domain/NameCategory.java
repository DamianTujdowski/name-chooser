package pl.imionator.imionator.domain;

public enum NameCategory {
    GIRL_ORDINARY("Dziewczynka - popularne"),
    GIRL_UNUSUAL("Dziewczynka - oryginalne"),
    GIRL_ALL_NAMES("Dziewczynka - wszystkie"),
    BOY_ORDINARY("Chłopczyk - popularne"),
    BOY_UNUSUAL("Chłopczyk - oryginalne"),
    BOY_ALL_NAMES("Chłopczyk - wszystkie");

    private final String displayValue;

    NameCategory(String displayValue) {
        this.displayValue=displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
