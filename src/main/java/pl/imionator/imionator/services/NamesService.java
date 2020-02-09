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
        return names.stream().findFirst().orElse(null);
    }

    //TODO refactor multiple conditionals
    public Name drawNameFromGivenCategory() {
        List<Name> namesDrawnFromCategory = namesRepository.getNamesDrawnByUser();
        if (namesDrawnFromCategory.size() == 0) return null;
//        Name nam = namesRepository.getNamesDrawnByUser().stream().reduce((first, second) -> second).orElse(null);
        Name drawnName = namesDrawnFromCategory.get(namesDrawnFromCategory.size() - 1);
        NameCategory nameCategory = drawnName.getNameCategory();
        List<String> categoryNames;
        if (nameCategory == NameCategory.GIRL_UNUSUAL) {
            categoryNames = namesRepository.getUnusualGirlNames();
        } else if (nameCategory == NameCategory.GIRL_ORDINARY) {
            categoryNames = namesRepository.getOrdinaryGirlNames();
        } else if (nameCategory == NameCategory.GIRL_ALL_NAMES) {
            categoryNames = Stream.concat(namesRepository.getUnusualGirlNames().stream(),
                    namesRepository.getOrdinaryGirlNames().stream())
                    .collect(Collectors.toList());
        } else if (nameCategory == NameCategory.BOY_UNUSUAL) {
            categoryNames = namesRepository.getUnusualBoyNames();
        } else if (nameCategory == NameCategory.BOY_ORDINARY) {
            categoryNames = namesRepository.getOrdinaryBoyNames();
        } else {
            categoryNames = Stream.concat(namesRepository.getUnusualBoyNames().stream(),
                    namesRepository.getOrdinaryBoyNames().stream())
                    .collect(Collectors.toList());
        }
        //TODO prevent from drawing same name twice
        Collections.shuffle(categoryNames);
        drawnName.setFirstName(categoryNames.get(0));
        return drawnName;
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
