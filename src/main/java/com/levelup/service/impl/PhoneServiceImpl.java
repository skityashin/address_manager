package com.levelup.service.impl;

import com.levelup.model.Phone;
import com.levelup.repository.PhoneRepository;
import com.levelup.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Class {@link com.levelup.service.impl.PhoneServiceImpl}
 *
 * @author Skityashin Vladimir
 * @version 1.0
 * @since 15.05.16
 */

@Service
public class PhoneServiceImpl implements PhoneService {

    @Autowired
    private PhoneRepository phoneRepository;

    @Override
    public void createPhone(Phone phone) {
        if (phone == null) {
            throw new IllegalArgumentException("phone cannot be null");
        }
        phoneRepository.createPhone(phone);
    }

    @Override
    public Phone findById(long id_phone) {
        return phoneRepository.findById(id_phone);
    }

    @Override
    public Phone findByNumber(String number) {
        return phoneRepository.findByNumber(number);
    }

    @Override
    public List<Phone> findByNumberPartial(String number) {
        return phoneRepository.findByNumberPartial(number);
    }

    @Override
    public List<Phone> getAllPhone() {
        return phoneRepository.getAllPhone();
    }

    @Override
    public void deleteById(long id_phone) {
        phoneRepository.deleteById(id_phone);
    }

    @Override
    public boolean isPhoneExist(long id_phone) {
        return phoneRepository.isPhoneExist(id_phone);
    }
}
