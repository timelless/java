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