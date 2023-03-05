FROM adoptopenjdk/openjdk11:alpine-jre

VOLUME /tmp

EXPOSE 8085

COPY target/Java-Diplom-Cloud-0.0.1-SNAPSHOT.jar cloud-app-by-sobin.jar

ADD src/main/resources/application.properties src/main/resources/application.properties

CMD ["java", "-jar" ,"cloud-app-by-sobin.jar"]