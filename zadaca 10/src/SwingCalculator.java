/*
Korištenjem Swing-a izraditi desktop verziju jednostavnog kalkulatora
s grafičkim sučeljem koji omogućava unos dva proizvoljna broja
preko tipkovnice i odabir osnovne operacije (+,-,*,/) nad njima te
vraća rezultat.
 */

import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Interface koji predstavlja operaciju nad brojevima
interface Operation<T> {
    T operate(T a, T b);
}

public class SwingCalculator extends JFrame {
    private JTextField firstNumberField;
    private JTextField secondNumberField;
    private JTextField resultField;
    private JComboBox<String> operationBox;

    public SwingCalculator() {
        setTitle("Jednostavan kalkulator");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Kreiranje GUI komponenti
        firstNumberField = new JTextField(5);
        secondNumberField = new JTextField(5);
        resultField = new JTextField(5);
        resultField.setEditable(false);

        String[] operations = {"+", "-", "*", "/"};
        operationBox = new JComboBox<>(operations);

        JButton calculateButton = new JButton("Izračunaj");

        // Dodavanje ActionListenera za dugme
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double num1 = Double.parseDouble(firstNumberField.getText());
                    double num2 = Double.parseDouble(secondNumberField.getText());
                    String operation = (String) operationBox.getSelectedItem();

                    BasicCalculator<Double> calculator = new BasicCalculator<>();
                    Operation<Double> op;

                    switch (operation) {
                        case "+":
                            op = (a, b) -> a + b;
                            break;
                        case "-":
                            op = (a, b) -> a - b;
                            break;
                        case "*":
                            op = (a, b) -> a * b;
                            break;
                        case "/":
                            if (num2 != 0) {
                                op = (a, b) -> a / b;
                            } else {
                                throw new ArithmeticException("Division by zero");
                            }
                            break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + operation);
                    }

                    double result = calculator.calculate(op, num1, num2);
                    resultField.setText(String.valueOf(result));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(SwingCalculator.this, "Unesite važeće brojeve.", "Greška", JOptionPane.ERROR_MESSAGE);
                } catch (ArithmeticException ex) {
                    JOptionPane.showMessageDialog(SwingCalculator.this, ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Kreiranje panela i dodavanje komponenti
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));
        panel.add(new JLabel("Prvi broj:"));
        panel.add(firstNumberField);
        panel.add(new JLabel("Drugi broj:"));
        panel.add(secondNumberField);
        panel.add(new JLabel("Operacija:"));
        panel.add(operationBox);
        panel.add(new JLabel("Rezultat:"));
        panel.add(resultField);
        panel.add(new JLabel(""));
        panel.add(calculateButton);

        // Dodavanje panela u okvir
        add(panel);

        setVisible(true);
    }

    public static void main(String[] args) {
        new SwingCalculator();
    }
}