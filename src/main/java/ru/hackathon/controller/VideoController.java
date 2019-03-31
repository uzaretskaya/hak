package ru.hackathon.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    @FXML
    private TableColumn<Event, String> descriptionColumn;

    @FXML
    private Button btPrevious;

    @FXML
    private Button btNext;

    @FXML
    private Button btPlay;

    @FXML
    private Button btPause;

    @FXML
    private Button btStop;

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
            setDuration(0L);
            currentRow = -1;
        } else {
            Long timeOpen = eventsData.get(--currentRow).getTimeOpen();
            setDuration(timeOpen);
        }
    }

    @FXML
    private void methodNext() {
        if (mediaPlayer == null || eventsData.size() <= (currentRow + 1)) {
            return;
        }
        Long timeOpen = eventsData.get(++currentRow).getTimeOpen();
        setDuration(timeOpen);
    }

    private void setDuration(Long v) {
        Duration duration = new Duration(v * 1000.0);
        mediaPlayer.seek(duration);
    }

    @FXML
    private void methodStop() {
        if (mediaPlayer == null) {
            return;
        }
        mediaPlayer.stop();
        currentRow = -1;
    }

    @FXML
    private void methodСhoice() {
        if (mediaPlayer == null || eventsData.size() == 0) {
            return;
        }
        Integer row = tableEvents.focusModelProperty().getValue().getFocusedCell().getRow();
        Long timeOpen = eventsData.get(row).getTimeOpen();
        setDuration(timeOpen);
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
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("description"));
        tableEvents.setItems(eventsData);

        Image imagePrevious = new Image(getClass().getResourceAsStream("/Previous.png"));
        btPrevious.graphicProperty().setValue(new ImageView(imagePrevious));

        Image imageNext = new Image(getClass().getResourceAsStream("/Next.png"));
        btNext.graphicProperty().setValue(new ImageView(imageNext));

        Image imagePlay = new Image(getClass().getResourceAsStream("/Play.png"));
        btPlay.graphicProperty().setValue(new ImageView(imagePlay));

        Image imagePause = new Image(getClass().getResourceAsStream("/Pause.png"));
        btPause.graphicProperty().setValue(new ImageView(imagePause));

        Image imageStop = new Image(getClass().getResourceAsStream("/Stop.png"));
        btStop.graphicProperty().setValue(new ImageView(imageStop));
    }



}
