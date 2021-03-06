package com.levelup.repository.impl;

import com.levelup.model.Phone;
import com.levelup.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

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

    /**
     * {@inheritDoc}
     */
    @Override
    public void createPhone(Phone phone) {
        if (phone == null) {
            throw new IllegalArgumentException("Phone cannot be null");
        }
        hibernateTemplate.saveOrUpdate(phone);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Phone findById(long id_phone) {
        return hibernateTemplate.load(Phone.class, id_phone);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Phone findByNumber(String number) {
        List<Phone> phones = (List<Phone>) hibernateTemplate.find("select p FROM Phone p WHERE p.number = ?", number);
        if (!CollectionUtils.isEmpty(phones)) {
            return phones.get(0);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Phone> findByNumberPartial(String number) {
        String query = "from Phone p where p.number like :number";
        List<Phone> phones = (List<Phone>) hibernateTemplate.findByNamedParam(query, "number", "%" + number + "%");
        for (Phone p : phones) {
            hibernateTemplate.initialize(p.getAddress());
        }
        return phones;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Phone> getAllPhone() {
        return (List<Phone>) hibernateTemplate.find("FROM Phone");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteById(long id_phone) {
        hibernateTemplate.delete(findById(id_phone));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPhoneExist(long id_phone) {
        return hibernateTemplate.contains(id_phone);
    }
}
