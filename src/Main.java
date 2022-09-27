import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        long underAgeStream = persons.stream()
                .filter(a -> a.getAge() < 18)
                .count();
        List<String> conscriptStream = persons.stream()
                .filter(a -> a.getAge() >= 18)
                .filter(a -> a.getAge() < 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());

        List<Person> workingAgeStream = persons.stream()
                .filter(a -> a.getAge() >= 18)
                .filter(e -> e.getEducation() == Education.HIGHER)
                .filter(p -> p.getSex() == Sex.WOMAN && p.getAge() < 60 || p.getSex() == Sex.MAN && p.getAge() < 65)
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());


        System.out.println(underAgeStream);
        for(Object l : conscriptStream) {
            System.out.println(l);
        }
        for (Object p : workingAgeStream) {
            System.out.println(p.toString());
        }

    }
}
