FROM openjdk:8-jdk-alpine

LABEL MAINTAINER="Paulo Henrique Ortolan"

ENV \
    SERVER_PORT=9090 \
    JAR_FILE=/tmp/Books.jar \
    COMPILED_PACKAGE=../../target/Books.jar

VOLUME /tmp

EXPOSE 9090 80081
#EXPOSE 9091 80083

COPY ${COMPILED_PACKAGE} /tmp/
ENTRYPOINT ["java","-Dspring.profiles.active=dev","-Dserver.port=$(SERVER_PORT)","-jar","$(JAR_FILE)"]

