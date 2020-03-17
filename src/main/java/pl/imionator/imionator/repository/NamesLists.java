package pl.imionator.imionator.repository;

import org.springframework.stereotype.Component;
import pl.imionator.imionator.domain.NameCategory;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public
class NamesLists {

    private List<String> ordinaryGirlNames;

    private List<String> unusualGirlNames;

    private List<String> modernGirlNames;

    private List<String> oldFashionedGirlNames;

    private List<String> ordinaryBoyNames;

    private List<String> unusualBoyNames;

    private List<String> modernBoyNames;

    private List<String> oldFashionedBoyNames;


    List<String> getOrdinaryGirlNames() {
        return initialize(ordinaryGirlNames, "ordinaryGirlNames");
    }

    List<String> getUnusualGirlNames() {
        return initialize(unusualGirlNames, "unusualGirlNames");
    }

    public List<String> getModernGirlNames() {
        return initialize(modernGirlNames, "modernGirlNames");
    }

    public List<String> getOldFashionedGirlNames() {
        return initialize(oldFashionedGirlNames, "oldFashionedGirlNames");
    }

    List<String> getOrdinaryBoyNames() {
        return initialize(ordinaryBoyNames, "ordinaryBoyNames");
    }

    List<String> getUnusualBoyNames() {
        return initialize(unusualBoyNames, "unusualBoyNames");
    }

    public List<String> getModernBoyNames() {
        return initialize(modernBoyNames, "modernBoyNames");
    }

    public List<String> getOldFashionedBoyNames() {
        return initialize(oldFashionedBoyNames, "oldFashionedBoyNames");
    }

    private List<String> initialize(List<String> list, String listName) {
        if (list == null) {
            switch (listName) {
                case "ordinaryGirlNames":
                    ordinaryGirlNames = new ArrayList<>();
                    fillOrdinaryGirlNamesList();
                    list = ordinaryGirlNames;
                    break;
                case "unusualGirlNames":
                    unusualGirlNames = new ArrayList<>();
                    fillUnusualGirlNamesList();
                    list = unusualGirlNames;
                    break;
                case "modernGirlNames":
                    modernGirlNames = new ArrayList<>();
                    fillModernGirlNamesList();
                    list = modernGirlNames;
                    break;
                case "oldFashionedGirlNames":
                    oldFashionedGirlNames = new ArrayList<>();
                    fillOldFashionedGirlNamesList();
                    list = oldFashionedGirlNames;
                    break;
                case "ordinaryBoyNames":
                    ordinaryBoyNames = new ArrayList<>();
                    fillOrdinaryBoyNamesList();
                    list = ordinaryBoyNames;
                    break;
                case "unusualBoyNames":
                    unusualBoyNames = new ArrayList<>();
                    fillUnusualBoyNamesList();
                    list = unusualBoyNames;
                    break;
                case "modernBoyNames":
                    modernBoyNames = new ArrayList<>();
                    fillModernBoyNamesList();
                    list = modernBoyNames;
                    break;
                case "oldFashionedBoyNames":
                    oldFashionedBoyNames = new ArrayList<>();
                    fillOldFashionedBoyNamesList();
                    list = oldFashionedBoyNames;
                    break;
            }
            return list;
        } else {
            return list;
        }
    }

    private void fillOrdinaryGirlNamesList() {
        Collections.addAll(ordinaryGirlNames, "Ada", "Adela", "Adrianna", "Agata", "Agnieszka", "Aldona",
                "Alicja", "Alina", "Anastazja", "Andżelika", "Aneta", "Aniela", "Anita", "Anna", "Arleta", "Aurelia",
                "Barbara", "Beata", "Bernadeta", "Bianka", "Blanka", "Bogna", "Bogumiła", "Bożena", "Brygida", "Cecylia",
                "Celina", "Dagmara", "Dalia", "Daniela", "Danuta", "Daria", "Dominika", "Dorota", "Edyta", "Eliza", "Elena",
                "Elwira", "Elżbieta", "Emilia", "Estera", "Eugenia", "Ewa", "Ewelina", "Felicja", "Gabriela", "Gloria",
                "Gracja", "Grażyna", "Greta", "Halina", "Halszka", "Hanna", "Helena", "Honorata", "Hortensja", "Ida",
                "Idalia", "Iga", "Ilona", "Inga", "Inka", "Irena", "Irmina", "Iwona", "Izabela", "Jagoda", "Joanna",
                "Jolanta", "Judyta", "Julia", "Julita", "Justyna", "Kaja", "Kama", "Kalina", "Karina", "Karolina",
                "Katarzyna", "Kinga", "Klara", "Klaudia", "Kornelia", "Krystyna", "Laura", "Lena", "Leokadia",
                "Lidia", "Lila", "Lilianna", "Lilia", "Liwia", "Lucyna", "Ludmiła", "Luiza", "Łucja", "Magdalena", "Maja",
                "Malina", "Malwina", "Małgorzata", "Marcela", "Marcelina", "Maria", "Marietta", "Mariola", "Marlena",
                "Marta", "Martyna", "Maryla", "Marzena", "Matylda", "Michalina", "Milena", "Monika", "Nadia", "Natalia",
                "Natasza", "Nela", "Nikola", "Nina", "Nikoletta", "Odeta", "Oksana", "Olga", "Oliwia", "Otylia", "Patrycja",
                "Paulina", "Pola", "Rebeka", "Renata", "Rita", "Roksana", "Roma", "Róża", "Sabina", "Sandra", "Sara", "Sonia",
                "Sylwia", "Tamara", "Tatiana", "Teresa", "Ula", "Urszula", "Waleria", "Wanda", "Weronika", "Wiktoria",
                "Wioletta", "Zofia", "Zuzanna", "Zyta", "Żaneta");
    }

    private void fillUnusualGirlNamesList() {
        Collections.addAll(unusualGirlNames, "Adamina", "Adria", "Aida", "Albina", "Amalia", "Amira", "Arabella",
                "Ariadna", "Astyrda", "Atena", "Babeta", "Beatrycze", "Benigna", "Berenika", "Berta", "Bibiana", "Bojana",
                "Cezaria", "Celestia", "Cina", "Dajmira", "Dagna", "Damroka", "Deresa", "Delinda", "Dilara", "Donata",
                "Edeltruda", "Elmira", "Elora", "Emina", "Eunika", "Ewarysta", "Faria", "Fabiola", "Fera", "Herma",
                "Hermina", "Hestia", "Ilga", "Ilia", "Iryda", "Irina", "Jokasta", "Juta", "Kamira", "Kiliana", "Kira",
                "Kleopatra", "Kodrula", "Koryna", "Lana", "Lara", "Larysa", "Leoncja", "Letycja", "Lola", "Marcjanna",
                "Marzanna", "Miroda", "Nitara", "Noemi", "Nora", "Nita", "Odyla", "Ofelia", "Odyta", "Oktawia", "Olimpia",
                "Oriana", "Ota", "Ożanna", "Obina", "Olena", "Olianna", "Petra", "Polianna", "Rachela", "Ramona", "Rajna",
                "Raszyda", "Regina", "Rozwita", "Ruta", "Ryksa", "Sarina", "Sarita", "Safana", "Salma", "Saloma", "Sora",
                "Sybilla", "Sydonia", "Tacjana", "Tybita", "Tessa", "Wilhelmina", "Wiwianna", "Żanna", "Żywia");
    }

    private void fillModernGirlNamesList() {
        Collections.addAll(modernGirlNames, "Adelajda", "Alberta", "Aleksa", "Amanda", "Amelia", "Andrea", "Angela",
                "Brenda", "Delfina", "Delia", "Diana", "Emanuela", "Emma", "Fiona", "Fabia", "Gaja", "Iliana", "Irma", "Jessica",
                "Kasandra", "Kiara", "Keira", "Laila", "Lea", "Lisa", "Luna", "Manuela", "Marika", "Minerwa", "Miranda",
                "Norma", "Pamela", "Rafaela", "Rut", "Sabrina", "Samanta", "Samara", "Selena", "Stella", "Tina", "Wanessa");
    }

    private void fillOldFashionedGirlNamesList() {
        Collections.addAll(oldFashionedGirlNames, "Albertyna", "Alfreda", "Alojza", "Antonina", "Anzelma", "Apolonia",
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

    private void fillOrdinaryBoyNamesList() {
        Collections.addAll(ordinaryBoyNames, "Adam", "Adrian", "Albert", "Aleksander", "Alfred", "Andrzej",
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

    private void fillUnusualBoyNamesList() {
        Collections.addAll(unusualBoyNames, "Aaron", "Abdon", "Abel", "Abelard", "Alfred", "Agrypin", "Albin",
                "Alwin", "Annasz", "Aron", "Aurelian", "Baldwin", "Beat", "Benon", "Bert", "Bolebor", "Dacjusz", "Danko",
                "Dymitr", "Eligiusz", "Emanuel", "Emir", "Erazm", "Ewald", "Ewaryst", "Faustyn", "Felicjan", "Fortunat",
                "Godfryd", "Gotard", "Hektor", "Hiacynt", "Hilary", "Honorat", "Izajasz", "Jacenty", "January", "Jonasz",
                "Justyn", "Justynian", "Kajusz", "Kastor", "Kemal", "Kordian", "Kosma", "Krzesimir", "Laurencjusz",
                "Lenart", "Lew", "Longin", "Ludmił", "Ludolf", "Makary", "Maksymin", "Manfred", "Manuel", "Marcjan",
                "Medard", "Metody", "Milan", "Modest", "Narcyz", "Nestor", "Norman", "Odon", "Oktawian", "Orest", "Orian",
                "Owidiusz", "Pafnucy", "Polikarp", "Prokop", "Protazy", "Rajmund", "Roland", "Rufus", "Sambor", "Stojan",
                "Sydoniusz", "Sylwan", "Telesfor", "Tyberiusz", "Urban", "Ursyn", "Walenty", "Waryn", "Zachariasz");
    }

    private void fillModernBoyNamesList() {
        Collections.addAll(modernBoyNames, "Alan", "Beniamin", "Brajan", "Bruno", "Denis", "Donat", "Edgar",
                "Edwin", "Eliot", "Erwin", "Gilbert", "Jonatan", "Kilian", "Leo", "Natan", "Nikolas");
    }

    private void fillOldFashionedBoyNamesList() {
        Collections.addAll(oldFashionedBoyNames, "Abraham", "Agaton", "Albrecht", "Alfons", "Alojzy", "Amadeusz",
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
}