# concurrent-junit
A http://junit.org test runner to run concurrent unit tests.  Runs the tests of one test class in the following order:

1. Runs methods marked with org.junit.Before annotation in the main thread.
2. Runs methods marked with org.junit.Test annotation. Each method is run in 4 parallel threads.
3. Runs methods marked with org.junit.After annotation in the main thread.  


See [WrongAtomicityStack](https://github.com/ThomasKrieger/concurrent-junit/blob/master/concurrent-junit/src/main/java/com/anarsoft/vmlens/concurrent/example/WrongAtomicityStack.java) for an example on how to use org.junit.Before.
See  [RaceConditionVolatileCounter](https://github.com/ThomasKrieger/concurrent-junit/blob/master/concurrent-junit/src/main/java/com/anarsoft/vmlens/concurrent/example/RaceConditionVolatileCounter.java) for an example on how to use org.junit.After.
Most useful when using a dynamic race condition catcher like http://vmlens.com.
