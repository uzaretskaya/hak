package ru.hackathon;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.hackathon.controller.MainViewController;

public class App extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(App.class.getResource("/view/mainView.fxml"));
        primaryStage.setTitle("Settings");
        primaryStage.setScene(new Scene(loader.load(), 658, 296));
        primaryStage.show();
        MainViewController controller = loader.getController();
        controller.setApp(this);
    }

}
