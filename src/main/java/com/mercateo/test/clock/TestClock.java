package com.mercateo.test.clock;

import java.time.Clock;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAmount;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TestClock extends Clock {

    @NonNull
    private Clock clock;

    @Override
    public ZoneId getZone() {
        return clock.getZone();
    }

    @Override
    public Clock withZone(ZoneId zone) {
        clock = clock.withZone(zone);
        return this;
    }

    @Override
    public Instant instant() {
        return clock.instant();
    }

    public void fastForward(@NonNull TemporalAmount temporalAmount) {
        clock = Clock.fixed(clock.instant().plus(temporalAmount), clock.getZone());
    }

    public void rewind(@NonNull TemporalAmount temporalAmount) {
        clock = Clock.fixed(clock.instant().minus(temporalAmount), clock.getZone());
    }

    public void set(@NonNull Instant instant) {
        clock = Clock.fixed(instant, clock.getZone());
    }

    public static TestClock fixed(@NonNull OffsetDateTime odt) {
        Clock clock = Clock.fixed(odt.toInstant(), odt.getOffset());
        return new TestClock(clock);
    }

}
