package pl.imionator.imionator.repository;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public
class NamesLists {
    //TODO fill in names lists

    private List<String> unusualGirlNames = new ArrayList<>();

    private List<String> ordinaryGirlNames = new ArrayList<>();

    private List<String> unusualBoyNames = new ArrayList<>();

    private List<String> ordinaryBoyNames = new ArrayList<>();

    List<String> getUnusualGirlNames() {
        boolean isNotInitialized = true;
        if (isNotInitialized) {
            isNotInitialized = false;
            fillUnusualGirlNamesList();
        }
        return unusualGirlNames;
    }

    List<String> getOrdinaryGirlNames() {
//        fillOrdinaryGirlNamesList();
        return ordinaryGirlNames;
    }

    List<String> getUnusualBoyNames() {
//        fillUnusualBoyNamesList();
        return unusualBoyNames;
    }

    List<String> getOrdinaryBoyNames() {
//        fillOrdinaryBoyNamesList();
        return ordinaryBoyNames;
    }

    @PostConstruct
    private void fillUnusualGirlNamesList() {
        Collections.addAll(unusualGirlNames, "Adela", "Amanda");
    }

    @PostConstruct
    private void fillOrdinaryGirlNamesList() {
        Collections.addAll(ordinaryGirlNames, "Ada", "Adrianna");
    }

    @PostConstruct
    private void fillUnusualBoyNamesList() {
        Collections.addAll(unusualBoyNames, "Aaron", "Alfred");
    }

    @PostConstruct
    private void fillOrdinaryBoyNamesList() {
        Collections.addAll(ordinaryBoyNames, "Adam", "Adrian");
    }

}
