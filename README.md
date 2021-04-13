# Fuel50 Homework Template project

Feel free to use this template to speed things up. It contains both Angular ``client`` and SprintBoot/MySQL ``server`` projects with the infrastructure you need to start.

## Prerequisites

* Node.js/npm (https://nodejs.org/en/)
* Angular CLI (https://cli.angular.io/)
* Angular Devkit (https://www.npmjs.com/package/@angular-devkit/build-angular)
* Docker (https://docs.docker.com/get-started/)

## Usage

### server

Run ``./gradlew composeUp`` to start the Docker containers for MySQL and Adminer, then ``./gradlew bootRun`` to start the SpringBoot application.
MySQL can be found on port 3306 (use root/example as credentials)
Adminer can be found on port 8081

### client

Start with ``npm install`` to install all dependencies
After that, run ``npm run start`` to start the Angular server
Navigate to ``http://localhost:4200`` to run the app.
