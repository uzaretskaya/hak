package ru.hackathon;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.hackathon.controller.MainViewController;

import java.io.IOException;

public class App extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        method();
    }

    public void method() {
        try {
            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(App.class.getResource("/view/mainView.fxml"));
            primaryStage.setTitle("Settings");
            primaryStage.setScene(new Scene(loader.load(), 500, 500));
            primaryStage.show();
            MainViewController controller = loader.getController();
            controller.setApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
