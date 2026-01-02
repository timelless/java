# Verte.x


## What is?

Vertex is a toolkit to build reactive applications on the java virtual machine.  

Reactive - message driven, elastic, resilient and responsive are very important characteristics of reactive system.  

Message driven - reactive application reacts to certain events by combining it with async programming and async message handling, more event handler.
In vertex this is done by vertex event loop. Vertex also has an actor like deployment model and actors are called Verticles. These verticles can communicate over the vertex event bus via messages and m using promise and future.  

Elastic - we can scale the number of JVM threads.  

Resilient - react to failure but no break the application.  


## Vert.x core

Non-blocking I/O (Event loop) - thread is not waiting to complete, it is processing events one after another as fast as possible.  

Vert.x object - Vertex.vertex() - vert.x object is like a control center, we can do a lot of things with it.  

Vert.x's actors are called verticles. They are running on event loop thread which are using non-blocking operations. Verticle can be deployed multiple times for scalability.  

Evet loop threads - each verticle is scheduled on event loop thread. Blocking operations by nature like db connection, file access cannot be avoided - it is possible to run them on worker tread.  

Worker - we can work on a worker - by deploying worker verticle or by calling vertex.executeBlocking(  

Event bus - support publish/subscribe messaging, point to point messaging & request-response messaging. Used for verticles to communicate with each other (in a thread safe way).  
Request response messaging - vertx.eventBus().request()  
Point to point - one verticle is sending a message to another without a response, vertx.eventBus().send()  
Publish/subscribe - one verticle is publishing a message that could be received by many consumers vertx.eventBus().publsh()  

We can send also json objects, json arrays or even custom objects over event bus. It is not supported by default - custom config codec must be configured (implements MessageCoded).  
Codec registration must be created once before each call/consume.  

Future & Promise - solving chaining callbacks. Promise - used to write an eventual value that could be completed or marked as failed, Future - used to read the value from the promise when it is available. Promise.promise(), promise.future().  
Future supports onSuccess and onFailure handlers.  
Promise is the 'write view', future is 'read view'.  

Futures are handling the outcome of promises. They can also process the result - map futures, coordinate futures, composite futures.  
Future coordination - compose() call.  
Composite futures - CompositeFuture.all() ...  

Vert.x launcher - used in fat jars as main class, can be extended if we need custom logic on start?  
Fat jar - jar that contains all compiled java classes for a project + java classes of all dependencies.  


## Vert.x web (rest api, rest client)

## Vert.x config

## Vert.x data - reactive sql clients

Multiple db connections on a single event loop thread.  
Sql client templates (library) supported - help us parse the result of sql queries.  
PgPool - postgres implementation of the reactive sql client.  


## Vert.x mutiny

It is a reactive programming library, implementing direct streams API.  
It is very simplistic nad it is highly integrated into quarkus.  
Three pillars - event driven, API navigability, simplicty.  
Multi - stream of 0 or more elements potentially unbounded.  
Uni - represents streams receiving either an item or a failure.  
Mutiny code generator - converts vertex core APIs into mutiny APIs, group - io.smallrye.reactive, artifact smallrye-nutiny-vertx-<MODULE> (MODULE is vertx module).  

.onItem() - do something with the item
.transform() - transform the item (concat for example)
.subscribe().with() - subscribing to the uni object and define multiple consumer (success and failure).  
Multi - .select().first(n), onFailure we can invoke another consumer or callable or we can use recover with.  


## Quarkus reactive & Vert.x

extend AbstractVerticle - use ing verticles. We need to create Verticle deployer class in order to register these verticles.  
How to inject Vert.x - we can inject Vertx instances into quarkus beans.  
Using event bus - vrtx.eventbus().publish()


## Vert.x0 web sockets
WS connection - full duplex communication over a single TCP connection.  
Broadcasting...