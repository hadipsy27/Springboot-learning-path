## Springboot data JPA [Rest API]

### How to use this application

- First create a new Mysql database, check **pom.xml** & **application.properties** see configuration.
- Clean and Build with skip Tests

    `mvn clean install -DskipTests`

- Clean & Run Application with skip Tests

    `mvn clean spring-boot:run -DskipTests`

### Overview of Spring Boot JPA Rest CRUD API example
###### We will build a Spring Boot JPA Rest CRUD API for a Tutorial application in that:
- Each Tutotial has id, title, description, published status.
- Apis help to create, retrieve, update, delete Tutorials.
- Apis also support custom finder methods such as find by published status or by title.

These are APIs that we need to provide:

| Methods | Urls                      | Actions                      |
|:--------|:--------------------------|:-----------------------------|
| POST    | /api/tutorials            | create new Tutorial          |
| GET     | /api/tutorials            | retrieve all Tutorials       |
| GET     | /api/tutorial/:id         | retrieve a Tutorial by `:id` |
| PUT     | /api/tutorial/:id         | update a Tutorial by `:id`   |
| DELETE  | /api/tutorial/:id         | delete a Tutorial by `:id`   |
| DELETE  | /api/tutorials            | delete all Tutorials         |
| GET     | /api/tutorials/published  | find all published Tutorials |

### API Documentation & Swagger UI 2

- API Docs

  `http://localhost:8080/v2/api-docs`


- Swagger UI

  `http://localhost:8080/swagger-ui/`



