Task Management System

Overview
This Task Management System is developed using Spring Boot 3.x and provides a RESTful API for managing tasks.

Prerequisites
- Java 17 or higher
- Maven 3.6.0 or higher

 Installation
1. Clone the repository
3. Build the project
4. mvn clean install
5. mvn test
6. downLoad and run jar fakeSMTP-2.0.jar on port 2525
7. Run the application

 DB
The application is configured to use the H2 database. You can access the H2 console using the following link: http://localhost:8080/h2-console


Email Notification
implement daily schudle job to send email notifications for today tasks using third party fakeSMTP-2.0.jar

Task Management APIs
Implement CRUD operations for managing tasks (create, read, update, delete) using spring-boot-starter-data-jpa
implement nested class validation and handle errors for task managment.


A Postman collection is attached for sample request and responses.
[task.postman_collection.json](https://github.com/user-attachments/files/16327386/task.postman_collection.json)

User Authentication and Authorization
implement spring-boot-starter-security to generate and validate jwt based on assigned user Role as below sample



{
  "sub": "zain",
  "iss": "banquemisr",
  "roles": [
    {
      "authority": "ADMIN"
    },
    {
      "authority": "ADMIN"
    },
    {
      "authority": "ADMIN"
    },
    {
      "authority": "ADMIN"
    }
  ],
  "iat": 1721613612,
  "exp": 1721700012
}


