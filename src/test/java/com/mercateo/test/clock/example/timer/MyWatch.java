package com.mercateo.test.clock.example.timer;

import java.time.Clock;
import java.time.OffsetDateTime;

public class MyWatch {

    private final Clock clock;

    public MyWatch(Clock clock) {
        this.clock = clock;
    }

    public String now() {
        return OffsetDateTime.now(clock).toString();
    }

}
