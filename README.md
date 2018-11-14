# note-app
Sample Restful API for the note app based on Spring Boot, JPA, Postgres technology stack.

The project comprises two java projects: note-api and note-core.

# note-api
Note API delivers Restful API for notes manipulation. The development is done on the Spring Boot base wit simple minimalistic implementation. Farter development of the project may suggest external profile specific properties or configuration management server.

Exception handler is added for proper mapping between exceptions and http error codes.
The resources returned follow HATEOAS constraint (spring hateoas starter used).

API is documented with Swagger.

# note-core
The core module encapsulates both dal and business layers. This is done for the sake of simplicity and maintainability of single version between packaged layers. In general it is highly probable that each of the layers would be located in a separate project. This would emphhasize the layers and enforce the direction between them.

The core is written with Spring Boot, Spring Data, Mockito.
Lombok could be used here to remove the getter/setter mess. Not added to ensure that the code  during the pull does not display compile time errors in the IDE-s.

QueryDSL could be used if needed for complex queries.

A simple test is created following TDD approch. In general, with farther development project's test configuration can be enhanced with data providers, extracted test configuration, and so on.

The business services throw its custom runtime exceptions to ensure the business contract does not expose (or use) undercovered implementation specific exceptions.

ModelMapper is used for entity<->model mappings between dal and business layers.


# DB
DB used is Postgres. DB connection configuration can be changed by setting application.properties props.
Specifically DB connection can be configured by setting the following props:
spring.datasource.url
spring.datasource.username
spring.datasource.password

Usually for production environment fine tuning of additional properties is required: needed to control connection pool, renew/idle/active connection policies.



