package com.levelup.service;

import com.levelup.model.Address;
import java.util.List;

/**
 * Class {@link com.levelup.service.AddressService}
 *
 * @author Skityashin Vladimir
 * @version 1.0
 * @since 15.05.16
 */

public interface AddressService {

    void createAddress (Address address);
    //    void updateAddress (Address address);
    Address findById (long id_address);
    Address findByContent (String content);
    List<Address> getAllAddress ();
    void deleteById (long id_address);
    boolean isAddressExist(long id_address);

}
