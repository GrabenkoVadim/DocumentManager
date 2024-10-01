###** Document Management System**
 - This project is a simple Document Management System (DMS) that allows users to store, search, and retrieve documents. The system provides functionality to save documents, search for them based on various criteria (such as title, content, author, and creation date), and retrieve documents by their unique ID. It uses in-memory storage and is built with a focus on clarity and simplicity.

## Features
 - Document Upsert: Save or update documents.
 - Search Functionality: Search documents by title, content, author, and date ranges.
 - Document Retrieval: Find documents by their unique ID.
 - The project implements a simple structure that can be extended to use persistent storage in the future, such as databases.

## Technologies Used
 - Java 17: The project is written in Java using modern language features.
 - Hibernate ORM: Used for object-relational mapping (ORM) and managing data entities.
 - Lombok: To reduce boilerplate code (e.g., getters, setters, builders).
 - JUnit: For unit testing and validation of business logic.
 - Maven: For project management and dependency handling.
 
## How to Run the Project
 - Prerequisites
 - Java 17 or later should be installed on your machine.
 - Maven should be installed for dependency management.
 - Steps to Run
 - Clone the repository
 - Build the project: Use Maven to compile and package the project:
mvn clean install
 - Run the application: You can run the application directly through your IDE (e.g., IntelliJ IDEA, Eclipse) or by executing the main class using the java command.
 - Run Tests: To run the unit tests, use the following command:
mvn test

## Using the Application
Once the project is running, you can interact with the system by creating documents, searching for them using different criteria, and retrieving them by their ID. Since the project currently uses in-memory storage, the data will be reset when the application restarts.
