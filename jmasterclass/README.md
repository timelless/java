# Java masterclass

## Overloaded vs Overridden
Overloading - define two more methods in the class with the same name but different arguments (compile time polymorphism)
Overriding - define a method in a child clas that already exists in parent class with the same signature (runtime polymorphism or dynamic method dispatch)

## Records
Record header is the record definition  [in()], it contains coma delimited components ci fields are private and final.

## this vs super in constructors
super - call parent class methods or variables
This - call current class methods or variables, this() - call a constructor fron another overloaded constructor

## Arrays
When creating an array without array initializer statement - all aray elements get initialized to the default value for that type. For 
primitive types zero for any kind of numeric value, for booleans - false, for any type class - null.
Linear search, interval searching (binary search is subtype). For binary search array must be sorted.  
Arrays cannot be resized - ways to do it: copy elements in a different array, Arrays.copyOf(), use ArrayList.

## Lists
ArrayList maintain an array in memory. It keeps track of the capacity or max size of the array in memory, but 
also the size of the elements that has been set. ArrayList is resizable (it happens behind the scenes). No primitive types support.  
ArrayList is created with an initial capacity if specified or the number of elements added when creating it.
When underlying array is not enough Java creates new array with greater capacity and copies the existing ones.

Big O approximates the cost of operation for a certain number of elements called - n. O(1) - constant time.  

LinkedList is not indexed at all, each element is linked to the prev & next element. Beginning of the list is called head, end - tail. It can 
be considered a queue (double linked queue). Getting or setting element is not simple math - you have to start from start or end and keep traversing.  
Adding or removing element is cheap - break 2 links and add 2 new links, no new arrays are created, elements are not shifted.

## Iterators
Iterator object that allows traversal over records in a collection (like db cursor). When created the cursor position points at position before first element.  
The first call of next method moves it between 1st and 2nd element.    
Iterator is forwards only and supports the remove method.  
ListIterator allows back and forth navigation, supports remove, add and set methods.    

.valueOf(15) - manual box primitive integer
.intValue() - manual unbox

## ENUMs
Special data type that contains predefined constants. Order by the way constant is defined.

## Interfaces
Before JDK8 interface could have only public abstract method. JDK8 introduced default and public static methods, JDK9 introduced private methods.  
Extension method is identified by the modifier default, concrete method (with method).  
Private method can be called from another private method or default method.
Methods are implicitly public and static.  

## Generics
Support of generic class & generic methods. T is type identifier or type parameter (parameterized type).  
Raw use of reference type is when type parameter is not specified, it is added for backward compatibility.  
Complier do compile check of the types.  Primitive types are not allowed.   
```<T extends Player> - T must be a type of Player or subtype of it. This is upper bound on the types that are allowed to be used with this class. When upper bound is not specified - it is Object.```  
For a method type parameters are placed after any modifiers and before method return type.  
A generic method can be used for static methods on generic class, because static methods can't use any type parameters.  
```<? extends Student> - wildcard in a type argument, wildcard can be used only in type argument not type parameter declaration.```  
Wildcard means that tye is unknown.  
List<?> - unbounded.  
```<? super Player> - type that is a player or a super (parent) type of player, this is lower bound.```  
Generrics exist to enforce tighter type checks at compile time. The compiler transforms generic class into a typed class. Byte code has no type parameters, the transformation process is called type erasure.  
Generic and static 
```<T extends Object1 & Object2> - defining multiple times, Clas must come first, interfaces are after.```  

## Nested classes, Local types & Anonymous classes
Four types of nested classes - static nested classes, instance or inner lasses, local class (declared within method body), anonymous class.  
Static nested class - advantage of being able to access private attributes on the outer class.  
Inner class can have any available modifiers, default is package private.  
``` outerClass.new InnerClas()```  
Local classes do not have access modifiers, they have all fields and method of enclosing class, they are final or effectively final. Effectively final is if a value is assigned to a variable and it is never change after that. Local classes use only final variables?  
Anonymous class is a local class without a name, it is never created with class declaration.

## Lambdas (lambda expressions, functions)
Introduced in JDK8. Allows us to pass blocks of code as parameters.  
Consist of parameters lists and expressions.  
Java takes its clue from the reference type.  
Method reference - lambda expressions for a methods already defined in the class (FI).  
When you create a variable that is lambda expression or method reference - the code is invoked when the target functional method is called.  

## Collections (framework)
Collection an object that represents a group of related objects.  
A hashed collection will optimally create a limited set of buckets to provide event distribution of the objects across the buckets in a full set.  
A hash code can be any valid integer, so it could be one of 4.2B valid numbers.  
Hashed implementations often combine the hash code with other techniques to create an efficient bucketing system that aims to evenly distribute objects, thereby optimizing performance.  
Set is not implicitly ordered (some implementations are), contains no duplicates, may contain single null element.  
Best performing implementation of Set interface is HashSet class. Constant time is O(1), it uses HashMap for it own implementation.  
LinkedHashSet & TreeSet are ordered sets, LinkedHashSet maintains the order of insertion of the elements. TreeSet is sorted collection sorted by the natural order of the elements or by specifying the required sort during the creation of the set.  
LinkedHashSet extends HashSet, it maintains relationship between the elements with the use of a doubly linked list between entries. The iteration order is the same as the insertion order i.e. order is predictable.  
TreeHashSet uses data structure that is  a derivative of whats called a binary search tree.  
O(1) is constant time, O(n) is linear time.  
TreeSet implements SortedSet. Elements are stored with keyed references.  
HashMap is unordered, LinkedHashMap is ordered by insertion order and TreeMap is a sorted map.  
EnumMap & EnumSet - implementation differs from HashMap and HashSet, extremely compact and efficient. All element of EnumSet must come from a single enum type.  
EnumSets are represented internally as bit vector.

Instance initializer is a block of code declared directly in a class body, initializer is executed before any code in class constructors. There might be multiple initializer blocks.  
Constructor that takes all the props in the same order is called canonical.  

## Streams
Entire operation is called stream pipeline, it ends with terminal operation which produces result or side effect. Intermediate operations.  
Streams are lazy - source elements are consume only as needed. Stream computations are optimized for performance.  
Reduction operation, additional terminal operations.  
Flatmap - intermediate operation that performs one to many transformation on elements in a stream pipeline. It flattens the result of hierarchical collection into one stream of uniformly typed elements.

## Math & DateTime
Precision - defines the number of digits in a decimal number. It includes digits to both the left and the right of the decimal point.  
Scale is the number of digits to the right of the decimal point in a number.  
BigDecimal clas stores a floating-point number into tow integer fields - first field hold an unscaled value with a type of BigInteger , the second value holds the scale and it could be positive, zero or negative.  
A positive or 0 scale defines how many digits in the unscaled value there are, if negative values is used it means that unscaled values is multiplied by ten to the power of the negation of the scale.  
In Java, the term “thread-safe” refers to code or data structures that function correctly when accessed by multiple threads simultaneously.  
TemporalField represent a specific field with a date-time such as month of the year, day of week, hour of day. It defines fields we commonly think about in terms of date-time components.  
TemporalUnit represents a unit or duration of time such as yeas, months, days or minutes. Rather than representing a part of date or time, it represents the amount of time you might use to measure intervals between date-times.  
Locales - ResourceBundle.

## Regexes
Literals - no special meaning and are one to one match.  
Character classes - some of these are predefined, others you can define yourself. The period is an example of character class.  
Qualifiers - these matchers identify the number of occurrences of a character.  
Boundary matchers - (or anchors) they specify the position in the text. Foe example at the start of the text or at the end.  
Gropus - these identify and allow for the capturing of subexpressions.  
Greedy regular expressions match as many characters as possible.  
Reluctant regular expressions match as few characters as possible.  
Default type is greedy.

## I/O
NIO - non-blocking I/O.  
NIO.2 - improvements if NIO.  
Checked exception represents an anticipated or common problem that may occur, opening non-existing file for example. How to handle - with try catch or with throw clause by specifying exception type.  
Unchecked exception is instance of Runtime exception or one of its subclasses.  
Any code in final close is executed with or without an exception.  
Try-with-resources - it takes a colon delimited list of resource variables. The resources must implement AutoClosable or Closable.  
File handle - reference to a file that is used by the OS to keep track of the file.  
File resource - the actual data from the file.  
Using FileInputStream is inefficient (every read is disk read) it is best to wrap it in BufferedInputStream.  

## Testing & JUnit testing
Watchpoint is not the same as field [watchpoint. Field watchpoint could be placed with alt + breakpoint in a class field. There is (alt +) right click context menu for breakpoints.  
Smart step into (shift = f7) - we can choose where do we want to go (in which method).  
We can change debug variable values with right click - set value.  
If we use 3rd party code we net to change IJ where to find the source  code to debug. We need ot add it into project settings - libraries.  
Unit usually refers to a method. If we put the cursor at the end of class name definition and hit alt + enter - context menu will appear with option to create test class.  
We need to set tests to run at compile time in project structure dialog or add JUnit into class path (alt + enter when it glows in red).  
We also can/need create separate run configuration, we want to run the tests not the application. 
JUnit assertEquals uses object equals.
@AddBefore, @Before - runs before every test.  
@BeforeClass, @AfterClass - to run code before and before/all test cases. Methods should be static.  
Parameterized tests - Class has annotation  @RunWIth(Parameterized.class); @Parameterized.Parameters - used for the method that provides the arguments... Junit4 is obsolete.  

## Modules (started in Java 9)
Modules is a named collection of data and code. It has name, inputs and outputs.  
Every module has module descriptor file. Module has input, export (output), it could export packages that enables other modules to use these exported packages.module-info.java is always in root folder, this is the descriptor file.
Normal module - without the open modified, grants access at compile time and run time to types in only those packages which are explicitly exported.  
Open module, with the open modifier grants access at compile time to types in only these packages which are explicitly exported, but grants access at runtime to types in all packages, as if all packages were exported.
Scalability, bette security and maintainability.  
Named module - created with module descriptor.  
Automatic module - created after adding jar file into module path. It is not explicitly declared by the developer inside module descriptor.  
Basic module module that is not open module.  
Unnamed module does not have a name and it is not declared. It is a module created by of all JAR files from the classpath.  
Aggregator modules - do not have onw code just module descriptor, they collect and export the contents of their modules.  

There are named and unnamed modules. Named modules can be normal or automatic, all platform  modules are named modules.  
Normal modules does not actually exists, we use normal name for modules that are named modules but not automatic. Automatic modules does not have module-descriptor file.  
Automatic modules  are created after adding jar file to the module path.

```
module modulet.app.com {
    moidule-statement;
    moidule-statement;
    ...
    // exports, requires, provides, uses, opens
}
```
## Concurrency and multithreading
Process - is a unit of execution that has its own memory space. The most instances of JVM run as a process, when we run java console application what we do is kicking off a process.  
When we start 2 java applications are running - each application has its onw memory space that is called a heap. The first cannot access the heap that belongs to the second java application.  
Thread is a singe unit of execution within a process, each process can have multiple threads. In java every process or application has at last one thread and that is the main thread for main program.  
Our code will run on the main thread which is automatically created by our java program but also multiple system threads could be created that handles tasks such as memory management or inout/output.  
Creating a thread requires less resources than creating a process. Every thread created by a process shares the process heap.  
Each thread has thread stack - this is memory that only single thread will have access to.  
Why multiple threads - offload long-running tasks (if it takes long time - main thread will be blocked until the task is completed); we can use multiple threads to process large amounts of data, which can improve performance of data intensive operations; web server is another use case for many threads allowing multiple connections.  
Concurrency - application doing more than one ting at a time. It allows different parts for the program to make progress independently, often leading to better resource utilization and improved performance.  
In concurrency one task does not have to complete, before another one can start, and multiple threads can make incremental progress.  

Each instance of a thread has some state, a tread could be constructed with no arguments or runnable instance as argument, it implements runnable interface. Threads has also priority and group.  
Priority is value from 1 to 10. High-priority threads have better chance of being scheduled by a thread scheduler, over the low priority threads. It is a suggestion to thread management process.  
Ways to create thread instance - extend Thread class, create new instance of thread that implements Runnable interface, use executor to create one or more thread instances.  

run() vs start() - when executed with run method it gets done synchronously in the current thread, the main thread runs this method like any other on a class and wait for it to complete before continue with the next task.  
If run with run() method is executed synchronously with start() method is executed asynchronously.  
private native void start0() - native modifier indicates that the method source code is not written in Java, it is written in another language like C or C++. The code in this example is part of a native library sucha as dll file.  
We use native library when we need to access system-level functionality; to interface with hardware; to optimize performance for tasks that might be computationally-intensive.  

join() method - allows you to make task dependencies, for example start separate installation thread only when download (thread) was completed.  
Time slicing (time-sharing) is a technique used in multitasking operating systems to allow multiple threads or processes to share a signe CPU for execution. Whether it completes the task or not in that time slice, doesn't matter to the thread management system.
When the time is up, it has to yield to another thread and wait until its turn again, unfortunately when threads are sharing heap memory things can change during that wait.  
Java memory model (JMM) defined some rules and behaviours for threads to help control and manage shared access data ad operations.  
Synchronization - the process of controlling thread's access to shared resources.  
When threads start and pause in the same block as other threads, this is called interleaving. On result of interleaving is that resource state may change while thread is paused in the middle of its work, in this case ine thread interfere with the work of another.  
The order in which the threads execute cannot be guaranteed.  
In programming atomic action is one that effectively happens all at once, it happens completely or it does not happen at all. Side effects of an atomic action are never visible until the action is completed.  
Thread-safe - object or block of code that is not compromised by execution of concurrent threads i.e. correctness and consistency of the program's output or state is nto affected by other threads. Atomic operations and immutable objects are examples of thread-safe code.  
The operating system ay read from heap variables and make a copy of the value in each tread's own storage cache i.e. each thread has its onw small fast memory storage that holds its own copy of a shared resource's value.
One thread may change a shared variable but this will might not be immediately reflected ot visible in heap - this is called memory inconsistency. Volatile keyword is used for a modifier of class variables, it is indicator that this variable's value may be changed by multiple threads. This modifier ensures that variable is always read from and written to the main memory, rather than form any thread-specific caches. It has limited usage. It does not guarantee atomicity.
When to use volatile - when variable is used to track the state of shared resource such as counter or a flag; when variable is used to communicate between threads. Volatile should not be used for variables used by a single thread or a variable that stores larg amount of data.  

Synchronized methods (synchronize keyword) - different invocations of synchronized methods on the same objects are guaranteed not to interleave. When one thread is executing a synchronized method for an object, all other threads that invoke synchronized method for the same object, block, and suspend their execution, until the fist thread is done with the object. When synchronized method exists it ensures that the state of the object is visible to all threads.  
Critical section - section  of the code that's referencing a shared resource like a variable. Only one thread at a time should be able to execute a critical section. When all critical sections are synchronized the class is thread safe.  
Synchronized code block - alternative of synchronized method. A lock is acquired in both cases on BankAccount instance - explicitly via code block and implicitly via Synchronized method keyword.  
Every object in Java has built-in intrinsic lock also known as monitor lock. A thread requires lock by executing synchronized method on the instance or by using the instance as the parameter to a synchronized statement (code block). A thread releases a lock when it exits from a synchronized block or method, even if it throws an exception. Only one thread at a time can acquire this lock, which prevents all other threads from accessing the instance state until the lock is released. 
All other threads which want access to the instance state through synchronized code, will block and wait until they can acquire a lock.  
Synchronized block code gives us more control in fact you can use a different object on which to acquire the lock.  
If thread that has the lock tries to call another method with Synchronized block - action will be allowed because current thread already have the lock, this feature is called Reentrant Synchronization.  

Deadlock occurs when two or more threads accessing multiple shared resources, it can also occur in a case of single resource with multiple synchronized methods.
Everytime we use Synchronized statement or code block we get intrinsic lock. This lock is easy to use, but it has limitations, it is exclusive lock - it excludes  all other threads from acquiring any other kind of lock.  
You cannot call wait or notify methods without exceptions being thrown if your current thread does not have the lock or is the owner of the lock. Wait and notify method can be used only for intrinsic lock.  

### Thread management with executor service 
SingleThreadExecutionService - Simplifies thread management by abstracting execution to the level of tasks which needed to be run, use thread pools  reducing the cost of new threads, efficient scaling, they have built-in synchronization reducing concurrency related errors, they implement graceful shutdown.
SingleThreadExecutionService makes sense if we want to have many threads work in a way - one async thread at a time, if we want N threads for fire up asynchronously all executing the same tasks - there are other ways.  

ExecutorService implementations let you stay focused on task that need to be run, rather than thread creation and management.  
Executors.newFixedThreadPool - it will only create the number for threads specified regardless of the number of tasks submitted.  
Thread pool mitigates the cost of crating, destroying threads by keeping a set of threads around in a pool for current and future work. Threads that was completed could be reassigned to another task.  
Thread pool consist of - worker thread - available in the pool to execute tasks; submitted tasks - placed in FIFO; thread pool manager - allocates tasks to threads and ensures proper thread synchronization.  
What is the difference between runnable and callable - callable returns a value. Execute vs submit call on executors. 
Feature is a result of async computation, it is a generic type, a placeholder for a result instance.  
invokeAny vs invokeAll.  
Schedule a task with ScheduledExecutorService.  
WorkStealingThreadPool (ForkJoinPool is java impl of it) - used for parallelism and concurrent execution of tasks, each worker thread has its own task queue. When worker task queue is empty it can steal tasks from the bac of other worker threads queues.
Task parallelism - divides a program into smaller tasks that get executed concurrently, data parallelism - process different parts of the same data concurrently (RecursiveTask + ForkJoinPool example).  
Parallel streams - allows us to perform operations on collections in parallel (call parallel() when getting the stream or use parallelStream() instead of stream()). Parallel streams introduce some overhead such as need to create and manage multiple threads, could be slower for small data.  
Parallel streams - groupingByConcurrent - group by thread safe way. Collections (most of) are not thread safe. ConcurrentSkipListMap. Collections.synchronizedMap.  
Concurrent collections vs synchronized collections. Most of standard collections can be used with synchronized wrapper from Collections helper class. But there are concurrent/thread safe collections available also (for new codee).  

Deadlock - two or more threads are blocked waiting for each other to release a resource.  
Livelock - two or more threads are continuously looping each waiting for the other to take some action.  
Starvation - a thread is not able to obtain the resources it needs to execute.  
Fair lock cba help to prevent thread starvation.  
AtomicInteger class - it is lock free.  
WatchService - service that watches registered objects for changes and events.  

## Databases and connectivity
View is a stored query which can be access as a table but hides the details of the table implementation.  
Index contains the table name, a key value and a record locator field to quickly identify a record.  
DDL - used to define manage and modify the database objects (create, alter, drop).  
DML - manipulate stored data (select, insert, update, delete).
JDBC - java database connectivity. JDBC driver is a Java library containing classes that implement JDBC API.  
We can connect to a db via DriverManager or DataSource, DataSource is newer and cooler way to do it.    
Store procedures are a way to encapsulate complex SQL logic and data manipulation into reusable modules. Java supports execution and retrieval of results from this code segments with a special kind of statement named CallableStatement (conn.prepareCall("CALL ... procedureName")).  
Callable statements parameter types - IN, OUT, INOUT.  
Stored functions are designed to perform a specific calculations or data manipulation and return single value. Stored function are immutable - should not have side effects and should not modify data. Functions support only IN parameters.  
Call function example = conn.prepareCall("{? = CALL functionName(?)}").  

JPA consist of ORM, the entity, entity manager, persistence context (special cached area where entities exists).  
Entity can be detached outside entity manager (create method). 
JPQL - Java persistence query language.  
JPA Tuple - ordered collection of values, retrieved from a database query.  

## Network fundamentals (java.net package)
WebSockets establish persistent connection between browser and server enabling bi-directional communication.  
Intranet = private network.  
Client and servers communicate through several layers of protocols - lowest layer is network layer which uses addresses to facilitate network communication, on top of this is data transport layer.  
Port is used to route the data to specific application that's waiting for it, port is between 0 - 65535.  
IP = Internet Protocol.  
The data transfer layer provides different protocols for transporting data - transmission control protocol (TCP) and user datagram protocol (UDP). TCP is connection oriented - it estabilishes  and maintain connection between hosts, UDP is connectionless - it sends the data but dont wait for response.  
A socket is one endpoint of two-way connection, the client have a socket and the server will also have a socket, multiple connections to the same server will use the same port but each client will have its onw socket.  
Closing a server socket does not close opened connections, it just forbids (do not accept) new ones.  
ServerSocket vs ServerCannel - servers socket is blocking i/o channel is not, socket requires 1 thread per client connection channel handles multiple client connections efficiently, socket is less scalable for large number of concurrent connections channel is highly scalable, socket uses tcp channels tcp or any NIO-compatible protocol.  
Polling vs event driven - polling  check for changes at regular intervals, event driven reacts to specific events.  
WebSockets establish a connection via http handshake.  
