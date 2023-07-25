# all_tables_api

How to start the all_tables_api application
---

1. Run `mvn clean install` to build your application
2. Start application with `java -jar target/all_tables_api-1.0-SNAPSHOT.jar server config.yml`
3. To check that your application is running enter url `http://localhost:8080`
4. Swagger UI is available at `http://localhost:8080/swagger`

How to run the all_tables_api tests
---

1. Run `mvn clean test` to run the tests

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`
