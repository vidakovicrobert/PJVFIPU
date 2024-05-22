import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Instanciranje studenata
        Student student1 = new Student("Marko", "Marković", 12345, new String[]{"Matematika", "Fizika"}, new String[]{"Ivan Ivanović", "Petar Petrović"});
        Student student2 = new Student("Ana", "Anić", 54321, new String[]{"Povijest", "Geografija"}, new String[]{"Marija Marić", "Ivana Ivančić"});

        // Instanciranje nastavnika
        Teacher teacher1 = new Teacher("Ivan", "Ivanović", new String[]{"Matematika", "Fizika"}, 3000);
        Teacher teacher2 = new Teacher("Marija", "Marić", new String[]{"Povijest", "Geografija"}, 2800);

        // Ispis detalja o studentima
        System.out.println("Detalji o studentu 1:");
        student1.displayDetails();
        System.out.println("\nDetalji o studentu 2:");
        student2.displayDetails();

        // Ispis detalja o nastavnicima
        System.out.println("\nDetalji o nastavniku 1:");
        teacher1.displayDetails();
        System.out.println("\nDetalji o nastavniku 2:");
        teacher2.displayDetails();

        Scanner scanner = new Scanner(System.in);

        // Lista za pohranu studenata i nastavnika
        List<Person> osobe = new ArrayList<>();

        // Unos studenata i nastavnika
        while (true) {
            System.out.println("\nŽelite li dodati novog studenta ili nastavnika? (student/nastavnik)");
            String vrstaOsobe = scanner.nextLine();

            if (!vrstaOsobe.equalsIgnoreCase("student") && !vrstaOsobe.equalsIgnoreCase("nastavnik")) {
                System.out.println("Nepoznata vrsta osobe. Molimo unesite 'student' ili 'nastavnik'.");
                continue;
            }

            System.out.println("Unesite ime:");
            String ime = scanner.nextLine();

            System.out.println("Unesite prezime:");
            String prezime = scanner.nextLine();

            if (vrstaOsobe.equalsIgnoreCase("student")) {
                System.out.println("Unesite ID studenta:");
                int studentID = scanner.nextInt();
                scanner.nextLine(); // Consuming newline character

                System.out.println("Unesite broj predmeta koje student sluša:");
                int brojPredmeta = scanner.nextInt();
                scanner.nextLine(); // Consuming newline character
                String[] predmeti = new String[brojPredmeta];
                for (int i = 0; i < brojPredmeta; i++) {
                    System.out.println("Unesite naziv predmeta " + (i + 1) + ":");
                    predmeti[i] = scanner.nextLine();
                }

                System.out.println("Unesite broj nastavnika:");
                int brojNastavnika = scanner.nextInt();
                scanner.nextLine(); // Consuming newline character
                String[] nastavnici = new String[brojNastavnika];
                for (int i = 0; i < brojNastavnika; i++) {
                    System.out.println("Unesite ime nastavnika " + (i + 1) + ":");
                    nastavnici[i] = scanner.nextLine();
                }

                osobe.add(new Student(ime, prezime, studentID, predmeti, nastavnici));
            } else {
                System.out.println("Unesite broj predmeta koje nastavnik predaje:");
                int brojPredmeta = scanner.nextInt();
                scanner.nextLine(); // Consuming newline character
                String[] predmeti = new String[brojPredmeta];
                for (int i = 0; i < brojPredmeta; i++) {
                    System.out.println("Unesite naziv predmeta " + (i + 1) + ":");
                    predmeti[i] = scanner.nextLine();
                }

                System.out.println("Unesite iznos plaće:");
                double iznosPlace = scanner.nextDouble();
                scanner.nextLine(); // Consuming newline character

                osobe.add(new Teacher(ime, prezime, predmeti, iznosPlace));
            }

            System.out.println("Želite li dodati još jednog studenta ili nastavnika? (da/ne)");
            String odgovor = scanner.nextLine();

            if (odgovor.equalsIgnoreCase("ne")) {
                break;
            }
        }

        // Ispis detalja o svim osobama
        for (Person osoba : osobe) {
            osoba.displayDetails();
            System.out.println();
        }

        // Zatvaranje skenera
        scanner.close();
    }
}