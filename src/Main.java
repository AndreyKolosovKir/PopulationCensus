import org.w3c.dom.ls.LSOutput;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> familis = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                            names.get(new Random().nextInt(names.size())),
                            familis.get(new Random().nextInt(familis.size())),
                            new Random().nextInt(100),
                            Sex.values()[new Random().nextInt(Sex.values().length)],
                            Education.values()[new Random().nextInt(Education.values().length)]
                    )
            );
        }

        long count1 = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();
        System.out.println(count1);


        List<String> listFamilies = persons.stream()
                .filter(person -> person.getAge() >= 18 && person.getAge() <= 27)
                .map(person -> person.toString())
                .collect(Collectors.toList());
        System.out.println(listFamilies);

        List<String> listFamilies2 = persons.stream()
                .filter(person -> person.getEducation().equals(Education.HIGHER))
                .filter(person -> person.getAge() >= 18)
                .filter(person -> person.getSex().equals(Sex.MAN) ? person.getAge() <= 65 : person.getAge() <= 60)
                .sorted(Comparator.comparing(Person::getFamily))
                .map(person -> person.toString())
                .collect(Collectors.toList());
        System.out.println(listFamilies2);
    }
}
