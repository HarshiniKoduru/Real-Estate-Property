package com.caseStudy.RealEstateProperty.Paging.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.caseStudy.RealEstateProperty.model.Realestate;
@Repository
public interface PagingRealestateRepository extends PagingAndSortingRepository<Realestate,Long> {

}
