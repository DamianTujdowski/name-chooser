package pl.imionator.imionator.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.context.annotation.SessionScope;
import pl.imionator.imionator.domain.Name;
import pl.imionator.imionator.domain.NameCategory;
import pl.imionator.imionator.domain.Sex;

import java.util.*;
import java.util.stream.Collectors;

@Repository
@SessionScope
public class NamesRepository {

    private NamesLists namesLists;

    private List<Name> userInput = new ArrayList<>();

    private List<Name> namesDrawnFromUserInput = new ArrayList<>();

    private List<Name> namesDrawnFromPropositionList = new ArrayList<>();

    private List<String> ordinaryGirlNames = new ArrayList<>();

    private List<String> unusualGirlNames = new ArrayList<>();

    private List<String> modernGirlNames = new ArrayList<>();

    private List<String> oldFashionedGirlNames = new ArrayList<>();

    private List<String> ordinaryBoyNames = new ArrayList<>();

    private List<String> unusualBoyNames = new ArrayList<>();

    private List<String> modernBoyNames = new ArrayList<>();

    private List<String> oldFashionedBoyNames = new ArrayList<>();

    public NamesRepository(NamesLists namesLists) {
        this.namesLists = namesLists;
    }

    {
        namesLists.fillOrdinaryGirlNamesList(ordinaryGirlNames);
        namesLists.fillUnusualGirlNamesList(unusualGirlNames);
        namesLists.fillModernGirlNamesList(modernGirlNames);
        namesLists.fillOldFashionedGirlNamesList(oldFashionedGirlNames);
        namesLists.fillOrdinaryBoyNamesList(ordinaryBoyNames);
        namesLists.fillUnusualBoyNamesList(unusualBoyNames);
        namesLists.fillModernBoyNamesList(modernBoyNames);
        namesLists.fillOldFashionedBoyNamesList(oldFashionedBoyNames);
    }

    public void saveUserInputName(Name name) {
        userInput.add(name);
    }

    public void saveNameDrawnFromUserInput(Name name) {
        namesDrawnFromUserInput.add(name);
    }

    public void deleteLastAddedName() {
        int size = userInput.size();
        if (size > 0) {
            userInput.remove(size - 1);
        }
    }

    public void saveNameDrawnFromPropositionList(Name name) {
        int size = namesDrawnFromPropositionList.size();
        if (size < 1 || !namesDrawnFromPropositionList.get(size - 1).getFirstName().equals("") || !name.getFirstName().equals("")) {
            namesDrawnFromPropositionList.add(name);
        }
    }

    public void deleteNameDrawnFromPropositionList(String name) {
        namesDrawnFromPropositionList = namesDrawnFromPropositionList.stream()
                .filter(n -> !n.getFirstName().equals(name))
                .collect(Collectors.toList());
    }

    public void removeNamesDrawnFromUserInput() {
        namesDrawnFromUserInput.clear();
    }

    public void filterNamesDrawnFromPropositionListBySex(Sex sex) {
        namesDrawnFromPropositionList = namesDrawnFromPropositionList.stream()
                .filter(name -> name.getSex() != sex)
                .collect(Collectors.toList());
    }

    public List<Name> getUserInput() {
        return userInput;
    }

    public List<Name> getNamesDrawnFromUserInput() {
        return namesDrawnFromUserInput;
    }

    public List<Name> getNamesDrawnFromPropositionList() {
        return namesDrawnFromPropositionList;
    }

    public void removeDrawnNameFromPropositions(NameCategory nameCategory, Sex sex, String randomName) {
        getNamesFromGivenCategory(sex, nameCategory).remove(randomName);
    }

    public List<String> getNamesFromGivenCategory(Sex sex, NameCategory nameCategory) {
        List<String> names = new ArrayList<>();
        switch (sex) {
            case GIRL:
                names = setGirlNamesList(nameCategory, names);
                break;
            case BOY:
                names = setBoyNamesList(nameCategory, names);
                break;
        }
        return names;
    }

    private List<String> setBoyNamesList(NameCategory nameCategory, List<String> names) {
        switch (nameCategory) {
            case ORDINARY:
                names = ordinaryBoyNames;
                break;
            case UNUSUAL:
                names = unusualBoyNames;
                break;
            case MODERN:
                names = modernBoyNames;
                break;
            case OLD_FASHIONED:
                names = oldFashionedBoyNames;
                break;
        }
        return names;
    }

    private List<String> setGirlNamesList(NameCategory nameCategory, List<String> names) {
        switch (nameCategory) {
            case ORDINARY:
                names = ordinaryGirlNames;
                break;
            case UNUSUAL:
                names = unusualGirlNames;
                break;
            case MODERN:
                names = modernGirlNames;
                break;
            case OLD_FASHIONED:
                names = oldFashionedGirlNames;
                break;
        }
        return names;
    }

    public List<String> getModernBoyNames() {
        return modernBoyNames;
    }
}
