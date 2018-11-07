package com.mercateo.test.clock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;

import com.google.common.testing.NullPointerTester;

import java.time.Clock;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import org.junit.Before;
import org.junit.Test;

public class TestClockTest {

    OffsetDateTime seed = OffsetDateTime.of(2018, 10, 19, 9, 27, 55, 0, ZoneOffset.UTC);

    @Before
    public void init() {
        initMocks(this);
    }

    @Test
    public void testNullContracts() {
        TestClock uut = TestClock.fixed(seed);
        NullPointerTester tester = new NullPointerTester();
        tester.testAllPublicInstanceMethods(uut);
        tester.testAllPublicConstructors(uut.getClass());
        tester.testAllPublicStaticMethods(uut.getClass());
    }

    @Test
    public void testFastForward() throws Exception {

        // Given
        TestClock uut = TestClock.fixed(seed);

        // When
        uut.fastForward(Duration.ofSeconds(3));

        // Then
        assertThat(OffsetDateTime.now(uut)).isEqualTo(seed.plus(Duration.ofSeconds(3)));

    }

    @Test
    public void testRewind() throws Exception {

        // Given
        TestClock uut = TestClock.fixed(seed);
        Duration jump = Duration.ofSeconds(3);

        // When
        uut.rewind(jump);

        // Then
        assertThat(OffsetDateTime.now(uut)).isEqualTo(seed.minus(jump));

    }

    @Test
    public void testFixed() throws Exception {

        // Given
        TestClock uut = TestClock.fixed(seed);

        // When
        Thread.sleep(99);

        // Then
        assertThat(OffsetDateTime.now(uut)).isEqualTo(seed);

    }

    @Test
    public void testWithZone() throws Exception {

        // Given
        TestClock uut = TestClock.fixed(seed);

        // When
        Clock copy = uut.withZone(ZoneOffset.ofHours(1));

        // Then
        assertThat(copy).isNotSameAs(uut);
        assertThat(copy.instant()).isEqualTo(uut.instant());
        assertThat(copy.getZone()).isEqualTo(ZoneOffset.ofHours(1));

    }

}
