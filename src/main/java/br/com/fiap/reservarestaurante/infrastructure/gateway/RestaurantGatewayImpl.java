package br.com.fiap.reservarestaurante.infrastructure.gateway;

import br.com.fiap.reservarestaurante.core.domain.Restaurant;
import br.com.fiap.reservarestaurante.core.dto.AddressDTO;
import br.com.fiap.reservarestaurante.core.gateway.RestaurantGateway;
import br.com.fiap.reservarestaurante.infrastructure.mapper.AddressMapper;
import br.com.fiap.reservarestaurante.infrastructure.mapper.RestaurantMapper;
import br.com.fiap.reservarestaurante.infrastructure.repository.RestaurantRepository;
import br.com.fiap.reservarestaurante.infrastructure.repository.model.AddressModel;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RestaurantGatewayImpl implements RestaurantGateway {

    private final RestaurantRepository restaurantRepository;

    public RestaurantGatewayImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public boolean nameIsAvailable(String name) {
        return !this.restaurantRepository.existsByName(name);
    }

    @Override
    public void save(Restaurant restaurant) {
        this.restaurantRepository.save(RestaurantMapper.toModel(restaurant));
    }

    @Override
    public Set<Restaurant> find(String name, String categoryName, AddressDTO address) {
        AddressModel addressModel = null;
        if (address != null)
            addressModel = AddressMapper.toModel(address);

        return this.restaurantRepository.findAll(name, categoryName, addressModel)
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

    @Override
    public Set<Restaurant> findUserReservations(String userId) {
        return restaurantRepository.findWithUserReservations(userId)
                .stream()
                .map(RestaurantMapper::toDomain)
                .collect(Collectors.toSet());
    }
}
