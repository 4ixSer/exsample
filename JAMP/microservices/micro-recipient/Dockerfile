FROM openjdk:17.0.1-oraclelinux7
WORKDIR /
ADD target/micro-recipient-0.0.1-SNAPSHOT.jar //
EXPOSE 9001
ENTRYPOINT [ "java", "-jar", "/micro-recipient-0.0.1-SNAPSHOT.jar"]
