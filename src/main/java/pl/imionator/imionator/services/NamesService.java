package pl.imionator.imionator.services;

import org.springframework.stereotype.Service;
import pl.imionator.imionator.domain.Name;
import pl.imionator.imionator.domain.NameCategory;
import pl.imionator.imionator.domain.Sex;
import pl.imionator.imionator.repository.NamesManager;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class NamesService {

    private NamesManager namesManager;

    public NamesService(NamesManager namesManager) {
        this.namesManager = namesManager;
    }

    public Name drawNameFromUserInput() {
        List<Name> names = namesManager.getUserInput();
        Collections.shuffle(names);
        return names
                .stream()
                .findFirst()
                .orElse(new Name(""));
    }

    public Name getLastNameDrawnFromPropositionList() {
        return namesManager.getNamesDrawnFromPropositionList()
                .stream()
                .reduce((first, second) -> second)
                .orElse(new Name(""));
    }

    public String getRandomNameFromGivenCategory(NameCategory nameCategory, Sex sex) {
        List<String> namesFromGivenCategory = namesManager.getNamesFromGivenCategory(sex, nameCategory);
        Collections.shuffle(namesFromGivenCategory);
        String randomName = namesFromGivenCategory
                .stream()
                .findFirst()
                .orElse("");
        namesManager.removeDrawnNameFromPropositions(nameCategory, sex, randomName);
        return randomName;
    }

    public List<Name> generateStatisticsFromPropositionListDrawBySex(Sex sex) {
        return namesManager.getNamesDrawnFromPropositionList()
                .stream()
                .filter(name -> !name.getFirstName().equals("") && name.getSex() == sex)
                .collect(Collectors.toList());
    }

    public Map<String, Long> generateStatisticsFromUserInputDraw() {
        return namesManager.getNamesDrawnFromUserInput()
                .stream()
                .map(Name::getFirstName)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (o1, o2) -> o1, LinkedHashMap::new));
    }
}
