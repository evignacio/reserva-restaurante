package br.com.fiap.reservarestaurante.core.domain;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;

import static org.assertj.core.api.Assertions.catchException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class WorkPeriodTest {

    @Test
    void shouldCreateWorkPeriod() {
        assertDoesNotThrow(() -> new WorkPeriod(DayOfWeek.MONDAY, 10, 21));
    }

    @Test
    void shouldReturnExceptionDayOfWeekNull() {
        var exception = catchException(() -> new WorkPeriod(null, 10, 21));

        assertThat(exception).isInstanceOf(IllegalArgumentException.class);
        assertThat(exception.getMessage()).isEqualTo("dayOfWeek cannot be null");
    }


    @Test
    void shouldReturnExceptionStartHourAfterEndHour() {
        var exception = catchException(() -> new WorkPeriod(DayOfWeek.MONDAY, 21, 10));

        assertThat(exception).isInstanceOf(IllegalArgumentException.class);
        assertThat(exception.getMessage()).isEqualTo("Start hour cannot over or equal end hour");
    }

    @Test
    void shouldReturnExceptionStartHourEqualEndHour() {
        var exception = catchException(() -> new WorkPeriod(DayOfWeek.MONDAY, 21, 21));

        assertThat(exception).isInstanceOf(IllegalArgumentException.class);
        assertThat(exception.getMessage()).isEqualTo("Start hour cannot over or equal end hour");
    }
}
