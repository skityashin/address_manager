package com.levelup.repository.impl;

import com.levelup.model.Phone;
import com.levelup.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Class {@link com.levelup.repository.impl.PhoneRepositoryImpl}
 *
 * @author Skityashin Vladimir
 * @version 1.0
 * @since 15.05.16
 */

@Repository
@Transactional
public class PhoneRepositoryImpl implements PhoneRepository {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public void createPhone(Phone phone) {
        if(phone == null) {
            throw new IllegalArgumentException("Phone cannot be null");
        } hibernateTemplate.saveOrUpdate(phone);
    }

    @Override
    public Phone findById(long id_phone) {
        return hibernateTemplate.get(Phone.class, id_phone);
    }

    @Override
    public List<Phone> getAllPhone() {
        return (List<Phone>) hibernateTemplate.find("select * FROM Phone");
    }

    @Override
    public void deleteById(long id_phone) {
        hibernateTemplate.delete(findById(id_phone));
    }

    @Override
    public boolean isPhoneExist(long id_phone) {
        return hibernateTemplate.contains(id_phone);
    }
}
