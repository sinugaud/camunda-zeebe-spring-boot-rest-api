package com.sinugaud.service;

import com.sinugaud.dto.TaskDetails;

import java.util.List;
import java.util.Map;

public interface TaskService {
    String completeTask(Long taskKey, Map<String, Object> inputProcessVariable) throws Exception;

    List<TaskDetails> getActiveTasks() throws Exception;

//    TaskList getCompletedTasks() throws Exception;

//    TasklistDetails getTaskDetails(String taskKey) throws JsonProcessingException;


}
