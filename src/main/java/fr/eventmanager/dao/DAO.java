package fr.eventmanager.dao;

import java.util.List;

/**
 * Created by guillaume-chs on 21/10/16.
 */
public interface DAO<K, E> {
    void persist(E entity);

    void remove(E entity);

    E findById(K id);

    List<E> findAll();
}
