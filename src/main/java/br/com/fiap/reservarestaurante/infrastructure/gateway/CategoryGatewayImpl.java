package br.com.fiap.reservarestaurante.infrastructure.gateway;

import br.com.fiap.reservarestaurante.core.domain.Category;
import br.com.fiap.reservarestaurante.core.gateway.CategoryGateway;
import br.com.fiap.reservarestaurante.infrastructure.repository.CategoryRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CategoryGatewayImpl implements CategoryGateway {

    private final CategoryRepository categoryRepository;

    public CategoryGatewayImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Optional<Category> findById(String id) {
        return this.categoryRepository.findById(id)
                .map(category -> new Category(category.getId(), category.getName()));
    }
}
