package pl.imionator.imionator.services;

import org.junit.jupiter.api.Test;
import pl.imionator.imionator.domain.Name;
import pl.imionator.imionator.domain.NameCategory;
import pl.imionator.imionator.domain.Sex;
import pl.imionator.imionator.repository.NamesRepository;
import pl.imionator.imionator.repository.NamesLists;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class NamesServiceTest {
    private NamesLists namesLists = new NamesLists();
    private NamesRepository namesRepository = new NamesRepository();
    private NamesService namesService = new NamesService(namesRepository);

    @Test
    void getLastNameDrawnFromPropositionList_noNamesDrawnYet_shouldReturnEmptyFirstName() {
        //given

        //when
        String firstName = namesService.getLastNameDrawnFromPropositionList().getFirstName();
        //then
        assertEquals("", firstName);
    }

    @Test
    void getLastNameDrawnFromPropositionList_namesAreAdded_shouldReturnLastAddedNameKarolina() {
        //given
        namesRepository.saveNameDrawnFromPropositionList(new Name("Damian", Sex.BOY, NameCategory.ORDINARY));
        namesRepository.saveNameDrawnFromPropositionList(new Name("Zosia", Sex.GIRL, NameCategory.ORDINARY));
        namesRepository.saveNameDrawnFromPropositionList(new Name("Rafał", Sex.BOY, NameCategory.ORDINARY));
        namesRepository.saveNameDrawnFromPropositionList(new Name("Karolina", Sex.GIRL, NameCategory.ORDINARY));
        //when
        Name lastDrawnName = namesService.getLastNameDrawnFromPropositionList();
        //then
        assertEquals("Karolina", lastDrawnName.getFirstName());
    }

    @Test
    void getRandomNameFromGivenCategory_SexGirlOrdinaryCategory_shouldBeTrue() {
        //given
        List<String> ordinaryGirlNames = namesRepository.getNamesFromGivenCategory(Sex.GIRL, NameCategory.ORDINARY);
        //when
        String ordinaryName = "Beata";
        //then
        assertTrue(ordinaryGirlNames.contains(ordinaryName));
    }

    @Test
    void getRandomNameFromGivenCategory_SexBoyModernCategory_shouldBeTrue() {
        //given
        List<String> modernBoyNames = namesRepository.getNamesFromGivenCategory(Sex.BOY, NameCategory.MODERN);
        //when
        String modernName = "Eliot";
        //then
        assertTrue(modernBoyNames.contains(modernName));
    }

    @Test
    void generateStatisticsFromPropositionListDrawBySex_SexBoy_shouldContainOnlyBoyNames() {
        //given
        namesRepository.saveNameDrawnFromPropositionList(new Name("Damian", Sex.BOY, NameCategory.ORDINARY));
        namesRepository.saveNameDrawnFromPropositionList(new Name("Zosia", Sex.GIRL, NameCategory.ORDINARY));
        namesRepository.saveNameDrawnFromPropositionList(new Name("Rafał", Sex.BOY, NameCategory.ORDINARY));
        namesRepository.saveNameDrawnFromPropositionList(new Name("Karolina", Sex.GIRL, NameCategory.ORDINARY));
        namesRepository.saveNameDrawnFromPropositionList(new Name("Julek", Sex.BOY, NameCategory.ORDINARY));
        namesRepository.saveNameDrawnFromPropositionList(new Name("Kamila", Sex.GIRL, NameCategory.ORDINARY));
        namesRepository.saveNameDrawnFromPropositionList(new Name("Klaudiusz", Sex.BOY, NameCategory.ORDINARY));
        namesRepository.saveNameDrawnFromPropositionList(new Name("Julia", Sex.GIRL, NameCategory.ORDINARY));
        //when
        List<Name> boyNamesFromPropositionList = namesService.generateStatisticsFromPropositionListDrawBySex(Sex.BOY);
        //then
        assertFalse(boyNamesFromPropositionList.contains(new Name("Zosia", Sex.GIRL, NameCategory.ORDINARY)));
        assertEquals(4, boyNamesFromPropositionList.size());
    }

    @Test
    void generateStatisticsFromUserInputDraw_fourNamesAdded_shouldReturnNamesInCorrectOrder() {
        //given
        namesRepository.saveNameDrawnFromUserInput(new Name("Damian"));
        namesRepository.saveNameDrawnFromUserInput(new Name("Karol"));
        namesRepository.saveNameDrawnFromUserInput(new Name("Marek"));
        namesRepository.saveNameDrawnFromUserInput(new Name("Damian"));
        namesRepository.saveNameDrawnFromUserInput(new Name("Karol"));
        namesRepository.saveNameDrawnFromUserInput(new Name("Jacek"));
        namesRepository.saveNameDrawnFromUserInput(new Name("Damian"));
        namesRepository.saveNameDrawnFromUserInput(new Name("Damian"));
        namesRepository.saveNameDrawnFromUserInput(new Name("Karol"));
        namesRepository.saveNameDrawnFromUserInput(new Name("Damian"));
        namesRepository.saveNameDrawnFromUserInput(new Name("Jacek"));
        Map<String, Long> expected = new TreeMap<>();
        expected.put("Damian", 5L);
        expected.put("Karol", 3L);
        expected.put("Jacek", 2L);
        expected.put("Marek", 1L);
        //when
        Map<String, Long> drawingResult = namesService.generateStatisticsFromUserInputDraw();
        //then
        assertEquals(expected, drawingResult);
    }

}