package ru.hackathon;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(App.class.getResource("/view/mainView.fxml"));
        primaryStage.setTitle("Settings");
        primaryStage.setScene(new Scene(loader.load(), 658, 296));
        Image imageVideo = new Image(getClass().getResourceAsStream("/Video.png"));
        primaryStage.getIcons().add(imageVideo);
        primaryStage.show();
    }

}
