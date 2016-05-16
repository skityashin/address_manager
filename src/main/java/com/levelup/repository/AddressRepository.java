package com.levelup.repository;

import com.levelup.model.Address;
import java.util.List;

/**
 * Class {@link com.levelup.repository.AddressRepository}
 *
 * @author Skityashin Vladimir
 * @version 1.0
 * @since 15.05.16
 */

public interface AddressRepository {
    void createAddress (Address address);
//    void updateAddress (Address address);
    Address findById (long id_address);
    List<Address> getAllAddress ();
    void deleteById (long id_address);
    boolean isAddressExist(long id_address);
    Address getAddressByContent(String content);
}
