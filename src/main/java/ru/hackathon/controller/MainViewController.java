package ru.hackathon.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.web.WebView;
import javafx.util.Duration;
import org.w3c.dom.NodeList;
import ru.hackathon.App;
import ru.hackathon.dao.FromMySql;
import ru.hackathon.dao.SQLHandler;
import ru.hackathon.model.Camera;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Stream;

import static javafx.scene.media.MediaPlayer.Status.STOPPED;


public class MainViewController implements Initializable {

    @FXML
    private MediaView video;
    @FXML
    private WebView data;

    private Media media;
    private MediaPlayer mediaPlayer;
    private double currentTime;

    private ObservableList<Camera> camerasData = FXCollections.observableArrayList();

    private App app;

    public void setApp(App app) {
        this.app = app;
    }

    @FXML
    private void methodPlay() {
        if (mediaPlayer == null) {
            media = new Media("http://pkg.bakhuss.ru/hackaton_bd/video/2019/03/30/1/video1.mp4");
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

    @FXML
    private void getData() {


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (mediaPlayer == null) {
            media = new Media("http://pkg.bakhuss.ru/hackaton_bd/video/2019/03/30/1/video1.mp4");
            mediaPlayer = new MediaPlayer(media);
            video.setMediaPlayer(mediaPlayer);
        }
        FromMySql sqlBase = new FromMySql();
        List<Camera> cameras = sqlBase.getCameras();
        for (Camera camera : cameras) {
            System.out.println(camera.getCameraId() + " - " + camera.getPathVideo());
        }
        camerasData.addAll(cameras);
    }
}
