package pl.imionator.imionator.domain;

public class Name {

    private String firstName;
    private NameCategory nameCategory;

    public Name() {
    }

    public Name(String name) {
        this.firstName = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public NameCategory getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(NameCategory nameCategory) {
        this.nameCategory = nameCategory;
    }

    @Override
    public String toString() {
        return firstName;
    }
}
