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
import javafx.util.Duration;
import ru.hackathon.VideoPlayer;
import ru.hackathon.dao.FromMySql;
import ru.hackathon.model.Camera;
import ru.hackathon.model.Event;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class VideoController implements Initializable {

    @FXML
    private MediaView video;
    private Media media;
    private MediaPlayer mediaPlayer;

    private String videoPath;
    private Long id_video;

    private ObservableList<Event> eventsData = FXCollections.observableArrayList();

    @FXML
    private TableView<Event> tableEvents;

    @FXML
    private TableColumn<Event, Long> time_openColumn;

    @FXML
    private TableColumn<Event, Long> time_closeColumn;

    public void setApp(VideoPlayer app) {
        this.videoPath = app.getVideoPath();
        this.id_video = app.getId_video();
    }

    @FXML
    private void methodPlay() {
        if (mediaPlayer == null){
            return;
        }
        mediaPlayer.play();
    }

    @FXML
    private void methodPause() {
        if (mediaPlayer == null) {
            return;
        }
        mediaPlayer.pause();
    }

    @FXML
    private void methodNext() {
        if (mediaPlayer == null) return;
        System.out.println(mediaPlayer.getStatus());
        double currentTime = mediaPlayer.getCurrentTime().toMillis();
        Duration duration = new Duration(currentTime + 5000.0);
        mediaPlayer.seek(duration);
    }

    @FXML
    private void methodStop() {
        if (mediaPlayer == null) {
            return;
        }
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
        List<Event> events = sqlBase.getEvents(id_video);
        eventsData.addAll(events);
        time_openColumn.setCellValueFactory(new PropertyValueFactory<Event, Long>("time_open"));
        time_closeColumn.setCellValueFactory(new PropertyValueFactory<Event, Long>("time_close"));
        tableEvents.setItems(eventsData);
    }

}
