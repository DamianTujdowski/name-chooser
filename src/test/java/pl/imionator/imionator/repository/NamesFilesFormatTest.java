package pl.imionator.imionator.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class NamesFilesFormatTest {

    @Test
    void shouldLoad_ordinaryGirlNames_WithoutWhitespacesAndCommas() {
        List<String> ordinaryGirlNames = readResult("static/names/ordinary_girl_names.txt");
        String filtered = ordinaryGirlNames.stream()
                .map(name -> name.replaceAll("[^ ,]", ""))
                .collect(Collectors.joining());
        assertEquals(0, filtered.length());
    }

    @Test
    void shouldLoad_unusualGirlNames_WithoutWhitespacesAndCommas() {
        List<String> ordinaryGirlNames = readResult("static/names/unusual_girl_names.txt");
        String filtered = ordinaryGirlNames.stream()
                .map(name -> name.replaceAll("[^ ,]", ""))
                .collect(Collectors.joining());
        assertEquals(0, filtered.length());
    }

    @Test
    void shouldLoad_modernGirlNames_WithoutWhitespacesAndCommas() {
        List<String> ordinaryGirlNames = readResult("static/names/modern_girl_names.txt");
        String filtered = ordinaryGirlNames.stream()
                .map(name -> name.replaceAll("[^ ,]", ""))
                .collect(Collectors.joining());
        assertEquals(0, filtered.length());
    }

    @Test
    void shouldLoad_oldFashionedGirlNames_WithoutWhitespacesAndCommas() {
        List<String> ordinaryGirlNames = readResult("static/names/old_fashioned_girl_names.txt");
        String filtered = ordinaryGirlNames.stream()
                .map(name -> name.replaceAll("[^ ,]", ""))
                .collect(Collectors.joining());
        assertEquals(0, filtered.length());
    }

    @Test
    void shouldLoad_ordinaryBoyNames_WithoutWhitespacesAndCommas() {
        List<String> ordinaryGirlNames = readResult("static/names/ordinary_boy_names.txt");
        String filtered = ordinaryGirlNames.stream()
                .map(name -> name.replaceAll("[^ ,]", ""))
                .collect(Collectors.joining());
        assertEquals(0, filtered.length());
    }

    @Test
    void shouldLoad_unusualBoyNames_WithoutWhitespacesAndCommas() {
        List<String> ordinaryGirlNames = readResult("static/names/unusual_boy_names.txt");
        String filtered = ordinaryGirlNames.stream()
                .map(name -> name.replaceAll("[^ ,]", ""))
                .collect(Collectors.joining());
        assertEquals(0, filtered.length());
    }

    @Test
    void shouldLoad_modernBoyNames_WithoutWhitespacesAndCommas() {
        List<String> ordinaryGirlNames = readResult("static/names/modern_boy_names.txt");
        String filtered = ordinaryGirlNames.stream()
                .map(name -> name.replaceAll("[^ ,]", ""))
                .collect(Collectors.joining());
        assertEquals(0, filtered.length());
    }

    @Test
    void shouldLoad_oldFashionedBoyNames_WithoutWhitespacesAndCommas() {
        List<String> ordinaryGirlNames = readResult("static/names/old_fashioned_boy_names.txt");
        String filtered = ordinaryGirlNames.stream()
                .map(name -> name.replaceAll("[^ ,]", ""))
                .collect(Collectors.joining());
        assertEquals(0, filtered.length());
    }



    private List<String> readResult(String filePath) {
        List<String> names = new ArrayList<>();
        try {
            Path path = Paths.get(Objects.requireNonNull(getClass()
                    .getClassLoader()
                    .getResource(filePath))
                    .toURI());
            Stream<String> lines = Files.lines(path);
            names = lines
                    .map(this::getStreamFromLine)
                    .flatMap(Function.identity())
                    .collect(Collectors.toList());
            lines.close();
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        return names;
    }

    private Stream<String> getStreamFromLine(String line) {
        return Arrays.stream(
                line
                        .replaceAll("\n", "")
                        .split(", ")
        );
    }

}