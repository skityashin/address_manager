package com.levelup.repository.impl;

import com.levelup.model.Address;
import com.levelup.repository.AddressRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
public class AddressRepositoryImpl implements AddressRepository{

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public void createAddress(Address address) {
//        if(address == null) {
//            throw new IllegalArgumentException("Address cannot be null");
//        }
        hibernateTemplate.saveOrUpdate(address);
    }


    @Override
    public Address findById(long id_address) {
        return hibernateTemplate.get(Address.class, id_address);
    }


    @Override
    public Address findByContent(String content) {
        List<Address> addresses  = (List<Address>) hibernateTemplate.find("select a FROM Address a WHERE a.content = ?", content);
        if (!CollectionUtils.isEmpty(addresses)) {
            return addresses.get(0);
        }
        return null;
    }

    @Override
    public List<Address> findByContentPartial(String content) {
        String query = "from Address a where a.content like :content";
        List<Address> addresses = (List<Address>) hibernateTemplate.findByNamedParam(query, "content", "%" + content + "%");
        return addresses;
    }

    @Override
    public List<Address> getAllAddress() {
        return (List<Address>) hibernateTemplate.find("select * FROM Address");
    }


    @Override
    public void deleteById(long id_address) {
        hibernateTemplate.delete(findById(id_address));
    }


    @Override
    public boolean isAddressExist(long id_address) {
        return hibernateTemplate.contains(id_address);
    }

//    @Override
//    public Address getAddressByContent(String content) {
//        ArrayList<Address> list = new ArrayList<Address>((Collection<? extends Address>) Arrays.asList(
//                hibernateTemplate.find("FROM Address a WHERE a.content = ?", content)));
//        return list.get(0);
//    }
}
