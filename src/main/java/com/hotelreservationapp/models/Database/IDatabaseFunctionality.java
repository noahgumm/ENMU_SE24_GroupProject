package com.hotelreservationapp.models.Database;

import java.util.List;

/**
 * Interface that defines the database functionality.
 */
public interface IDatabaseFunctionality<T> {
    /**
     * Creates a new object in the database.
     * @param object The object to create
     * @return True if the object was created successfully, false otherwise
     */
    boolean create(T object);

    /**
     * Updates an object in the database.
     * @param object The object to update
     * @return True if the object was updated successfully, false otherwise
     */
    boolean update(T object);
    
    /**
     * Deletes an object from the database.
     * @param object The object to delete
     * @return True if the object was deleted successfully, false otherwise
     */
    boolean delete(T object);
    
    /**
     * Reads an object from the database based on the provided ID.
     * @param id
     * @return
     */
    T read(int id);

    /**
     * Reads all objects from the database.
     * @return
     */
    List<T> readAll();
}
