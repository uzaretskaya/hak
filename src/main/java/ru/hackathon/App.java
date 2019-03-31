package ru.hackathon;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ru.hackathon.controller.MainViewController;

import java.io.IOException;

public class App extends Application {

    private Stage primaryStage;

    public App() {
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        method();
    }

    public void method() {
        try {
            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(App.class.getResource("/view/mainView.fxml"));
            AnchorPane mainView = (AnchorPane) loader.load();

            Scene scene = new Scene(mainView);
            primaryStage.setTitle("VideoPlayer");
            primaryStage.setWidth(700);
            primaryStage.setHeight(721);
            primaryStage.setScene(scene);
            primaryStage.show();

            MainViewController controller = loader.getController();
            controller.setApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
