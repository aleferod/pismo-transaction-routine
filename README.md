# pismo-transaction-routine
  Transaction Routine API 
# API Documentation (OpenAPI)
  http://localhost:[SERVER_PORT]/swagger-ui/index.html
  
# Deploy and Running

- ### Docker
```bash
  $ ./run-app-on-docker.sh
```
  - See logs (Docker container)
```bash
  $ docker logs transaction-routine-microservice -n all
```
  
- ## Kubernetes (Minikube)

  ### Notes: 
  #### check minikube status
  
```bash
    $ minikube status 
```
  
  #### all status must be Running

  #### start minikube cluster if needed 
  
```bash
    $ minikube start 
```
  
  #### run all commands inside k8s folder
 
## Deploy MYSQL on K8S

 - create mysql-k8s namespace
```bash
    $ kubectl create namespace mysql-k8s
```
   
 - deploy and run mysql onto kubernetes
```bash
    $ kubectl apply -f mysql/
```
  
 - Get MYSQL Service ClusterIP 
```bash
    $ kubectl -n mysql-k8s get svc
```
 
## Deploy App on K8S

 - create transaction-routine namespace
```bash
    $ kubectl create namespace transaction-routine
```

 - Put MYSQL ClusterIP in MYSQL_URL variable at __k8s/transaction-routine-deployment.yml__
 
 - Run the application in kubernetes cluster (Minikube)
```bash
    $ ./run-app-on-kubernetes.sh
```
 - Check status from transaction-routine-app pod
   
```bash
    $ kubectl -n transaction-routine get pods
```
   Status must be Running
   
  - See logs (Kubernetes POD)
```bash
  $ kubectl -n transaction-routine logs [POD_NAME]
```
  - Get POD name 
```bash
     $ kubectl -n transaction-routine get pods
```
  
# Usage

- import __pismo-transaction-routine.postman_collection.json__ collection in Postman
- import the postman environments (__docker-local.postman_environment.json__, __kubernetes-minikube-local.postman_environment.json__)

  ### Notes: 
  Using __kubernetes-minikube-local.postman_environment.json__, is necessary set __$service-port__ variable with the right value for it port binded from service
  
  - Run the following command to see the port binded from service
  ```bash
     $ kubectl -n transaction-routine get svc
  ```
