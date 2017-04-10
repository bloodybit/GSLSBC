FROM java:8
MAINTAINER snet.tu-berlin.de

WORKDIR /build

# Dependencies
ADD target/gslsbc-0.0.1.jar app.jar

EXPOSE 2001
EXPOSE 2002

ENTRYPOINT ["java", "-jar", "app.jar"]