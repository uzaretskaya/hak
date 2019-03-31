package ru.hackathon;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.hackathon.controller.VideoController;

public class VideoPlayer extends Application {

    private String videoPath;

    @Override
    public void start(Stage primaryStage) throws Exception {
        VideoController controller = new VideoController();
        controller.setApp(this);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/videoPlayer.fxml"));
        loader.setController(controller);
        Scene scene = new Scene(loader.load(), 500, 500);
        primaryStage.setTitle("VideoPlayer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void start(Stage primaryStage, String videoPath) throws Exception {
        this.videoPath = videoPath;
        start(primaryStage);
    }

    public String getVideoPath() {
        return videoPath;
    }
}
