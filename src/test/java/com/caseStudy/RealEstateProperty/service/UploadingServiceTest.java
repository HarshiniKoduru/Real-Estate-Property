package com.caseStudy.RealEstateProperty.service;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.util.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.caseStudy.RealEstateProperty.Paging.repository.PagingRealestateRepository;
import com.caseStudy.RealEstateProperty.model.Realestate;
import com.caseStudy.RealEstateProperty.repository.RealEstateRepository;

@RunWith(MockitoJUnitRunner.class)
public class UploadingServiceTest {
	
	@Mock
	PagingRealestateRepository mockRepository;

	@Mock
	RealEstateRepository mockSaveRepository;
	
	@InjectMocks
	UploadingService uploadingService;
	
   
	private MultipartFile convertExcelToMultiPartFile() throws IOException {

		File file = new File("src/main/resources/Realestate.xlsx");
		FileInputStream input = new FileInputStream(file);
		MultipartFile multipartFile = new MockMultipartFile("file",
		      file.getName(), "text/plain", IOUtils.toByteArray(input));
		return multipartFile;		
	}
	
	@Test
	public void uploadingServiceSaveTest() throws IOException {
		MultipartFile file = convertExcelToMultiPartFile();
		Mockito.when(mockSaveRepository.saveAll(Mockito.anyList())).thenReturn(new ArrayList<>());
		uploadingService.save(file);
	}
	
	@Test
	public void uploadingServiceGetAllCustomersTest() throws IOException {
		Realestate realestate = new Realestate();
		realestate.setBorrower("TestName");
		realestate.setCost(12345);
		realestate.setFlood_risk("N");
		
		List<Realestate> data = new ArrayList<>();
		data.add(realestate);

		Page<Realestate> page = new PageImpl<>(data);
		Mockito.when(mockRepository.findAll((Pageable) Mockito.any())).thenReturn(page);
		assertNotNull(uploadingService.getCustomers(0, 10, "borrower"));
		assertTrue(uploadingService.getCustomers(0, 10, "cost").size()>0);
	}
	
	
	@Test
	public void uploadingServiceGetAllCustomersReturnsEmptyCustomersTest() throws IOException {
		List<Realestate> data = new ArrayList<>();
		Page<Realestate> page = new PageImpl<>(data);
		Mockito.when(mockRepository.findAll((Pageable) Mockito.any())).thenReturn(page);
		assertNotNull(uploadingService.getCustomers(0, 10, "borrower"));
		assertFalse(uploadingService.getCustomers(0, 10, "cost").size()>0);
	}
}
