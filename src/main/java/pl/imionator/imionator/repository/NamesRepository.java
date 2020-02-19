package pl.imionator.imionator.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.imionator.imionator.domain.Name;
import pl.imionator.imionator.domain.NameCategory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class NamesRepository {

    private List<Name> userInput = new ArrayList<>();

    private List<Name> namesDrawnFromUserInput = new ArrayList<>();

    private List<Name> namesDrawnFromPropositionList = new ArrayList<>();

    public void saveUserInputName(Name name) {
        userInput.add(name);
    }

    public void saveNameDrawnFromUserInput(Name name) {
        namesDrawnFromUserInput.add(name);
    }

    public void saveNameDrawnFromPropositionList(Name name) {
        if (name.getNameCategory() != null) {
            namesDrawnFromPropositionList.add(name);
        }
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
            case GIRL_UNUSUAL:
                names = NamesLists.unusualGirlNames;
                break;
            case GIRL_ORDINARY:
                names = NamesLists.ordinaryGirlNames;
                break;
            case GIRL_ALL_NAMES:
                names = Stream.concat(NamesLists.unusualGirlNames.stream(), NamesLists.ordinaryGirlNames.stream())
                        .collect(Collectors.toList());
                break;
            case BOY_UNUSUAL:
                names = NamesLists.unusualBoyNames;
                break;
            case BOY_ORDINARY:
                names = NamesLists.ordinaryBoyNames;
                break;
            case BOY_ALL_NAMES:
                names = Stream.concat(NamesLists.unusualBoyNames.stream(), NamesLists.ordinaryBoyNames.stream())
                        .collect(Collectors.toList());
                break;
            default:
                names = new ArrayList<>();
        }
        return names;
    }
}
