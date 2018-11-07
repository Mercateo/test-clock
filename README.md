# TestClock
A windable clock for tests.

# Why using Clock at all?
You might ask why to use [java.time.Clock](https://docs.oracle.com/javase/8/docs/api/java/time/Clock.html). Why not `System.currentTimeMillis()` or `TimeZone.getDefault()` or `new Date()`? I'd like to cite the documentation from oracle
> Use of a Clock is optional. All key date-time classes also have a now() factory method that uses the system clock in the default time zone. The primary purpose of this abstraction is to allow alternate clocks to be plugged in as and when required. Applications use an object to obtain the current time rather than a static method. **This can simplify testing.** 

### in example
You have a very simple watch
```java
public class MyWatch {
    public String now() {
        return OffsetDateTime.now().toString();
    }
}
```
Now you want to test it's behavior: *Does it return the current time?* How? The following approach to test this watch works for exactly one point in time
```java
    @Test
    public void testMyWatch() {
        MyWatch myWatch = new MyWatch();
        String now = myWatch.now();
        assertThat(now).isEqualTo("2018-11-07T10:12:12.414+01:00");
    }
```
**That watch isn't testable.** But if you use *Clock* it becomes testable, see *com.mercateo.test.clock.example.timer.MyWatchTest*

# Why using this *TestClock*?
In case of **over-time behavior** you might want to test this behavior as well. How do you test it? 
Will you call `Thread.sleep(...)` in your test?
```java
doSomething();
Thread.sleep(5000);
checkSomething();
```
see *com.mercateo.test.clock.example.MyCacheTest.testGet_cache_expired()*
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
see *com.mercateo.test.clock.example.MyCacheTest.testGet_cache_expired_with_TestClock()*

# Howto use
add dependency to your pom.xml
```xml
    <dependency>
        <groupId>com.mercateo</groupId>
        <artifactId>test-clock</artifactId>
        <version>1.0.0</version>
        <scope>test</scope>
    </dependency>
```
use *TestClock* in your test
```java
    OffsetDateTime seed = OffsetDateTime.of(2018, 10, 19, 9, 27, 55, 0, ZoneOffset.UTC);
    TestClock clock = TestClock.fixed(seed)

    doSomething(clock);

    clock.fastForward(Duration.ofSeconds(3));

    checkSomething();
```
