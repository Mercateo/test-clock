package com.mercateo.test.clock.example;

import java.time.Clock;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.function.Supplier;

public class MyCache {

    private final Supplier<Object> loader;

    private final Duration expiration;

    private OffsetDateTime lastFetch;

    private Object cachedValue;

    private final Clock clock;

    public MyCache(Duration expiration, Supplier<Object> loader, Clock clock) {
        this.expiration = expiration;
        this.loader = loader;
        this.clock = clock;
    }

    Object get() throws Exception {
        if (needToLoad()) {
            cachedValue = loader.get();
            lastFetch = OffsetDateTime.now(clock);
        }
        return cachedValue;
    }

    private boolean needToLoad() {
        if (cachedValue == null) {
            return true;
        }
        return OffsetDateTime.now(clock).isAfter(lastFetch.plus(expiration));

    }

}
