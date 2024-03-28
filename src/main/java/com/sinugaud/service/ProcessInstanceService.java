package com.sinugaud.service;

import com.sinugaud.dto.ProcessInstanceListDto;
import io.camunda.zeebe.client.api.response.Process;

import java.util.List;
import java.util.Map;

public interface ProcessInstanceService {

    String startProcess(String processDefinitionId, Map<String, Object> processVariables)
            throws Exception;

    List<Process> deployProcess(String processName) throws Exception;

    String startProcessByKey(Long processDefinitionKey, Map<String, Object> inputProcessVariable)
            throws Exception;

    List<ProcessInstanceListDto> getActiveProcessInstances() throws Exception;


}
