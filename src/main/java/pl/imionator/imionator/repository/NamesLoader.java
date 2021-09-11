package pl.imionator.imionator.repository;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class NamesLoader {

    List<String> fillOrdinaryGirlNamesList() {
        return readFromFile("names/ordinary_girl_names.txt");
    }

    List<String> fillUnusualGirlNamesList() {
        return readFromFile("names/unusual_girl_names.txt");
    }

    List<String> fillModernGirlNamesList() {
        return readFromFile("names/modern_girl_names.txt");
    }

    List<String> fillOldFashionedGirlNamesList() {
        return readFromFile("names/old_fashioned_girl_names.txt");
    }

    List<String> fillOrdinaryBoyNamesList() {
        return readFromFile("names/ordinary_boy_names.txt");
    }

    List<String> fillUnusualBoyNamesList() {
        return readFromFile("names/unusual_boy_names.txt");
    }

    List<String> fillModernBoyNamesList() {
        return readFromFile("names/modern_boy_names.txt");
    }

    List<String> fillOldFashionedBoyNamesList() {
        return readFromFile("names/old_fashioned_boy_names.txt");
    }

    private List<String> readFromFile(String filePath) {

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
                        .replaceAll("\\n", "")
                        .split(", ")
        );
    }

}