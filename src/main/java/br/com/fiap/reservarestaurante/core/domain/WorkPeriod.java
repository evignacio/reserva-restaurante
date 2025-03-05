package br.com.fiap.reservarestaurante.core.domain;

import java.time.DayOfWeek;

public class WorkPeriod {
    private DayOfWeek dayOfWeek;
    private int startHour;
    private int endHour;

    public WorkPeriod(DayOfWeek dayOfWeek, int startHour, int endHour) {
        if (startHour >= endHour)
            throw new IllegalArgumentException("Start hour cannot over or equal end hour");

        setDayOfWeek(dayOfWeek);
        setStartHour(startHour);
        setEndHour(endHour);
    }

    public int getStartHour() {
        return startHour;
    }

    private void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public int getEndHour() {
        return endHour;
    }

    private void setEndHour(int endHour) {
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
