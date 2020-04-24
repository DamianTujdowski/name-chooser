package pl.imionator.imionator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.imionator.imionator.domain.Name;
import pl.imionator.imionator.domain.NameCategory;
import pl.imionator.imionator.domain.Sex;
import pl.imionator.imionator.repository.NamesRepository;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class NamesService {

    private NamesRepository namesRepository;

    public NamesService(NamesRepository namesRepository) {
        this.namesRepository = namesRepository;
    }

    public Name drawNameFromUserInput() {
        List<Name> names = namesRepository.getUserInput();
        Collections.shuffle(names);
        return names
                .stream()
                .findFirst()
                .orElse(new Name(""));
    }

    public Name getLastNameDrawnFromPropositionList() {
        return namesRepository.getNamesDrawnFromPropositionList()
                .stream()
                .reduce((first, second) -> second)
                .orElse(new Name(""));
    }

    public String getRandomNameFromGivenCategory(NameCategory nameCategory, Sex sex) {
        List<String> namesFromGivenCategory = namesRepository.getNamesFromGivenCategory(sex, nameCategory);
        Collections.shuffle(namesFromGivenCategory);
        return namesFromGivenCategory.size() > 0 ? namesFromGivenCategory.remove(0) : "";
    }

    public List<Name> generateStatisticsFromPropositionListDrawBySex(Sex sex) {
        return namesRepository.getNamesDrawnFromPropositionList()
                .stream()
                .filter(name -> !name.getFirstName().equals("") && name.getSex() == sex)
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
