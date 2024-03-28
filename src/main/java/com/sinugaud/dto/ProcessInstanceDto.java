package com.sinugaud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProcessInstanceDto {
    private Long processInstanceKey;
    private Map<String, Object> variables;
    private Long processDefinitionKey;
    private String elementId;
    private String bpmnElementType;
    private Integer flowScopeKey;
    private String intent;
    private String bpmnProcessId;
    private Long elementInstanceKey;
    private Integer version;
}
