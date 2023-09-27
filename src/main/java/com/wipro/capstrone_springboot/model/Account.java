package com.wipro.capstrone_springboot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

//import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="cus_acc")
public class Account {
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int accNo;
	private String accType;
	private double accBal;
	@ManyToOne
	@JoinColumn(name="cus_ID")
	@JsonIgnore
	private Customer cust;
	public Account(int i, String string, double d) {
		this.accNo = i;
		this.accType = string;
		this.accBal  = d;
		// TODO Auto-generated constructor stub
	}
	public Account(int i, String string, double d, Customer cust2) {
		// TODO Auto-generated constructor stub
		this.accNo = i;
		this.accType = string;
		this.accBal = d;
		this.cust = cust2;
	}
	
	public Account() {
		//super();
	}
	
	public Account(String accType, double accBal, Customer cust) {
		super();
		this.accType = accType;
		this.accBal = accBal;
		this.cust = cust;
	}
	public int getAccNo() {
		return accNo;
	}
	public void setAccNo(int accNo) {
		this.accNo = accNo;
	}
	public String getAccType() {
		return accType;
	}
	public void setAccType(String accType) {
		this.accType = accType;
	}
	public double getAccBal() {
		return accBal;
	}
	public void setAccBal(double accBal) {
		this.accBal = accBal;
	}
	public Customer getCust() {
		return cust;
	}
	public void setCust(Customer cust) {
		this.cust = cust;
	}
	public static void serAcnType(String accType2) {
		// TODO Auto-generated method stub
		
	}
	

}
