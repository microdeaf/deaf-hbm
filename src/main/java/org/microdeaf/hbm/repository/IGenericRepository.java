package org.microdeaf.hbm.repository;

import org.microdeaf.hbm.model.BaseEntity;
import org.microdeaf.common.utility.PageResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface IGenericRepository<T extends BaseEntity, PK extends Serializable> {

    T findOne(PK id);

    T findOne(String hql, Map<String, Object> params);

    T findOneDisable(String hql, Map<String, Object> params);

    List<T> findAll();

    List<T> findAll(Sort sort);

    List<T> findAll(String hql, Map<String, Object> params);

    Iterable<T> findAll(Iterable<PK> iterable);

    PageResult<T> findAll(String searchFilter, Integer pageNumber, Integer pageSize);

    Page<T> findAll(Pageable pageable);

    Long count();

    boolean exists(PK id);

    T save(T entity);

    T saveAndFlush(T entity);

    void flush();

    T update(T entity);

    void deleteInBatch(Iterable<T> iterable);

    void deleteAllInBatch();

    void delete(PK id);

    void delete(T entity);

    void delete(Iterable<? extends T> iterable);

    void deleteAll();
}
