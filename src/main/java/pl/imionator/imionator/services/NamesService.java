package pl.imionator.imionator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.imionator.imionator.domain.Name;
import pl.imionator.imionator.repository.NamesLists;
import pl.imionator.imionator.repository.NamesRepository;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class NamesService {

    private NamesRepository namesRepository;

    private NamesLists namesLists;

    @Autowired
    public NamesService(NamesRepository namesRepository, NamesLists namesLists) {
        this.namesRepository = namesRepository;
        this.namesLists = namesLists;
    }

    public Name drawFromNamesGivenByUser() {
        List<Name> names = namesRepository.getUserInput();
        Collections.shuffle(names);
        return names.stream().findFirst().orElse(null);
    }

    public Name drawNameFromPropositionList() {
        List<Name> namesDrawnFromCategory = namesRepository.getNamesDrawnFromPropositionList();
        if (namesDrawnFromCategory.size() == 0) return null;
        Name drawnName = namesDrawnFromCategory.get(namesDrawnFromCategory.size() - 1);
        drawnName.setFirstName(namesLists.drawNameFromGivenCategory(drawnName.getNameCategory()));
        return drawnName;
    }

    public Map<String, Long> generateStatisticsFromUserInputDraw() {
        return namesRepository.getNamesDrawnFromUserInput()
                .stream()
                .map(Name::getFirstName)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (o1, o2) -> o1, LinkedHashMap::new));
    }
}
