# smartbar-backoffice

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: <https://quarkus.io/>.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/smartbar-backoffice-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult <https://quarkus.io/guides/maven-tooling>.

## Related Guides


## Provided Code

### RESTEasy JAX-RS

Easily start your RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started#the-jax-rs-resources)


## Cutom

ARC in implementation of CDI.  
Quarkus is to make applications that run in cloud native infrastructure includes serverless, a core ID and guideline, apps should start as quickly as possible and require less resources.  
Design first or code first? If design first we can first design our api with open api for example.  
Quarqus run on vertex (vert.x) instance at is core, handling incoming reqursts. Quarqus runtime is build on top of it.  
Extensions ARC - dependency injection framework of quarkus, Resteasy - enables writing Jax-rs APIs, Rest easy jackson - to serialize object ot JSON with Jaxon. Extensions also autoconfigure the packed library or framework to make it as much comfortable to use w/o providing tons of configurations first.
API stubs generator plugin - https://openapi-generator.tech/docs/generators/jaxrs-spec, after adding this "org.openapitools" classes are generated under target/generated-sources/openapi/src, this default implementation can be used out of the box.  The generator generates implementation (API stubs).  
io.rest-assured - used to perform validation on a JSON response body (in tests).  
<returnResponse>true</returnResponse> - provide response body directly (http response body) or with enabling this in pom to use Jackson object mapper to pick method return value and serialize it to JSON.  
Vert.x aims for developing reactive non-blocking applications. Reactive applications are assumed to achieve much better behaviour in terms of resource consumption when it comes to scaling. A single instance of reactive application is expected to be able to handle a lot more concurrent requests.  
How to: switch to reactive style - by switching extensions (have 'reactive' in name). Code remains blocking even with reactive dependencies. To make it non-blocking we need to add @NonBlocking to the class (or method).  
When method is synchronous quarkus will call this method on a worker thread, it has dedicated thread pool for this, so the code blocks this worker thread and to be 100% precise about that this determination is done at build time not runtime.  

Parallel requests are executed on separate worker threads, this leads to classic blocking request model having much higher consumption, since each worker thread is mapped to operating system thread.
When non-blocking quarkus will invoke the handler code on the IO thread but method signature is still synchronous. This annotation does not cause quarkus to do some magic in the background automatically, we should avoid calling blocking APIs in the handler method of course.  
The non-blocking notation is not the only option to be reactive, the handler method could also return reactive data type by itself, quarkus will then run in on IO thread again.  
When you want to use Jax-rs response class, the non-blocking annotation is the only option, since the method signature is synchronous by nature.  
Arc is implementing CDI lite not CDI full, arc has some limitations - single bean archive with annotated bean discovery mode, also benefits like default beans.  

If we use constructor based injection because if injection directly into a private member - arc will use reflection when setting private members. Since quarkus uses GraalVM to build native images usage of reflection is generally limited. It is possible but results in bigger native images since every member must be registered for reflection explicitly.  
Quarkus is running tests on port 8081, it ships with junit. It also encapsulate its onw infrastructure in there for starting the application before test run. This allows you to write integration tests against running instance of the application just like spring boot. Quarkus makes it possible to avoid mocking system boundaries by integrating with test containers.  
@QuarkusTest activates junit extension.
Instead of mocking classes (MockCategoriesService) we can switch to mockito
```
@Mock
public class MockCategoriesServiceDeprecated extends CategoriesService {

    @Override
    public Category get() {
        return new Category().name("mock");
    }
}
```

After we add hibernate and postgres dependencies since we did not configure anything hibernate extension autoconfigures the database connections and starts proper docket container, this capability is called dev service. 
It is like this because of 2 things: we did include JDBC data source and second we did not configure JDBC url. Only available on dev/test  mode.  
When nothing is defined quarkus creates the schema based on entity annotations.  

Default profiles - dev, test (8081) & prod (when no other profiles are active).  
How to run in prod - 1) mvn clean package build 2) run quarkus-run.jar from target.  

Runtime prop - evaluated at runtime, build time properties - evaluated while build time and have no relevance when running the application, interssection of these could exist. Build time cannot be change or overiden at runtime.  

Panache - additional layer of abstraction above JPA which aims to further ease the use of JPA. Implements active record pattern and repository pattern (so EM is not used).  
When using PanacheEntity quarkus will rewrite this accessor access to ensure encapsulation and that information is hidden at runtime.  
Quarkus panache mock is need fo testing.  
If we do not like panache active record (extend PanacheEntity) pattern we can use repository pattern (implements PanacheRepository) (repository is needed to manage the entities).  
With repository patter you are mainly change the repository class, with active record you mainly change your entity.  
With extension rest resources with for hibernate orm - we can automatically expose implemented repository methods as rest endpoints.  
Implement PanacheRepositoryResource or PanacheEntityResource based on if we use repository patter or active record pattern.  
@ResourceProperties on top of Resource interface.  
For transactional  resources like entity manager - those calls can only be executed on a worker thread. 
We can use Hibernate ORM in reactive mode with different extensions but only db client is available in stable mode (quarkus reactive pg client).  

Application is protected by Quarkus security manager - it is activated before every request, it lands first here. Security manager looks first for configured http auth mechanism could be basic auth, jwt or else. 
Actual auth is delegated to identity provider - it gets extracted creds and performs actual auth - look on DB, using dedicated entity provider, if it fails - it goes back to htt auth mechanism which then generates a challenge for the user and returns back without letting the user access the requesed resource. 
On success identity provider makes security identity - it carries verified details also security rules relevant for security  checks.  
Security manager check by security identity if the user is allowed (authorized) to access the requested resource.  
After login  we have quarkus-credentials cooke that stores encrypted user credentials, quarkus do not work with sessions on server side for security reasons quarkus generates new cookies in background on some interval (each min?).  


When building for prod - quarkus-app - the dir with final build result, quarkus-run.jar is runnable.   
Running the app java jar ...quarkus-run.jar  


Run in a docker image (JVM docker image). Target env is container runtime (like docker).  
Dockerfile.jvm - builds a container with a jvm inside which will run the app
legacy-jar - legacy
Docker.native - run a native image of the application
native-micro - same approach but smaller image.  
In pom files - exec-maven-plugin is added to perform docker build from exactly that scene docker file. (run mvn clean install)  

Native image with GraalVM
Documentation > build native executable -> build native image w/o graalvm installed  

Quarkus CLI - create quarkus application (similar to maven and gradle).  


Http clients - Java http client, Apache http client, JaxRs, MicroProfile client

With @Retry - quarkus will retry http request up to 3 times without any delay.

Quarkus cache - extension for cache. @CacheResult  annotation. It does not ship with quarkus core. Caching is applied on non-private methods.   
Quarkus creates cache key by using method arguments by default. If there is just 1 String argument key will be argument value for example.  
If no arguments in method Quarkus will use default cache key, if > 1 arguments - Quarkus will use composite cache key. Equals and hash code is used.  
Cache invalidation - action based approach or time based approach. Annotate non-private method with @CacheInvalidate - will use the same generator strategy and method invocation is executed.  
@CacheInvalidateAll - invalidate all, no methods argument must be presented - method body could be empty. This is action based invalidation.  

For Time based invalidations - we need to add entry configuration (expire after access, expire after write...). Default cache provider is Caffeine.  
Caching concurrency - lockTimeout prop usage. If cache does not exist Quarkus will grant exclusive access to the calling thread, concurrent invocations of the same method with the same key will be suspended until the current invocation returns. 
LockTimeout tells quarkus how long to ex clusively grant access to the first non-cache hitting invocation.  

Uni data-type is non blocking.  


Messaging - producers enter data into the channel. Channel is stream of data. Consumers consume data. Data always flow from producer to consumer. Message broker can act as a producer.  
Each channel have 1 producer and 1 consumer. Producer can make its data avalaible for sever channels therefore several consumers - this is called broadcasting.  

Apache kafka - event streaming system  
RabbitMQ - message broker.

Consumer method is declared with @Incoming. Emitter is needed to create rest resource into a producer - public Asd(@Channel("my-channel") Emitter<String> emitter)... [MunityEmitter] 
@Outgoing - defined a channel and also create the method a producer for this channel meanwhile.  
Broadcast is still experimental (12.2025). @Broadcast (on producer [emitter] or on consumer [method]) marks that a channel has several consumers.  
@Broadcast - makes producer use > 1 producers, @Merge - makes consumer consume > 1 producers.  

Messages must always be confirmed by consumer if was processed or accepted. If the method implemented as message consumer has been run through successfully w/o an exception - this is success. if we work with messages direclty we have to take care about confirmation by ourselves.  
For kafka acknowledgment is = offset is commited.  
Multiple confirmations are available for chained messages via callbacks. @Acknowledgment - Quarkus handles confirmations. Virtual threads (after Java 21)?, Quarkus picks workers or I/O thread like before (in http APIs), if consumer is sync but producer is not - consumer will be executed on the very same thread that producer is running i.e. consumer will be executed on I/O thread (in our example).  

@Unremovable - Quarkus removes cdi beans that are treated as not used to keep app as efficient as possible, if cdi bean does not have injection point but are used porgramatically, this is whwn we use this annotation.  



