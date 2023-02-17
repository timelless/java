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