package com.caseStudy.RealEstateProperty.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name ="Real_Estate_Data")
public class Realestate {
	@Id
	@Column(name ="Loan number",unique=true, nullable=false)
	private long loan_number;
	
	@Column(name="Borrower Name")
	private String borrower;
	
	@Column(name="Date of birth")
	private String dob;
	  
	@Column(name="Property Address")
	private String property_address;
	
	@Column(name="Cost")
	private double cost;
	
	@Column(name="Flood Risk")
	private String flood_risk;
	
	public Realestate() {
		
	}
	public Realestate(long loan_number, String borrower, String dob, String property_address, double cost,
			String flood_risk) {
		super();
		this.loan_number = loan_number;
		this.borrower = borrower;
		this.dob = dob;
		this.property_address = property_address;
		this.cost = cost;
		this.flood_risk = flood_risk;
	}

	
	public String getFlood_risk() {
		return flood_risk;
	}
	public void setFlood_risk(String flood_risk) {
		this.flood_risk = flood_risk;
	}
	
	public long getLoan_number() {
		return loan_number;
	}
	public void setLoan_number(long loan_number) {
		this.loan_number = loan_number;
	}
	public String getBorrower() {
		return borrower;
	}
	public void setBorrower(String borrower_name) {
		this.borrower = borrower_name;
	}
	public String getDate_of_birth() {
		return dob;
	}
	public void setDate_of_birth(String dob) {
		this.dob = dob;
	}
	public String getProperty_address() {
		return property_address;
	}
	public void setProperty_address(String property_address) {
		this.property_address = property_address;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}

}
