package com.levelup.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.levelup.model.Countries;
import com.levelup.model.Phone;

import java.util.List;

/**
 * Class {@link com.levelup.dto.AddressDto}
 *
 * @author Skityashin Vladimir
 * @version 1.0
 * @since 15.05.16
 */

@JsonAutoDetect
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class AddressDto {

    private Countries country;
    private String content;
    private String phone;

    public AddressDto() {
    }

    public Countries getCountry() {
        return country;
    }

    public void setCountry(Countries country) {
        this.country = country;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
