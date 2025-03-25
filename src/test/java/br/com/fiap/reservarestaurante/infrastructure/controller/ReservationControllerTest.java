package br.com.fiap.reservarestaurante.infrastructure.controller;

import br.com.fiap.reservarestaurante.core.dto.CreateReservationDTO;
import br.com.fiap.reservarestaurante.core.dto.ReservationDTO;
import br.com.fiap.reservarestaurante.infrastructure.repository.RestaurantRepository;
import br.com.fiap.reservarestaurante.infrastructure.repository.model.*;
import br.com.fiap.reservarestaurante.metadata.IntegrationTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureDataMongo
@ActiveProfiles("test")
class ReservationControllerTest {

    private final String idReservation = "idReservation";

    @MockitoBean
    private RestaurantRepository restaurantRepository;

    private RestaurantModel restaurantModel;

    @Autowired
    private MockMvc mockMvc;

    private ReservationDTO reservationDTO;

    @BeforeEach
    void init() {
        AddressModel address = AddressModel.builder()
                .city("São Paulo")
                .country("Brazil")
                .state("SP")
                .street("Rua A")
                .number(123)
                .zipCode("12345678")
                .build();

        CategoryModel category = new CategoryModel("IdCategory", "Italian");

        Set<WorkPeriodModel> workPeriods = new HashSet<>();
        var startHour = 0;
        var endHour = 23;

        workPeriods.add(new WorkPeriodModel(DayOfWeek.MONDAY, startHour, endHour));
        workPeriods.add(new WorkPeriodModel(DayOfWeek.TUESDAY, startHour, endHour));
        workPeriods.add(new WorkPeriodModel(DayOfWeek.WEDNESDAY, startHour, endHour));
        workPeriods.add(new WorkPeriodModel(DayOfWeek.THURSDAY, startHour, endHour));
        workPeriods.add(new WorkPeriodModel(DayOfWeek.FRIDAY, startHour, endHour));
        workPeriods.add(new WorkPeriodModel(DayOfWeek.SATURDAY, startHour, endHour));
        workPeriods.add(new WorkPeriodModel(DayOfWeek.SUNDAY, startHour, endHour));

        var reservation = ReservationModel.builder()
                .id(idReservation)
                .idRestaurant("idRestaurant")
                .idUser("idUser")
                .amountOfTables(3)
                .date(LocalDateTime.now().plusDays(2).withHour(12))
                .build();

        restaurantModel = RestaurantModel.builder()
                .id("idRestaurant")
                .name("Restaurant Name")
                .address(address)
                .maxCapacity(50)
                .category(category)
                .workPeriods(workPeriods)
                .reservations(Set.of(reservation))
                .build();

        reservationDTO = new ReservationDTO("idReservation", "idRestaurant", "Restaurant Name", "idUser", 2, LocalDateTime.now());
    }

    @Test
    @IntegrationTest
    void shouldCreateReservation() throws Exception {
        CreateReservationDTO createReservationDTO = new CreateReservationDTO(restaurantModel.getId(), "idUser", 2, LocalDateTime.now());
        var objMapper = new ObjectMapper();
        objMapper.registerModule(new JavaTimeModule());
        var request = objMapper.writeValueAsString(createReservationDTO);

        when(restaurantRepository.findById(restaurantModel.getId())).thenReturn(Optional.of(restaurantModel));
        when(restaurantRepository.save(restaurantModel)).thenReturn(restaurantModel);

        mockMvc.perform(post("/reservations")
                        .contentType("application/json")
                        .content(request))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.restaurantId").value(reservationDTO.restaurantId()))
                .andExpect(jsonPath("$.data.userId").value(reservationDTO.userId()));
    }

    @Test
    @IntegrationTest
    void shouldFinalizeReservation() throws Exception {
        when(restaurantRepository.findById(restaurantModel.getId())).thenReturn(Optional.of(restaurantModel));

        mockMvc.perform(patch("/reservations/" + idReservation + "/restaurants/" + restaurantModel.getId()))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @IntegrationTest
    void shouldFindUserReservations() throws Exception {
        when(restaurantRepository.findWithUserReservations("idUser")).thenReturn(Set.of(restaurantModel));

        mockMvc.perform(get("/reservations/users/idUser"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", hasSize(1)))
                .andExpect(jsonPath("$.data[0].reservationId").value(reservationDTO.reservationId()))
                .andExpect(jsonPath("$.data[0].userId").value(reservationDTO.userId()))
                .andExpect(jsonPath("$.data[0].restaurantId").value(reservationDTO.restaurantId()));
    }
}