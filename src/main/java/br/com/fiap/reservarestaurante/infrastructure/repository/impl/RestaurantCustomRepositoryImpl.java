package br.com.fiap.reservarestaurante.infrastructure.repository.impl;

import br.com.fiap.reservarestaurante.infrastructure.repository.RestaurantCustomRepository;
import br.com.fiap.reservarestaurante.infrastructure.repository.model.AddressModel;
import br.com.fiap.reservarestaurante.infrastructure.repository.model.RestaurantModel;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Set;

@Component
public class RestaurantCustomRepositoryImpl implements RestaurantCustomRepository {

    private final MongoTemplate mongoTemplate;

    public RestaurantCustomRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Set<RestaurantModel> findAll(String name, String categoryName, AddressModel address) {
        var query = new Query();

        if (name != null)
            query.addCriteria(Criteria.where("name").is(name));

        if (categoryName != null)
            query.addCriteria(Criteria.where("category.name").is(categoryName));

        if (address != null)
            query.addCriteria(Criteria.where("address.city").is(address.getCity())
                    .and("address.state").is(address.getState())
                    .and("address.country").is(address.getCountry()));

        var restaurants = mongoTemplate.find(query, RestaurantModel.class);
        return Set.copyOf(restaurants);
    }

    @Override
    public Set<RestaurantModel> findWithUserReservations(String userId) {
        var query = new Query();

        var today = LocalDateTime.now();

        if (userId != null)
            query.addCriteria(Criteria.where("reservations").elemMatch(Criteria.where("idUser").is(userId).and("date").gte(today)));

        var restaurants = mongoTemplate.find(query, RestaurantModel.class);
        return Set.copyOf(restaurants);
    }
}
