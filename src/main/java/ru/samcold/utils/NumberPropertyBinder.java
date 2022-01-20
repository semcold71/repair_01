package ru.samcold.utils;

import javafx.beans.property.Property;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

public class NumberPropertyBinder {
    // region sinleton
    private static NumberPropertyBinder instance;

    private NumberPropertyBinder() {
    }

    public static NumberPropertyBinder getInstance() {
        if (instance == null) {
            instance = new NumberPropertyBinder();
        }
        return instance;
    }
    // endregion

    public void bind(TextField field, Property<Number> property) {
        field.textProperty().bindBidirectional(property, new StringConverter<>() {

            @Override
            public String toString(Number number) {
                return String.valueOf(number);
            }

            @Override
            public Number fromString(String s) {
                if (Number.class.equals(Integer.class)) {
                    return Integer.parseInt(s);
                } else if (Number.class.equals(Double.class)) {
                    return Double.parseDouble(s);
                }
                return null;
            }
        });
    }
}
