# Java Design Patterns & SOLID Design Principles


## Section 4 - 10: Creational Design Patterns
### Builder
```
src/main/java/com/cpc/dp
```
* We are building the desired object step by step and collection all the data in private properties
* Client class is the "director", we need builder instance, also user entity - we are building the DTO by exising user object (entity)
* Inner static class builder
* Decorator role is rarely implemented as separate class
* Use builder if there are a lot of constructor arguments

### Simple factory
```
src/main/java/com/coffeepoweredcrew/simplefactory
```
* Used to move the creation logic outside the class
* Used when we need to instantiate by different ways
* Different from Factory method design pattern

### Factory method
```
src/main/java/com/coffeepoweredcrew/factorymethod
```
* Used to move the creation logic outside the class
* We do not know in advance which class will be instantiated
* No need to change the client code if new classes are added
* Subclasses are providing the actual instance

### Prototype
```
src/main/java/com/coffeepoweredcrew/prototype
```
* When creation is costly
* Making copies, not instances from scratch
* Deep copy & shallow copy

### Abstract factory
```
src/main/java/com/coffeepoweredcrew/abstractfactory2
```
* Used when we have 2 or more objects that works together
* Intent is to isolate the client code from these objects and their creation
* Strat games example
* Uses Factory method design pattern
* We can implement singletons

### Singleton
```
src/main/java/com/coffeepoweredcrew/singleton
```
* Constructor is not accessible globally
* Subclassing/inheritance is not allowed
* Keeping track of the instance
* Give access to the instance with a public method
* Eager singleton - create the instance as soon as the class is loaded
* Lazy singleton - create the instance on demand
* !! synchronized & volatile
* Difficult for unit tests

### Object pool
```
src/main/java/com/coffeepoweredcrew/objectbool
```
* If cost of creating an instance is high or we need a lot of such object for short period of time
* Ways to implement: pre-create object or cache unused instances
* Reset should not be a costly operation
* Cached objects improve the performance but increase memory consumption and startup time
* If reset is too long move it from synchronized context since it block the pool
* Don't pool any long live objects (used by the client for a long time)
* Used for external resources like threads and connections


## Section 11 - 18: Structural Design Patterns
### Adapter
```
src/main/java/com/coffeepoweredcrew/adapter
```
* Make existing object work with client by adapting the object to the interface used by the client
* Also called wrapper as it wraps the exising object
* Two variation - class and object adapter

### Bridge
```
src/main/java/com/coffeepoweredcrew/bridge
```
* We can decouple our implementation and abstraction
* Implemented by creating two separate inheritance hierarchies - one for implementation and one for abstraction
* The hierarchies are connected by composition
* If we have single implementor we can skip this
* Suitable if abstraction can decide which implementor to use
* Provides gret extensibility, build & package separately
* Can be used with abstract factory design pattern
* It is hard to implement if implementation is already is complete

### Decorator
```
src/main/java/com/coffeepoweredcrew/decorator
```
* When we want no enhance object behaviour at runtime
* It wraps the object whom we want to enhance and then in provided the exact same interface as the object
* It can add new methods on top of already exising ones
* Avoid large state in base classes or components
* Pay attention to equals or hashCode

### Composite
```
src/main/java/com/coffeepoweredcrew/composite
```
* If we have tree structure, or part-whole relationship (multiple objects create another one)
* Create object in tree structure
* Not the simple composition known from OOP

### Facade
```
src/main/java/com/coffeepoweredcrew/facade
```
* If we have client code which interacts with large number of classes in a subsystems
* Facade provides a simple interface to a subsystem
* Not one to one method forwarding
* Minimize the complexity of a subsystem
* Great to simplify dependencies
* Simplify the work of client with a subsystem
* Encapsulate the interaction

### Flyweight
```
src/main/java/com/coffeepoweredcrew/flyweight
```
* If we have client code which interacts with large number of classes in a subsystems
* Allows us to share object to multiple contexts. Instrinct (shared in every context) & extrinct (context specific state)
* Factory is always necessary as client code need a center place where to ask for a shared flyweight object. Instances could be quite large and we need central place to store them.
* Instrinct should always be immutable.

### Proxy
```
src/main/java/com/coffeepoweredcrew/proxy
```
* Placeholder or surrogate to another object (replace it)
* Protection proxy, remote proxy, virtual proxy
* Client is unaware for proxy existing


## Section 19 - 31: Behavioral Design Patterns
### Chain of Responsibility
```
src/main/java/com/coffeepoweredcrew/chainofresponsibility
```
* When we need to avoid coupling between code who sends the request and the code who handles it (functionality)
* Object are chained together
* If object can't handle the request passes it to the next in the chain
* Handling the request is not guaranteed
* Client uses only the first object in the chain
* Always prefer interfaces

### Command
```
src/main/java/com/coffeepoweredcrew/command
```
* Represent request or method call as an object
* Advantage is we can send the object to a different parts of the application or just to store it
* Support or "undo" & "redo"
* Error handling is difficult to implement

### Interpreter
```
src/main/java/com/coffeepoweredcrew/interpreter
```
* Used when we want to process a simple "language" with rules and grammar
* - File access requires user role and admin role

### Mediator
```
src/main/java/com/coffeepoweredcrew/mediator
```
* We can use it when we have a group of objects which communicate with each other
* Remove the complexity between object interaction
* All the objects will now only the mediator
* Only mediator has collection with the other objects
* !! colleagues.stream().filter(c-> c != control).forEach(c->c.controlChanged(control));
* !! .addListener((v, o, n) -> {
* Coupled with the concrete implementations unlike Observer
* Observer is for one to many communication only

### Iterator
```
src/main/java/com/coffeepoweredcrew/iterator
```
* When we have aggregate objects (with collection) and we want ot give access to these elements (inside the aggregation/collection)
* Iterators are stateful

### Momento
```
src/main/java/com/coffeepoweredcrew/momento
```
* When we want to store object state and nobody can modify/ead it

### Observer
```
src/main/java/com/coffeepoweredcrew/observer
```
* Notify multiple objects that a state is changed (observers)
* One to many communication process
* We don't know wash is changed, just that something is changed

### State
```
src/main/java/com/coffeepoweredcrew/state
```
* Allows object to behave differently based in its external state
* This pattern allows ti define state specific behaviours in separated classes
* We don't modify the object when defining new states

### Strategy
```
src/main/java/com/coffeepoweredcrew/strategy
```
* Allows us to encapsulate an algorithm in a class

### Template method
```
src/main/java/com/coffeepoweredcrew/templatemethod
```
* Define an algorithm in a method as a series of steps. Each stem is a method call and the method is defined as abstract method in the same class

### Visitor
```
src/main/java/com/coffeepoweredcrew/visitor
```
* Allows us to define new operations for object without changing the class definition

### Null object
```
src/main/java/com/coffeepoweredcrew/nullobject
```
* We use null as absence of value of object. Using this pattern we do not have to make null checks