package pl.imionator.imionator.repository;

import org.springframework.stereotype.Component;
import pl.imionator.imionator.domain.NameCategory;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class NamesLists {
    //TODO fill in names lists
    private String[] girlUnusualNames = {"Adela", "Amanda"};
    private List<String> unusualGirlNames = new LinkedList<>(Arrays.asList(girlUnusualNames));

    private String[] girlOrdinaryNames = {"Monika", "Oliwia"};
    private List<String> ordinaryGirlNames = new LinkedList<>(Arrays.asList(girlOrdinaryNames));

    private String[] boyUnusualNames = {"Adela", "Amanda"};
    private List<String> unusualBoyNames = new LinkedList<>(Arrays.asList(boyUnusualNames));

    private String[] boyOrdinaryNames = {"Tomasz", "Karol"};
    private List<String> ordinaryBoyNames = new LinkedList<>(Arrays.asList(boyOrdinaryNames));

    //TODO in namesLists leave only arrays, make lists from arrays in NameRepository,
    //TODO implement method returning list based on NameCategory, in NamesService refactor method responsible for drawing names
    public String drawNameFromGivenCategory(NameCategory nameCategory) {
        List<String> names;
        switch (nameCategory) {
            case GIRL_UNUSUAL:
                names = unusualGirlNames;
                break;
            case GIRL_ORDINARY:
                names = ordinaryGirlNames;
                break;
            case GIRL_ALL_NAMES:
                names = Stream.concat(unusualGirlNames.stream(), ordinaryGirlNames.stream())
                        .collect(Collectors.toList());
                break;
            case BOY_UNUSUAL:
                names = unusualBoyNames;
                break;
            case BOY_ORDINARY:
                names = ordinaryBoyNames;
                break;
            default:
                names = Stream.concat(unusualBoyNames.stream(), ordinaryBoyNames.stream())
                        .collect(Collectors.toList());
        }
        Collections.shuffle(names);
        return names.size() > 0 ? names.remove(0) : null;
    }
}
