# baboonjobs-ms-users
Baboonjobs-ms-users is  a REST microservice for BaboonJobs. Its functionality is user management and authorization:
* Create user account
* Generate authorization token for registered users
* Return user information



See Baboonjobs projects:
* baboonjobs-ms-users: https://github.com/baboondev/baboonjobs-ms-users **(this project)**
* baboonjobs-ms-jobs: https://github.com/baboondev/baboonjobs-ms-jobs
* baboonjobs-frontend: https://github.com/baboondev/baboonjobs-frontend

## Contents

[TOC]

## About Baboonjobs
balbla


## Requirements 

+ Java JDK 17 https://jdk.java.net/17/
+ Apache Maven: https://maven.apache.org/
+ Mongo DB Database: https://www.mongodb.com/es/atlas


## Install and run

```shell
# Clone project
> git clone https://github.com/baboondev/baboonjobs-ms-jobs.git
> cd baboonjobs-ms-users
# After config properties, Install mvn dependencies
> mvn clean install
# Run
> mvn spring-boot:run
```
You can see others ways how to run a spring-boot application in this article: https://docs.spring.io/spring-boot/docs/2.0.x/reference/html/using-boot-running-your-application.html
#### Configure properties

Go to the file `src/main/resources/application.properties` and replace values
```
spring.data.mongodb.uri=YOUR_MONGODB_URI
spring.data.mongodb.database=YOUR_MONGO_DATABSE
server.port=PORT
```

## Endpoints
This is a brief overview of the endpoints. To see them in detail please visit the documentation generated in Swagger in your project at `<YOUR_URL_APP>/swagger-ui/index.html`

| Method | Endpoint        | Description                                                  |
| ------ | --------------- | ------------------------------------------------------------ |
| `POST` | /auth/signin    | Get user information and an access token if the credentials (email, password) sent are correct. <br />**Body:** `{"email": "string", "password": "string"}`<br />**Responses**: 200, 401, 500 |
| `POST` | /auth/signup    | Create a new user<br />**Params**: role (employee/employer)<br />**Body**: `{   "_id": "string",   "email": "string",   "firstname": "string",   "lastname": "string",   "password": "string"}`<br />**Responses**: 201, 500 |
| `GET`  | /auth/user/{id} | Get user data from a user id<br /> **Path**: id<br />**Responses**: 200, 401 |



## Screenshots

Swagger Docs:

![Swagger docs](docs/ms-users-swagger.png)
