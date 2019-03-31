package ru.hackathon.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ru.hackathon.App;
import ru.hackathon.VideoPlayer;
import ru.hackathon.dao.FromMySql;
import ru.hackathon.model.Camera;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

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
                    player.start(videoPath);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FromMySql sqlBase = new FromMySql();
        List<Camera> cameras = sqlBase.getCameras();
        camerasData.addAll(cameras);
        cameraIDColumn.setCellValueFactory(new PropertyValueFactory<Camera, Long>("cameraId"));
        videoPathColumn.setCellValueFactory(new PropertyValueFactory<Camera, String>("pathVideo"));
        tableCameras.setItems(camerasData);

    }
}
