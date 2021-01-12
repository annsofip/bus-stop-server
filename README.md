# Bus Stop App Server

This project is build using gradle, spring boot and groovy. To run requires groovy version 3.

## Available Scripts

In the project directory, on a *nix system run:

### `./gradlew bootRun --args='--spring.profiles.active=dev' `

on a windows system you can probably run:

### `gradlew.bat bootRun --args='--spring.profiles.active=dev' `

Runs the app in the development mode on port 8080.\
Open [http://localhost:8080](http://localhost:8080) to view it in the browser.

## Swagger
Swagger doc can be found here
http://localhost:8080/swagger-ui.html

##Available endpoints:
### /api/topstops
Gets the buslines with the most stops on the line. 
http://localhost:8080/topstops
