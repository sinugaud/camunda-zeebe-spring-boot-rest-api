
package com.sinugaud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private Long processInstanceKey;
    private Map<String, Object> variables;
    private String assignee;
    private String jobName;
    private String type;
    private Long taskId;
    private String recordType;
    private String intent;
    private Integer retries;
    private String worker;
    private Object timeout;
    private String errorMessage;
    private String elementId;
    private Long processDefinitionKey;
    private Integer processDefinitionVersion;
    private Long elementInstanceKey;
}