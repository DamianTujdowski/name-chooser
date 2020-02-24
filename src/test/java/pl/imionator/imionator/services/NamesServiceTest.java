package pl.imionator.imionator.services;

import org.junit.jupiter.api.Test;
import pl.imionator.imionator.domain.Name;
import pl.imionator.imionator.domain.NameCategory;
import pl.imionator.imionator.repository.NamesRepository;
import pl.imionator.imionator.repository.NamesLists;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NamesServiceTest {
    private NamesLists namesLists = new NamesLists();
    private NamesRepository namesRepository = new NamesRepository(namesLists);
    NamesService namesService = new NamesService(namesRepository);

    @Test
    public void shouldReturnNull() {
        assertNull(namesService.getLastNameDrawnFromPropositionList());
    }

    @Test
    public void sizeShuoldBeZero() {
        List<Name> names = namesRepository.getNamesDrawnFromPropositionList();
        assertEquals(0, names.size());
    }

    @Test
    public void shouldAlsoReturnNameKarolina() {
        //given
        namesRepository.saveNameDrawnFromPropositionList(new Name("Damian", NameCategory.BOY_ORDINARY));
        namesRepository.saveNameDrawnFromPropositionList(new Name("Zosia", NameCategory.GIRL_ORDINARY));
        namesRepository.saveNameDrawnFromPropositionList(new Name("Rafał", NameCategory.BOY_ORDINARY));
        namesRepository.saveNameDrawnFromPropositionList(new Name("Karolina", NameCategory.GIRL_ORDINARY));
        //when
        Name lastDrawnName = namesService.getLastNameDrawnFromPropositionList();
        //then
        assertEquals("Karolina", lastDrawnName.getFirstName());
    }

    @Test
    public void shouldReturnNameKarolina() {
        //given
        namesRepository.saveNameDrawnFromPropositionList(new Name("Damian", NameCategory.BOY_ORDINARY));
        namesRepository.saveNameDrawnFromPropositionList(new Name("Zosia", NameCategory.GIRL_ORDINARY));
        namesRepository.saveNameDrawnFromPropositionList(new Name("Rafał", NameCategory.BOY_ORDINARY));
        namesRepository.saveNameDrawnFromPropositionList(new Name("Karolina", NameCategory.GIRL_ORDINARY));
        //when
        List<Name> names = namesRepository.getNamesDrawnFromPropositionList();
        Name lastDrawnName = names.get(names.size() - 1);
        //then
        assertEquals(lastDrawnName.getFirstName(), "Karolina");
    }
}