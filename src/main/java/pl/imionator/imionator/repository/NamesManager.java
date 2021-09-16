package pl.imionator.imionator.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.context.annotation.SessionScope;
import pl.imionator.imionator.domain.Name;
import pl.imionator.imionator.domain.NameCategory;
import pl.imionator.imionator.domain.Sex;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@SessionScope
public class NamesManager {

    private final List<Name> userInput;

    private final List<Name> namesDrawnFromUserInput;

    private final List<Name> namesDrawnFromPropositionList;

    private final List<String> ordinaryGirlNames;

    private final List<String> unusualGirlNames;

    private final List<String> modernGirlNames;

    private final List<String> oldFashionedGirlNames;

    private final List<String> ordinaryBoyNames;

    private final List<String> unusualBoyNames;

    private final List<String> modernBoyNames;

    private final List<String> oldFashionedBoyNames;

    public NamesManager(NamesLoader namesLoader) {
        userInput = new ArrayList<>();
        namesDrawnFromUserInput = new ArrayList<>();
        namesDrawnFromPropositionList = new ArrayList<>();
        ordinaryGirlNames = namesLoader.fillOrdinaryGirlNamesList();
        unusualGirlNames = namesLoader.fillUnusualGirlNamesList();
        modernGirlNames = namesLoader.fillModernGirlNamesList();
        oldFashionedGirlNames = namesLoader.fillOldFashionedGirlNamesList();
        ordinaryBoyNames = namesLoader.fillOrdinaryBoyNamesList();
        unusualBoyNames = namesLoader.fillUnusualBoyNamesList();
        modernBoyNames = namesLoader.fillModernBoyNamesList();
        oldFashionedBoyNames = namesLoader.fillOldFashionedBoyNamesList();
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
        Name nameToRemove = namesDrawnFromPropositionList.stream()
                .filter(n -> n.getFirstName().equals(name))
                .findFirst()
                .orElse(new Name());
        namesDrawnFromPropositionList.remove(nameToRemove);
    }

    public void removeNamesDrawnFromUserInput() {
        namesDrawnFromUserInput.clear();
    }

    public void filterNamesDrawnFromPropositionListBySex(Sex sex) {
        List<Name> namesToRemoveBySex = namesDrawnFromPropositionList.stream()
                .filter(name -> name.getSex() == sex)
                .collect(Collectors.toList());
        namesDrawnFromPropositionList.removeAll(namesToRemoveBySex);
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

    public List<Name> getUserInput() {
        return userInput;
    }

    public List<Name> getNamesDrawnFromUserInput() {
        return namesDrawnFromUserInput;
    }

    public List<Name> getNamesDrawnFromPropositionList() {
        return namesDrawnFromPropositionList;
    }
}
