# all_tables_api

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

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`
