package pl.imionator.imionator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.imionator.imionator.domain.Name;
import pl.imionator.imionator.domain.NameCategory;
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

    public Name drawNameFromUserInput() {
        List<Name> names = namesRepository.getUserInput();
        Collections.shuffle(names);
        return names.stream().findFirst().orElse(null);
    }

    public Name drawNameFromPropositionList() {
        List<Name> namesDrawnFromGivenCategory = namesRepository.getNamesDrawnFromPropositionList();
        if (namesDrawnFromGivenCategory.size() == 0) return null;
        Name drawnName = namesDrawnFromGivenCategory.get(namesDrawnFromGivenCategory.size() - 1);
        drawnName.setFirstName(getRandomNameFromGivenCategory(drawnName.getNameCategory()));
        return drawnName;
    }

    private String getRandomNameFromGivenCategory(NameCategory nameCategory) {
        List<String> namesFromGivenCategory = namesRepository.getNamesFromGivenCategory(nameCategory);
        Collections.shuffle(namesFromGivenCategory);
        return namesFromGivenCategory.size() > 0 ? namesFromGivenCategory.remove(0) : null;
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
