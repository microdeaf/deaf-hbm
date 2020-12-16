package org.microdeaf.hbm.repository.impl.hibernate;

import lombok.extern.slf4j.Slf4j;
import org.microdeaf.common.enums.ActiveType;
import org.microdeaf.common.utility.PageResult;
import org.microdeaf.hbm.model.BaseEntity;
import org.microdeaf.hbm.repository.IGenericRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Slf4j
public abstract class GenericRepository<T extends BaseEntity, PK extends Serializable> implements IGenericRepository<T, PK> {

    @PersistenceContext
    private EntityManager entityManager;

    public abstract Class<T> clazz();

    public EntityManager getSession() {
        return entityManager;
    }

    public T findOne(PK id) {
        T entity = entityManager.find(clazz(), id);
        return (entity != null && entity.getIsEnabled() == ActiveType.ENABLED.getValue()) ? entity : null;
    }

    public T findOne(String hql, Map<String, Object> params) {
        hql += " and e.isEnabled = " + ActiveType.ENABLED.getStringValue();
        Query query = entityManager.createQuery("from " + clazz().getName() + " e " + hql);
        params.entrySet().forEach(p -> query.setParameter(p.getKey(), p.getValue()));
        try {
            return (T) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public T findOneDisable(String hql, Map<String, Object> params) {
        hql += " and e.isEnabled = " + ActiveType.DISABLE.getStringValue();
        Query query = entityManager.createQuery("from " + clazz().getName() + " e " + hql);
        params.entrySet().forEach(p -> query.setParameter(p.getKey(), p.getValue()));
        try {
            return (T) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<T> findAll() {
        String hql = "from " + clazz().getName() + " e where e.isEnabled = " + ActiveType.ENABLED.getStringValue();
        return (List<T>) entityManager.createQuery(hql).getResultList();
    }

    public List<T> findAll(String hql, Map<String, Object> params) {
        hql += " and isEnabled = " + ActiveType.ENABLED.getStringValue();
        Query query = entityManager.createQuery("from " + clazz().getName() + " e " + hql);
        params.entrySet().forEach(p -> query.setParameter(p.getKey(), p.getValue()));
        return query.getResultList();
    }

    public Page<T> findAll(Pageable pageable) {
        return null;
    }

    public List<T> findAll(Iterable<PK> iterable) {
        return null;
    }

    public List<T> findAll(Sort sort) {
        return null;
    }

    public PageResult<T> findAll(String searchFilter, Integer pageNumber, Integer pageSize) {
        PageResult<T> pageResult = new PageResult<T>();
        try {
            Query query = entityManager.createQuery("from " + clazz().getName() + " e where e.isEnabled = " +
                    ActiveType.ENABLED.getStringValue() + (searchFilter != null && searchFilter.length() > 0 ?
                    " and " + searchFilter : ""));

            if (pageSize != null && pageSize > 0) {
                query.setFirstResult(pageNumber * pageSize);
                query.setMaxResults(pageSize);
            }
            List result = query.getResultList();
            pageResult.setResult(result);
            pageResult.setPageNumber(pageNumber);
            pageResult.setPageSize(pageSize);
            pageResult.setTotal(result.size());
            return pageResult;
        } catch (Exception e) {
            e.printStackTrace();
            return pageResult;
        }
    }

    public Long count() {
        Query countQuery = entityManager.createQuery("Select count (e.id) from " + clazz().getName() + " e");
        return (Long) countQuery.getSingleResult();
    }

    public boolean exists(PK id) {
        return findOne(id) != null;
    }

    public T save(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    public List<T> save(Iterable<T> iterable) {
        return null;
    }

    public T saveAndFlush(T entity) {
        save(entity);
        flush();
        return entity;
    }

    public void flush() {
        entityManager.flush();
    }

    @Override
    public T update(T entity) {
        if (entity.getId() != null) {
            return save(entity);
        }
        return null;
    }

    public void delete(PK id) {
        T entity = entityManager.find(clazz(), id);
        entity.setIsEnabled(ActiveType.DISABLE.getValue());
        entityManager.persist(entity);
    }

    public void delete(T entity) {
        entity.setIsEnabled(ActiveType.DISABLE.getValue());
        entityManager.persist(entity);
    }

    public void delete(Iterable<? extends T> iterable) {
        iterable.forEach(entity -> {
            entity.setIsEnabled(ActiveType.DISABLE.getValue());
            entityManager.persist(entity);
        });
    }

    public void deleteAll() {
        List<T> entities = findAll();
        for (T entity : entities) {
            entity.setIsEnabled(ActiveType.DISABLE.getValue());
            entityManager.persist(entity);
        }
    }

    public void deleteInBatch(Iterable<T> iterable) {

    }

    public void deleteAllInBatch() {

    }

}
