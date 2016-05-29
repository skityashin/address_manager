package com.levelup.service.impl;

import com.levelup.model.Address;
import com.levelup.repository.AddressRepository;
import com.levelup.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Class {@link com.levelup.service.impl.AddressServiceImpl}
 *
 * @author Skityashin Vladimir
 * @version 1.0
 * @since 15.05.16
 */

@Service
//@Transactional
public class AddressServiceImpl implements AddressService{

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public void createAddress(Address address) {
//        if(address == null) {
//            throw new IllegalArgumentException("address cannot be null");
//        }
        addressRepository.createAddress(address);
    }

    @Override
    public Address findById(long id_address) {
        return addressRepository.findById(id_address);
    }

    @Override
    public Address findByContent(String content) {
        return addressRepository.findByContent(content);
    }

    @Override
    public List<Address> findByContentPartial(String content) {
        return addressRepository.findByContentPartial(content);
    }

    @Override
    public List<Address> getAllAddress() {
        return addressRepository.getAllAddress();
    }

    @Override
    public void deleteById(long id_address) {
        addressRepository.deleteById(id_address);
    }

    @Override
    public boolean isAddressExist(long id_address) {
        return addressRepository.isAddressExist(id_address);
    }
}
