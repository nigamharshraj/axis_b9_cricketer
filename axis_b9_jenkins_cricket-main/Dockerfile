FROM openjdk:8

# Adding and renaming the jar file
ADD /target/SpringBootDemo-0.1.jar cricketapp.jar

#Expose 8081 port for hitting REST end points
EXPOSE 8081

ENTRYPOINT [ "java", "-jar", "cricketapp.jar" ]

