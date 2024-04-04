# how to make it run

# requirements on your machine 
you need to have : 
- docker 
- make sure your have port 5435 free on your machine (postgres container mapping port on localhost side)

# how to make it run 

go to the following folder : 
java-reference-application/src/main/resources/docker-compose

launch the docker container with linux commande : 
docker-compose up

make sure your postgres container is started using this command : 
docker ps | grep 5435 

go to the folder containing pom.xml and create the jar with the command : 
mvn clean package -Dmaven.test.skip

now, launch the application : 
java -jar target/reference-0.0.1-SNAPSHOT.jar






 























 
