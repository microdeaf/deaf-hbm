package org.microdeaf.common.service.impl;

import org.microdeaf.hbm.model.BaseEntity;
import org.microdeaf.hbm.repository.IGenericRepository;
import org.microdeaf.common.service.IGenericService;
import org.microdeaf.common.utility.PageResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public abstract class GenericService<T extends BaseEntity, PK extends Serializable> implements IGenericService<T, PK> {

    public abstract IGenericRepository getRepository();

    public T findOne(PK id) {
        return (T) getRepository().findOne(id);
    }

    public T findOne(String hql, Map<String, Object> params) {
        return (T) getRepository().findOne(hql, params);
    }

    public T findOneDisable(String hql, Map<String, Object> params) {
        return (T) getRepository().findOneDisable(hql, params);
    }

    public List<T> findAll() {
        return getRepository().findAll();
    }

    public List<T> findAll(Sort sort) {
        return getRepository().findAll(sort);
    }

    public List<T> findAll(String hql, Map<String, Object> params) {
        return getRepository().findAll(hql, params);
    }

    public Iterable<T> findAll(Iterable<PK> iterable) {
        return getRepository().findAll(iterable);
    }

    public PageResult<T> findAll(String searchFilter, Integer pageNumber, Integer pageSize) {
        return getRepository().findAll(searchFilter, pageNumber, pageSize);
    }

    public Page<T> findAll(Pageable pageable) {
        return getRepository().findAll(pageable);
    }

    public Long count() {
        return getRepository().count();
    }

    public boolean exists(PK id) {
        return getRepository().exists(id);
    }

    public T save(T entity) {
        return (T) getRepository().save(entity);
    }

    public List<T> save(List<T> entities) {
        List<T> result = new ArrayList<T>();
        for (T entity : entities) {
            result.add(save(entity));
        }
        return result;
    }

    public T saveAndFlush(T entity) {
        return (T) getRepository().saveAndFlush(entity);
    }

    public void flush() {
        getRepository().flush();
    }

    @Override
    public T update(T entity) {
        return (T) getRepository().update(entity);
    }

    public void deleteInBatch(Iterable<T> iterable) {
        getRepository().deleteInBatch(iterable);
    }

    public void deleteAllInBatch() {
        getRepository().deleteAllInBatch();
    }

    public void delete(PK id) {
        getRepository().delete(id);
    }

    public void delete(T entity) {
        getRepository().delete(entity);
    }

    public void delete(Iterable<? extends T> iterable) {
        getRepository().delete(iterable);
    }

    public void deleteAll() {
        getRepository().deleteAll();
    }
}