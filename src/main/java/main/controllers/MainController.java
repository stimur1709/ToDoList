package main.controllers;

import main.entity.TaskEntity;
import main.entity.UserEntity;
import main.service.TaskService;
import main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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


    @GetMapping("/task")
    public String getTasksPage(Model model) {
        model.addAttribute("tasks", taskService.getAllTask());
        return "tasks";
    }

    @GetMapping("/task/add")
    public String createTask(Model model) {
        model.addAttribute("createTask", new TaskEntity());
        return "task";
    }

    @PostMapping("/task/save")
    public String createTask(@ModelAttribute("createTask") TaskEntity taskEntity) {
        taskService.createTask(taskEntity);
        return "redirect:/main/task";
    }
}
