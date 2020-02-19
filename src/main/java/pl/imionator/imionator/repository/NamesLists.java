package pl.imionator.imionator.repository;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
class NamesLists {
    //TODO fill in names lists

    static List<String> unusualGirlNames = new ArrayList<>();

    static List<String> ordinaryGirlNames = new ArrayList<>();
//    static final String[] ordinaryGirlNames = {"Monika", "Oliwia"};

    static List<String> unusualBoyNames = new ArrayList<>();
//    static final String[] unusualBoyNames = {"Adela", "Amanda"};

    static List<String> ordinaryBoyNames = new ArrayList<>();
//    static final String[] ordinaryBoyNames = {"Tomasz", "Karol"};

    @PostConstruct
    List<String> getUnusualGirlNames() {
        Collections.addAll(unusualGirlNames, "Adela", "Amanda");
        return unusualGirlNames;
    }

    public static List<String> getOrdinaryGirlNames() {
        Collections.addAll(unusualGirlNames, "Adela", "Amanda");
        return ordinaryGirlNames;
    }

    public static List<String> getUnusualBoyNames() {
        return unusualBoyNames;
    }

    public static List<String> getOrdinaryBoyNames() {
        return ordinaryBoyNames;
    }
}
