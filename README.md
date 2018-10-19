# TestClock
A windable clock for tests.

# What for?
In case of time-dependent behavior you might want to test this behavior. How do you test it? 
Will you call `Thread.sleep(...)` in your test?
```java
doSomething();
Thread.sleep(5000);
checkSomething();
```
see com.mercateo.test.clock.example.MyCacheTest.testGet_cache_expired()
This test will need at least 5 seconds to be finished just because of idling 5 seconds.


What if you have a cache that expires after 1 hour? Do you want to `Thread.sleep( 1 hr )`?


Why don't you just fast forward in that test?
```java
TestClock clock = ...
doSomething(clock);
clock.fastForward(Duration.ofSeconds(5));
checkSomething();
```
This test will not idle for 5 seconds but will only take as long as your business code runs.
see com.mercateo.test.clock.example.MyCacheTest.testGet_cache_expired_with_TestClock()

# Howto use

```xml
    <dependency>
        <groupId>com.mercateo</groupId>
        <artifactId>test-clock</artifactId>
        <version>0.0.1-SNAPSHOT</version>
        <scope>test</scope>
    </dependency>
```

```java
    OffsetDateTime seed = OffsetDateTime.of(2018, 10, 19, 9, 27, 55, 0, ZoneOffset.UTC);
    TestClock clock = TestClock.fixed(seed)

    doSomething(clock);

    clock.fastForward(Duration.ofSeconds(3));

    checkSomething();
```
