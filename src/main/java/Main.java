import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private TextField celsiusField = new TextField();
    private Label resultLabel = new Label();
    private ComboBox<String> combobox = new ComboBox();
    
    private double result;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        combobox.getItems().setAll("Fahrenheit", "Kelvin");
        combobox.setValue("Fahrenheit");

        celsiusField.setPromptText("Enter Celsius");

        Button convertButton = new Button("Convert");
        convertButton.setOnAction(e ->  {
            try {
                double celsius = Double.parseDouble(celsiusField.getText()); 
                String conversion = combobox.getValue();

                if (conversion == "Fahrenheit"){
                    CelsiusToFahrenheit(celsius);
                } else {
                    CelsiusToKelvin(celsius);
                }

            } catch (NumberFormatException ex){
                resultLabel.setText("Invalid value. Enter valid number");
            }
        });

        Button saveButton = new Button("Save to DB");
        saveButton.setOnAction(e -> Database.saveTemperature(
           
                Double.parseDouble(celsiusField.getText()), result, combobox.getValue(), resultLabel));

        VBox root = new VBox(10, celsiusField, combobox, convertButton, resultLabel, saveButton);
        Scene scene = new Scene(root, 300, 200);

        stage.setTitle("Celsius to Fahrenheit");
        stage.setScene(scene);
        stage.show();
    }

    public void CelsiusToFahrenheit(double celsius){
        result = (celsius * 1.8) + 32;
        resultLabel.setText(String.format("Fahrenheit: %.2f", result));

    }

    public void CelsiusToKelvin(double celsius) {
        result = celsius + 273.5;
        resultLabel.setText(String.format("Kelvin: %.2f", result));
    }

}