package main;

import main.dao.Dao;
import main.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private Dao dao;

    @PostMapping()
    public String post(@ModelAttribute Task task, Model model, RedirectAttributes redirectAttributes) {
        model.addAttribute("task", dao.post(task));
        redirectAttributes.addFlashAttribute(model);
        return "redirect:/task";
    }

    @GetMapping()
    public String getAll(Model model) {
        model.addAttribute("tasks", dao.index());
        return "task";
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable int id) {
        return dao.get(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity put(Task task, @PathVariable int id) {
        return dao.put(task, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        dao.delete(id);
    }

    @DeleteMapping("")
    public void deleteAll() {
        dao.deleteAll();
    }
}
