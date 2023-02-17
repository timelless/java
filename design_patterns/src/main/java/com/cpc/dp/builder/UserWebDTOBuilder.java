package com.cpc.dp.builder;

import java.time.LocalDate;
import java.time.Period;

//The concrete builder for UserWebDTO
//TODO implement builder
public class UserWebDTOBuilder implements UserDTOBuilder {

    // We are building the desired object step by step
    // and collection all the data in private properties

    private String firstName;
    private String lastName;
    private String age;
    private String address;
    private UserWebDTO dto;

    @Override
    public UserDTOBuilder withFirstName(String fname) {
        this.firstName = fname;
        return this;
    }

    @Override
    public UserDTOBuilder withLastName(String lname) {
        this.lastName = lname;
        return this;
    }

    @Override
    public UserDTOBuilder withBirthday(LocalDate date) {
        Period ageInYears = Period.between(date, LocalDate.now());
        String age = Integer.toString(ageInYears.getYears());
        return this;
    }

    @Override
    public UserDTOBuilder withAddress(Address address) {
        this.address = address.getHouseNumber() + ", " + address.getStreet() + ", " +  address.getCity() + ", " +  address.getState() + ", " +  address.getZipcode();
        return this;
    }

    @Override
    public UserDTO build() {
        dto = new UserWebDTO(firstName + " " + lastName, address, age);
        return dto;
    }

    @Override
    public UserDTO getUserDTO() {
        return dto;
    }
}
