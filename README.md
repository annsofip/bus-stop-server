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

## Available endpoints:
### /api/buslines/most-stops
Gets the buslines with the most stops on the line. 
http://localhost:8080/topstops

## API-key
To be able to call the services an api key for `SL Hållplatser och Linjer 2` is needed. You can get one by signing up here
https://www.trafiklab.se/api/sl-hallplatser-och-linjer-2
This needs to be set as a environmental variable called TRAFIKLAB_API_KEY.
This can be done with this command on a unix-system:

`export TRAFIKLAB_API_KEY=<apiKey>`
