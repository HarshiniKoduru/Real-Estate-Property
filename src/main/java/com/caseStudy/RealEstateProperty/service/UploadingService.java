package com.caseStudy.RealEstateProperty.service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.caseStudy.RealEstateProperty.Paging.repository.PagingRealestateRepository;
import com.caseStudy.RealEstateProperty.helper.ExcelStore;
import com.caseStudy.RealEstateProperty.model.Realestate;
import com.caseStudy.RealEstateProperty.repository.RealEstateRepository;
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UploadingService {

	@Autowired
	private RealEstateRepository repository;
	@Autowired
	private PagingRealestateRepository repository_paging;
	
	public void save(MultipartFile file) {
		if(!FileUtils.EMPTY_FILE_ARRAY.equals(file))
		{
	    try {
	    	if(ExcelStore.isTypeExcel(file.getResource().getFilename())) {
	      List<Realestate> realestate_data = ExcelStore.parseExcelFile(file.getInputStream(),file.getResource().getFilename());
	      repository.saveAll(realestate_data);
	    	}
	    } catch (IOException e) {
	      throw new RuntimeException("Please Upload Excel data: " + e.getMessage());
	    }	
		}
		else {
		      throw new RuntimeException("File is empty");
		}
	  }
	 public List<Realestate> getCustomers(Integer pageNo, Integer pageSize, String sortBy)
	    {
	        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
	 
	        Page<Realestate> pagedResult = repository_paging.findAll(paging);
	         
	        if(pagedResult.hasContent()) {
	            return pagedResult.toList();
	        } 
	        else {
	            return new ArrayList<Realestate>();
	        }
	    }
	}
	

