package main.dao;

import main.model.Task;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface Dao {
    List index();

    ResponseEntity get(int id);

    int post(Task task);

    ResponseEntity put(Task task, int id);

    void delete(int id);

    void deleteAll();
}
