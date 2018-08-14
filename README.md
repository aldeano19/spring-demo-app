# Spring Demo App

### General Objective
Create a restful api with one service that takes in an account number and returns first name, 
last name, and address. Read the data from an `in-memory database` or `properties file` or `actual database`.

### Technologies used
* Spring Boot Starter 
* Spring Boot Starter Web
* Spring Boot Starter Test
* Spring Boot Starter Data Mongodb
* Swagger UI for easier testing of the application
* In memory MongoDB with de.flapdoodle.embed.mongo
    * Note that a fully functional production database is also set up but disabled since an In-Memory database was 
    preferred on the problem description. 
* MockMvc to perform web requests while testing
* Mockito to mock Service and repository calls
* Google Cloud 

### Development details taken into account

1. Monolithic Architecture 

2. Unit Tests
    * Use Builder Pattern to Create test data
    * Use Mockito to mock unnecessary abstraction layers
    
3. Throw exceptions
    * Exceptions respond with proper HTTP Error Code
    
4. Abstraction layers
    * Controllers (Client's entry point)
    * Services (Business Logic, Data Validations, etc)
    * Repositories (JPA Interface, Custom Interfaces and Implementations for complex queries)
    
5. Java Docs added to code
 
6. Swagger UI setup

7. Deployment to a Google Cloud Compute Engine VM

8. Read sensitive information from System Environment

9. Created a multithreaded process to demonstrate implementation of synchronized method
    * Upload a zip file with multiple spreadsheets
    * Every row on each spreadsheet contains information to create a new User
    * Every step of the process is logged to the same logfile using a Custom build Logging helper
    * If the log method of that custom helper is not synchronized then the log file wil be unreadable
    * Look at the examples int the root path of this repo to compare: `[synch]demo-spring-app.log` vs `[unsynch]demo-spring-app.log`
    
### Deployment method
1. Using `gradle`, create a bootable jar file.
2. Copy the file to the server.
3. Run with `java -jar demo-1.jar`

