package pl.imionator.imionator.repository;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NamesListsTest {
    NamesLists namesLists = new NamesLists();

    @Test
    public void shouldReturnListWithTwoElements() {
        //when
        List<String> names = namesLists.getOrdinaryBoyNames();
        //then
        assertEquals(2, names.size());
    }

    @Test
    public void shouldReturnAdamName() {
        //given
        List<String> names = namesLists.getOrdinaryBoyNames();
        //when
        String name = names.get(0);
        //then
        assertEquals("Adam", name);
    }

}