package com.levelup.repository;

import com.levelup.model.Phone;
import java.util.List;

/**
 * Class {@link com.levelup.repository.PhoneRepository}
 *
 * @author Skityashin Vladimir
 * @version 1.0
 * @since 15.05.16
 */

public interface PhoneRepository {

    /**
     * Persists or updates new entity
     *
     * @param phone
     */
    void createPhone(Phone phone);

    /**
     * Searches for {@link Phone} entity with a given id in database
     *
     * @param id_phone entity identifier
     * @return found entity, or NULL
     */
    Phone findById(long id_phone);

    /**
     * Searches for {@link Phone} entity with a given id in database
     *
     * @param number entity identifier
     * @return found entity, or NULL
     */
    Phone findByNumber(String number);

    /**
     * * Searches for {@link Phone} entity with a given id in database
     *
     * @param number entity identifier
     * @return list with every {@link Phone} entity, persisted in database
     */
    List<Phone> findByNumberPartial(String number);

    /**
     * @return list with every {@link Phone} entity, persisted in database
     */
    List<Phone> getAllPhone();

    /**
     * Removes given entity from database
     *
     * @param id_phone entity to be removed
     */
    void deleteById(long id_phone);

    /**
     * Checks whether there is an entity with a given id in the database
     *
     * @param id_phone
     * @return result(true or false)
     */
    boolean isPhoneExist(long id_phone);
}
