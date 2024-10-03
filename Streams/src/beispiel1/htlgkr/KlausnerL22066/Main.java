package beispiel1.htlgkr.KlausnerL22066;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        List<Weapon> weapons = new ArrayList<>();


        weapons = Files.lines(new File("weapons.csv").toPath())
                .skip(1)
                .map(s -> s.split(";"))
                .map(s -> new Weapon(
                        s[0],
                        CombatType.valueOf(s[1]),
                        DamageType.valueOf(s[2]),
                        Integer.parseInt(s[3]),
                        Integer.parseInt(s[4]),
                        Integer.parseInt(s[5]),
                        Integer.parseInt(s[6])
                ))
                .collect(Collectors.toList());

        weapons.sort((w1, w2) -> {
            return Integer.compare(w1.getDamage(), w2.getDamage());
        });

        weapons.forEach(System.out::println);

        System.out.println();

        List<Weapon> sortedList = new ArrayList<>();
        sortedList = weapons.stream()
                .sorted(Comparator
                        .comparingInt(Weapon::getDamage)
                        .thenComparing(Weapon::getCombatType)
                        .thenComparing(Weapon::getDamageType)
                        .thenComparing(Weapon::getName))
                .toList();

        weapons.forEach(System.out::println);

        System.out.println();
        System.out.println("Sorted");
        System.out.println();

        sortedList.forEach(System.out::println);

        Printable printable = (List<Weapon> list) -> list.forEach(System.out::println);

    }

}
