FROM openjdk:11.0.15-jre

ARG BUILD_ENV='dev'

# Create app directory
RUN mkdir -p /app/sso-con/
WORKDIR /app/sso-con/

# Bundle app source
ADD ./target/*.jar refund-inq.jar

ADD ./opt/dev-icd/application.yaml application.yaml

ENTRYPOINT ["java", "-jar","/app/sso-con/refund-inq.jar", "--spring.config.location=file:/app/sso-con/application.yaml"]

EXPOSE 8080