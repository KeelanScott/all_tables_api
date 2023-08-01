# all_tables_api

How to set environment variables
---

1. To set the environment variables enter these commands into your ~/.zshrc file
    1. `export DB_HOST="academy2020.cpc8rvmbbd9k.eu-west-2.rds.amazonaws.com"`
    2. `export DB_NAME="allTables_JamesE"`
    3. `export DB_USERNAME="YOUR USERNAME"`
    4. `export DB_PASSWORD="YOUR PASSWORD"`
2. Run `mvn clean intall -DskipTests` to build your application and reset environment variables.

How to start the all_tables_api application
---
### Maven
1. Run `mvn clean install` to build your application
2. Start application with `java -jar target/all_tables_api-1.0-SNAPSHOT.jar server config.yml`
3. To check that your application is running enter url `http://localhost:8080`

### Docker
1. Run `docker build --build-arg DB_HOST=${DB_HOST} --build-arg DB_PASSWORD=${DB_PASSWORD} --build-arg DB_NAME=${DB_NAME} --build-arg DB_USERNAME=${DB_USERNAME} -t all_tables_api .`
2. Run `docker run -p 8080:8080 all_tables_api`
3. To check that your application is running enter url `http://localhost:8080`
4. Swagger UI is available at `http://localhost:8080/swagger`

How to run the all_tables_api tests
---

1. Run `mvn clean test` to run the tests
2. Run `mvn clean integration-test` to run the integration tests

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`
