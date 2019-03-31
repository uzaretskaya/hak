package ru.hackathon;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import ru.hackathon.controller.VideoController;

public class VideoPlayer {

    private String videoPath;
    private Long idVideo;

    public void start(String videoPath, Long id) throws Exception {
        this.videoPath = videoPath;
        this.idVideo = id;
        Stage primaryStage = new Stage();
        VideoController controller = new VideoController();
        controller.setApp(this);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/videoPlayer.fxml"));
        loader.setController(controller);
        Scene scene = new Scene(loader.load(), 700, 690);
        primaryStage.setTitle("VideoPlayer");
        primaryStage.setScene(scene);
        Image imageVideo = new Image(getClass().getResourceAsStream("/Video.png"));
        primaryStage.getIcons().add(imageVideo);
        primaryStage.show();
    }

    public String getVideoPath() {
        return videoPath;
    }

    public Long getIdVideo() {
        return idVideo;
    }

}
