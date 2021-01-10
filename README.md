# Bus Stop App Server

This project is build using gradle, spring boot and groovy.

In the project directory, you can run:

### `./gradlew bootRun --args='--spring.profiles.active=dev' `

The application will run on port 8080 

##Available endpoints:

### /topstops
Gets the buslines with the most stops on the line. 
http://localhost:8080/topstops
