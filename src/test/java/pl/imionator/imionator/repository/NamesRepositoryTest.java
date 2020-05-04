package pl.imionator.imionator.repository;

import org.junit.jupiter.api.Test;
import pl.imionator.imionator.domain.Name;
import pl.imionator.imionator.domain.NameCategory;
import pl.imionator.imionator.domain.Sex;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NamesRepositoryTest {
    NamesLists namesLists = new NamesLists();
    NamesRepository namesRepository = new NamesRepository();

    @Test
    public void saveNamesDrawnFromPropositionList_namesWithEmptyFirstNameAreAdde_shouldSaveOnlyTwoNamesWithEmptyFirstName() {
        //given
        namesRepository.saveNameDrawnFromPropositionList(new Name("Janina", Sex.GIRL, NameCategory.ORDINARY));
        namesRepository.saveNameDrawnFromPropositionList(new Name("Oliwia", Sex.GIRL, NameCategory.ORDINARY));
        namesRepository.saveNameDrawnFromPropositionList(new Name("Justyna", Sex.GIRL, NameCategory.ORDINARY));
        namesRepository.saveNameDrawnFromPropositionList(new Name("", Sex.GIRL, NameCategory.ORDINARY));
        namesRepository.saveNameDrawnFromPropositionList(new Name("", Sex.GIRL, NameCategory.ORDINARY));
        namesRepository.saveNameDrawnFromPropositionList(new Name("Jadwiga", Sex.GIRL, NameCategory.ORDINARY));
        namesRepository.saveNameDrawnFromPropositionList(new Name("", Sex.GIRL, NameCategory.ORDINARY));
        namesRepository.saveNameDrawnFromPropositionList(new Name("", Sex.GIRL, NameCategory.ORDINARY));
        //when
        List<Name> names = namesRepository.getNamesDrawnFromPropositionList();
        //then
        assertEquals(6, names.size());
    }

    @Test
    void getNamesFromGivenCategory_SexGirlOrdinaryCategory_sizeShouldEqual55() {
        //given
        List<String> names = namesRepository.getNamesFromGivenCategory(Sex.GIRL, NameCategory.ORDINARY);
        //when
        int size = names.size();
        //then
        assertEquals(155, size);
    }

    @Test
    void getNamesFromGivenCategory_SexGirlOrdinaryModern_sizeShouldEqua41() {
        //given
        List<String> names = namesRepository.getNamesFromGivenCategory(Sex.GIRL, NameCategory.MODERN);
        //when
        int size = names.size();
        //then
        assertEquals(41, size);
    }

    @Test
    void getNamesDrawnFromPropositionList_noNamesDrawnYet_drawnNamesListSizeShouldBeZero() {
        //given

        //when
        List<Name> names = namesRepository.getNamesDrawnFromPropositionList();
        //then
        assertEquals(0, names.size());
    }


    @Test
    void getNamesDrawnFromPropositionList_namesAreAdded_shouldReturnLastAddedNameKarolina() {
        //given
        namesRepository.saveNameDrawnFromPropositionList(new Name("Damian", Sex.BOY, NameCategory.ORDINARY));
        namesRepository.saveNameDrawnFromPropositionList(new Name("Zosia", Sex.GIRL, NameCategory.ORDINARY));
        namesRepository.saveNameDrawnFromPropositionList(new Name("Rafał", Sex.BOY, NameCategory.ORDINARY));
        namesRepository.saveNameDrawnFromPropositionList(new Name("Karolina", Sex.GIRL, NameCategory.ORDINARY));
        //when
        List<Name> names = namesRepository.getNamesDrawnFromPropositionList();
        Name lastDrawnName = names.get(names.size() - 1);
        //then
        assertEquals(lastDrawnName.getFirstName(), "Karolina");
    }

    @Test
    void removeDrawnName_OneNameIsDrawnFromList_shouldRemoveDrawnNameFromPropositionList() {
        //given

        //when
        namesRepository.removeDrawnName(NameCategory.MODERN, Sex.BOY, "Eliot");
        //then
        assertFalse(namesRepository.getModernBoyNames().contains("Eliot"));
    }
}