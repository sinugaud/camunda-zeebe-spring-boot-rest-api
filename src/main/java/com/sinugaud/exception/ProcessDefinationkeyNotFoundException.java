package com.sinugaud.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcessDefinationkeyNotFoundException extends Exception {
  String message = "Key Not Found Exeption";
}
