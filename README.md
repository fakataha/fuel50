# Mood Tracker

## Required Technologies:
* Spring MVC
* MySQL
* Angular
* Bootstrap 4.X 
* JPA

## Summary
Develop a “mood tracker” web app that captures the mood of the individual via a web page and stores it into one of these 5 categories:
* Happy
* Just normal really
* A bit “meh”
* Grumpy
* Stressed out – not a happy camper

The user should be presented with the above 5 options, and using a Cookie, track that the user has submitted their response. The user cannot submit more than one response per day (between 12am and 11:59:59pm) – so if the user tries to submit more than one response per day, a message should show that says “Sorry, you have already submitted your response for today, try again tomorrow!”

The user should be able to submit an (optional) message with their selection, no more than 350 characters long. The user should receive a confirmation that their rating has been successfully submitted.

After the user has submitted their message – they should be able to see an “Overall Team Mood” indicator – that shows the overall mood of all responses for the day. This should be the average of all responses received for the day. If you can find a way to represent this visually that would be ideal. Below the team mood, display the comments received for the current day.

The user is anonymous – so this app does not require any authentication. The UI design is up to you. Make it as simple or beautiful as you have time for.

## Prerequisites

* Node.js/npm (https://nodejs.org/en/)
* Angular CLI (https://cli.angular.io/)
* Angular Devkit (https://www.npmjs.com/package/@angular-devkit/build-angular)
* Docker (https://docs.docker.com/get-started/)

## Usage

### server

Run ``./gradlew composeUp`` to start the Docker containers for MySQL and Mood Tracker, then ``./gradlew bootRun`` to start the SpringBoot application.
MySQL can be found on port 3306 (use root/example as credentials)
Mood Tracker API can be found on port 8081

### client

Start with ``npm install`` to install all dependencies
After that, run ``npm run start`` to start the Angular server
Navigate to ``http://localhost:4200`` to run the app.

## Technical Choices
* Use Builder patterns (avoid Lombok).
* Use field injection over constructors.
* Use OffsetDateTime to persist creation date for events.
* Introduce component tests as a separate module.
* Use enum for moods.
* No caching.