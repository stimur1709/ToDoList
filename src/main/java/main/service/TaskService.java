package main.service;

import main.entity.TaskEntity;
import main.entity.UserEntity;
import main.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class TaskService {

    TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<TaskEntity> getAllTask(Integer id) {
        return taskRepository.getTasksListByUserId(id);
    }

    public void createTask(TaskEntity taskEntity, UserEntity user) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        try {
            taskEntity.setDate(formatter.parse(formatter.format(date)));
            taskEntity.setUser(user);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        taskRepository.save(taskEntity);
    }
}
