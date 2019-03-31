package ru.hackathon.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;
import ru.hackathon.App;
import ru.hackathon.VideoPlayer;
import ru.hackathon.dao.FromMySql;
import ru.hackathon.model.Camera;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    @FXML
    private MediaView video;
    private Media media;
    private MediaPlayer mediaPlayer;

    private ObservableList<Camera> camerasData = FXCollections.observableArrayList();

    @FXML
    private TableView<Camera> tableCameras;

    @FXML
    private TableColumn<Camera, Long> cameraIDColumn;

    @FXML
    private TableColumn<Camera, String> videoPathColumn;

    private App app;

    public void setApp(App app) {
        this.app = app;
    }

    @FXML
    private void methodPlay() {
        if (mediaPlayer == null) {
            return;
//            media = new Media("http://pkg.bakhuss.ru/hackaton_bd/video/2019/03/30/1/video1.mp4");
//            mediaPlayer = new MediaPlayer(media);
//            video.setMediaPlayer(mediaPlayer);
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
    private void methodChoise() {
        Integer row = null;
        try {
            row = tableCameras.focusModelProperty().getValue().getFocusedCell().getRow();
        } catch (Exception e){
            e.printStackTrace();
        }
        if (row != null){
            String videoPath = camerasData.get(row).getPathVideo();
            if (videoPath != ""){
                VideoPlayer player = new VideoPlayer();
                try {
                    player.start(new Stage(), videoPath);
                } catch (Exception e){
                    e.printStackTrace();
                }
//                media = new Media(videoPath);
//                mediaPlayer = new MediaPlayer(media);
//                video.setMediaPlayer(mediaPlayer);
            }
        }


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        if (mediaPlayer == null) {
//            media = new Media("http://pkg.bakhuss.ru/hackaton_bd/video/2019/03/30/1/video1.mp4");
//            mediaPlayer = new MediaPlayer(media);
//            video.setMediaPlayer(mediaPlayer);
//        }
        FromMySql sqlBase = new FromMySql();
        List<Camera> cameras = sqlBase.getCameras();
        camerasData.addAll(cameras);
        cameraIDColumn.setCellValueFactory(new PropertyValueFactory<Camera, Long>("cameraId"));
        videoPathColumn.setCellValueFactory(new PropertyValueFactory<Camera, String>("pathVideo"));
        tableCameras.setItems(camerasData);

    }
}
