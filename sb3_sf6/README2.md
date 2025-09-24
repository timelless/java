# in28Minutes

## Game console Java application
Java beans, context, initialization

### Maven commands
* Force dependencies update: ```mvn clean install -U```
* Spotless check: ```spotless:check```
* Spottles apply: ```spotless:check```

### Spring container manages spring beans and their lifecycle (IOC container)
* Bean factory - basic spring container
* Application context - advanced spring container with advanced enterprise-specific features
    * Easy to use in web applications
    * Easy initialization
    * Easy integration with Spring AOP

### POJOs and Java beans (EJBs), Spring beans
* POJO is every Java object
* In order to be EJB an object must have
    * public no args constructor
    * have getters and setters
    * implement serializable interface
* Spring bean is any java object managed by Spring

### Bean qualifiers
* @Primary
* @Qualifier

### Dependency injection in Sorting
Constructor based is recommended
* Constructor based
* Setter based - with @Autowired annotation applied on the setters
* Field - with @Autowired annotation applied to the field

### Bean initialization
Eager by default
* Eager - bean is initialized even if it is not loaded or not calling any methods for the bean
* Lazy - via @lazy annotation

### Spring bean scopes - @Scope annotation, ConfigurableBeanFactory
Singleton - by default
* Prototype - get new bean from Spring context
* Singleton - get the same bean

Web ware contexts
* Request
* Session
* Application
* Websocket

### @PostConstruct & @PreDestroy
Methods are called/executed after and before beans is created or destroyed

___
## Jakarta CDI

### What is Jakarta CDI api
Spring framework implements CDI, CDI is specification (interface). Different annotations

### Stereotype annotations
@Service, @Controller, @Repository

### Important annotations
@Configuration - class that defines one or more @Bean  
@ComponentScan - define specific package to scan for components  
@Bean  
@Component  
@Service  
@Controller  
@Repository  
@Primary  
@Qualifier  
@Lazy  
@Scope  
@PostConstruct  
@PreDestroy
@Named  
@Inject

### Spring modules
* Web - Spring MVC
* Data access - JDBC, JPA
* Testing  - Spring MVC test
* Web reactive - Spring webflux

### Spring projects
* Spring framework
* Spring security
* Spring data
* Spring boot
* Spring cloud

Spring project > Spring framework > Spring modules

## Getting started

### Autoconfiguration
All default configuration is located in sprig boot autoconfigure jar.

### Profiling
Active profile is configured with spring.profiles.active=pord, configured profile has higher priority.

### Manage application configuration
@ConfigurationProperties annotation is used to identify property value and use it (manage application configuration).

### Deploy
Deploy - install java, install server, deploy war file; Embedded server - install java, run jar file (tomcat is already part of the jar file).  

### Monitor (spring boot actuator)
Beans, Metrics, Health, Mappings...  

## Getting started with JPA
JDBC is low level api, while JPA is high level ORM.  
With JPA we map object to db (entity).  
EntityManager does not have db transactions, @Transactional is explicitly needed for JPA.  
Hibernate vs JPA - JPA defines specifications (it is an API), Hibernate is implementation of JPA.

## Hibernate app
@RequestParam - bind request param to a method argument.  
DispatcherServlet is modern implementation (spring mvc) of front controller.

## Request, Model & Session
Whenever we want to retain value between certain requests we use session - @SessionAttributes, we need to use it in all the controllers when we are going to use this variable.  
ModelMap is used to set view model values

## Validations ins spring boot
Spring boot starter validation package.  
Command bean (form packing object) - spring form tag lib to make form tags to map bean properties to form fields. Use @Size, @Min, @Max. Add @Valid to the bean argument.  

## Spring security

## AOP
Spring AOP, AspectJ  
Import spring-aop, @Aspect & @Configuration. Pointcut = when/what to execute. @After, @AfterReturning, @AfterThrowing

## Mocking, Mockito
Stubs, mocks, @Mock, @InjectMock

## Functional vs Imperative programing
Functional - declarative code, we focus on what needs to be done, not how.    
Imperative - step by step logic to solve a problem - how to achieve result.  
Stream enables pipeline processing.  

Intermediate operations - filter (result is another stream), these operations are not executed until terminal operation is called.  
Terminal operations - these operations consume the stream and return a result.  

Comparator, Predicate, Function, Consumer, BinaryOperator, Supplier, BiPredicate, BiFunction, BiConsumer, UnaryOperator... Functional interface - core component of functional programming approach. Functional interface is an interface which has exact 1 abstract method. It allows passing
behaviour or code as a lambda expression, @FunctionalInterface annotation.  

Primitive functional interface were added to avoid boxing/unboxing overhead. IntBinaryOperator instead of BinaryOperator<Integer>.

Optional - helps handle missing value, container that may have or not specific value.  

Parallel streams - by using parallel(), parallelStream()  

High order functions - function that can  return or take function as a parameter (predicate for example)  
Behaviour parametrization - passing behaviour (a function) as a parameter (dynamically)  
First-class functions (function as first-class citizen) - treat function as values (store function in a variable, pass function as an argument, return function)