package pl.imionator.imionator.repository;

import org.springframework.stereotype.Repository;
import pl.imionator.imionator.domain.Name;

import java.util.ArrayList;
import java.util.List;

@Repository
public class NamesRepository {
    private List<Name> namesGivenByUser = new ArrayList<>();
    private List<Name> namesDrawnByUser = new ArrayList<>();

    public void addNameGivenByUser(Name name) {
        namesGivenByUser.add(name);
    }

    public void addNameDrawnByUser(Name name) {
        namesDrawnByUser.add(name);
    }

    public List<Name> getNamesGivenByUser() {
        return namesGivenByUser;
    }

    public List<Name> getNamesDrawnByUser() {
        return namesDrawnByUser;
    }
}
