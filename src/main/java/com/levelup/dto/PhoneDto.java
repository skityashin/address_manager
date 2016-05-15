package com.levelup.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.levelup.model.Address;

/**
 * Class {@link com.levelup.dto.PhoneDto}
 *
 * @author Skityashin Vladimir
 * @version 1.0
 * @since 15.05.16
 */

@JsonAutoDetect
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class PhoneDto {

    private String namber;
    private Address address;

    public PhoneDto() {
    }

    public String getNamber() {
        return namber;
    }

    public void setNamber(String namber) {
        this.namber = namber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
