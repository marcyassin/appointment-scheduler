# appointment-scheduler
Service for scheduling appointments

## endpoints

##### GET localhost:8080/api/v1/appointment-scheduler/clients/:clientId
###### Parameters
- clientId (string)

###### Response
- 200 - Returns a list of all scheduled appointments for this client
    - Note: Returns empty list if client has no appointments

###### Example Response: 
```
[
  {
    "clientId": "cId1234",
    "start": "2021-03-19T12:00:00",
    "end": "2021-03-19T12:30:00"
  },
  {
    "clientId": "cId1234",
    "start": "2021-03-20T12:00:00",
    "end": "2021-03-20T12:30:00"
  }
]
```

##### POST localhost:8080/api/v1/appointment-scheduler/clients/:clientId?start=yyyy-MM-dd'T'HH:mm
###### Parameters
- clientId (string)
- start (Url Parameter)
  - start should be formatted according to yyyy-MM-dd'T'HH:mm

###### Response
- 200 - Creates a new appointment for the client and returns a list of all scheduled appointments for this client 
- 400 - Returns an error response if the requested client id is not in the database or if the start date is invalid
  - start date is invalid if the given minute is not equal to 00 or 30
  - or if the client already has an appointment on that date

###### Example Response: 
```
[
  {
    "clientId": "cId1234",
    "start": "2021-03-19T12:00:00",
    "end": "2021-03-19T12:30:00"
  },
  {
    "clientId": "cId1234",
    "start": "2021-03-20T12:00:00",
    "end": "2021-03-20T12:30:00"
  }
]
```

## Building and Running appointment-scheduler Locally

### Build
```shell script
mvn clean package
```

### Run
```
java -jar target/AppointmentSchedulerAPI-0.0.1-SNAPSHOT.jar
```

## Building and Running appointment-scheduler (Docker)

### Pull docker image
```shell script
docker pull marcyassin/appointment-scheduler:latest
```

### Run docker container
```shell script
docker run -d -p 8080:8080 -t marcyassin/appointment-scheduler:latest
```

### Test 
```shell script
GET http://localhost:8080/api/v1/appointment-scheduler/clients/cId1234

Response: (should see)
[
  {
    "clientId": "cId1234",
    "start": "2021-03-19T12:00:00",
    "end": "2021-03-19T12:30:00"
  }
]
```

### TODO
- Move clientId to unique generator model with validation instead of string + authentication
- Or just use oAuth to handle identifying users
- Client creation should happen first before appointment creation in another API endpoint / service
- Connect Service to Datastore (postgres or NoSQL: cassandra, mongo) to persist appointments 
- Add service health points for service + DB instance
- Add logic to only allow appointments for current date and beyond
- Add logic to expire/remove already passed appointments (by date)
- Add additional endpoints/features: reschedule appointments, delete appointments etc
- Add integration tests to verify each above endpoint behavior (see `AppointmentSchedulerTests`)
- Add docker compose file to set up full test environment
- Add more configurations (application.properties) to ready for test, prod environments
- wrap service in a CI pipeline to streamline builds 
- and much more






