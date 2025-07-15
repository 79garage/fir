## build common lib
FROM maven:3.8.5-openjdk-17 AS build-lib
COPY con-common-service/src /usr/src/app/src
COPY con-common-service/pom.xml /usr/src/app
COPY con-common-service/lib /usr/src/app/lib
RUN mvn -f /usr/src/app/pom.xml clean install -DskipTests

## build app
FROM maven:3.8.5-openjdk-17 AS build-app
COPY --from=build-lib /root/.m2 /root/.m2
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean install -DskipTests

# build image
FROM azul/zulu-openjdk-alpine:17-jre-headless-latest
# Create app directory
RUN mkdir -p /app/sso-con/
WORKDIR /app/sso-con/
ENV TZ="Asia/Bangkok"
# Bundle app source
COPY --from=build-app /usr/src/app/target/*.jar /app/sso-con/con-refund-inquiry.jar
ADD ./opt/dr/application.yaml application.yaml
ENTRYPOINT ["java", "-jar","/app/sso-con/con-refund-inquiry.jar", "--spring.config.location=file:/app/sso-con/application.yaml"]
EXPOSE 8080