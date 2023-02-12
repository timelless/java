# Docker & Java exercise


## Section 4: Docker with Java Spring Boot Hello World Rest API (JARs)
```
./hello-world-rest-api
```

### Manual approach
* building jar file wkith maven tool window
* docker run -dit openjdk:8-jdk-alpine
* copy the jar file: docker container cp target/hello-world-rest-api.jar confident_jennings:\tmp
* crate image from the running container: docker commit confident_jennings javadocker/test1:tag
* run the image: docker run javadocker/test1
* execute the jar on startup: docker container commit --change='CMD ["java", "-jar", "/tmp/hello-world-rest-api.jar"]' confident_jennings javadocker/test:ver2

### Auto approach
* add dockerfile
* specify intruction
* build the image: docker build -t javadocker/test:dockerfile1 .
* run the image: docker run -p 8080:8080 javadocker/test:dockerfile1

* see image history: docker history javadocker/test:dockerfile1
* add exponse instruction in the docker file (cache is not using for this na the follwing lines when createing new images)

### Build image with just one command

#### Dockerfile spotify maven plugin
* add spotify maven plugin confiuration in pom.xml
* make maven build (also with: mvn package -DskipTests), image is also createds

##### Improve caching (Maven dependency plugin)
Improve bulds and cache maven dependencies, jdk images etc.
* mvn clean package

#### Using JIB Plugin to Create Docker Images
Dockerfile is not needed for JIB

#### fabric8io
...


## Section 5: Docker with Java Spring Boot Todo Web Application (WARs)
```
./todo-web-application-h2
```

### Building the war file
* in memory database is used, called h2
* secured with sring security, boot with spring boot, JPA, Hibernate
* packaing for the application is war, in this case the application should be run in web server
* the tipical start for spring boot application is spring-boot-starter-web which includes tomcat into the deplyable unit, thats why the scope of spring-boot-starter-tomcat dependency is provided - tomcat will be not uncluded in the war file
* in order to use war file extending SpringBootServletInitializer is required

### Create docker image for the war file
* catalina.sh is the shell file to laounch up Tomcat
* running the image:  docker run -p 8030:8080 in28min/todo-web-application-h2:0.0.1-SNAPSHOT


## Section 6: Docker with Java Spring Boot Todo Web Application using MySQL
```
./todo-web-application-mysql
```

### MYSQL container and local application

* mysql is used as applicatoin database
* h2 is used ad tests database

* docker run --detach --env MYSQL_ROOT_PASSWORD=dummypassword --env MYSQL_USER=todos-user --env MYSQL_PASSWORD=dummytodos --env MYSQL_DATABASE=todos --name mysql --publish 3306:3306 mysql:5.7
* docker run ... --link=mysql ... link is depricated
* -e define env. variable
* docker container run -p 8080:8080 --link=mysql -e RDS_HOSTNAME=mysql in28min/todo-web-application-mysql:0.0.1-SNAPSHOT
* docker container run --network=host  in28min/todo-web-application-mysql:0.0.1-SNAPSHOT

### More on networking

* docker network create web-applicatoin-mysql-network
* docker run --detach --env MYSQL_ROOT_PASSWORD=dummypassword --env MYSQL_USER=todos-user --env MYSQL_PASSWORD=dummytodos --env MYSQL_DATABASE=todos --name mysql --publish 3306:3306 --network=web-applicatoin-mysql-network  mysql:5.7
* docker container run -d -p 8080:8080 --network=web-applicatoin-mysql-network -e RDS_HOSTNAME=mysql in28min/todo-web-application-mysql:0.0.1-SNAPSHOT
* docker run --detach --env MYSQL_ROOT_PASSWORD=dummypassword --env MYSQL_USER=todos-user --env MYSQL_PASSWORD=dummytodos --env MYSQL_DATABASE=todos --name mysql --publish 3306:3306 --network=web-applicatoin-mysql-network --volume mysql-database-volume:/var/lib/mysql  mysql:5.7


## Section 7: Docker with Java Spring Boot React Full Stack Application

* .dockerignore
* multi-stage builds - noting is done locally
* docker-compose scale is now deprecated. Instead, you can use docker-compose up -d --scale servicename=3


## Section 8: Docker - Run Java Spring Boot Microservices