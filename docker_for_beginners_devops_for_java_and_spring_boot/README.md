# Docker & Java exercise

## Section 4: Docker with Java Spring Boot Hello World Rest API
./01-hello-world-rest-api

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

