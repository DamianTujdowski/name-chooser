package pl.imionator.imionator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.imionator.imionator.domain.Name;
import pl.imionator.imionator.repository.NamesRepository;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class NamesService {

    private NamesRepository namesRepository;

    @Autowired
    public NamesService(NamesRepository namesRepository) {
        this.namesRepository = namesRepository;
    }

    public void saveNameToInputList(Name name) {
        namesRepository.addNameToInputList(name);
    }

    public void saveNameToDrawingList(Name name) {
        namesRepository.addNameToDrawingList(name);
    }

    public List<Name> namesGivenByUser() {
        return namesRepository.getNamesFromInput();
    }

    public List<Name> namesDrawedByUser() {
        return namesRepository.getNamesFromDrawing();
    }

    public Name nameDrawer() {
        List<Name> names = namesRepository.getNamesFromInput();
        Collections.shuffle(names);
        return names.get(0);
    }

    public Map<String, Long> namesDrawStats() {
        return namesRepository.getNamesFromDrawing()
                .stream()
                .map(Name::toString)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (o1, o2) -> o1, LinkedHashMap::new));
    }
}
