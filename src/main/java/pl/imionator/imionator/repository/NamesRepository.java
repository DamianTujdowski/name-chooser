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
            case GIRL_MODERN:
                names = namesLists.getModernGirlNames();
                break;
            case GIRL_OLD_FASHIONED:
                names = namesLists.getOldFashionedGirlNames();
                break;
            case BOY_ORDINARY:
                names = namesLists.getOrdinaryBoyNames();
                break;
            case BOY_UNUSUAL:
                names = namesLists.getUnusualBoyNames();
                break;
            case BOY_MODERN:
                names = namesLists.getModernBoyNames();
                break;
            case BOY_OLD_FASHIONED:
                names = namesLists.getOldFashionedBoyNames();
                break;
            default:
                names = new ArrayList<>();
        }
        return names;
    }
}
