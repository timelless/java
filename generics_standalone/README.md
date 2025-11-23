# Generics (standalone)

Parametrized types - List<Integer> - parametrized type. (List<E>)
Generic is assigned to the expression not the instance  
Type erasure - all generics and checks are available/happening at compile time, once it compiled to bytecode there is no generics in compiled code  
Inheritance - does not work like classes, cannot use super class for example but just specified type  
?  wildcard - specific type that is unknown  
Upper bounds - <? extends Number> <E extends Animal>, multiple upper bounds - <E extends Animal & Eats> (<E extends Animal, Eats> - Eats is another type variable), if class is presented - class goes first then interfaces  
Lower bounds - <? super Number> - either be number or super type. 

Upper bound is used for reading  
Lower bound is using for writing  

Static - when creating generic instance generic type is related to the instances, it does not work like this with static methods - public static <E extends Animal> boolean isCompatible(Animal an1, Animal an2)... 