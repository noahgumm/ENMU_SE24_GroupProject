package com.hotelreservationapp.models;

import java.util.List;

/**
 * Interface that defines the database functionality.
 * @param <T> The type of object that the database functionality is for
 */
public interface IDatabaseFunctionality<T> {
    /**
     * Creates a new object in the database.
     * @param object The object to create
     */
    void create(T object);

    /**
     * Updates an object in the database.
     * @param object The object to update
     */
    void update(T object);
    
    /**
     * Deletes an object from the database.
     * @param object The object to delete
     */
    void delete(T object);
    
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
