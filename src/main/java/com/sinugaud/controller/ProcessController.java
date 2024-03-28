package com.sinugaud.controller;

import com.sinugaud.service.ProcessInstanceService;
import io.camunda.zeebe.client.api.response.Process;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@Log4j2
public class ProcessController {
    @Autowired
    private ProcessInstanceService processInstanceService;

    @PostMapping("/process/deploy")
    public ResponseEntity<List<Process>> deployProcess(
            @RequestParam("processName") String processName) throws Exception {
        return new ResponseEntity<>(processInstanceService.deployProcess(processName), HttpStatus.OK);
    }

    @PostMapping("/process/start-by-key")
    public ResponseEntity<String> processInstanceCreationWithProcessDefinitionKey(
            @RequestParam("processDefinitionKey") Long processDefinitionKey,
            @RequestBody Map<String, Object> inputProcessVariable)
            throws Exception {
        return new ResponseEntity<>(
                processInstanceService.startProcessByKey(processDefinitionKey, inputProcessVariable),
                HttpStatus.CREATED);
    }

    @PostMapping("/process/start")
    public ResponseEntity<String> processInstanceCreationWithBpmnProcessId(
            @RequestParam("processDefinitionId") String processDefinitionId,
            @RequestBody Map<String, Object> processVariables)
            throws Exception {
        return new ResponseEntity<>(
                processInstanceService.startProcess(processDefinitionId, processVariables),
                HttpStatus.CREATED);
    }


}
