FROM maven:latest

WORKDIR /code
COPY . /code
ARG DB_HOST
ARG DB_PASSWORD
ARG DB_NAME
ARG DB_USERNAME

ENV DB_HOST ${DB_HOST}
ENV DB_PASSWORD ${DB_PASSWORD}
ENV DB_NAME ${DB_NAME}
ENV DB_USERNAME ${DB_USERNAME}

RUN mvn clean install -DskipTests=true

EXPOSE 8080

CMD ["java","-jar","target/all_tables_api-1.0-SNAPSHOT.jar","server","config.yml"]



