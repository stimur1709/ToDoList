package main.dao;

import main.model.Task;
import main.model.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskDaoImpl implements Dao {

    @Autowired
    TaskRepository taskRepository;

    @Override
    public List<Task> index() {
        Iterable<Task> taskIterable = taskRepository.findAll();
        ArrayList<Task> tasks = new ArrayList<>();
        for (Task task : taskIterable) {
            tasks.add(task);
        }
        return tasks;
    }

    @Override
    public ResponseEntity get(int id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        return optionalTask.map(task -> new ResponseEntity(task, HttpStatus.OK)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @Override
    public int post(Task task) {
        Task newTask = taskRepository.save(task);
        return newTask.getId();
    }

    @Override
    public ResponseEntity put(Task task, int id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        taskRepository.save(task);
        return optionalTask.map(value -> new ResponseEntity(value, HttpStatus.OK)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @Override
    public void delete(int id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        taskRepository.delete(optionalTask.get());
    }

    @Override
    public void deleteAll() {
        taskRepository.deleteAll();
    }
}
