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

    /**
     * Persists or updates new entity
     *
     * @param address
     */
    void createAddress(Address address);

    /**
     * Searches for {@link Address} entity with a given id in database
     *
     * @param id_address entity identifier
     * @return found entity, or NULL
     */
    Address findById(long id_address);

    /**
     * Searches for {@link Address} entity with a given id in database
     *
     * @param content entity identifier
     * @return found entity, or NULL
     */
    Address findByContent(String content);

    /**
     * Searches for {@link Address} entity with a given id in database
     *
     * @param content entity identifier
     * @return list with every {@link Address} entity, persisted in database
     */
    List<Address> findByContentPartial(String content);

    /**
     * @return list with every {@link Address} entity, persisted in database
     */
    List<Address> getAllAddress();

    /**
     * Removes given entity from database
     *
     * @param id_address entity to be removed
     */
    void deleteById(long id_address);

    /**
     * Checks whether there is an entity with a given id in the database
     *
     * @param id_address
     * @return result(true or false)
     */
    boolean isAddressExist(long id_address);
}
