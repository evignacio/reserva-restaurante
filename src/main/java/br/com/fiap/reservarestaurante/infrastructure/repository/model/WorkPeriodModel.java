package br.com.fiap.reservarestaurante.infrastructure.repository.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.DayOfWeek;

@Data
@Builder
@AllArgsConstructor
public class WorkPeriodModel {
    @NotNull
    private DayOfWeek dayOfWeek;
    private int startHour;
    private int endHour;

    public WorkPeriodModel() {
        //Default
    }
}
