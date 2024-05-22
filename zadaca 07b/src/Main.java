import java.util.Scanner;
/*
Doradi kod iz prethodne zadaće (jednostavni komandno-linijski
kalkulator) tako da se koriste lambda izrazi, a u slučajevima
kad lambda izraz nije prikladan, jer treba definirati dodatne atribute
ili metode koristi anonimnu klasu.
Kod poprati vlastitim komentarima.
 */

// Interface koji predstavlja operaciju nad brojevima
interface Operation<T> {
    T operate(T a, T b);
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BasicCalculator<Double> calculator = new BasicCalculator<>();

        System.out.println("Jednostavan kalkulator");
        System.out.println("Dostupne operacije: +, -, *, /");
        System.out.println("Unesite 'exit' za izlaz.");

        while (true) {
            System.out.print("Unesite prvi broj: ");
            String input = scanner.next();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Hvala što ste koristili kalkulator!");
                break;
            }

            double num1 = Double.parseDouble(input);

            System.out.print("Unesite drugi broj: ");
            double num2 = scanner.nextDouble();

            System.out.print("Unesite operaciju (+, -, *, /): ");
            String operation = scanner.next();

            // Lambda izrazi za operacije
            Operation<Double> op;
            switch (operation) {
                case "+":
                    op = (a, b) -> a + b; //zbrajanje
                    break;
                case "-":
                    op = (a, b) -> a - b; // oduzimanje
                    break;
                case "*":
                    op = (a, b) -> a * b; // množenje
                    break;
                case "/":
                    op = (a, b) -> {
                        if (b != 0) {
                            return a / b; //dijeljenje
                        } else {
                            throw new ArithmeticException("Division by zero");
                        }
                    };
                    break;
                default:
                    System.out.println("Nevažeća operacija");
                    continue;
            }

            // Izvršavanje operacije
            double result = calculator.calculate(op, num1, num2);
            System.out.println("Rezultat: " + result);
        }
    }
}

