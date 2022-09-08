FROM openjdk:17
EXPOSE 5500
ADD target/cloud-storage-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]