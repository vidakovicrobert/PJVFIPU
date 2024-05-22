import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws MaxNumberOfQuestionsException {
        Quiz quiz = new Quiz("Java Quiz", 4); // Definiramo kviz s nazivom "Java Quiz" i maksimalnim brojem pitanja 4

        // Dodajemo pitanja i odgovore
        quiz.addQuestion("U kojem jeziku se programira za Android?", "Java");
        quiz.addQuestion("U kojem jeziku se programira za iPhone?", "Objective-C");

        // Omogućujemo dodavanje novih pitanja putem tipkovnice
        Scanner scanner = new Scanner(System.in);
        String addMore = "da";
        while (addMore.equalsIgnoreCase("da")) {
            System.out.println("Želite li dodati još pitanja? (da/ne)");
            addMore = scanner.nextLine();
            if (addMore.equalsIgnoreCase("da")) {
                try {
                    System.out.println("Unesite pitanje:");
                    String question = scanner.nextLine();
                    System.out.println("Unesite odgovor:");
                    String answer = scanner.nextLine();
                    quiz.addQuestion(question, answer);
                } catch (MaxNumberOfQuestionsException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        // Postavljanje broja točnih odgovora na nulu
        int noOfCorrectAnswers = 0;

        // Postavljanje broja ukupnih pitanja na broj pitanja u kvizu
        int totalQuestions = quiz.getNoOfQuestions();

        // Provjera točnosti odgovora
        for (int i = 0; i < totalQuestions; i++) {
            System.out.println(quiz.getQuestion(i));
            String userAnswer = scanner.nextLine();
            if (quiz.isCorrectAnswer(i, userAnswer)) {
                noOfCorrectAnswers++;
                System.out.println("Točno!");
            } else {
                System.out.println("Netočno.");
            }
        }

        // Ispis broja točnih odgovora u apsolutnom i postotnom iznosu
        System.out.println("Imali ste " + noOfCorrectAnswers + " točnih odgovora od ukupno " + totalQuestions + ".");
        double percentageCorrect = ((double) noOfCorrectAnswers / totalQuestions) * 100;
        System.out.printf("To je %.2f%% točnih odgovora.\n", percentageCorrect);
    }
}