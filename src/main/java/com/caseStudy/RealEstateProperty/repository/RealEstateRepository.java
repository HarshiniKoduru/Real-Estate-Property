package com.caseStudy.RealEstateProperty.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caseStudy.RealEstateProperty.model.Realestate;
@Repository
public interface RealEstateRepository extends JpaRepository<Realestate,Long> {

}
