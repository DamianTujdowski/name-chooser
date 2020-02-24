package pl.imionator.imionator.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.imionator.imionator.domain.Name;
import pl.imionator.imionator.domain.NameCategory;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class NamesRepository {

    private NamesLists namesLists;

    private List<Name> userInput = new ArrayList<>();

    private List<Name> namesDrawnFromUserInput = new ArrayList<>();

    private List<Name> namesDrawnFromPropositionList = new ArrayList<>();

    @Autowired
    public NamesRepository(NamesLists namesLists) {
        this.namesLists = namesLists;
    }

    public void saveUserInputName(Name name) {
        userInput.add(name);
    }

    public void saveNameDrawnFromUserInput(Name name) {
        namesDrawnFromUserInput.add(name);
    }

    public void saveNameDrawnFromPropositionList(Name name) {
            namesDrawnFromPropositionList.add(name);
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

    public List<String> getNamesFromGivenCategory(NameCategory nameCategory) {
        List<String> names;
        switch (nameCategory) {
            case GIRL_ORDINARY:
                names = namesLists.getOrdinaryGirlNames();
                break;
            case GIRL_UNUSUAL:
                names = namesLists.getUnusualGirlNames();
                break;
            case GIRL_ALL_NAMES:
                names = Stream.concat(namesLists.getUnusualGirlNames().stream(), namesLists.getOrdinaryGirlNames().stream())
                        .collect(Collectors.toList());
                break;
            case BOY_UNUSUAL:
                names = namesLists.getUnusualBoyNames();
                break;
            case BOY_ORDINARY:
                names = namesLists.getOrdinaryBoyNames();
                break;
            case BOY_ALL_NAMES:
                names = Stream.concat(namesLists.getUnusualBoyNames().stream(), namesLists.getOrdinaryBoyNames().stream())
                        .collect(Collectors.toList());
                break;
            default:
                names = new ArrayList<>();
        }
        return names;
    }

}
