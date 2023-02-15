FROM openjdk:17
# Temporarily rename output file
COPY ./target/seMethods-0.1.0.1.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "seMethods-0.1.0.1.jar", "db:3306"]