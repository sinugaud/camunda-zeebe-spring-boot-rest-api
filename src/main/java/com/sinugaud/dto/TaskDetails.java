package com.sinugaud.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Data
@Getter
@Setter
public class TaskDetails {
  private Long taskId; // Job key
  private Long processInstanceId;
  private Long taskKey; // task definition id
  private String assignee;
  private Long deadline;
  private Map<String, Object> variables;
  private Long elementInstanceKey;
}
