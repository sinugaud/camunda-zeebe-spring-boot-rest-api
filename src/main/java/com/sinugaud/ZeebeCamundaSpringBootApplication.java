package com.sinugaud;

import io.camunda.zeebe.spring.client.annotation.Deployment;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Deployment(resources = "classpath:process_task.bpmn")
public class ZeebeCamundaSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZeebeCamundaSpringBootApplication.class, args);
    }

}
