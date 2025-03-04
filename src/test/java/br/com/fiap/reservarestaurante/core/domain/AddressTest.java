package br.com.fiap.reservarestaurante.core.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class AddressTest {

    @Test
    void shouldCreateAddress() {
        var result = assertDoesNotThrow(() -> new Address("São Paulo", "SP", "Brazil", "Rua A", 123, "12345678"));
        assertThat(result).isNotNull();
        assertThat(result.getCity()).isEqualTo("São Paulo");
        assertThat(result.getState()).isEqualTo("SP");
        assertThat(result.getCountry()).isEqualTo("Brazil");
        assertThat(result.getStreet()).isEqualTo("Rua A");
        assertThat(result.getNumber()).isEqualTo(123);
        assertThat(result.getZipCode()).isEqualTo("12345678");
    }

    @CsvSource(value = {
            "null",
            "''"
    }, nullValues = {"null"})
    @ParameterizedTest
    void shouldReturnExceptionCityNullOrEmpty(String city) {
        var exception = catchThrowable(() -> new Address(city, "SP", "Brazil", "Rua A", 123, "12345678"));
        assertThat(exception).isInstanceOf(IllegalArgumentException.class);
        assertThat(exception.getMessage()).isEqualTo("City cannot be null or empty");
    }

    @CsvSource(value = {
            "null",
            "''"
    }, nullValues = {"null"})
    @ParameterizedTest
    void shouldReturnExceptionStateNullOrEmpty(String state) {
        var exception = catchThrowable(() -> new Address("São Paulo", state, "Brazil", "Rua A", 123, "12345678"));
        assertThat(exception).isInstanceOf(IllegalArgumentException.class);
        assertThat(exception.getMessage()).isEqualTo("State cannot be null or empty");
    }

    @Test
    void shouldReturnExceptionStateInvalidLength() {
        var exception = catchThrowable(() -> new Address("São Paulo", "S", "Brazil", "Rua A", 123, "12345678"));
        assertThat(exception).isInstanceOf(IllegalArgumentException.class);
        assertThat(exception.getMessage()).isEqualTo("State length must be 2");
    }

    @CsvSource(value = {
            "null",
            "''"
    }, nullValues = {"null"})
    @ParameterizedTest
    void shouldReturnExceptionCountryNullOrEmpty(String country) {
        var exception = catchThrowable(() -> new Address("São Paulo", "SP", country, "Rua A", 123, "12345678"));
        assertThat(exception).isInstanceOf(IllegalArgumentException.class);
        assertThat(exception.getMessage()).isEqualTo("Country cannot be null or empty");
    }

    @CsvSource(value = {
            "null",
            "''"
    }, nullValues = {"null"})
    @ParameterizedTest
    void shouldReturnExceptionStreetNullOrEmpty(String street) {
        var exception = catchThrowable(() -> new Address("São Paulo", "SP", "Brazil", street, 123, "12345678"));
        assertThat(exception).isInstanceOf(IllegalArgumentException.class);
        assertThat(exception.getMessage()).isEqualTo("Street cannot be null or empty");
    }

    @Test
    void shouldReturnExceptionNumberInvalid() {
        var exception = catchThrowable(() -> new Address("São Paulo", "SP", "Brazil", "Rua A", 0, "12345678"));
        assertThat(exception).isInstanceOf(IllegalArgumentException.class);
        assertThat(exception.getMessage()).isEqualTo("Number cannot be less than 1 ");
    }

    @CsvSource(value = {
            "null",
            "''"
    }, nullValues = {"null"})
    @ParameterizedTest
    void shouldReturnExceptionZipCodeNullOrEmpty(String zipCode) {
        var exception = catchThrowable(() -> new Address("São Paulo", "SP", "Brazil", "Rua A", 123, zipCode));
        assertThat(exception).isInstanceOf(IllegalArgumentException.class);
        assertThat(exception.getMessage()).isEqualTo("ZipCode cannot be null or empty");
    }

    @Test
    void shouldReturnExceptionZipCodeInvalidLength() {
        var exception = catchThrowable(() -> new Address("São Paulo", "SP", "Brazil", "Rua A", 123, "1234567"));
        assertThat(exception).isInstanceOf(IllegalArgumentException.class);
        assertThat(exception.getMessage()).isEqualTo("ZipCode length must be 8");
    }
}