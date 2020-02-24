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

    //TODO make randomResult page display info when proposition names list from given category is empty
    public Name getLastNameDrawnFromPropositionList() {
        return namesRepository.getNamesDrawnFromPropositionList()
                .stream()
                .reduce((first, second) -> second)
                .orElse(null);
    }

    public String getRandomNameFromGivenCategory(NameCategory nameCategory) {
        List<String> namesFromGivenCategory = namesRepository.getNamesFromGivenCategory(nameCategory);
        Collections.shuffle(namesFromGivenCategory);
        return namesFromGivenCategory.size() > 0 ? namesFromGivenCategory.remove(0) : null;
    }

    public List<Name> generateStatisticsFromPropositionListDraw() {
        return namesRepository.getNamesDrawnFromPropositionList().stream()
                .filter(name -> name.getFirstName() != null)
                .collect(Collectors.toList());
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
