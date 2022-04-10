package dao;

import exception.*;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

/**
 * Public interface for managing lifecycle of entity objects
 */
public interface Repository<K, V extends dao.Identifiable<K>> {

    interface IdGenerator<K> {
        Integer getNextId();
        K getCurrentId();

        void reset(K readObject);
    }

    class LongIdGenerator implements IdGenerator<Long>{
        private int lastId = 0;
        @Override
        public Integer getNextId() {
            return ++ lastId;
        }

        @Override
        public Long getCurrentId() {
            return null;
        }

        @Override
        public void reset(Long readObject) {

        }
    }

    /**
     * Find all users in repository
     * @return array of all users
     */
    Collection<V> findAll();

    List<V> findAllSorted(Comparator<V> comparator);

    /**
     * Find user by id
     * @return the user with given id, or null if id not found in repository
     */
    V create(V entity);
    void addAll(Collection<V> entities);
    void clear();
    V update(V entity) throws NonexistingEntityException;
    long count();
    void deleteById(Long id) throws NonexistingEntityException;
    V findById(Long id) throws NonexistingEntityException;
}
