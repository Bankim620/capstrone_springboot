package com.wipro.capstrone_springboot;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wipro.capstrone_springboot.Controller.CustomerController;
import com.wipro.capstrone_springboot.Service.CustomerService;
import com.wipro.capstrone_springboot.model.Account;
import com.wipro.capstrone_springboot.model.Customer;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CustomerController.class)
class CustomerControllerTest {
	@MockBean
	CustomerService service;
	
	@InjectMocks
	CustomerController controller;
	
	@Autowired
	MockMvc mockmvc;
	
	int custId;
	Customer cust,custm,custmer,updateCust;
	List<Account> listOne,listTwo,listUpdate;
	
	List<Customer> customers,customerListByName;
	
	String custFirstName,custLastName,custEmail;
	
	Account acc_01,acc_02,acc_01_custm,acc_02_custm;

	@BeforeEach
	void setUp() throws Exception {
		custFirstName = "Bankim";
		custLastName = "Singh";
		custEmail = "bankim.wipro";
		acc_01 = new Account(1212,"Saving",12100.00);
		acc_02 = new Account(1213,"Demat",12000.00);
		custId = 101;
		listOne = new ArrayList<Account>();
		listOne.add(acc_01);
		listOne.add(acc_02);
		cust = new Customer(101,"Bankim","Kumar","Singh","bankim.wipro","629556576","Patna BH",listOne);
		/*
		 * acc_01.setCustomer(cust); acc_02.setCustomer(cust);
		 */
		custm = cust;
		acc_01_custm = acc_01;
		acc_02_custm = acc_02;
		listTwo = new ArrayList<Account>();
		listTwo.add(new Account(1214,"Saving",12178.00,custmer));
		listOne.add(new Account(1215,"Demat",20000.00,custmer));
		custmer = new Customer(102,"Pankaj","","Kumar","pankaj.wipro","7003632685","Darbhanga", listOne);
		listUpdate = new ArrayList<Account>();
		listUpdate.add(new Account(1212,"Saving",100000.00,updateCust));
		listUpdate.add(new Account(1213,"Demat",120000.00,updateCust));
		listUpdate.add(new Account(1216,"Current",1500000.00,updateCust));
		updateCust = new Customer(101,"Shyam","Chandra","Das","shyam.wipro","7003635862","Kochi",listUpdate);
		customers = new ArrayList<Customer>();
		customers.add(cust);
		customers.add(custmer);
		customerListByName = new ArrayList<Customer>();
		customerListByName.add(cust);
		
		
	}

	@Test
	void testAdd() throws Exception{
		
		when(service.addCustomer(cust)).thenReturn(custm);
		
		mockmvc.perform(MockMvcRequestBuilders.post("/customer/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(toJson(cust))
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(201))
				.andExpect(MockMvcResultMatchers.jsonPath("$.cusId").value(101))
				.andExpect(MockMvcResultMatchers.jsonPath("$.acc[0].accNo").value(1212));
		
		
	}

	@Test
	void testGetAll() throws Exception{
		when(service.getAllCustomer()).thenReturn(customers);
		
		mockmvc.perform(MockMvcRequestBuilders.get("/customer/getAll")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(200))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0].cusId").value(101))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[1].cusId").value(102));
	}

	@Test
	void testGetbyId() throws Exception{
		when(service.getCustomerById(custId)).thenReturn(cust);
		
		mockmvc.perform(MockMvcRequestBuilders.get("/customer/get/101")
				.accept(MediaType.APPLICATION_JSON))
		        .andExpect(MockMvcResultMatchers.status().is(200))
		        .andExpect(MockMvcResultMatchers.jsonPath("$.cusFirstName").value("Bankim"));
	}

	@Test
	void testUpdate() throws Exception{
		
		when(service.getCustomerById(custId)).thenReturn(cust);
		when(service.updateCustomer(custId, updateCust)).thenReturn(updateCust);
		
		mockmvc.perform(MockMvcRequestBuilders.put("/customer/update/101")
				.contentType(MediaType.APPLICATION_JSON)
				.content(toJson(updateCust))
				.accept(MediaType.APPLICATION_JSON))
		        .andExpect(MockMvcResultMatchers.status().is(200))
		        .andExpect(MockMvcResultMatchers.jsonPath("$.cusId").value(101))
		        .andExpect(MockMvcResultMatchers.jsonPath("$.cusFirstName").value("Shyam"))
		        .andExpect(MockMvcResultMatchers.jsonPath("$.acc[2].accNo").value(1216));
	}
	
	

	
	public String toJson(Customer c) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(c);

	}

}