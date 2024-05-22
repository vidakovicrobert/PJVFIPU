import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BasicCalculator calculator = new BasicCalculator();

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

            double result;
            switch (operation) {
                case "+":
                    result = calculator.add(num1, num2);
                    break;
                case "-":
                    result = calculator.subtract(num1, num2);
                    break;
                case "*":
                    result = calculator.multiply(num1, num2);
                    break;
                case "/":
                    result = calculator.divide(num1, num2);
                    break;
                default:
                    System.out.println("Nevažeća operacija");
                    continue;
            }

            System.out.println("Rezultat: " + result);
        }
    }
}