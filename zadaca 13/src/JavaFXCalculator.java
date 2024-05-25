/*
Prepraviti raniju Swing/JavaFX/GWT/Vaadin verziju jednostavnog
kalkulatora s grafičkim sučeljem na način da se izračuni kalkulatora
odvijaju u zasebnoj dretvi. (Po želji ubaciti pauze u izračun te pokazivač
napredovanja).
 */
import javafx.application.Application;
import javafx.concurrent.Task;
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
    private ProgressBar progressBar;

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

        // Kreiranje ProgressBar-a
        progressBar = new ProgressBar(0);
        progressBar.setPrefWidth(200);

        // Dodavanje ActionListenera za dugme
        calculateButton.setOnAction(event -> {
            try {
                double num1 = Double.parseDouble(firstNumberField.getText());
                double num2 = Double.parseDouble(secondNumberField.getText());
                String operation = operationBox.getSelectionModel().getSelectedItem();

                BasicCalculator<Double> calculator = new BasicCalculator<>();
                Operation<Double> op;

                // Odabir operacije na osnovu korisničkog izbora
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

                // Kreiranje Task-a za izračun s pauzama
                Task<Double> calculationTask = new Task<>() {
                    @Override
                    protected Double call() throws Exception {
                        // Simuliranje izračuna s pauzama
                        final int steps = 10;
                        for (int i = 0; i < steps; i++) {
                            if (isCancelled()) {
                                break;
                            }
                            Thread.sleep(100); // Pauza od 100ms
                            updateProgress(i + 1, steps); // Ažuriranje napretka
                        }
                        return calculator.calculate(op, num1, num2);
                    }
                };

                // Povezivanje ProgressBar-a s Task-om
                progressBar.progressProperty().bind(calculationTask.progressProperty());

                // Postavljanje akcija za uspješan završetak Task-a
                calculationTask.setOnSucceeded(e -> {
                    resultField.setText(String.valueOf(calculationTask.getValue()));
                    progressBar.progressProperty().unbind();
                    progressBar.setProgress(0.0);
                });

                // Postavljanje akcija za slučaj greške
                calculationTask.setOnFailed(e -> {
                    progressBar.progressProperty().unbind();
                    progressBar.setProgress(0);
                    Throwable ex = calculationTask.getException();
                    if (ex instanceof ArithmeticException) {
                        showAlert(Alert.AlertType.ERROR, "Greška", "Dijeljenje s nulom nije dozvoljeno.");
                    } else {
                        showAlert(Alert.AlertType.ERROR, "Greška", "Došlo je do pogreške prilikom izračuna.");
                    }
                });

                // Pokretanje Task-a u novoj dretvi
                Thread calculationThread = new Thread(calculationTask);
                calculationThread.setDaemon(true);
                calculationThread.start();

            } catch (NumberFormatException ex) {
                showAlert(Alert.AlertType.ERROR, "Greška", "Unesite važeće brojeve.");
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
        grid.add(progressBar, 1, 5);

        Scene scene = new Scene(grid, 300, 210);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Metoda za prikazivanje upozorenja
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