package pl.imionator.imionator.domain;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Name {

    @Size(min = 3, max = 25, message = "Imię musi posiadać od 3 do 25 liter.")
    @Pattern(regexp = "[a-zA-Z]*", message = "Podałeś znak niewystępujący w imionach. Dozwolone są tylko litery alfabetu.")
    private String firstName;

    private Sex sex;

    private NameCategory nameCategory;

    public Name() {
    }

    public Name(String firstName) {
        this.firstName = firstName;
    }

    public Name(String firstName, NameCategory nameCategory) {
        this.firstName = firstName;
        this.nameCategory = nameCategory;
    }

    public Name(String firstName, Sex sex, NameCategory nameCategory) {
        this.firstName = firstName;
        this.sex = sex;
        this.nameCategory = nameCategory;
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

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }
}
