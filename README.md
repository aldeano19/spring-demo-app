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
