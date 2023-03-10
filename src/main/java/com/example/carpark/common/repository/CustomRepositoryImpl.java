package com.example.carpark.common.repository;

import com.example.carpark.common.dto.PaginationQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.util.List;

public class CustomRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements CustomRepository<T, ID> {

    private final EntityManager entityManager;

    public CustomRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }


    @Override
    public List<T> getAllAndFilter(String column, String value, Pageable pageable) {
        var cb = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(this.getDomainClass());
        Root<T> root = cq.from(this.getDomainClass());
        if (column != null && value != null) {
            Predicate predicate = cb.like(root.get(column), "%" + value + "%");
            cq.where(predicate);
        }
        TypedQuery<T> query = entityManager.createQuery(cq);
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        return query.getResultList();
    }

    @Override
    public Page<T> getAllAndFilter(PaginationQuery.Filter filter, Pageable pageable) {
        var cb = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> countQuery = cb
                .createQuery(Long.class);
        countQuery.select(cb.count(countQuery.from(this.getDomainClass())));
        Long count = entityManager.createQuery(countQuery)
                .getSingleResult();

        CriteriaQuery<T> cq = cb.createQuery(this.getDomainClass());
        Root<T> root = cq.from(this.getDomainClass());
        if (filter.getField() != null && filter.getValue() != null) {
            Predicate predicate = cb.like(root.get(filter.getField()), "%" + filter.getValue() + "%");
            cq.where(predicate);
        }
        TypedQuery<T> query = entityManager.createQuery(cq);
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        return new PageImpl<>(query.getResultList(), pageable, count);
    }

}
