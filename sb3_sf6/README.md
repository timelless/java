# [NEW] Master Spring Boot 3 & Spring Framework 6 with Java
*Course: https://www.udemy.com/course/spring-boot-and-spring-framework-tutorial-for-beginners/*

*Resources: https://github.com/in28minutes/master-spring-and-spring-boot/tree/main/01-spring*


## Section 2: Getting Started with Java Spring Framework
```
./section2
```
### Phase 1: Tightly coupled java code
```
com.phase1
```
### Phase 2: Lose coupling - interfaces
```
com.phase2
```
### Phase 3: Spring managing and wiring objects for us
```
com.phase3
```
### Phase 4: Game example with beans
```
com.phase3
```
* Making spring context on our own
* Making our own Spring configuration class - HelloWorldConfiguration
* Things managed by Spring are called Spring beans
* Spring container manages Spring beans and their lifecycle
* Types of IoC containers: 
* - Bean factory - basic Spring container
* - Application context - advanced Spring container: easy web applications, easy internationalization, easy integration with Spring AOP

* POJO - plain old java object - every java object is POJO
* Java bean/EJB
* - it should have public no argument constructor
* - should have getters and setters
* - it should be serializable
* Spring beans - everything managed by Spring frameworkd
* !! Try with resources


## Section 3: Using Spring Framework to Create and Manage Your Java Objects
```
./section3
```
### Java beans cration and auto-wiring
```
com.beans
```
* @Configuration
* @ComponentScan("com.vktest.vktest.game")
* @Component - the instance will be created and managed by Spring
* @Primary
* @Qualifier("SuperContraGameQuailifier")
* GameRunner(@Qualifier("SuperContraGameQuailifier") GamingConsole game)
* @Primary - a bean should be given preference when multiple canidates are qualified
* @Qualifiler - a specific bean shpuld be auto-wired
* @Autowired private SortingAlgorithm algorithm;
* @Qualifiler has higher priority tham @Primary
* @Component @Qualifier("SuperContraGameQuailifier") - if @Qualifier anotation is missing bean name culd be used, example: superContraGame
### Dependency injection types
```
com.di, com.realworld
```
* @Component can be used on any Java class
* @Bean - Typically used on methods in Spring configuration class
* @Bean - If we have a lot of custom logic before creating a Bean, or creating 3rd party Beans (libraries and stuff)
* !! @ComponentScan with no args will look in the package where it is defined
* !! System.out::println
* Constructior based - dependencies are set by creating bean using its constructor -  @Autowired is put for the for the constructor (not required)
* Setter based - dependencies are set by calling setter methods on the beans - @Autowired is put for the setter methods
* Field - dependency is injected using reflection - @Autowired annotation on a field


##Section 4: Exploring Spring Framework Advanced Features
```
./section4
```
### Lazy initialization
```
com.lazy
```
* Default initialization for Sring beans is eager (at startup)
* Eager is recommended
* Lazy not recommended
* Lazy could can be used for @Component or @Bean
* When using lazy, lazy-resoliution proxy will be injected instead of actual dependency
* Can me used on @Configuration class - all the @Beans instide will be lazy initialized
* Lazy errors will not be seen on start up (runtime execeptions)
### Scopes
```
com.scopes
```
* Prototype & singleton scopes
* Web-aware Spring application context - Request, Session, Application, Websocket
*Java singleton (GOF) vs Spring singleton
- Java singleton - one object instance per JVM
- Spring singleton - one object instance per IoC container
### Post-construct & Pre-destroy
```
com.pps
```
* !! jakarta
* J2EE > Java EE > Jakarta EE
* Intialy was built into JDK, later was separated under J2EE, Java EE is rebranding of J2EE, Oracle gave Java EE rights to Eclipse foundation and it became Jakarta EE
#### Jakarta sepcifications
* Jakarta server pages (JSP)
* Jakarta standart tag libray (JSTL)
* Jakarta enterprise beans (EJB)
* Jakarta RESTful web services (JAX-RS)
* Jakarta bean validatoin
* Jakarta context and dependency injection (CDI)
* Jakarta persistance (JPA)
* Jakarta is supported by Spring 6 and Spring boot 3 (earlies was javax)
#### CDI (context and dependency injection)
```
com.cdi
```
* Spring framework implements CDI - Inject (@Autowired), Named (@Component), Qualifier, Scope, Singleton
```
<dependency>
	<groupId>jakarta.inject</groupId>
	<artifactId>jakarta.inject-api</artifactId>
	<version>2.0.1</version>
</dependency>
```
* !! Maven dependencies
#### XML Configuration
```
com.xmlc
```
#### Stereotype anotations
* @Component - generic annotation applicable for any class, base for all spring stereotypes annotations
* - @Service - indicates that the annotated class has business logic
* - @Controller - indicates that the annotated class is a controller (e.g. web controller)
* - @Repostiry -  indicates that the annotated class is used to retrieve and or manipulate data in database
* Use the most specific service
#### Spring big picture
* N/A
#### Modules
* ! Fundamental features - core (Ioc container, Dependency injection, Autowiring ...)
* Web - Spring MVC etc (Web applications, REST API)
* Web reactive - Spring WebFlux etc
* Data access - JDBC, JPA
* Integrate other applications - JMS etc
* Testing - mock objects, Spring MVC Test etc

##### Projects=
* Spring framework
* Spring security
* Spring data
* Spring integration - communication between applications
* Spring boot - build microservices
* Spring cloud - build cloud native applications


## Section 5: Getting Started with Spring Boot
```
./springboot-test
```
* Setting up Spring porjects before Spring boot awas not easy
* A lot of thing to configure - dependenies, web.xml, spring configuration, NFRs (loggin, monitoring, error handling)

* Spring boot goal is production-ready application quickly
* - Spring boot initilizr
* - Spring boot starter projects
* - Spring boot auto configuraion
* - Spring boot dev tools
* Production ready
* - Logging
* - Different configuration for different environtments (profiles)
* - Monitoring (Spring boot actuator)

### Spring boot starter projects
* Provide all the dependencies needed (dependency descriptions)
* - Spring boot starter web
* - Spring boot starter test
* - Spring boot starter data JPA
* - Spring boot starter data JDBC
* - Spring boot starter security

### Spring boot  auto configuraion
* Auto configuration located in Spring boot auto configuration jar(s)
* logging.level.org.springframework=debug, default logging level is info
* CONDITIONS EVALUATION REPORT (in log)
* - Positive matches - were auto configured
* - Negative matches - did not get auto configured

### Spring boot dev tools
* Helps with not restarting the server for example

### Production ready
* application-dev.properties
* application-prod.properties

### Embeded servers
* The server is already a part of the jar file
* clean install

### Spring boot actuator (monitoring)
* http://localhost:8080/actuator

### Spring boot vs Spring MVC vs Spring
* Sprintg framework - dependency injetion (@Component)
* Spring MVC - spring module for web apps and REST (@Controller, @RequestMapping)
* Spring project - quick build for prod ready env. (eliminate configuration of Spring, Spring MVC)
* Spring boot is a wrapper for Spring and Spring MVC
* @SpringBootApplication annotation is a combination of 3 annotations: @SpringBootConfiguration + @EnableAutoConfiguration + @ComponentScan

## Section 6: Getting Started with JPA and Hibernate with Spring and Spring Boot
```
./jpa-hiberate
```
* CommandLineRunner - execute commands on app boot
* new BeanPropertyRowMapper<> (Course.class)
* @PersistenceContext
* JPA - maps entities to tables

## Hibernate vs JPA
* JPA defines specifications
* Hibernate in implemenation of JPA (the most popular)
* Use Hiberante jar but JPA imports!

## Section 7: Build Java Web Application with Spring Framework, Spring Boot and Hibernate
```
./restful
```