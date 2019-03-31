package ru.hackathon.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;
import ru.hackathon.VideoPlayer;
import ru.hackathon.dao.FromMySql;
import ru.hackathon.model.Camera;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class VideoController implements Initializable {

    @FXML
    private MediaView video;
    private Media media;
    private MediaPlayer mediaPlayer;

    private ObservableList<Camera> camerasData = FXCollections.observableArrayList();

    private VideoPlayer app;
    private String videoPath;

    public void setApp(VideoPlayer app) {
        this.app = app;
        this.videoPath = app.getVideoPath();
    }

    @FXML
    private void methodPlay() {
        if (mediaPlayer == null) {
            media = new Media(videoPath);
            mediaPlayer = new MediaPlayer(media);
            video.setMediaPlayer(mediaPlayer);
        }
        System.out.println(mediaPlayer.getStatus());
        mediaPlayer.play();
    }

    @FXML
    private void methodPause() {
        if (mediaPlayer == null) return;
        System.out.println(mediaPlayer.getStatus());
        mediaPlayer.pause();
    }

    @FXML
    private void methodNext() {
        if (mediaPlayer == null) return;
        System.out.println(mediaPlayer.getStatus());
        double currentTime = mediaPlayer.getCurrentTime().toMillis();
        Duration duration = new Duration(currentTime + 5000.0);
        System.out.println(currentTime);
        mediaPlayer.seek(duration);
    }

    @FXML
    private void methodStop() {
        if (mediaPlayer == null) return;
        mediaPlayer.stop();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (mediaPlayer == null) {
            media = new Media(videoPath);
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setAutoPlay(true);
            video.setMediaPlayer(mediaPlayer);
        }
        FromMySql sqlBase = new FromMySql();
        List<Camera> cameras = sqlBase.getCameras();
        camerasData.addAll(cameras);
    }

}
