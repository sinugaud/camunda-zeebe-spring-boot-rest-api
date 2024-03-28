package com.sinugaud.service.impl;

import com.sinugaud.dto.TaskDetails;
import com.sinugaud.exception.ServerException;
import com.sinugaud.service.TaskService;
import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ActivateJobsResponse;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.spring.client.annotation.Variable;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.sinugaud.constants.Constant.USER_TASK_TYPE;
import static com.sinugaud.constants.Constant.WORKER_NAME;

@Service
@Log4j2
public class TaskServiceImpl implements TaskService {

    @Value("${zeebe.assignee.value:'io.camunda.zeebe:assignee'}")
    private String zeebeAssigneeValue;


    @Autowired
    private ZeebeClient client;

    @Autowired
    private MessageSource messageSource;

    public String completeTask(Long taskKey, @Variable Map<String, Object> inputProcessVariable)
            throws Exception {
//        String message;
//        String[] params = new String[2];
//        params[0] = String.valueOf(taskKey);

        try {

            client
                    .newCompleteCommand(taskKey)
                    .variables(inputProcessVariable)
                    .requestTimeout(Duration.ofMinutes(100))
                    .send()
                    .join();

            log.info("task completed success:" + taskKey + inputProcessVariable);

            return "Task complete success "+ taskKey;
        } catch (Exception ex) {
            log.error("Exception occurred while complete task : {}", ex.getMessage());
            throw new ServerException(ex.getMessage(), ex);
        }
    }

    @Override
    public List<TaskDetails> getActiveTasks() throws Exception {

        List<TaskDetails> taskDetailsList = new ArrayList<>();
        try {
            ActivateJobsResponse response =
                    client
                            .newActivateJobsCommand()
                            .jobType(USER_TASK_TYPE)
                            .maxJobsToActivate(100)
                            .timeout(Duration.ofMillis(5))
                            .workerName(WORKER_NAME)
                            .requestTimeout(Duration.ofMinutes(1))
                            .send()
                            .join();

            for (ActivatedJob activatedJob : response.getJobs()) {
                TaskDetails taskDetails = new TaskDetails();
                taskDetails.setProcessInstanceId(activatedJob.getProcessInstanceKey());
                taskDetails.setTaskKey(activatedJob.getProcessDefinitionKey());
                taskDetails.setElementInstanceKey(activatedJob.getElementInstanceKey());
                taskDetails.setTaskId(activatedJob.getKey());
                taskDetails.setDeadline(activatedJob.getDeadline());
                taskDetails.setAssignee(
                        activatedJob.getCustomHeaders().get(zeebeAssigneeValue)); // need to fix this
                taskDetails.setVariables(activatedJob.getVariablesAsMap());
                taskDetailsList.add(taskDetails);
            }
            return taskDetailsList;
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }
}