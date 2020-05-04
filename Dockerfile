FROM openjdk:8-jre-alpine 
WORKDIR /usr/local/runme
RUN apk --no-cache add curl
RUN curl -H 'Cache-Control: no-cache' -X GET http://192.168.160.99:8082/artifactory/libs-release-local/pt/ua/deti/es/p11/java-maven-app/0.0.1/java-maven-app-0.0.1.jar -O
ENTRYPOINT ["java","-jar", "java-maven-app-0.0.1.jar"]
