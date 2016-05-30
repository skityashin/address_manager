package com.levelup.repository.impl;

import com.levelup.model.Address;
import com.levelup.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Class {@link com.levelup.repository.impl.AddressRepositoryImpl}
 *
 * @author Skityashin Vladimir
 * @version 1.0
 * @since 15.05.16
 */

@Repository
@Transactional
public class AddressRepositoryImpl implements AddressRepository {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    /**
     * {@inheritDoc}
     */
    @Override
    public void createAddress(Address address) {
        hibernateTemplate.saveOrUpdate(address);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Address findById(long id_address) {
        return hibernateTemplate.get(Address.class, id_address);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Address findByContent(String content) {
        List<Address> addresses = (List<Address>) hibernateTemplate.find("select a FROM Address a WHERE a.content = ?", content);
        if (!CollectionUtils.isEmpty(addresses)) {
            return addresses.get(0);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Address> findByContentPartial(String content) {
        String query = "from Address a where a.content like :content";
        List<Address> addresses = (List<Address>) hibernateTemplate.findByNamedParam(query, "content", "%" + content + "%");
        return addresses;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Address> getAllAddress() {
        return (List<Address>) hibernateTemplate.find("select * FROM Address");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteById(long id_address) {
        hibernateTemplate.delete(findById(id_address));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAddressExist(long id_address) {
        return hibernateTemplate.contains(id_address);
    }
}
