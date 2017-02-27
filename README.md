# concurrent-junit
A http://junit.org test runner to run concurrent unit tests.  Runs the tests of one test class in the following order:

1. Runs methods marked with org.junit.Before annotation in the main thread.
2. Runs methods marked with org.junit.Test annotation. Each method is run in 4 parallel threads.
3. Runs methods marked with org.junit.After annotation in the main thread.  

See [WrongAtomicityStack](https://github.com/ThomasKrieger/concurrent-junit/blob/master/concurrent-junit/src/main/java/com/anarsoft/vmlens/concurrent/example/WrongAtomicityStack.java) for an example on how to use org.junit.Before. See  [RaceConditionVolatileCounter](https://github.com/ThomasKrieger/concurrent-junit/blob/master/concurrent-junit/src/main/java/com/anarsoft/vmlens/concurrent/example/RaceConditionVolatileCounter.java) for an example on how to use org.junit.After. Most useful when using a dynamic race condition catcher like http://vmlens.com.

#Java Doc
[javadoc](https://thomaskrieger.github.io/concurrent-junit/apidocs/) 

#Supported junit versions
concurrent-junit is tested with junit 4.11 and 4.12

#Latest release
* [jar](http://search.maven.org/remotecontent?filepath=com/vmlens/concurrent-junit/1.0.0/concurrent-junit-1.0.0.jar) 
* [sources](http://search.maven.org/remotecontent?filepath=com/vmlens/concurrent-junit/1.0.0/concurrent-junit-1.0.0-sources.jar) 
* [javadoc](http://search.maven.org/remotecontent?filepath=com/vmlens/concurrent-junit/1.0.0/concurrent-junit-1.0.0-javadoc.jar) 


## Maven

```xml
<dependency>
<groupId>com.vmlens</groupId>
<artifactId>concurrent-junit</artifactId>
<version>1.0.0</version>
</dependency>
```


#License
concurrent-junit is released under the [Eclipse Public License 1.0](http://www.eclipse.org/legal/epl-v10.html)

#Blog Entries about concurrent junit
 * [Detecting Java Race Conditions With Tests, Part 2: Non Atomic Updates](http://vmlens.com/articles/detecting-java-race-conditions-with-tests-part-2/) 27/06/2016
 * [Detecting Java Race Conditions With Tests, Part 1: Lost Updates](http://vmlens.com/articles/detecting-java-race-conditions-with-tests/) 11/03/2016
 * [A new way to junit test your multithreaded java code](http://vmlens.com/articles/a-new-way-to-junit-test-your-multithreaded-java-code/) 18/11/2015




