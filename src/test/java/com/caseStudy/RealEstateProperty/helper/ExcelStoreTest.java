package com.caseStudy.RealEstateProperty.helper;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.util.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.caseStudy.RealEstateProperty.model.Realestate;

@RunWith(MockitoJUnitRunner.class)
public class ExcelStoreTest {
	
	private MultipartFile convertExcelToMultiPartFile(String fileName) throws IOException {

		File file = new File(fileName);
		FileInputStream input = new FileInputStream(file);
		MultipartFile multipartFile = new MockMultipartFile("file",
		      file.getName(), "text/plain", IOUtils.toByteArray(input));
		return multipartFile;		
	}
	
	@Test
	public void isTypeExcelTest() {
		assertTrue(ExcelStore.isTypeExcel("RealEstate.xlsx"));
		assertFalse(ExcelStore.isTypeExcel("RealEstate.txt"));
		assertFalse(ExcelStore.isTypeExcel("RealEstate.pdf"));
	}
	
	@Test
	public void parseExcelFileToList() throws IOException {
		MultipartFile file = convertExcelToMultiPartFile("src/main/resources/Realestate.xlsx");
	    List<Realestate> realestate_data = ExcelStore.parseExcelFile(file.getInputStream(),file.getResource().getFilename());
	    assertNotNull(realestate_data);
	    assertTrue(realestate_data.size()>0);
	}
	
	@Test
	public void parseExcelFileToList100Records() throws IOException {
		MultipartFile file = convertExcelToMultiPartFile("src/main/resources/100_records.xlsx");
	    List<Realestate> realestate_data = ExcelStore.parseExcelFile(file.getInputStream(),file.getResource().getFilename());
	    assertNotNull(realestate_data);
	    assertTrue(realestate_data.size()>0);
	}
	
	
}
