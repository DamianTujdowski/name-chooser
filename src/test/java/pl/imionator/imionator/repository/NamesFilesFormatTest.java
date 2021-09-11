package pl.imionator.imionator.repository;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class NamesFilesFormatTest {

    @Test
    void shouldLoad_ordinaryGirlNames_WithoutWhitespacesAndCommas() {
        List<String> ordinaryGirlNames = readResult("names/ordinary_girl_names.txt");
        String filtered = ordinaryGirlNames.stream()
                .map(name -> name.replaceAll("[^ ,]", ""))
                .collect(Collectors.joining());
        assertEquals(0, filtered.length());
    }

    @Test
    void shouldLoad_unusualGirlNames_WithoutWhitespacesAndCommas() {
        List<String> ordinaryGirlNames = readResult("names/unusual_girl_names.txt");
        String filtered = ordinaryGirlNames.stream()
                .map(name -> name.replaceAll("[^ ,]", ""))
                .collect(Collectors.joining());
        assertEquals(0, filtered.length());
    }

    @Test
    void shouldLoad_modernGirlNames_WithoutWhitespacesAndCommas() {
        List<String> ordinaryGirlNames = readResult("names/modern_girl_names.txt");
        String filtered = ordinaryGirlNames.stream()
                .map(name -> name.replaceAll("[^ ,]", ""))
                .collect(Collectors.joining());
        assertEquals(0, filtered.length());
    }

    @Test
    void shouldLoad_oldFashionedGirlNames_WithoutWhitespacesAndCommas() {
        List<String> ordinaryGirlNames = readResult("names/old_fashioned_girl_names.txt");
        String filtered = ordinaryGirlNames.stream()
                .map(name -> name.replaceAll("[^ ,]", ""))
                .collect(Collectors.joining());
        assertEquals(0, filtered.length());
    }

    @Test
    void shouldLoad_ordinaryBoyNames_WithoutWhitespacesAndCommas() {
        List<String> ordinaryGirlNames = readResult("names/ordinary_boy_names.txt");
        String filtered = ordinaryGirlNames.stream()
                .map(name -> name.replaceAll("[^ ,]", ""))
                .collect(Collectors.joining());
        assertEquals(0, filtered.length());
    }

    @Test
    void shouldLoad_unusualBoyNames_WithoutWhitespacesAndCommas() {
        List<String> ordinaryGirlNames = readResult("names/unusual_boy_names.txt");
        String filtered = ordinaryGirlNames.stream()
                .map(name -> name.replaceAll("[^ ,]", ""))
                .collect(Collectors.joining());
        assertEquals(0, filtered.length());
    }

    @Test
    void shouldLoad_modernBoyNames_WithoutWhitespacesAndCommas() {
        List<String> ordinaryGirlNames = readResult("names/modern_boy_names.txt");
        String filtered = ordinaryGirlNames.stream()
                .map(name -> name.replaceAll("[^ ,]", ""))
                .collect(Collectors.joining());
        assertEquals(0, filtered.length());
    }

    @Test
    void shouldLoad_oldFashionedBoyNames_WithoutWhitespacesAndCommas() {
        List<String> ordinaryGirlNames = readResult("names/old_fashioned_boy_names.txt");
        String filtered = ordinaryGirlNames.stream()
                .map(name -> name.replaceAll("[^ ,]", ""))
                .collect(Collectors.joining());
        assertEquals(0, filtered.length());
    }



    private List<String> readResult(String filePath) {
        InputStream input = NamesLoader.class.getClassLoader().getResourceAsStream(filePath);
        InputStreamReader streamReader = new InputStreamReader(Objects.requireNonNull(input), StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);

        return reader.lines()
                .flatMap(this::mapLineToStream)
                .collect(Collectors.toList());

    }

    private Stream<String> mapLineToStream(String line) {
        return Arrays.stream(
                        line
                        .replaceAll("\n", "")
                        .split(", ")
        );
    }

}