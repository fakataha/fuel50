# Mood Tracker Server

## Usage

Run ``./gradlew composeUp`` to start the Docker containers for MySQL and Mood Tracker, then ``./gradlew bootRun`` to start the SpringBoot application.
MySQL can be found on port 3306 (use root/example as credentials)
Mood Tracker API can be found on port 8081

## Testing

Following a test pyramid strategy, the server has included several modules such as 'componentTest' and 'integrationTest'.

To run these additional tests:
```bash
./gradlew componentTest integrationTest
```

### Component Testing
Intended to test the application using in memory database and application startup. The goal is to check functionality of the entire system in a local developer environment.

### Integration Testing
Intended to test the functionality between the application and any integrations it relies on in a Dockerised container to provide closer fidelity to production environments.

### Smoke Testing
Intended to confirm the application is deployed and running successfully. Mostly beneficial in a mature CI/CD pipeline to confirm the deploy worked as expected.