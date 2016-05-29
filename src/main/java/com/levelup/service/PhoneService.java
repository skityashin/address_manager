package com.levelup.service;

import com.levelup.model.Phone;

import java.util.List;

/**
 * Class {@link com.levelup.service.PhoneService}
 *
 * @author Skityashin Vladimir
 * @version 1.0
 * @since 15.05.16
 */

public interface PhoneService {

    void createPhone(Phone phone);

    Phone findById(long id_phone);

    Phone findByNumber(String number);

    List<Phone> findByNumberPartial(String number);

    List<Phone> getAllPhone();

    void deleteById(long id_phone);

    boolean isPhoneExist(long id_phone);
}
