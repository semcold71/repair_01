package ru.samcold.utils;

import javafx.scene.control.TextField;

public class NumberValidator {
    // region sinleton
    private static NumberValidator instance;

    private NumberValidator() {
    }

    public static NumberValidator getInstance() {
        if (instance == null) {
            instance = new NumberValidator();
        }
        return instance;
    }
    // endregion

    public void DoubleValidator(TextField tf) {
        tf.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("|[-\\+]?|[-\\+]?\\d+\\.?|[-\\+]?\\d+\\.?\\d+")) {
                tf.setText(oldValue);
            }
        });
    }

    public  void IntegerValidator(TextField tf, int min, int max) {
        tf.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                if (!newValue.matches("[0-9]*")) {
                    tf.setText(oldValue);
                }
                if (Integer.parseInt(newValue) > max || Integer.parseInt(newValue) < min) {
                    tf.setText(oldValue);
                }
            } catch (NumberFormatException e) {
                System.out.println("A numeric field contain null or empty string \n" + e.getMessage());
            }
        });
    }
}
