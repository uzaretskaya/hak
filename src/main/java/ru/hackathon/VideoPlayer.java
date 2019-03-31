package ru.hackathon;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.hackathon.controller.VideoController;

public class VideoPlayer  {

    private String videoPath;

    public void start(String videoPath) throws Exception {
        this.videoPath = videoPath;
        Stage primaryStage = new Stage();
        VideoController controller = new VideoController();
        controller.setApp(this);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/videoPlayer.fxml"));
        loader.setController(controller);
        Scene scene = new Scene(loader.load(), 700, 442);
        primaryStage.setTitle("VideoPlayer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public String getVideoPath() {
        return videoPath;
    }
}
