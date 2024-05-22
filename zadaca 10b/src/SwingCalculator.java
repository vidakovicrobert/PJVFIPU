/*
Načiniti sljedeće preinake na jednostavnoj verziji kalkulatora s
grafičkim sučeljem kojeg ste napravili za prethodnu domaću zadaću:
- dinamičku izmjenu delegata (Metal / Windows / CDE) te dobiti različit gledaj-i-
osjeti (Look and Feel) aplikacije,
- računanja kalkulatora (iako vrlo jednostavna) smjestite u poseban pozadinski
posao korištenjem SwingWorker klase.
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
    private JComboBox<String> lookAndFeelBox;

    public SwingCalculator() {
        setTitle("Jednostavan kalkulator");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Kreiranje GUI komponenti
        firstNumberField = new JTextField(10);
        secondNumberField = new JTextField(10);
        resultField = new JTextField(15);
        resultField.setEditable(false);

        String[] operations = {"+", "-", "*", "/"};
        operationBox = new JComboBox<>(operations);

        JButton calculateButton = new JButton("Izračunaj");

        // Izmjena temi
        String[] lookAndFeels = {"Metal", "Nimbus", "CDE/Motif", "Windows", "Windows Classic"};
        lookAndFeelBox = new JComboBox<>(lookAndFeels);

        lookAndFeelBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedLookAndFeel = (String) lookAndFeelBox.getSelectedItem();
                try {
                    switch (selectedLookAndFeel) {
                        case "Metal":
                            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
                            break;
                        case "Nimbus":
                            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
                            break;
                        case "CDE/Motif":
                            UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
                            break;
                        case "Windows":
                            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                            break;
                        case "Windows Classic":
                            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
                            break;
                        default:
                            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                            break;
                    }
                    SwingUtilities.updateComponentTreeUI(SwingCalculator.this);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

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

                    // Pokretanje pozadinskog posla
                    new CalculationWorker(calculator, op, num1, num2).execute();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(SwingCalculator.this, "Unesite važeće brojeve.", "Greška", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Kreiranje panela i dodavanje komponenti
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));
        panel.add(new JLabel("Prvi broj:"));
        panel.add(firstNumberField);
        panel.add(new JLabel("Drugi broj:"));
        panel.add(secondNumberField);
        panel.add(new JLabel("Operacija:"));
        panel.add(operationBox);
        panel.add(new JLabel("Tema programa:"));
        panel.add(lookAndFeelBox);
        panel.add(new JLabel("Rezultat:"));
        panel.add(resultField);
        panel.add(new JLabel(""));
        panel.add(calculateButton);

        // Dodavanje panela u okvir
        add(panel);

        setVisible(true);
    }

    private class CalculationWorker extends SwingWorker<Double, Void> {
        private final BasicCalculator<Double> calculator;
        private final Operation<Double> operation;
        private final double num1, num2;

        public CalculationWorker(BasicCalculator<Double> calculator, Operation<Double> operation, double num1, double num2) {
            this.calculator = calculator;
            this.operation = operation;
            this.num1 = num1;
            this.num2 = num2;
        }

        @Override
        protected Double doInBackground() throws Exception {
            return calculator.calculate(operation, num1, num2);
        }

        @Override
        protected void done() {
            try {
                double result = get();
                resultField.setText(String.valueOf(result));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SwingCalculator();
            }
        });
    }
}