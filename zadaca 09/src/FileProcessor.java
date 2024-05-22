/*
Izraditi jednostavnu komandno-linijsku aplikaciju u Javi koja:
1. otvara tekstualnu datoteku kao tok znakova (ime datoteke neka bude primljeno kao prvi argument main metode),
2. čita liniju po liniju i sadržaj ispisuje:
- u drugu novu tekstualnu datoteku (ime neka bude primljeno kao drugi argument main metode) i
- u konzolu (samo ako je prisutan treći argument „-p” ili „-print”)
3. i na kraju ispisuje poruku o uspješnosti.

Program na kraju zapakirati kao JAR datoteku za distribuciju.
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileProcessor {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java FileProcessor <inputFile> <outputFile> [-p | -print]");
            return;
        }

        String inputFile = args[0];
        String outputFile = args[1];
        boolean printToConsole = args.length > 2 && (args[2].equals("-p") || args[2].equals("-print"));

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
                if (printToConsole) {
                    System.out.println(line);
                }
            }

            System.out.println("File processing completed successfully.");

        } catch (IOException e) {
            System.err.println("An error occurred during file processing: " + e.getMessage());
        }
    }
}
