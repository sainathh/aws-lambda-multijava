FROM openjdk:8-alpine

# Required for starting application up.
RUN apk update && apk add /bin/sh

RUN mkdir -p /root/sainadh
ENV PROJECT_HOME /root/sainadh

COPY target/loans-home-0.0.1-SNAPSHOT.jar $PROJECT_HOME/loans-home.jar

WORKDIR $PROJECT_HOME

CMD ["java" ,"-jar","./loans-home.jar"]
