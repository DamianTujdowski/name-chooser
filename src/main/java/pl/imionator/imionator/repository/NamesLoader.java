package pl.imionator.imionator.repository;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class NamesLoader {

    List<String> fillOrdinaryGirlNamesList() {
        return readFromFile("static/names/ordinary_girl_names.txt");
    }

    List<String> fillUnusualGirlNamesList() {
        return readFromFile("static/names/unusual_girl_names.txt");
    }

    List<String> fillModernGirlNamesList() {
        return readFromFile("static/names/modern_girl_names.txt");
    }

    List<String> fillOldFashionedGirlNamesList() {
        return readFromFile("static/names/old_fashioned_girl_names.txt");
    }

    List<String> fillOrdinaryBoyNamesList() {
        return readFromFile("static/names/ordinary_boy_names.txt");
    }

    List<String> fillUnusualBoyNamesList() {
        return readFromFile("static/names/unusual_boy_names.txt");
    }

    List<String> fillModernBoyNamesList() {
        return readFromFile("static/names/modern_boy_names.txt");
    }

    List<String> fillOldFashionedBoyNamesList() {
        return readFromFile("static/names/old_fashioned_boy_names.txt");
    }

    private List<String> readFromFile(String resourcePath) {
        List<String> list = new ArrayList<>();
        try {
            list = read(resourcePath);
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private List<String> read(String resourcePath) throws URISyntaxException, IOException {
        Path path = Paths.get(Objects.requireNonNull(getClass()
                .getClassLoader()
                .getResource(resourcePath))
                .toURI());

        Stream<String> lines = Files.lines(path);

        List<String> list = lines
                .map(this::getStreamFromLine)
                .flatMap(Function.identity())
                .collect(Collectors.toList());

        lines.close();
        return list;
    }

    private Stream<String> getStreamFromLine(String line) {
        return Arrays.stream(
                line
                .replaceAll("\n", "")
                .split(", ")
        );
    }

}