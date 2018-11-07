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

    private Instant instant;

    private ZoneId zone;

    @Override
    public ZoneId getZone() {
        return zone;
    }

    @Override
    public Clock withZone(ZoneId zone) {
        this.zone = zone;
        return this;
    }

    @Override
    public Instant instant() {
        return instant;
    }

    public void fastForward(@NonNull TemporalAmount temporalAmount) {
        set(instant().plus(temporalAmount));
    }

    public void rewind(@NonNull TemporalAmount temporalAmount) {
        set(instant().minus(temporalAmount));
    }

    public void set(@NonNull Instant instant) {
        this.instant = instant;
    }

    public static TestClock fixed(@NonNull OffsetDateTime odt) {
        return new TestClock(odt.toInstant(), odt.getOffset());
    }

}
