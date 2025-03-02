package br.com.fiap.reservarestaurante.domain;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.catchException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class WorkPeriodTest {

    @Test
    void shouldCreateWorkPeriod() {
        var startHour = LocalDateTime.of(2025, 3, 3, 10, 0);
        var endHour = LocalDateTime.of(2025, 3, 3, 21, 0);
        assertDoesNotThrow(() -> new WorkPeriod(DayOfWeek.MONDAY, startHour, endHour));
    }

    @Test
    void shouldReturnExceptionDayOfWeekNull() {
        var startHour = LocalDateTime.of(2025, 3, 3, 10, 0);
        var endHour = LocalDateTime.of(2025, 3, 3, 21, 0);
        var exception = catchException(() -> new WorkPeriod(null, startHour, endHour));

        assertThat(exception).isInstanceOf(IllegalArgumentException.class);
        assertThat(exception.getMessage()).isEqualTo("dayOfWeek cannot be null");
    }

    @Test
    void shouldReturnExceptionStartHourNull() {
        var endHour = LocalDateTime.of(2025, 3, 3, 21, 0);
        var exception = catchException(() -> new WorkPeriod(DayOfWeek.MONDAY, null, endHour));

        assertThat(exception).isInstanceOf(IllegalArgumentException.class);
        assertThat(exception.getMessage()).isEqualTo("Start and end hours cannot be null");
    }

    @Test
    void shouldReturnExceptionEndHourNull() {
        var startHour = LocalDateTime.of(2025, 3, 3, 10, 0);
        var exception = catchException(() -> new WorkPeriod(DayOfWeek.MONDAY, startHour, null));

        assertThat(exception).isInstanceOf(IllegalArgumentException.class);
        assertThat(exception.getMessage()).isEqualTo("Start and end hours cannot be null");
    }

    @Test
    void shouldReturnExceptionStartHourWithDayDifferentFromDayOfWeek() {
        var startHour = LocalDateTime.of(2025, 3, 4, 10, 0);
        var endHour = LocalDateTime.of(2025, 3, 4, 21, 0);
        var exception = catchException(() -> new WorkPeriod(DayOfWeek.MONDAY, startHour, endHour));

        assertThat(exception).isInstanceOf(IllegalArgumentException.class);
        assertThat(exception.getMessage()).isEqualTo("The day of start hour cannot be different of the day of the week");
    }

    @Test
    void shouldReturnExceptionEndHourWithDayDifferentFromDayOfWeek() {
        var startHour = LocalDateTime.of(2025, 3, 3, 10, 0);
        var endHour = LocalDateTime.of(2025, 3, 5, 21, 0);
        var exception = catchException(() -> new WorkPeriod(DayOfWeek.MONDAY, startHour, endHour));

        assertThat(exception).isInstanceOf(IllegalArgumentException.class);
        assertThat(exception.getMessage()).isEqualTo("The day of end hour cannot be different of the day of the week");
    }

    @Test
    void shouldReturnExceptionStartHourAfterEndHour() {
        var startHour = LocalDateTime.of(2025, 3, 3, 21, 0);
        var endHour = LocalDateTime.of(2025, 3, 3, 10, 0);
        var exception = catchException(() -> new WorkPeriod(DayOfWeek.MONDAY, startHour, endHour));

        assertThat(exception).isInstanceOf(IllegalArgumentException.class);
        assertThat(exception.getMessage()).isEqualTo("Start hour cannot be after or equal end hour");
    }

    @Test
    void shouldReturnExceptionStartHourEqualEndHour() {
        var startHour = LocalDateTime.of(2025, 3, 3, 21, 0);
        var endHour = LocalDateTime.of(2025, 3, 3, 10, 0);
        var exception = catchException(() -> new WorkPeriod(DayOfWeek.MONDAY, startHour, endHour));

        assertThat(exception).isInstanceOf(IllegalArgumentException.class);
        assertThat(exception.getMessage()).isEqualTo("Start hour cannot be after or equal end hour");
    }
}
