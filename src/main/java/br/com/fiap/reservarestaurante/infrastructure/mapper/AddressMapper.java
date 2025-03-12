package br.com.fiap.reservarestaurante.infrastructure.mapper;

import br.com.fiap.reservarestaurante.core.domain.Address;
import br.com.fiap.reservarestaurante.infrastructure.repository.model.AddressModel;

public abstract class AddressMapper {

    private AddressMapper() {
    }

    public static AddressModel toModel(Address address) {
        return AddressModel.builder()
                .city(address.getCity())
                .state(address.getState())
                .country(address.getCountry())
                .street(address.getStreet())
                .number(address.getNumber())
                .zipCode(address.getZipCode())
                .build();
    }

    public static Address toDomain(AddressModel addressModel) {
        return new Address(
                addressModel.getCity(),
                addressModel.getState(),
                addressModel.getCountry(),
                addressModel.getStreet(),
                addressModel.getNumber(),
                addressModel.getZipCode()
        );
    }
}
