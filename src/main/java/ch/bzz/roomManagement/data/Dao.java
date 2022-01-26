package ch.bzz.roomManagement.data;

import ch.bzz.roomManagement.util.Result;

import java.util.List;

/**
 * interface for data access objects
 * <p>
 * M151 BookDB
 *
 * @author Marcel Suter
 * @version 1.0
 */
public interface Dao<T, K> {

    /**
     * gets all datasets in a table
     * @return list of model-objects
     */
    default List<T> getAll() {
        throw new UnsupportedOperationException();
    }
    /**
     * gets all datasets in a table
     * @param k condition
     * @return list of model-objects with specified condition
     */
    default List<T> getAll(K k) {
        throw new UnsupportedOperationException();
    }

    /**
     * gets a single datasets in a table
     * @param k  primary key
     * @return model-object
     */
    default T getEntity(K k) {
        throw new UnsupportedOperationException();
    }

    default T getEntity(K k, K l) {
        throw new UnsupportedOperationException();
    }

    /**
     * saves an object to the database entity
     * @param t model-object
     * @return Result-code
     */
    default Result save (T t) {
        throw new UnsupportedOperationException();
    }


    /**
     * updates an object in the database
     * @param t model-object
     * @return Result-code
     */
    default Result update(T t) { throw new UnsupportedOperationException(); }

    /**
     * delets an entity in the database
     * @param k primary key
     * @return Result-code
     */
    default Result delete (K k) {
        throw new UnsupportedOperationException();
    }

    /**
     * counts the datasets in a table
     * @return
     */
    default Integer count() {
        return 0;
    }

}
