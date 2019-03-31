package ru.hackathon.dao;

import ru.hackathon.model.Camera;
import ru.hackathon.model.Event;

import java.util.List;

public interface CameraRepository {
    void setCamera(Camera camera);

    List<Camera> getCameras();

    void updateCamera(Camera camera);

    void removeCamera(Camera camera);

    List<Event> getEvents(Long id_video);

}
