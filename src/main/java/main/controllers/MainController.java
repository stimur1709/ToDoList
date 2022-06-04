package main.controllers;

import main.entity.TaskEntity;
import main.entity.UserEntity;
import main.service.TaskService;
import main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/main")
public class MainController {
    private final TaskService taskService;
    private final UserService userService;

    @Autowired
    public MainController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @GetMapping
    public String getUsersPage(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/user/add")
    public String createUser(Model model) {
        model.addAttribute("createUser", new UserEntity());
        return "user";
    }

    @PostMapping("/user/save")
    public String createUser(@ModelAttribute("createUser") UserEntity userEntity) {
        userService.createUser(userEntity);
        return "redirect:/main";
    }


    @GetMapping("/task/{id}")
    public String getTasksPage(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("tasks", taskService.getAllTask(id));
        model.addAttribute("user", userService.getUserId(id));
        return "tasks";
    }

    @GetMapping("/task/{id}/add")
    public String createTask(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("createTask", new TaskEntity());
        model.addAttribute("user", userService.getUserId(id));
        return "task";
    }

    @PostMapping("/task/{id}/save")
    public String createTask(@PathVariable("id") Integer id, @ModelAttribute("createTask") TaskEntity taskEntity) {
        taskService.createTask(taskEntity, userService.getUserId(id));
        return "redirect:/main/task/" + taskEntity.getUser().getId();
    }
}
