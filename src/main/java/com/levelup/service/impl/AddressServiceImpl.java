package com.levelup.service.impl;

import com.levelup.model.Address;
import com.levelup.repository.AddressRepository;
import com.levelup.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Class {@link com.levelup.service.impl.AddressServiceImpl}
 *
 * @author Skityashin Vladimir
 * @version 1.0
 * @since 15.05.16
 */

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public void createAddress(Address address) {
        addressRepository.createAddress(address);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Address findById(long id_address) {
        return addressRepository.findById(id_address);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Address findByContent(String content) {
        return addressRepository.findByContent(content);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Address> findByContentPartial(String content) {
        return addressRepository.findByContentPartial(content);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Address> getAllAddress() {
        return addressRepository.getAllAddress();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteById(long id_address) {
        addressRepository.deleteById(id_address);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAddressExist(long id_address) {
        return addressRepository.isAddressExist(id_address);
    }
}
