package pl.imionator.imionator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.imionator.imionator.domain.Name;
import pl.imionator.imionator.domain.NameCategory;
import pl.imionator.imionator.repository.NamesRepository;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class NamesService {

    private NamesRepository namesRepository;

    @Autowired
    public NamesService(NamesRepository namesRepository) {
        this.namesRepository = namesRepository;
    }

    public Name drawFromNamesGivenByUser() {
        List<Name> names = namesRepository.getNamesGivenByUser();
        Collections.shuffle(names);
        return names.get(0);
    }

    //TODO refactor multiple conditionals
    public Name drawNameFromGivenCategory(Name name) {
        List<String> names = new ArrayList<>();
        if (name.getNameCategory() == NameCategory.GIRL_UNUSUAL) {
            names = namesRepository.getUnusualGirlNames();
        } else if (name.getNameCategory() == NameCategory.GIRL_ORDINARY) {
            names = namesRepository.getOrdinaryGirlNames();
        } else if (name.getNameCategory() == NameCategory.GIRL_ALL_NAMES) {
            names = Stream.concat(namesRepository.getUnusualGirlNames().stream(),
                    namesRepository.getOrdinaryGirlNames().stream())
                    .collect(Collectors.toList());
        }

        Collections.shuffle(names);
        name.setFirstName(names.get(0));
        return name;
    }

    public Map<String, Long> generateStatistics() {
        return namesRepository.getNamesDrawnByUser()
                .stream()
                .map(Name::getFirstName)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (o1, o2) -> o1, LinkedHashMap::new));
    }
}
