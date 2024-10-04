Microservices Project
This project demonstrates a microservices architecture implemented using Spring Boot, Spring Cloud, Hibernate, MySQL, Docker, and Kubernetes. It contains four main microservices, each deployed in its container, with centralized service discovery using Eureka and API routing through API Gateway.

Table of Contents
Technologies Used
Microservices Architecture
Service Communication Flow
Setup and Deployment
Docker Setup
Kubernetes Setup
Endpoints
Diagram
Technologies Used
Spring Boot: Backend framework for building microservices.
Spring Cloud: For managing inter-service communication and discovery.
Hibernate: ORM for database access.
MySQL: Relational database used to store service data.
Docker: Containerization of services.
Kubernetes: Orchestration of microservices, ensuring high availability and scalability.
Microservices Architecture
The project consists of four primary microservices:

Eureka Service: Centralized service discovery (Spring Cloud Netflix Eureka).
API Gateway: Routing gateway for requests (Spring Cloud Gateway).
Address Service: A service managing address-related data.
Student Service: A service managing student data and communicates with the Address Service to retrieve address information.
Service Communication Flow
Eureka Service registers the following services:

API Gateway
Address Service
Student Service
Request Flow:

Address Service:
A client request to Address Service first passes through the API Gateway, which then forwards it to Address Service. The Address Service interacts with the MySQL database to retrieve or store the required data.
Student Service:
A request to get student by ID passes through the API Gateway and is forwarded to the Student Service. However, before responding, Student Service fetches the address information from Address Service. This flow looks like:
API Gateway → Student Service → Address Service → MySQL (for Address Service data) → API Gateway → Client.
Setup and Deployment
Docker Setup
Build Docker images for all services:

docker-compose build
Run the containers:

docker-compose up
Docker Compose will create the following containers:

eureka-service
api-gateway
address-service
student-service
mysql
Kubernetes Setup
Apply the Kubernetes deployment files for all services and MySQL:

kubectl apply -f eureka-deployment.yaml
kubectl apply -f api-gateway-deployment.yaml
kubectl apply -f address-service-deployment.yaml
kubectl apply -f student-service-deployment.yaml
kubectl apply -f mysql-deployment.yaml
Check the status of the deployments:

kubectl get deployments
kubectl get pods
Use port forwarding (if necessary) to access services locally:

kubectl port-forward <pod-name> <local-port>:<container-port>
Endpoints
Address Service

POST /api/address/create: Create a new address.
GET /api/address/{id}: Get address by ID.
Student Service

GET /api/student/{id}: Get student by ID, including address details fetched from the Address Service.
API Gateway (routes to the above services):

http://<api-gateway-host>:<port>/address-service/api/address/...
http://<api-gateway-host>:<port>/student-service/api/student/...
Diagram
Below is a diagram illustrating the architecture and communication flow between the services:

Service Architecture Diagram:
                  +---------------------+
                  |     Eureka Server    |
                  +---------------------+
                     |         |         |
                     |         |         |
      +--------------+         |         +-----------------+
      |                        |                           |
+----------------+    +---------------------+     +---------------------+
| API Gateway    |    | Address Service     |     | Student Service      |
| (Port 9090)    |--->| (Port 8082)         |<----| (Port 8080)          |
+----------------+    +---------------------+     +---------------------+
                          |                           |
                          +--------MySQL DB-----------+
                           (Port 3306)

Explanation of the Diagram:
Eureka Server registers and manages instances of API Gateway, Address Service, and Student Service.
API Gateway routes requests to either Address Service or Student Service.
Student Service calls Address Service to fetch address data before responding to the client.
This project demonstrates the use of microservices, service discovery, API gateway routing, containerization, and orchestration with Kubernetes.
