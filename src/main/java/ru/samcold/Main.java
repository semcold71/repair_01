package ru.samcold;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.samcold.controllers.MainController;
import ru.samcold.domain.Customer;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/main.fxml"));
        Parent parent = loader.load();
        stage.setTitle("Repair Doc");
        stage.setScene(new Scene(parent, 1000, 800));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
