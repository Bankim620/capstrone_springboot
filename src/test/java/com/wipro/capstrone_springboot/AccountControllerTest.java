package com.wipro.capstrone_springboot;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wipro.capstrone_springboot.Controller.AccountController;
import com.wipro.capstrone_springboot.Repository.AccountRepository;
import com.wipro.capstrone_springboot.Repository.CustomerRepository;
import com.wipro.capstrone_springboot.Service.AccountService;
import com.wipro.capstrone_springboot.Service.CustomerService;
import com.wipro.capstrone_springboot.model.Account;
import com.wipro.capstrone_springboot.model.Customer;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AccountController.class)
class AccountControllerTest {
	
	@MockBean
	AccountService service;
	
	@MockBean
	CustomerService cservice;
	
	@MockBean
	CustomerRepository crepo;
	
	@MockBean
	AccountRepository repo;
	
	
	
	
	@InjectMocks
	AccountController controller;
	
	
	@Autowired
	MockMvc mockmvc;
	
	
	int custId,accountId,fromacnt,toacnt;
	Customer cust;
	List<Account> listacnt;
	List<Customer> listcust;
	Account newacnt,fromaccount,acc_01;
	double amount;
	Optional<Customer> custopt;
	
	@BeforeEach
	void setUp() throws Exception {
		custId = 101;
		accountId = 1214;
		fromacnt = 1212;
		toacnt = 1213;
		fromaccount = new Account(1212,"Saving",12100.00);
		acc_01 = new Account(1213,"Demat",15000.00);
		amount = 500.00;
		listacnt = new ArrayList<Account>();
		listacnt.add(fromaccount);
		listacnt.add(acc_01);
		cust = new Customer(101,"Abhijit","","Mahata","abhijit.wipro","7003632685","Patna BH",listacnt);
		System.out.println(cust);
		listacnt.get(0).setCust(cust);
		listacnt.get(1).setCust(cust);
		custopt = Optional.of(cust);
		listcust = new ArrayList<Customer>();
		newacnt = new Account(1214,"Loan",500000.00,cust);
		listacnt.add(newacnt);
		listcust.add(cust);
		when(crepo.save(cust)).thenReturn(cust);
		
		
		
	}

	

	@Test
	void testGetallAccount() throws Exception{
		when(service.getAllAccounts()).thenReturn(listacnt);
		
		mockmvc.perform(MockMvcRequestBuilders.get("/customer/account/getAllAccount")
			   .accept(MediaType.APPLICATION_JSON))
		       .andExpect(MockMvcResultMatchers.status().is(200));
	}

	@Test
	void testFundTransfer()throws Exception {
		when(service.transferFunds(fromacnt,toacnt,amount)).thenReturn("success");
		
		MvcResult result = mockmvc.perform(MockMvcRequestBuilders.put("/customer/account/fundtransfer/1212/1213/500.00")
				                  .accept(MediaType.APPLICATION_JSON))
				                  .andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		String data = response.getContentAsString();
		System.out.println(data);
		assertEquals(200, response.getStatus());
		assertEquals("success", data);
	}

	@Test
	void testGetByAcntno() throws Exception{
		when(service.getBalanceOf(fromacnt)).thenReturn(fromaccount);
		
		mockmvc.perform(MockMvcRequestBuilders.get("/customer/account/getbyacntno/1212")
			   .accept(MediaType.APPLICATION_JSON))
		       .andExpect(MockMvcResultMatchers.status().is(200));
	}
	
	public String toJson(Account ac) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(ac);
	}




	

}
