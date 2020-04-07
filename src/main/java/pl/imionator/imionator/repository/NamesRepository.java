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

    public NamesRepository(NamesLists namesLists) {
        this.namesLists = namesLists;
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
        if (size < 1 || namesDrawnFromPropositionList.get(size - 1).getFirstName() != null || name.getFirstName() != null) {
            namesDrawnFromPropositionList.add(name);
        }
    }

    public void deleteNameDrawnFromPropositionList(String name) {
        namesDrawnFromPropositionList = namesDrawnFromPropositionList.stream()
                .filter(n -> !n.getFirstName().equals(name))
                .collect(Collectors.toList());
    }

    public void clearNamesDrawnFromUserInput() {
        namesDrawnFromUserInput.clear();
    }

    public void clearBoyNamesDrawnFromPropositionList() {
        namesDrawnFromPropositionList = namesDrawnFromPropositionList.stream()
                .filter(name -> name.getSex() != Sex.BOY)
                .collect(Collectors.toList());
    }

    public void clearGirlNamesDrawnFromPropositionList() {
        namesDrawnFromPropositionList = namesDrawnFromPropositionList.stream()
                .filter(name -> name.getSex() != Sex.GIRL)
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
                names = namesLists.getOrdinaryBoyNames();
                break;
            case UNUSUAL:
                names = namesLists.getUnusualBoyNames();
                break;
            case MODERN:
                names = namesLists.getModernBoyNames();
                break;
            case OLD_FASHIONED:
                names = namesLists.getOldFashionedBoyNames();
                break;
        }
        return names;
    }

    private List<String> setGirlNamesList(NameCategory nameCategory, List<String> names) {
        switch (nameCategory) {
            case ORDINARY:
                names = namesLists.getOrdinaryGirlNames();
                break;
            case UNUSUAL:
                names = namesLists.getUnusualGirlNames();
                break;
            case MODERN:
                names = namesLists.getModernGirlNames();
                break;
            case OLD_FASHIONED:
                names = namesLists.getOldFashionedGirlNames();
                break;
        }
        return names;
    }
}
