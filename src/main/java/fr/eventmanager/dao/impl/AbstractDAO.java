package fr.eventmanager.dao.impl;

import fr.eventmanager.dao.DAO;
import fr.eventmanager.dao.EntityManagerService;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by guillaume-chs on 21/10/16.
 */
public abstract class AbstractDAO<K, E> implements DAO<K, E> {
    private final Class<E> entityClass;
    private final EntityManagerService emService;

    AbstractDAO() {
        this.entityClass = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        this.emService = EntityManagerService.getInstance();
    }

    Class<E> getEntityClass() {
        return this.entityClass;
    }

    EntityManagerService getEntityManagerService() {
        return emService;
    }

    @Override
    public void persist(E entity) {
        emService.performOperation(em -> em.persist(entity));
    }

    @Override
    public void remove(E entity) {
        emService.performOperation(em -> em.remove(entity));
    }

    @Override
    public E findById(K id) {
        return emService.performQuery(em -> em.find(getEntityClass(), id));
    }

    @Override
    public List<E> findAll() {
        return emService.performQuery(em -> {
            CriteriaQuery<E> cq = em.getCriteriaBuilder().createQuery(getEntityClass());
            Root<E> root = cq.from(getEntityClass());
            cq.select(root);

            TypedQuery<E> q = em.createQuery(cq);
            return q.getResultList();
        });
    }
}
