package br.com.fiap.reservarestaurante.infrastructure.controller;

import br.com.fiap.reservarestaurante.core.domain.Address;
import br.com.fiap.reservarestaurante.core.domain.Category;
import br.com.fiap.reservarestaurante.core.domain.WorkPeriod;
import br.com.fiap.reservarestaurante.core.dto.CreateRestaurantDTO;
import br.com.fiap.reservarestaurante.infrastructure.repository.CategoryRepository;
import br.com.fiap.reservarestaurante.infrastructure.repository.RestaurantRepository;
import br.com.fiap.reservarestaurante.infrastructure.repository.model.AddressModel;
import br.com.fiap.reservarestaurante.infrastructure.repository.model.CategoryModel;
import br.com.fiap.reservarestaurante.infrastructure.repository.model.RestaurantModel;
import br.com.fiap.reservarestaurante.infrastructure.repository.model.WorkPeriodModel;
import br.com.fiap.reservarestaurante.metadata.IntegrationTest;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.Optional;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureDataMongo
@ActiveProfiles("test")
class RestaurantControllerTest {

    private static final String idRestaurant = "1";

    @MockitoBean
    RestaurantRepository restaurantRepository;

    @Autowired
    private MockMvc mockMvc;

    private RestaurantModel restaurantModel;

    @MockitoBean
    private CategoryRepository categoryRepository;

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

        WorkPeriodModel workPeriod = WorkPeriodModel.builder()
                .dayOfWeek(DayOfWeek.MONDAY)
                .startHour(9)
                .endHour(22)
                .build();

        Set<WorkPeriodModel> workPeriods = Set.of(workPeriod);

        restaurantModel = RestaurantModel.builder()
                .id(idRestaurant)
                .name("Restaurant Name")
                .address(address)
                .maxCapacity(50)
                .category(category)
                .workPeriods(workPeriods)
                .build();

    }

    @Test
    @IntegrationTest
    void shouldListRestaurants() throws Exception {
        when(restaurantRepository.findAll("Restaurant Name", "Italian", null)).thenReturn(Set.of(restaurantModel));
        mockMvc.perform(get("/restaurants").param("name", "Restaurant Name").param("categoryName", "Italian"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", hasSize(1)));
    }

    @Test
    @IntegrationTest
    void shouldCreateRestaurant() throws Exception {
        Address address = new Address("São Paulo", "SP", "Brazil", "Rua A", 123, "12345678");
        Category category = new Category("Italian");
        WorkPeriod workPeriod = new WorkPeriod(DayOfWeek.MONDAY, 9, 22);
        Set<WorkPeriod> workPeriods = Set.of(workPeriod);
        CreateRestaurantDTO restaurantDTO = new CreateRestaurantDTO("Restaurant Name", category.getName(), address, 50, workPeriods);

        var objMapper = new ObjectMapper();
        var request = objMapper.writeValueAsString(restaurantDTO);

        when(restaurantRepository.existsByName("Restaurant Name")).thenReturn(false);
        when(categoryRepository.findByName("Italian")).thenReturn(Optional.of(restaurantModel.getCategory()));
        when(restaurantRepository.save(restaurantModel)).thenReturn(restaurantModel);

        mockMvc.perform(post("/restaurants")
                        .contentType("application/json")
                        .content(request))
                .andDo(print())
                .andExpect(jsonPath("$.data.name").value(restaurantModel.getName()))
                .andExpect(jsonPath("$.data.maxCapacity").value(restaurantModel.getMaxCapacity()))
                .andExpect(status().isOk());
    }

    @Test
    @IntegrationTest
    void shouldDeleteRestaurant() throws Exception {
        when(restaurantRepository.findById(idRestaurant)).thenReturn(Optional.of(restaurantModel));
        doNothing().when(restaurantRepository).deleteById(idRestaurant);
        mockMvc.perform(delete("/restaurants/" + idRestaurant))
                .andDo(print())
                .andExpect(status().isOk());
    }
}