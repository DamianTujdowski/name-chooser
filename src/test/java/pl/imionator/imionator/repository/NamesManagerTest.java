package pl.imionator.imionator.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.imionator.imionator.domain.Name;
import pl.imionator.imionator.domain.NameCategory;
import pl.imionator.imionator.domain.Sex;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class NamesManagerTest {

    @Autowired
    private NamesLoader namesLoader;

    @Autowired
    private NamesManager namesManager;

    @Test
    void shouldLoadAllNamesFromFiles() {
        List<String> ordinaryGirlNames = namesManager.getNamesFromGivenCategory(Sex.GIRL, NameCategory.ORDINARY);
        assertEquals(155, ordinaryGirlNames.size());
        List<String> unusualGirlNames = namesManager.getNamesFromGivenCategory(Sex.GIRL, NameCategory.UNUSUAL);
        assertEquals(100, unusualGirlNames.size());
        List<String> modernGirlNames = namesManager.getNamesFromGivenCategory(Sex.GIRL, NameCategory.MODERN);
        assertEquals(41, modernGirlNames.size());
        List<String> oldFashionedGirlNames = namesManager.getNamesFromGivenCategory(Sex.GIRL, NameCategory.OLD_FASHIONED);
        assertEquals(111, oldFashionedGirlNames.size());
        List<String> ordinaryBoyNames = namesManager.getNamesFromGivenCategory(Sex.BOY, NameCategory.ORDINARY);
        assertEquals(113, ordinaryBoyNames.size());
        List<String> unusualBoyNames = namesManager.getNamesFromGivenCategory(Sex.BOY, NameCategory.UNUSUAL);
        assertEquals(87, unusualBoyNames.size());
        List<String> modernBoyNames = namesManager.getNamesFromGivenCategory(Sex.BOY, NameCategory.MODERN);
        assertEquals(16, modernBoyNames.size());
        List<String> oldFashionedBoyNames = namesManager.getNamesFromGivenCategory(Sex.BOY, NameCategory.OLD_FASHIONED);
        assertEquals(126, oldFashionedBoyNames.size());
    }

    @Test
    void saveNamesDrawnFromPropositionList_namesWithEmptyFirstNameAreAdde_shouldSaveOnlyTwoNamesWithEmptyFirstName() {
        //given
        namesManager.saveNameDrawnFromPropositionList(new Name("Janina", Sex.GIRL, NameCategory.ORDINARY));
        namesManager.saveNameDrawnFromPropositionList(new Name("Oliwia", Sex.GIRL, NameCategory.ORDINARY));
        namesManager.saveNameDrawnFromPropositionList(new Name("Justyna", Sex.GIRL, NameCategory.ORDINARY));
        namesManager.saveNameDrawnFromPropositionList(new Name("", Sex.GIRL, NameCategory.ORDINARY));
        namesManager.saveNameDrawnFromPropositionList(new Name("", Sex.GIRL, NameCategory.ORDINARY));
        namesManager.saveNameDrawnFromPropositionList(new Name("Jadwiga", Sex.GIRL, NameCategory.ORDINARY));
        namesManager.saveNameDrawnFromPropositionList(new Name("", Sex.GIRL, NameCategory.ORDINARY));
        namesManager.saveNameDrawnFromPropositionList(new Name("", Sex.GIRL, NameCategory.ORDINARY));
        //when
        List<Name> names = namesManager.getNamesDrawnFromPropositionList();
        //then
        assertEquals(6, names.size());
    }

    @Test
    void getNamesDrawnFromPropositionList_noNamesDrawnYet_drawnNamesListSizeShouldBeZero() {
        //given

        //when
        List<Name> names = namesManager.getNamesDrawnFromPropositionList();
        //then
        assertEquals(0, names.size());
    }

    @Test
    void getNamesDrawnFromPropositionList_namesAreAdded_shouldReturnLastAddedNameKarolina() {
        //given
        namesManager.saveNameDrawnFromPropositionList(new Name("Damian", Sex.BOY, NameCategory.ORDINARY));
        namesManager.saveNameDrawnFromPropositionList(new Name("Zosia", Sex.GIRL, NameCategory.ORDINARY));
        namesManager.saveNameDrawnFromPropositionList(new Name("Rafał", Sex.BOY, NameCategory.ORDINARY));
        namesManager.saveNameDrawnFromPropositionList(new Name("Karolina", Sex.GIRL, NameCategory.ORDINARY));
        //when
        List<Name> names = namesManager.getNamesDrawnFromPropositionList();
        Name lastDrawnName = names.get(names.size() - 1);
        //then
        assertEquals(lastDrawnName.getFirstName(), "Karolina");
    }

    @Test
    void removeDrawnName_OneNameIsDrawnFromList_shouldRemoveDrawnNameFromPropositionList() {
        //given
        List<String> modernBoyNames = namesManager.getNamesFromGivenCategory(Sex.BOY, NameCategory.MODERN);
        //when
        namesManager.removeDrawnNameFromPropositions(NameCategory.MODERN, Sex.BOY, "Eliot");
        //then
        assertFalse(modernBoyNames.contains("Eliot"));
    }
}