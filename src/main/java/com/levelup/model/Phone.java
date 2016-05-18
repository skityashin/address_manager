package com.levelup.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Class {@link com.levelup.model.Phone}
 *
 * @author Skityashin Vladimir
 * @version 1.0
 * @since 11.05.16
 */

@Entity
@Table(name = "phone")
@NamedQuery(name = "Phone.findAll", query = "SELECT p FROM Phone p")
public class Phone implements Serializable {

    private long id_phone;
    private String namber;
    private Address address;

    public Phone() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_phone")
    public long getId_phone() {
        return id_phone;
    }

    public void setId_phone(long id_phone) {
        this.id_phone = id_phone;
    }

    @Column(name = "namber")
    public String getNamber() {
        return namber;
    }

    public void setNamber(String namber) {
        this.namber = namber;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", nullable = false)
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return namber;
    }
}
