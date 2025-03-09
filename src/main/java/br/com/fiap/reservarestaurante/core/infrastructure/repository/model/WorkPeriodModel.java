package br.com.fiap.reservarestaurante.core.infrastructure.repository.model;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.DayOfWeek;

@Data
@Builder
public class WorkPeriodModel {
    @NotNull
    private DayOfWeek dayOfWeek;
    private int startHour;
    private int endHour;
}
