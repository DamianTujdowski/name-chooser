package pl.imionator.imionator.repository;

import org.springframework.stereotype.Component;
import pl.imionator.imionator.domain.NameCategory;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public
class NamesLists {
    //TODO fill in names lists

    private List<String> unusualGirlNames;

    private List<String> ordinaryGirlNames;

    private List<String> unusualBoyNames;

    private List<String> ordinaryBoyNames;

    List<String> getUnusualGirlNames() {
        return initialize(unusualGirlNames, "unusualGirlNames");
    }

    List<String> getOrdinaryGirlNames() {
        return initialize(ordinaryGirlNames, "ordinaryGirlNames");
    }

    List<String> getUnusualBoyNames() {
        return initialize(unusualBoyNames, "unusualBoyNames");
    }

    List<String> getOrdinaryBoyNames() {
        return initialize(ordinaryBoyNames, "ordinaryBoyNames");
    }

    private List<String> initialize(List<String> list, String listName) {
        if (list == null) {
            switch (listName) {
                case "unusualGirlNames":
                    unusualGirlNames = new ArrayList<>();
                    fillUnusualGirlNamesList();
                    list = unusualGirlNames;
                    break;
                case "ordinaryGirlNames":
                    ordinaryGirlNames = new ArrayList<>();
                    fillOrdinaryGirlNamesList();
                    list = ordinaryGirlNames;
                    break;
                case "unusualBoyNames":
                    unusualBoyNames = new ArrayList<>();
                    fillUnusualBoyNamesList();
                    list = unusualBoyNames;
                    break;
                case "ordinaryBoyNames":
                    ordinaryBoyNames = new ArrayList<>();
                    fillOrdinaryBoyNamesList();
                    list = ordinaryBoyNames;
                    break;
            }
            return list;
        } else {
            return list;
        }
    }

    private void fillUnusualGirlNamesList() {
        Collections.addAll(unusualGirlNames, "Adela", "Amanda");
    }

    private void fillOrdinaryGirlNamesList() {
        Collections.addAll(ordinaryGirlNames, "Ada", "Adrianna");
    }

    private void fillUnusualBoyNamesList() {
        Collections.addAll(unusualBoyNames, "Aaron", "Alfred");
    }

    private void fillOrdinaryBoyNamesList() {
        Collections.addAll(ordinaryBoyNames, "Adam", "Adrian");
    }

}
