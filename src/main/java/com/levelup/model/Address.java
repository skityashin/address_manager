package com.levelup.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Class {@link com.levelup.model.Address}
 *
 * @author Skityashin Vladimir
 * @version 1.0
 * @since 11.05.16
 */

@Entity
@Table(name = "address")
@NamedQuery(name = "Address.findAll", query = "SELECT a FROM Address a")
public class Address implements Serializable {

    private long id_address;
    private Countries country;
    private String content;
    private List<Phone> phones;

    public Address() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_address", unique = true)
    public long getId_address() {
        return id_address;
    }

    public void setId_address(long id_address) {
        this.id_address = id_address;
    }

    @Enumerated(EnumType.STRING)
    public Countries getCountry() {
        return country;
    }

    public void setCountry(Countries country) {
        this.country = country;
    }

    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "address", cascade = CascadeType.ALL, targetEntity = Phone.class)
    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }
}
