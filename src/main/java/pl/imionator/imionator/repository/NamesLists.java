package pl.imionator.imionator.repository;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class NamesLists {
    //TODO fill in names lists
    private List<String> unusualGirlNames = Arrays.asList("Adela", "Amanda");

    private List<String> ordinaryGirlNames = Arrays.asList("Adela", "Amanda");

    private List<String> unusualBoyNames = Arrays.asList("Adela", "Amanda");

    private List<String> ordinaryBoyNames = Arrays.asList("Adela", "Amanda");

    public List<String> getUnusualGirlNames() {
        return unusualGirlNames;
    }

    public List<String> getOrdinaryGirlNames() {
        return ordinaryGirlNames;
    }

    public List<String> getUnusualBoyNames() {
        return unusualBoyNames;
    }

    public List<String> getOrdinaryBoyNames() {
        return ordinaryBoyNames;
    }
}
