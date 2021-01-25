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
    "clientId": "client@clients.com",
    "start": "2021-03-19T12:00:00",
    "end": "2021-03-19T12:30:00"
  },
  {
    "clientId": "client@clients.com",
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
    "clientId": "client@clients.com",
    "start": "2021-03-19T12:00:00",
    "end": "2021-03-19T12:30:00"
  },
  {
    "clientId": "client@clients.com",
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

### TODO
- Move clientId to unique generator model with validation instead of string + authentication
- Client creation should happen first before appointment creation in another API endpoint / service
- Connect Service to Datastore (postgres or NoSQL: cassandra, mongo) to persist appointments 
- Add service health points for service + DB instance
- Add additional endpoints, reschedule appointments, delete appointments etc
- Add integration tests to verify each endpoint behavior
- Add docker compose file
- wrap service in a CI pipeline to streamline builds 






