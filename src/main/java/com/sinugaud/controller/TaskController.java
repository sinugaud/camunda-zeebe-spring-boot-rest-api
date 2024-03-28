package com.sinugaud.controller;

import com.sinugaud.dto.TaskDetails;
import com.sinugaud.service.TaskService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Log4j2
public class TaskController {
    @Autowired
    private TaskService taskService;


    @PostMapping("/task/complete")
    public ResponseEntity<String> completeTask(
            @RequestParam(name = "taskKey") Long taskKey, @RequestBody Map<String, Object> inputProcessVariable)
            throws Exception {
        return new ResponseEntity<>(
                taskService.completeTask(taskKey, inputProcessVariable), HttpStatus.OK);
    }

    @GetMapping("/tasks/active")
    public ResponseEntity<List<TaskDetails>> getActiveTasks() throws Exception {
        return new ResponseEntity<>(taskService.getActiveTasks(), HttpStatus.OK);
    }


}
