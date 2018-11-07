package com.mercateo.test.clock.example.timer;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Clock;
import java.time.OffsetDateTime;

import org.junit.Test;

public class MyWatchTest {

    @Test
    public void testNow() {

        // given
        OffsetDateTime given = OffsetDateTime.parse("2018-11-07T10:12:12.414+01:00");
        Clock clock = Clock.fixed(given.toInstant(), given.getOffset());
        MyWatch myWatch = new MyWatch(clock);

        // when
        String now = myWatch.now();

        // then
        assertThat(now).isEqualTo("2018-11-07T10:12:12.414+01:00");

    }

}
