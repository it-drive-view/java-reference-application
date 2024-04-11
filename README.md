# how to make it run

## requirements on your machine
ensure you have :
- docker installed on your machine
- http port 8080 free on your machine : it will be the port of the http tomcat server of the spring boot application
- http port 5435 free on your machine : it will be the mapping port on localhost side binding the internal docker postgres 5432 port

## make the application run

- go to the following folder : \
java-reference-application/src/main/resources/docker-compose

- launch the docker container with linux command :  \
docker-compose up

- make sure your postgres container is started using this command :  \
docker ps | grep 5435

- go to the folder containing pom.xml and create the jar with the command :  \
mvn clean package -Dmaven.test.skip

- now, launch the application :  \
java -jar target/reference-0.0.1-SNAPSHOT.jar

application should fetch and persist some data from remote REST services.

- Once it is done, you can test this end-point computing some data from persisted data :  \
curl -X GET http://localhost:8080/api/v1/statistic/job/keywords

## launching tests

- use the classic maven command :
mvn clean test
