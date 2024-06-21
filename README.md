# Event_Registration_System_SpringBoot
Built an event registration system for organizing and managing events with Spring Security and JWT authentication and authorization.. Implemented CRUD operations to create, view, update, and delete event details. Stored event information in a PostgreSQL database, including event name, date, location, and registration details.
# Set Up
First, you need to clone this repository

`https://github.com/Pavanteja02/Event_Registration_System_SpringBoot`

Then in `src/main/resources/application.properties` enter your database name, username and password.

After that, you can run the service

```
cd Event_Registration_System_SpringBoot
mvn spring-boot:run
```
# API End Points
In current [configuration](https://github.com/Pavanteja02/Event_Registration_System_SpringBoot/blob/main/src/main/java/com/abc/event_registration_system/config/WebSecurityConfig.java) every URL endpoint with /auth/ in it won't be checked by JWT filter and Spring Security, so that means everyone can use it.
# API Documentation
## Create User
`POST http://localhost:8080/auth/createuser`

Request:
```JSON
{
    "id":1,
    "username":"user1",
    "password":"pass1",
    "firstName":"fn1",
    "lastName":"ln1",
    "email":"email1@sample.com",
    "enabled":true
    
}
```
Response: 200 OK or exception
## Login
`POST http://localhost:8080/auth/login`

Request:
```JSON
{
    "username":"user1",
    "password":"pass1"
}
```
Response:
```JSON
{
    "id": 1,
    "username": "user1",
    "firstName": "fn1",
    "lastName": "ln1",
    "email": "email1@sample.com",
    "enabled": true,
    "token": {
        "accessToken": "JWT_TOKEN_VALUE",
        "expiresIn": 3600000
    }
}
```
## Refresh Token
`POST http://localhost:8080/auth/refresh`

Authentication: you must send JWT with request 

All users are authorized to use this endpoint

Response:
```JSON
 {
        "accessToken": "JWT_TOKEN_VALUE",
        "expiresIn": 3600000
 }
```
## Change Password
`POST http://localhost:8080/auth/change-password`

Authentication: you must send JWT with request

All users are authorized to use this endpoint

Request:
```JSON
 {
    "oldPassword": "12345",
    "newPassword": "asdfg"
}
```
Response: 200 OK or exception
## Create Event
`POST http://localhost:8080/events/createevent`

Only authenticated users can use this endpoint

Provide token in Authorization and username in the params

Request:
```JSON
{
    "id":1,
    "title":"Scrum call",
    "description":"daily standup call",
    "location":"conference room A",
    "organizer":"user1",
    "startTime":"2024-06-21T09:00:00.000+0000",
    "endTime":"2024-06-21T10:00:00.000+0000",
    "maxParticipants":20,
    "username":"user1"
}
```
Response: 200 OK or exception
## Get Event by Id
`GET http://localhost:8080/events/getevent`

Only authenticated users can use this endpoint

Provide eventid,username in the params and token in Authorization

Response:
```JSON
{
    "id":1,
    "title":"Scrum call",
    "description":"daily standup call",
    "location":"conference room A",
    "organizer":"user1",
    "startTime":"2024-06-21T09:00:00.000+0000",
    "endTime":"2024-06-21T10:00:00.000+0000",
    "maxParticipants":20,
    "username":"user1"
}
```
## Get Events by Username
`GET http://localhost:8080/events/geteventsbyusername`

Only authenticated users can use this endpoint

Provide username in the params and token in Authorization

Response:
```JSON
{
    "id":1,
    "title":"Scrum call",
    "description":"daily standup call",
    "location":"conference room A",
    "organizer":"user1",
    "startTime":"2024-06-21T09:00:00.000+0000",
    "endTime":"2024-06-21T10:00:00.000+0000",
    "maxParticipants":20,
    "username":"user1"
},
{
    "id":4,
    "title":"Scrum call",
    "description":"daily standup call",
    "location":"conference room A",
    "organizer":"user1",
    "startTime":"2024-06-21T09:00:00.000+0000",
    "endTime":"2024-06-21T10:00:00.000+0000",
    "maxParticipants":50,
    "username":"user1"
}
```
## Update Event
`PUT http://localhost:8080/events/updateevent`

Only authenticated users can use this endpoint

Provide eventid, username in the params and token in Authorization

Request:
```JSON
{
    
    "description":"quarter review call",
    "organizer":"abc",
    "maxParticipants":100,
   
}
```
Response:
```JSON
{
    "id":1,
    "title":"Scrum call",
    "description":"quarter review call",
    "location":"conference room A",
    "organizer":"abc",
    "startTime":"2024-06-21T09:00:00.000+0000",
    "endTime":"2024-06-21T10:00:00.000+0000",
    "maxParticipants":100,
    "username":"user1"
}
```
## Delete Event
`DELETE http://localhost:8080/events/deleteevent`

Only authenticated users can use this endpoint

Provide eventid, username in the params and token in Authorization

Response: 200 OK or exception
