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



 







