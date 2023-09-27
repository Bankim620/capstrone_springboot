package com.wipro.capstrone_springboot.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="cus")
public class Customer {
	@Override
	public String toString() {
		return "Customer [cusId=" + cusId + ", cusFirstName=" + cusFirstName + ", cusMiddleName=" + cusMiddleName
				+ ", cusLastName=" + cusLastName + ", cusEmail=" + cusEmail + ", cusPhn=" + cusPhn + ", cusAddress="
				+ cusAddress + ", acc=" + acc + "]";
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cusId;
	private String cusFirstName;
	private String cusMiddleName;
	private String cusLastName;
	private String cusEmail;
	private String cusPhn;
	private String cusAddress;
	@OneToMany(mappedBy="cust",cascade=CascadeType.ALL)
	private List<Account> acc;
	
	
	
	public Customer(int cusId, String cusFirstName, String cusMiddleName, String cusLastName, String cusEmail,
			String cusPhn, String cusAddress, List<Account> acc) {
		super();
		this.cusId = cusId;
		this.cusFirstName = cusFirstName;
		this.cusMiddleName = cusMiddleName;
		this.cusLastName = cusLastName;
		this.cusEmail = cusEmail;
		this.cusPhn = cusPhn;
		this.cusAddress = cusAddress;
		this.acc = acc;
	}
	public Customer() {
		// TODO Auto-generated constructor stub
	}
	
	public int getCusId() {
		return cusId;
	}
	public void setCusId(int cusId) {
		this.cusId = cusId;
	}
	public String getCusFirstName() {
		return cusFirstName;
	}
	public void setCusFirstName(String cusFirstName) {
		this.cusFirstName = cusFirstName;
	}
	public String getCusMiddleName() {
		return cusMiddleName;
	}
	public void setCusMiddleName(String cusMiddleName) {
		this.cusMiddleName = cusMiddleName;
	}
	public String getCusLastName() {
		return cusLastName;
	}
	public void setCusLastName(String cusLastName) {
		this.cusLastName = cusLastName;
	}
	public String getCusEmail() {
		return cusEmail;
	}
	public void setCusEmail(String cusEmail) {
		this.cusEmail = cusEmail;
	}
	public String getCusPhn() {
		return cusPhn;
	}
	public void setCusPhn(String cusPhn) {
		this.cusPhn = cusPhn;
	}
	public String getCusAddress() {
		return cusAddress;
	}
	public void setCusAddress(String cusAddress) {
		this.cusAddress = cusAddress;
	}
	public List<Account> getAcc() {
		return acc;
	}
	public void setAcc(List<Account> acc) {
		this.acc = acc;
	}

	
	
}
