# Camunda Zeebe Spring Boot Project

This project demonstrates how to integrate Camunda Zeebe with Spring Boot to deploy processes, create process instances, get active tasks, and complete tasks via REST endpoints.

## Setup

1. **Download Zeebe Broker:**
   Download the latest version of Zeebe Broker from [here](https://github.com/camunda/zeebe/releases).

2. **Setup Zeebe Broker:**
   Follow the installation instructions provided in the Zeebe documentation to set up and start the Zeebe Broker.

3. **Clone Camunda Docker Compose:**
   Clone the Camunda Docker Compose repository from [here](https://github.com/camunda/camunda-platform.git).

4. **Start Docker Compose:**
   Navigate to the cloned directory and start the Docker Compose setup using the following command:
# docker compose up
## Usage

1. **Run Spring Boot Application:**
Run the Spring Boot application, which contains REST endpoints to interact with Camunda Zeebe.

2. **Deploy Process:**
Use the appropriate REST endpoint to deploy a BPMN process to Zeebe.

3. **Start Process Instance:**
Send a POST request to start a process instance using the deployed process.

4. **Get Active Tasks:**
Retrieve active tasks for a process instance by making a GET request to the corresponding endpoint.

5. **Complete Task:**
Complete a task for a process instance by sending a POST request to the completion endpoint with the task ID and any required data.

## REST Endpoints

### Process Controller

- **Deploy Process:**
- Endpoint: `/process/deploy`
- Method: POST
- Description: Deploy a BPMN process to Zeebe.
- Request Parameters: `processName`

- **Start Process Instance by Key:**
- Endpoint: `/process/start-by-key`
- Method: POST
- Description: Start a new process instance using the process definition key.
- Request Parameters: `processDefinitionKey`
- Request Body: Map containing process variables.

- **Start Process Instance by ID:**
- Endpoint: `/process/start`
- Method: POST
- Description: Start a new process instance using the process definition ID.
- Request Parameters: `processDefinitionId`
- Request Body: Map containing process variables.

### Task Controller

- **Complete Task:**
- Endpoint: `/task/complete`
- Method: POST
- Description: Complete a task for a specific process instance.
- Request Parameters: `taskKey`
- Request Body: Map containing input process variables.

- **Get Active Tasks:**
- Endpoint: `/tasks/active`
- Method: GET
- Description: Retrieve active tasks for all process instances.
