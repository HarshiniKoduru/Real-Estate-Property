package com.caseStudy.RealEstateProperty.controller;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.util.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.multipart.MultipartFile;

import com.caseStudy.RealEstateProperty.Paging.repository.PagingRealestateRepository;
import com.caseStudy.RealEstateProperty.model.Realestate;
import com.caseStudy.RealEstateProperty.repository.RealEstateRepository;
import com.caseStudy.RealEstateProperty.service.UploadingService;

@RunWith(SpringJUnit4ClassRunner.class)
public class CasestudyControllerTest {

	@Mock
	UploadingService serivce;
	@InjectMocks
	CasestudyController caseStudyController;
	
	@Mock
	PagingRealestateRepository mockRepository;

	@Mock
	RealEstateRepository mockSaveRepository;

	private MultipartFile convertExcelToMultiPartFile() throws IOException {

		File file = new File("src/main/resources/100_records.xlsx");
		FileInputStream input = new FileInputStream(file);
		MultipartFile multipartFile = new MockMultipartFile("file",
		      file.getName(), "text/plain", IOUtils.toByteArray(input));
		return multipartFile;		
	}
	
    @Test
    public void testCustomerRewards() throws Exception {
		List<Realestate> data = new ArrayList<>();
		Page<Realestate> page = new PageImpl<>(data);
		Mockito.when(mockRepository.findAll((Pageable) Mockito.any())).thenReturn(page);
		Mockito.when(mockSaveRepository.saveAll(Mockito.anyList())).thenReturn(new ArrayList<>());
    	List<Realestate> realestates = caseStudyController.uploadFile(convertExcelToMultiPartFile(), 0, 10, "cost");
    	assertNotNull(realestates);
    	List<Realestate> sortedCustomers = caseStudyController.getSortedCustomers(0, 10, "cost");
    	assertNotNull(sortedCustomers);
    	
    }
}
