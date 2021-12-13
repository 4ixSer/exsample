FROM openjdk:17.0.1-oraclelinux7
WORKDIR /
ADD target/micro-sender-0.0.1-SNAPSHOT.jar //
EXPOSE 9000
ENTRYPOINT [ "java", "-jar", "/micro-sender-0.0.1-SNAPSHOT.jar"]
