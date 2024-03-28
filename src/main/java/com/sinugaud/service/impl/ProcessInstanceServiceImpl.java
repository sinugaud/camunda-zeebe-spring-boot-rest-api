package com.sinugaud.service.impl;

import com.sinugaud.dto.ProcessInstanceListDto;
import com.sinugaud.exception.ServerException;
import com.sinugaud.service.ProcessInstanceService;
import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.Process;
import io.camunda.zeebe.client.api.response.*;
import io.camunda.zeebe.spring.client.annotation.Variable;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.sinugaud.constants.Constant.USER_TASK_TYPE;

@Service
@Log4j2
public class ProcessInstanceServiceImpl implements ProcessInstanceService {

    @Value("${zebee.job.worker:'worker'}")
    private String zeebeJobWorker;

    @Autowired
    private ZeebeClient client;


    @Override
    public String startProcess(
            String processDefinitionId, @Variable Map<String, Object> processVariables) throws Exception {
        String message;
        String[] params = new String[2];
        params[0] = String.valueOf(processDefinitionId);
        ProcessInstanceEvent event = null;

        try {
            event =
                    client
                            .newCreateInstanceCommand()
                            .bpmnProcessId(processDefinitionId)
                            .latestVersion()
                            .variables(processVariables)
                            .send()
                            .join();
            log.info(
                    "started instance for workflowKey='{}', bpmnProcessId='{}', version='{}' with workflowInstanceKey='{}'",
                    event.getProcessDefinitionKey(),
                    event.getBpmnProcessId(),
                    event.getVersion(),
                    event.getProcessInstanceKey());

            return "started instance for process instance= " + event.getProcessInstanceKey() + ", bpmnProcessId=" + event.getBpmnProcessId() + ", version=" + event.getVersion();

        } catch (Exception ex) {

            log.info("Exception occurred while initiating process with id: {}", ex.getMessage());
//            params[1] = ex.getMessage();
//            message = messageSource.getMessage("process.start.failure", params, Locale.getDefault());
            throw new ServerException(ex.getMessage(), ex);
        }
    }

    @Override
    public List<Process> deployProcess(String processName) throws Exception {

        try {
            DeploymentEvent deploymentEvent =
                    client.newDeployResourceCommand().addResourceFromClasspath(processName).send().join();
            log.info("Process Deploy Success ");
            return deploymentEvent.getProcesses();
        } catch (Exception ex) {
            log.info("Exception occurred while deploying process : {}", ex.getMessage());
            ex.printStackTrace();
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public String startProcessByKey(
            Long processDefinitionKey, Map<String, Object> inputProcessVariable) throws Exception {

        try {
            final ProcessInstanceEvent event =
                    client
                            .newCreateInstanceCommand()
                            .processDefinitionKey(processDefinitionKey)
                            .variables(inputProcessVariable)
                            .send()
                            .join();
            log.info(
                    "started instance for workflowKey='{}', bpmnProcessId='{}', version='{}' with workflowInstanceKey='{}'",
                    event.getProcessDefinitionKey(),
                    event.getBpmnProcessId(),
                    event.getVersion(),
                    event.getProcessInstanceKey());

            return "started instance for workflowKey='{}', bpmnProcessId='{}', version='{}' with workflowInstanceKey='{}'" +
                    event.getProcessDefinitionKey() +
                    event.getBpmnProcessId() +
                    event.getVersion() +
                    event.getProcessInstanceKey();

        } catch (Exception ex) {
            log.info("Exception occurred while initiating process with key : {}", ex.getMessage());
            throw new ServerException(ex.getMessage(), ex);
        }
    }

    @Override
    public List<ProcessInstanceListDto> getActiveProcessInstances() throws Exception {
        List<ProcessInstanceListDto> processInstanceDtoList = new ArrayList<>();
        try {
            final ActivateJobsResponse response =
                    client
                            .newActivateJobsCommand()
                            .jobType(USER_TASK_TYPE)
                            .maxJobsToActivate(100)
                            .workerName(zeebeJobWorker)
                            .send()
                            .get();
            for (ActivatedJob activatedJob : response.getJobs()) {
                ProcessInstanceListDto processInstanceDto = new ProcessInstanceListDto();
                processInstanceDto.setProcessInstanceId(activatedJob.getProcessInstanceKey());
            }
            return processInstanceDtoList;
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }


}
