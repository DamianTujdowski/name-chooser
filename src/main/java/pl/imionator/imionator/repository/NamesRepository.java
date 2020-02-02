package pl.imionator.imionator.repository;

import org.springframework.stereotype.Repository;
import pl.imionator.imionator.domain.Name;

import java.util.ArrayList;
import java.util.List;

@Repository
public class NamesRepository {
    private List<Name> namesFromInput = new ArrayList<>();
    private List<Name> namesFromDrawing = new ArrayList<>();

    public void addNameToInputList(Name name) {
        namesFromInput.add(name);
    }

    public void addNameToDrawingList(Name name) {
        namesFromDrawing.add(name);
    }

    public List<Name> getNamesFromInput() {
        return namesFromInput;
    }

    public List<Name> getNamesFromDrawing() {
        return namesFromDrawing;
    }
}
