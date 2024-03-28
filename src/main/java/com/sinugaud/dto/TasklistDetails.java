package com.sinugaud.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class TasklistDetails {

  Long id;
  String name;
  String taskDefinitionId;
  String processName;
  String creationDate;
  String completionDate;
  String assignee;
  String taskState;
  @JsonIgnore String formKey;
  Long processDefinitionKey;
  Long processInstanceKey;
  String dueDate;
  @JsonIgnore String followUpDate;
  @JsonIgnore String candidateUsers;
  @JsonIgnore String candidateGroups;
  String taskForm;
}
