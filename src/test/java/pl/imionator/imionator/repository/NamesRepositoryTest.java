package pl.imionator.imionator.repository;

import org.junit.jupiter.api.Test;
import pl.imionator.imionator.domain.Name;
import pl.imionator.imionator.domain.NameCategory;
import pl.imionator.imionator.domain.Sex;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class NamesRepositoryTest {
    NamesLists namesLists = new NamesLists();
    NamesRepository namesRepository = new NamesRepository(namesLists);

    @Test
    public void shouldSaveOnlyTwoNameObjectsWithFirstNameSetToNull() {
        //given
        namesRepository.saveNameDrawnFromPropositionList(new Name("Janina", Sex.GIRL, NameCategory.ORDINARY));
        namesRepository.saveNameDrawnFromPropositionList(new Name("Oliwia", Sex.GIRL, NameCategory.ORDINARY));
        namesRepository.saveNameDrawnFromPropositionList(new Name(null, Sex.GIRL, NameCategory.ORDINARY));
        namesRepository.saveNameDrawnFromPropositionList(new Name(null, Sex.GIRL, NameCategory.ORDINARY));
        namesRepository.saveNameDrawnFromPropositionList(new Name("Justyna", Sex.GIRL, NameCategory.ORDINARY));
        namesRepository.saveNameDrawnFromPropositionList(new Name("Jadwiga", Sex.GIRL, NameCategory.ORDINARY));
        namesRepository.saveNameDrawnFromPropositionList(new Name(null, Sex.GIRL, NameCategory.ORDINARY));
        namesRepository.saveNameDrawnFromPropositionList(new Name(null, Sex.GIRL, NameCategory.ORDINARY));
        //when
        List<Name> names = namesRepository.getNamesDrawnFromPropositionList()
                .stream()
                .filter(name -> name.getFirstName() == null)
                .collect(Collectors.toList());
        //then
        assertEquals(2, names.size());
    }

    @Test
    void shouldReturnGirlsOrdinaryNamesList() {
        //given
        List<String> names = namesRepository.getNamesFromGivenCategory(Sex.GIRL, NameCategory.ORDINARY);
        //when
        int size = names.size();
        //then
        assertEquals(155, size);
    }

    @Test
    void shouldReturnGirlsModernNamesList() {
        //given
        List<String> names = namesRepository.getNamesFromGivenCategory(Sex.GIRL, NameCategory.MODERN);
        //when
        int size = names.size();
        //then
        names.forEach(System.out::println);
        assertEquals(41, size);
    }

}