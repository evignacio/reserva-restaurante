package br.com.fiap.reservarestaurante.infrastructure.gateway;

import br.com.fiap.reservarestaurante.infrastructure.repository.CategoryRepository;
import br.com.fiap.reservarestaurante.infrastructure.repository.model.CategoryModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryGatewayImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryGatewayImpl categoryGateway;

    @Test
    void shouldReturnCategory() {
        var categoryName = "Italian";
        var categoryModel = CategoryModel.builder()
                .id("categoryId")
                .name("categoryName")
                .build();

        when(categoryRepository.findByName(categoryName)).thenReturn(Optional.of(categoryModel));
        var category = categoryGateway.findByName(categoryName);

        assertThat(category).isPresent();
    }

    @Test
    void shouldReturnOptionalEmpty() {
        var categoryName = "Italian";

        when(categoryRepository.findByName(categoryName)).thenReturn(Optional.empty());
        var category = categoryGateway.findByName(categoryName);

        assertThat(category).isEmpty();
    }
}
