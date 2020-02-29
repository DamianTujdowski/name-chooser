package pl.imionator.imionator.repository;

import org.junit.jupiter.api.Test;
import pl.imionator.imionator.domain.Name;
import pl.imionator.imionator.domain.NameCategory;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class NamesRepositoryTest {
    NamesLists namesLists = new NamesLists();
    NamesRepository namesRepository = new NamesRepository(namesLists);

    @Test
    public void shouldSaveOnlyTwoNameObjectsWithFirstNameSetToNull() {
        //given
        namesRepository.saveNameDrawnFromPropositionList(new Name("Janina", NameCategory.GIRL_ORDINARY));
        namesRepository.saveNameDrawnFromPropositionList(new Name("Oliwia", NameCategory.GIRL_ORDINARY));
        namesRepository.saveNameDrawnFromPropositionList(new Name(null, NameCategory.GIRL_ORDINARY));
        namesRepository.saveNameDrawnFromPropositionList(new Name(null, NameCategory.GIRL_ORDINARY));
        namesRepository.saveNameDrawnFromPropositionList(new Name("Justyna", NameCategory.GIRL_ORDINARY));
        namesRepository.saveNameDrawnFromPropositionList(new Name("Jadwiga", NameCategory.GIRL_ORDINARY));
        namesRepository.saveNameDrawnFromPropositionList(new Name(null, NameCategory.GIRL_ORDINARY));
        namesRepository.saveNameDrawnFromPropositionList(new Name(null, NameCategory.GIRL_ORDINARY));
        //when
        List<Name> names = namesRepository.getNamesDrawnFromPropositionList()
                .stream()
                .filter(name -> name.getFirstName() == null)
                .collect(Collectors.toList());
        //then
        assertEquals(2, names.size());
    }

}