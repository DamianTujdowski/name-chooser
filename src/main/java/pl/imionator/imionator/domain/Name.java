package pl.imionator.imionator.domain;

public class Name {

    private String firstName;

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

    @Override
    public String toString() {
        return firstName;
    }
}
