package main.controllers;

import main.entity.TaskEntity;
import main.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/task")
public class MainController {
    private final TaskService taskService;

    @Autowired
    public MainController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String getMainPage(Model model) {
        model.addAttribute("tasks", taskService.getAllTask());
        return "index";
    }

    @GetMapping("/add")
    public String getTaskPage(Model model) {
        model.addAttribute("createTask", new TaskEntity());
        return "task";
    }

    @PostMapping("/save")
    public String createTask(@ModelAttribute("createTask") TaskEntity taskEntity) {
        taskService.createTask(taskEntity);
        return "redirect:/task";
    }
}
