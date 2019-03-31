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
    private Long idVideo;
    private Integer currentRow = -1;

    private ObservableList<Event> eventsData = FXCollections.observableArrayList();

    @FXML
    private TableView<Event> tableEvents;

    @FXML
    private TableColumn<Event, Long> timeOpenColumn;

    @FXML
    private TableColumn<Event, Long> timeCloseColumn;

    public void setApp(VideoPlayer app) {
        this.videoPath = app.getVideoPath();
        this.idVideo = app.getIdVideo();
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
    private void methodPrevious() {
        if (mediaPlayer == null) {
            return;
        }
        if (currentRow <= 0){
            Duration duration = new Duration(0.0);
            mediaPlayer.seek(duration);
            currentRow = -1;
        } else {
            Long time_open = eventsData.get(--currentRow).getTimeOpen();
            double currentTime = mediaPlayer.getCurrentTime().toMillis();
            Duration duration = new Duration(currentTime - time_open * 1000.0);
            mediaPlayer.seek(duration);
        }
    }

    @FXML
    private void methodNext() {
        if (mediaPlayer == null || eventsData.size() <= (currentRow + 1)) {
            return;
        }
        Long timeOpen = eventsData.get(++currentRow).getTimeOpen();
        double currentTime = mediaPlayer.getCurrentTime().toMillis();
        Duration duration = new Duration(currentTime + timeOpen * 1000.0);
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
        List<Event> events = sqlBase.getEvents(idVideo);
        eventsData.addAll(events);
        timeOpenColumn.setCellValueFactory(new PropertyValueFactory<Event, Long>("timeOpen"));
        timeCloseColumn.setCellValueFactory(new PropertyValueFactory<Event, Long>("timeClose"));
        tableEvents.setItems(eventsData);
    }

}
