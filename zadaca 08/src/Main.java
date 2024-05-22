
/*
Napiši program koji prima imena dviju datoteka preko naredbenog retka
- svaki redak datoteke sadrži jedno ime
- program treba ispisati sva imena koja se nalaze u prvoj datoteci, a nisu u drugoj datoteci
- datoteke su relativno male (sadrže desetak imena)
- redoslijed ispisivanja imena je bitan.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        // Provjera jesu li zadani argumenti
        if (args.length != 2) {
            System.out.println("Potrebno je unijeti dvije datoteke kao argumente.");
            return;
        }

        String firstFileName = args[0];
        String secondFileName = args[1];

        HashSet<String> firstFileNames = new HashSet<>();
        HashSet<String> secondFileNames = new HashSet<>();

        // Čitanje imena iz prve datoteke
        try (BufferedReader firstReader = new BufferedReader(new FileReader(firstFileName))) {
            String line;
            while ((line = firstReader.readLine()) != null) {
                firstFileNames.add(line.trim());
            }
        } catch (IOException e) {
            System.out.println("Greška pri čitanju prve datoteke: " + e.getMessage());
            return;
        }

        // Čitanje imena iz druge datoteke
        try (BufferedReader secondReader = new BufferedReader(new FileReader(secondFileName))) {
            String line;
            while ((line = secondReader.readLine()) != null) {
                secondFileNames.add(line.trim());
            }
        } catch (IOException e) {
            System.out.println("Greška pri čitanju druge datoteke: " + e.getMessage());
            return;
        }

        // Pronalaženje imena koja se nalaze u prvoj, a ne nalaze u drugoj datoteci
        for (String name : firstFileNames) {
            if (!secondFileNames.contains(name)) {
                System.out.println(name);
            }
        }
    }
}