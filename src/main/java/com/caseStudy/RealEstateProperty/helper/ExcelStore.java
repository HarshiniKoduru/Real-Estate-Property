package com.caseStudy.RealEstateProperty.helper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.caseStudy.RealEstateProperty.model.Realestate;
import org.apache.commons.io.FilenameUtils;
public class ExcelStore {
	
	public static boolean isTypeExcel(String name) {
		 String excel = "xlsx";
		 if(!FilenameUtils.isExtension(name,excel)) {
			 return false;
		 }
		 return true;
	}

	  public static List<Realestate> parseExcelFile(InputStream is, String name) {
			try {
				Workbook workbook = new XSSFWorkbook(is);

				Sheet sheet = workbook.getSheetAt(0);  
			List<Realestate> lstCustomers = new ArrayList<Realestate>();

				int rowNumber = 0;
				
				for(Row currentRow: sheet) {

					// skip header
					if (rowNumber == 0) {
						rowNumber++;
						continue;
					}
					Realestate realestate = new Realestate();

					int cellIndex = 0;
					for (int colNum = 0; colNum < currentRow.getLastCellNum(); colNum++) {
						  Cell currentCell = currentRow.getCell(colNum);

						if (cellIndex == 0) {

						    if(null != currentCell && null != Double.valueOf(currentCell.getNumericCellValue()).toString()){
						    	realestate.setLoan_number((long)currentCell.getNumericCellValue());
						    }
						    else {
								 realestate.setLoan_number(0);
						    }
						}
							 
						 else if (cellIndex == 1) {
							 if(null != currentCell && null != currentCell.getStringCellValue()){
								    	realestate.setBorrower(currentCell.getStringCellValue());
								    }
								    else {
										 realestate.setBorrower("");
								    }  
							 } 
						 else if (cellIndex == 2) { 
							
							 if(null != currentCell && null != currentCell.getStringCellValue()){
								 realestate.setDate_of_birth(currentCell.getStringCellValue());
							    }
							    else {
									 realestate.setDate_of_birth("");
							    }  
					       
						} else if (cellIndex == 3) { 
							 if(null != currentCell && null != currentCell.getStringCellValue()){
								 realestate.setProperty_address(currentCell.getStringCellValue());
							    }
							    else {
									 realestate.setProperty_address("");
							    }  
							
						}
						else if (cellIndex == 4) {
						    if(null != currentCell && null!=Double.valueOf(currentCell.getNumericCellValue()).toString() ){
						    	
							 realestate.setCost((double)currentCell.getNumericCellValue());
						    }
						    else {
								 realestate.setCost(0.0);
						    }
						}
						else if (cellIndex == 5) {
							 if(null != currentCell && null != currentCell.getStringCellValue()){
								realestate.setFlood_risk(currentCell.getStringCellValue());
							}
							    }
							    else {
									 realestate.setFlood_risk("");
							    }  
							 

						cellIndex++;
					}
				

					lstCustomers.add(realestate);
				}

				// Close WorkBook
				workbook.close();

				return lstCustomers;
			} catch (IOException e) {
				throw new RuntimeException("FAIL! -> message = " + e.getMessage());
			}
		}
	}

