package ru.hackathon.dao;

import ru.hackathon.model.Camera;

import java.util.List;

public interface CameraRepository {
    void setCamera(Camera camera);

    List<Camera> getCameras();

    void updateCamera(Camera camera);

    void removeCamera(Camera camera);
}
