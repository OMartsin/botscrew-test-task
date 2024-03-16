University Management Console Application

This console-based Java application manages university departments, lecturers, and their degrees. It's built with Spring Boot and uses a relational database to store data. The application is pre-populated with a small dataset upon the first launch.
Getting Started
Prerequisites

    Java 17 or later
    Maven
    Access to a relational database (MySQL, PostgreSQL, etc.)

Configuration

Before running the project, you need to configure your database connection settings. Update the application.properties file with your database URL, username, and password:
```properties
spring.datasource.url=jdbc:mysql://your_database_url:port/database_name
spring.datasource.username=your_username
spring.datasource.password=your_password
```

The database will be automatically populated with a small dataset on the first launch.
Usage

The application supports the following commands:

    - Who is head of department {department_name}
        Output: "Head of {department_name} department is {head_of_department_name}" or "Department not found"

    - Show {department_name} statistics
        Output: "assistants - {assistants_count}, associate professors - {associate_professors_count}, professors - {professors_count}" or "Department not found or has no employees"

    - Show the average salary for the department {department_name}
        Output: "The average salary of {department_name} is {average_salary}" or "Department not found or has no employees"

    - Show count of employee for {department_name}
        Output: "{department_name} has {employee_count} employees" or "Department not found or has no employees"

    - Global search by {template}
        Example Input: "Global search by van"
        Output: "Ivan Petrenko, Petro Ivanov"

Enter exit to stop the application.
Architectural Decisions

The project was designed with simplicity and educational purposes in mind. As such, some common patterns and practices were considered but ultimately not implemented due to the project's scope and complexity:

- DTOs and Command Pattern: While DTOs (Data Transfer Objects) and the Command Pattern for handling requests could enhance the project's architecture, they were deemed unnecessary for this straightforward application. Implementing these patterns could be considered for larger projects with more complex requirements.

- Error Handling: Basic error handling is implemented, with the application providing user-friendly messages for common issues like missing departments or employees.

Expansion and Further Development

The project is structured to allow easy expansion. Additional functionalities, such as more detailed management of lecturers and departments or the introduction of courses and student entities, can be added with minimal adjustments to the existing codebase.

For those interested in exploring more about university management systems and related themes, you might find our last hackathon project intriguing. It covers similar ground with this test project. You can check it out here: https://github.com/mmaksymko/int20h-trandafyl-back.
