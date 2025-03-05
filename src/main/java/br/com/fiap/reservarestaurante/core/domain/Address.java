package br.com.fiap.reservarestaurante.core.domain;

public class Address {
    private String city;
    private String state;
    private String country;
    private String street;
    private int number;
    private String zipCode;

    public Address(String city, String state, String country, String street, int number, String zipCode) {
      setCity(city);
      setState(state);
      setCountry(country);
      setStreet(street);
      setNumber(number);
      setZipCode(zipCode);
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        if (city == null || city.isEmpty())
            throw new IllegalArgumentException("City cannot be null or empty");

        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        if (state == null || state.isEmpty())
            throw new IllegalArgumentException("State cannot be null or empty");

        if (state.length() != 2)
            throw new IllegalArgumentException("State length must be 2");
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        if (country == null || country.isEmpty())
            throw new IllegalArgumentException("Country cannot be null or empty");

        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        if (street == null || street.isEmpty())
            throw new IllegalArgumentException("Street cannot be null or empty");

        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        if (number < 1)
            throw new IllegalArgumentException("Number cannot be less than 1 ");

        this.number = number;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        if (zipCode == null || zipCode.isEmpty())
            throw new IllegalArgumentException("ZipCode cannot be null or empty");

        if (zipCode.length() != 8)
            throw new IllegalArgumentException("ZipCode length must be 8");

        this.zipCode = zipCode;
    }
}
