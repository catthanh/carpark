package com.example.carpark.common.repository;

import com.example.carpark.common.dto.PaginationQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface CustomRepository<T, ID> extends JpaRepositoryImplementation<T, ID> {
    List<T> getAllAndFilter(String column, String value, Pageable pageable);
    Page<T> getAllAndFilter(PaginationQuery.Filter filter, Pageable pageable);
}
