package br.com.fiap.reservarestaurante.domain;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class WorkPeriod {
    private LocalDateTime startHour;
    private LocalDateTime endHour;
    private DayOfWeek dayOfWeek;

    public WorkPeriod(DayOfWeek dayOfWeek, LocalDateTime startHour, LocalDateTime endHour) {
        if (startHour == null || endHour == null)
            throw new IllegalArgumentException("Start and end hours cannot be null");

        if (startHour.isAfter(endHour) || startHour.equals(endHour))
            throw new IllegalArgumentException("Start hour cannot be after or equal end hour");

        setDayOfWeek(dayOfWeek);
        setStartHour(startHour);
        setEndHour(endHour);
    }

    public LocalDateTime getStartHour() {
        return startHour;
    }

    private void setStartHour(LocalDateTime startHour) {
        if (startHour.getDayOfWeek() != this.dayOfWeek)
            throw new IllegalArgumentException("The day of start hour cannot be different of the day of the week");

        this.startHour = startHour;
    }

    public LocalDateTime getEndHour() {
        return endHour;
    }

    private void setEndHour(LocalDateTime endHour) {
        if (endHour.getDayOfWeek() != this.dayOfWeek)
            throw new IllegalArgumentException("The day of end hour cannot be different of the day of the week");

        this.endHour = endHour;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    private void setDayOfWeek(DayOfWeek dayOfWeek) {
        if (dayOfWeek == null)
            throw new IllegalArgumentException("dayOfWeek cannot be null");

        this.dayOfWeek = dayOfWeek;
    }
}
