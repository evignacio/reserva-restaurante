package br.com.fiap.reservarestaurante.infrastructure.gateway;

import br.com.fiap.reservarestaurante.core.domain.Address;
import br.com.fiap.reservarestaurante.core.domain.Restaurant;
import br.com.fiap.reservarestaurante.core.gateway.RestaurantGateway;
import br.com.fiap.reservarestaurante.infrastructure.mapper.RestaurantMapper;
import br.com.fiap.reservarestaurante.infrastructure.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RestaurantGatewayImpl implements RestaurantGateway {

    private final RestaurantRepository restaurantRepository;

    @Override
    public boolean nameIsAvailable(String name) {
        return this.restaurantRepository.existsByName(name);
    }

    @Override
    public void save(Restaurant restaurant) {
        this.restaurantRepository.save(RestaurantMapper.toModel(restaurant));
    }

    @Override
    public Set<Restaurant> find(String name, String categoryId, Address address) {
        return this.restaurantRepository.findAll()
                .stream()
                .map(RestaurantMapper::toDomain)
                .collect(Collectors.toSet());
    }

    @Override
    public Optional<Restaurant> findById(String id) {
        return this.restaurantRepository.findById(id)
                .map(RestaurantMapper::toDomain);
    }

    @Override
    public void delete(String id) {
        restaurantRepository.deleteById(id);
    }
}
