FROM openjdk:11
ADD target/SchoolRegistration-1.0-SNAPSHOT.jar SchoolRegistration-1.0-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "SchoolRegistration-1.0-SNAPSHOT.jar"]