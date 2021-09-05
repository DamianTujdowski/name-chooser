package pl.imionator.imionator.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.imionator.imionator.domain.Name;
import pl.imionator.imionator.domain.NameCategory;
import pl.imionator.imionator.domain.Sex;
import pl.imionator.imionator.repository.NamesManager;
import pl.imionator.imionator.repository.NamesLoader;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class NamesServiceTest {

    @Autowired
    private NamesLoader namesLoader;

    @Autowired
    private NamesManager namesManager;

    @Autowired
    private NamesService namesService;

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
        namesManager.saveNameDrawnFromPropositionList(new Name("Damian", Sex.BOY, NameCategory.ORDINARY));
        namesManager.saveNameDrawnFromPropositionList(new Name("Zosia", Sex.GIRL, NameCategory.ORDINARY));
        namesManager.saveNameDrawnFromPropositionList(new Name("Rafał", Sex.BOY, NameCategory.ORDINARY));
        namesManager.saveNameDrawnFromPropositionList(new Name("Karolina", Sex.GIRL, NameCategory.ORDINARY));
        //when
        Name lastDrawnName = namesService.getLastNameDrawnFromPropositionList();
        //then
        assertEquals("Karolina", lastDrawnName.getFirstName());
    }

    @Test
    void getRandomNameFromGivenCategory_SexGirlOrdinaryCategory_shouldBeTrue() {
        //given
        List<String> ordinaryGirlNames = namesManager.getNamesFromGivenCategory(Sex.GIRL, NameCategory.ORDINARY);
        //when
        String ordinaryName = "Beata";
        //then
        assertTrue(ordinaryGirlNames.contains(ordinaryName));
    }

    @Test
    void getRandomNameFromGivenCategory_SexBoyModernCategory_shouldBeTrue() {
        //given
        List<String> modernBoyNames = namesManager.getNamesFromGivenCategory(Sex.BOY, NameCategory.MODERN);
        //when
        String modernName = "Eliot";
        //then
        assertTrue(modernBoyNames.contains(modernName));
    }

    @Test
    void generateStatisticsFromPropositionListDrawBySex_SexBoy_shouldContainOnlyBoyNames() {
        //given
        namesManager.saveNameDrawnFromPropositionList(new Name("Damian", Sex.BOY, NameCategory.ORDINARY));
        namesManager.saveNameDrawnFromPropositionList(new Name("Zosia", Sex.GIRL, NameCategory.ORDINARY));
        namesManager.saveNameDrawnFromPropositionList(new Name("Rafał", Sex.BOY, NameCategory.ORDINARY));
        namesManager.saveNameDrawnFromPropositionList(new Name("Karolina", Sex.GIRL, NameCategory.ORDINARY));
        namesManager.saveNameDrawnFromPropositionList(new Name("Julek", Sex.BOY, NameCategory.ORDINARY));
        namesManager.saveNameDrawnFromPropositionList(new Name("Kamila", Sex.GIRL, NameCategory.ORDINARY));
        namesManager.saveNameDrawnFromPropositionList(new Name("Klaudiusz", Sex.BOY, NameCategory.ORDINARY));
        namesManager.saveNameDrawnFromPropositionList(new Name("Julia", Sex.GIRL, NameCategory.ORDINARY));
        //when
        List<Name> boyNamesFromPropositionList = namesService.generateStatisticsFromPropositionListDrawBySex(Sex.BOY);
        //then
        assertFalse(boyNamesFromPropositionList.contains(new Name("Zosia", Sex.GIRL, NameCategory.ORDINARY)));
        assertEquals(4, boyNamesFromPropositionList.size());
    }

    @Test
    void generateStatisticsFromUserInputDraw_fourNamesAdded_shouldReturnNamesInCorrectOrder() {
        //given
        namesManager.saveNameDrawnFromUserInput(new Name("Damian"));
        namesManager.saveNameDrawnFromUserInput(new Name("Karol"));
        namesManager.saveNameDrawnFromUserInput(new Name("Marek"));
        namesManager.saveNameDrawnFromUserInput(new Name("Damian"));
        namesManager.saveNameDrawnFromUserInput(new Name("Karol"));
        namesManager.saveNameDrawnFromUserInput(new Name("Jacek"));
        namesManager.saveNameDrawnFromUserInput(new Name("Damian"));
        namesManager.saveNameDrawnFromUserInput(new Name("Damian"));
        namesManager.saveNameDrawnFromUserInput(new Name("Karol"));
        namesManager.saveNameDrawnFromUserInput(new Name("Damian"));
        namesManager.saveNameDrawnFromUserInput(new Name("Jacek"));
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