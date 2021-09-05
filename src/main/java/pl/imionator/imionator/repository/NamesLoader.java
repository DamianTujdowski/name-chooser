package pl.imionator.imionator.repository;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
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

    List<String>  fillUnusualGirlNamesList() {
        return readFromFile("static/names/unusual_girl_names.txt");
    }

    void fillModernGirlNamesList(List<String> list) {
        Collections.addAll(list, "Adelajda", "Alberta", "Aleksa", "Amanda", "Amelia", "Andrea", "Angela",
                "Brenda", "Delfina", "Delia", "Diana", "Emanuela", "Emma", "Fiona", "Fabia", "Gaja", "Iliana", "Irma", "Jessica",
                "Kasandra", "Kiara", "Keira", "Laila", "Lea", "Lisa", "Luna", "Manuela", "Marika", "Minerwa", "Miranda",
                "Norma", "Pamela", "Rafaela", "Rut", "Sabrina", "Samanta", "Samara", "Selena", "Stella", "Tina", "Wanessa");
    }

    void fillOldFashionedGirlNamesList(List<String> list) {
        Collections.addAll(list, "Albertyna", "Alfreda", "Alojza", "Antonina", "Anzelma", "Apolonia",
                "Augusta", "Balbina", "Beatrycja", "Benedykta", "Błażena", "Boguchwała", "Bolesława", "Bona", "Bronisława",
                "Bromira", "Brunhilda", "Celestyna", "Czesława", "Ciesława", "Cinosława", "Dana", "Dargomira", "Dargosława",
                "Dąbrówka", "Dobiesława", "Dobrochna", "Domasława", "Eleonora", "Ernesta", "Ernestyna", "Eryka", "Faustyna",
                "Felicjana", "Feliksa", "Filipa", "Filomena", "Flora", "Florentyna", "Franciszka", "Fryderyka", "Genowefa",
                "Gertruda", "Gizela", "Gryzelda", "Hadriana", "Henrieta", "Henryka", "Hermenegilda", "Hiacynta", "Hilaria",
                "Hildegarda", "Hipolita", "Huberta", "Idosława", "Ildefonsa", "Ingeborga", "Ingryda", "Jadwiga", "Jagna",
                "Janina", "Jarmiłą", "Jaromiła", "Jaśmina", "Jowita", "Józefina", "Julisława", "Kazimiera", "Klarysa",
                "Klaudyna", "Klementyna", "Klotylda", "Konstancja", "Ksenia", "Kunegunda", "Laurencja", "Leonora", "Leontyna",
                "Leopolda", "Luborada", "Lucjana", "Melania", "Mieczysława", "Miłosława", "Miłowita", "Mojmira", "Najmiła",
                "Najsława", "Pelagia", "Petronela", "Polmira", "Przemysława", "Przybysława", "Rozalia", "Rozalinda",
                "Rozamunda", "Rudolfina", "Serafina", "Sobiesława", "Telimena", "Teodora", "Teofilia", "Walentyna",
                "Wiesłąwa", "Wisława", "Władysława", "Włodzimiera", "Zdzisława", "Zenobia", "Żelisława", "Żywisława");
    }

    void fillOrdinaryBoyNamesList(List<String> list) {
        Collections.addAll(list, "Adam", "Adrian", "Albert", "Aleksander", "Alfred", "Andrzej",
                "Antoni", "Ariel", "Arkadiusz", "Arnold", "Artur", "August", "Bartłomiej", "Bartosz", "Bernard", "Błażej",
                "Bogdan", "Bogumił", "Bogusław", "Bogusz", "Borys", "Bożydar", "Cecyl", "Cezary", "Cyprian", "Cyryl",
                "Czesław", "Damian", "Daniel", "Dariusz", "Dawid", "Dominik", "Donald", "Dorian", "Edmund", "Edward",
                "Emil", "Eugeniusz", "Eryk", "Fabian", "Filip", "Florian", "Gabriel", "Gaweł", "Gracjan", "Grzegorz",
                "Hubert", "Igor", "Ireneusz", "Iwo", "Jacek", "Jakub", "Jan", "Janusz", "Jarosław", "Jędrzej",
                "Joachim", "Julian", "Juliusz", "Kacper", "Kajetan", "Kamil", "Karol", "Klaudiusz", "Konrad", "Kornel",
                "Korneliusz", "Kryspin", "Krystian", "Krzysztof", "Lucjan", "Ludwik", "Łukasz", "Maciej", "Maksymilian",
                "Marcel", "Marceli", "Marcin", "Marek", "Mariusz", "Mateusz", "Maurycy", "Michał", "Mikołaj", "Miłosz",
                "Norbert", "Olaf", "Olgierd", "Oliwier", "Oskar", "Patryk", "Paweł", "Piotr", "Radosław", "Rafał",
                "Remigiusz", "Robert", "Sebastian", "Serafin", "Sergiusz", "Seweryn", "Sławomir", "Stefan", "Sylwester",
                "Szymon", "Tadeusz", "Tomasz", "Tymon", "Tymoteusz", "Tytus", "Wiktor", "Witold", "Wojciech");
    }

    void fillUnusualBoyNamesList(List<String> list) {
        Collections.addAll(list, "Aaron", "Abdon", "Abel", "Abelard", "Alfred", "Agrypin", "Albin",
                "Alwin", "Annasz", "Aron", "Aurelian", "Baldwin", "Beat", "Benon", "Bert", "Bolebor", "Dacjusz", "Danko",
                "Dymitr", "Eligiusz", "Emanuel", "Emir", "Erazm", "Ewald", "Ewaryst", "Faustyn", "Felicjan", "Fortunat",
                "Godfryd", "Gotard", "Hektor", "Hiacynt", "Hilary", "Honorat", "Izajasz", "Jacenty", "January", "Jonasz",
                "Justyn", "Justynian", "Kajusz", "Kastor", "Kemal", "Kordian", "Kosma", "Krzesimir", "Laurencjusz",
                "Lenart", "Lew", "Longin", "Ludmił", "Ludolf", "Makary", "Maksymin", "Manfred", "Manuel", "Marcjan",
                "Medard", "Metody", "Milan", "Modest", "Narcyz", "Nestor", "Norman", "Odon", "Oktawian", "Orest", "Orian",
                "Owidiusz", "Pafnucy", "Polikarp", "Prokop", "Protazy", "Rajmund", "Roland", "Rufus", "Sambor", "Stojan",
                "Sydoniusz", "Sylwan", "Telesfor", "Tyberiusz", "Urban", "Ursyn", "Walenty", "Waryn", "Zachariasz");
    }

    void fillModernBoyNamesList(List<String> list) {
        Collections.addAll(list, "Alan", "Beniamin", "Brajan", "Bruno", "Denis", "Donat", "Edgar",
                "Edwin", "Eliot", "Erwin", "Gilbert", "Jonatan", "Kilian", "Leo", "Natan", "Nikolas");
    }

    void fillOldFashionedBoyNamesList(List<String> list) {
        Collections.addAll(list, "Abraham", "Agaton", "Albrecht", "Alfons", "Alojzy", "Amadeusz",
                "Ambroży", "Anastazy", "Anatol", "Anzelm", "Apolinary", "Apoloniusz", "Arkady", "Aureliusz", "Baltazar",
                "Barabasz", "Bazyli", "Benedykt", "Boguchwał", "Bolesław", "Bonawentura", "Bonifacy", "Bronisław", "Brunon",
                "Budzisław", "Cecylian", "Celestyn", "Dalbor", "Damazy", "Danisław", "Darwit", "Dionizy", "Dobiesław",
                "Eliasz", "Ernest", "Feliks", "Ferdynand", "Florentyn", "Franciszek", "Fryderyk", "Gerard", "Gerwazy",
                "Gniewosz", "Gustaw", "Gwido", "Gwidon", "Heliodor", "Henryk", "Herakliusz", "Herbert", "Hermes",
                "Hieronim", "Hipolit", "Horacy", "Hugo", "Hugon", "Ignacy", "Ildefons", "Izydor", "Jaromir", "Jeremiasz",
                "Józef", "Jerzy", "Kazimierz", "Klemens", "Konstanty", "Ksawery", "Lech", "Leon", "Leokadiusz", "Leonard",
                "Leopold", "Lesław", "Lubomir", "Łucjan", "Mieczysław", "Miłomir", "Miłosław", "Miron", "Mirosław",
                "Mojmir", "Nataniel", "Nikodem", "Onufry", "Otto", "Pankracy", "Pelagiusz", "Petroniusz", "Przemysław",
                "Radomił", "Radzimir", "Rajmund", "Roman", "Roch", "Romuald", "Ryszard", "Samuel", "Serwacy", "Sobiesław",
                "Stanisław", "Szczepan", "Teodor", "Teofil", "Tobiasz", "Wacław", "Waldemar", "Walerian", "Walery",
                "Wawrzyniec", "Wespazjan", "Wielisław", "Wilhelm", "Wincenty", "Wirgiliusz", "Wit", "Władysław",
                "Włodzimierz", "Włodzisław", "Zbigniew", "Zdzisław", "Zenobiusz", "Zenon", "Zygfryd", "Zygmunt", "Żarko",
                "Żelisław");
    }

    private List<String> readFromFile(String resourcePath) {
        List<String> list = new ArrayList<>();

        try {
            Path path = Paths.get(Objects.requireNonNull(getClass()
                    .getClassLoader()
                    .getResource(resourcePath))
                    .toURI());

            Stream<String> lines = Files.lines(path);

            list = lines
                    .map(this::getStreamFromLine)
                    .flatMap(Function.identity())
                    .collect(Collectors.toList());

            lines.close();
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private Stream<String> getStreamFromLine(String line) {
        return Arrays.stream(line
                .replaceAll("\n", "")
                .split(", "));
    }

}