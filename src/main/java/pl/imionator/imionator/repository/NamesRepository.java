package pl.imionator.imionator.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.imionator.imionator.domain.Name;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class NamesRepository {

    private NamesLists namesLists;

    private List<Name> namesGivenByUser = new ArrayList<>();

    private List<Name> namesDrawnByUser = new ArrayList<>();

    @Autowired
    public NamesRepository(NamesLists namesLists) {
        this.namesLists = namesLists;
    }

    public void saveNameGivenByUser(Name name) {
        namesGivenByUser.add(name);
    }

    public void saveNameDrawnByUser(Name name) {
        namesDrawnByUser.add(name);
    }

    public List<Name> getNamesGivenByUser() {
        return namesGivenByUser;
    }

    public List<Name> getNamesDrawnByUser() {
        return namesDrawnByUser;
    }

    public List<String> getUnusualGirlNames() {
        return namesLists.getUnusualGirlNames();
    }

    public List<String> getOrdinaryGirlNames() {
        return namesLists.getOrdinaryGirlNames();
    }

    public List<String> getUnusualBoyNames() {
        return namesLists.getUnusualBoyNames();
    }

    public List<String> getOrdinaryBoyNames() {
        return namesLists.getOrdinaryBoyNames();
    }
}
