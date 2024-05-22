/*
Prepraviti raniju Swing verziju jednostavnog kalkulatora s grafičkim sučeljem u
JavaFX verziju.
 */
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

// Interface koji predstavlja operaciju nad brojevima
interface Operation<T> {
    T operate(T a, T b);
}

public class JavaFXCalculator extends Application {
    private TextField firstNumberField;
    private TextField secondNumberField;
    private TextField resultField;
    private ComboBox<String> operationBox;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Jednostavan kalkulator");

        // Kreiranje GUI komponenti
        firstNumberField = new TextField();
        secondNumberField = new TextField();
        resultField = new TextField();
        resultField.setEditable(false);

        String[] operations = {"+", "-", "*", "/"};
        operationBox = new ComboBox<>();
        operationBox.getItems().addAll(operations);
        operationBox.getSelectionModel().selectFirst();

        Button calculateButton = new Button("Izračunaj");

        // Dodavanje ActionListenera za dugme
        calculateButton.setOnAction(event -> {
            try {
                double num1 = Double.parseDouble(firstNumberField.getText());
                double num2 = Double.parseDouble(secondNumberField.getText());
                String operation = operationBox.getSelectionModel().getSelectedItem();

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
                showAlert(Alert.AlertType.ERROR, "Greška", "Unesite važeće brojeve.");
            } catch (ArithmeticException ex) {
                showAlert(Alert.AlertType.ERROR, "Greška", "Dijeljenje s nulom nije dozvoljeno.");
            }
        });

        // Kreiranje panela i dodavanje komponenti
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(8);
        grid.setHgap(10);

        grid.add(new Label("Prvi broj:"), 0, 0);
        grid.add(firstNumberField, 1, 0);
        grid.add(new Label("Drugi broj:"), 0, 1);
        grid.add(secondNumberField, 1, 1);
        grid.add(new Label("Operacija:"), 0, 2);
        grid.add(operationBox, 1, 2);
        grid.add(new Label("Rezultat:"), 0, 3);
        grid.add(resultField, 1, 3);
        grid.add(calculateButton, 1, 4);

        Scene scene = new Scene(grid, 400, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}