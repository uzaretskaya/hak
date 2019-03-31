package ru.hackathon;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.hackathon.controller.VideoController;

public class VideoPlayer  {

    private String videoPath;
    private Long id_video;

    public void start(String videoPath, Long id) throws Exception {
        this.videoPath = videoPath;
        this.id_video = id;
        Stage primaryStage = new Stage();
        VideoController controller = new VideoController();
        controller.setApp(this);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/videoPlayer.fxml"));
        loader.setController(controller);
        Scene scene = new Scene(loader.load(), 700, 690);
        primaryStage.setTitle("VideoPlayer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public String getVideoPath() {
        return videoPath;
    }

    public Long getId_video() {
        return id_video;
    }

}
