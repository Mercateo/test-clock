package com.mercateo.test.clock.example.cache;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import com.mercateo.test.clock.TestClock;

import java.time.Clock;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.function.Supplier;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;

public class MyCacheTest {

    @Mock
    Supplier<Object> loader;

    Clock clock = Clock.systemDefaultZone();

    Duration expireAfter = Duration.ofSeconds(5);

    @Before
    public void init() {
        initMocks(this);
        when(loader.get()).then(x -> new Object());
    }

    @Test
    public void testGet() throws Exception {

        // Given
        MyCache myCache = new MyCache(expireAfter, loader, clock);

        // When
        myCache.get();
        myCache.get();
        myCache.get();
        myCache.get();
        myCache.get();

        // Then
        verify(loader).get();

    }

    @Test
    @Ignore("this is how NOT to do it")
    public void testGet_cache_expired() throws Exception {

        // Given
        MyCache myCache = new MyCache(expireAfter, loader, clock);

        // When
        myCache.get();
        Thread.sleep(expireAfter.toMillis() + 1);
        myCache.get();

        // Then
        verify(loader, times(2)).get();

    }

    @Test
    public void testGet_cache_expired_with_TestClock() throws Exception {

        // Given
        TestClock clock = TestClock.fixed(OffsetDateTime.of(2018, 10, 19, 9, 27, 55, 0,
                ZoneOffset.UTC));
        MyCache myCache = new MyCache(expireAfter, loader, clock);

        // When
        myCache.get();
        clock.fastForward(expireAfter.plusMillis(1));
        myCache.get();

        // Then
        verify(loader, times(2)).get();

    }

}
