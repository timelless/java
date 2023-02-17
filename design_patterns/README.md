# Java Design Patterns & SOLID Design Principles


## Section 4 - 10: Creational Design Patterns
```
./creational
```

### Builder
```
./creational/builder
```
* We are building the desired object step by step and collection all the data in private properties
* Client class is the "director", we need builder instance, also user entity - we are building the DTO by exising user object (entity)
* Inner static class builder
* Decorator role is rarely implemented as separate class
* Use builder if there are a lot of constructor arguments
