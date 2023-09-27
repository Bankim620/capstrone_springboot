package com.wipro.capstrone_springboot;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.wipro.capstrone_springboot.Repository.AccountRepository;
import com.wipro.capstrone_springboot.Repository.CustomerRepository;
import com.wipro.capstrone_springboot.Service.CustomerService;
import com.wipro.capstrone_springboot.model.Account;
import com.wipro.capstrone_springboot.model.Customer;


@ExtendWith(SpringExtension.class)
class CustomerServiceTest {
	
	
	@Mock
	CustomerRepository repository;
	
	@Mock
	AccountRepository acntrepository;
	
	@InjectMocks
	CustomerService service;
	
	
	int custId,accountOneId,accountTwoId;
	Customer cust,custm,custmer,updateCust,customeractual;
	List<Account> listOne,listTwo,listUpdate,listThree;
	
	List<Customer> customers,customerListByName,custListActual;
	
	String custFirstName,custLastName,custEmail;
	Optional<Customer> optionalcust;
	Optional<Account> optionalAcntOne,optionalAcntTwo;
	
	Account accountOne,accountTwo,acntOne,acntTwo;

	@BeforeEach
	void setUp() throws Exception {
		
		custFirstName = "Bankim";
		custLastName = "Singh";
		custEmail = "bankim.wipro";
		custId = 101;
		accountOneId = 1212;
		accountTwoId = 1213;
		accountOne=new Account(1212,"Saving",12100.00);
		accountTwo = new Account(1213,"Demat",12000.00);
		acntOne = accountOne;
		acntTwo = accountTwo;
		listOne = new ArrayList<Account>();
		listOne.add(accountOne);
		listOne.add(accountTwo);
		listThree = new ArrayList<Account>();
		listThree.add(accountOne);
		listThree.add(accountTwo);
		cust = new Customer(101,"bankim","Kumar","Singh","bankim.wipro","20423454","Bihar",listOne);
		custm = new Customer(101,"bankim","Kumar","Singh","bankim.wipro","20423454","Kerla",listThree); ;
		listOne.get(0).setCust(cust);
		listOne.get(1).setCust(cust);
		listThree.get(0).setCust(custm);
		listThree.get(1).setCust(custm);
		optionalcust = Optional.of(cust);
		optionalAcntTwo = Optional.of(accountTwo);
		optionalAcntOne = Optional.of(accountOne);
		listTwo = new ArrayList<Account>();
		listTwo.add(new Account(1214,"Saving",12178.00,custmer));
		listOne.add(new Account(1215,"Demat",20000.00,custmer));
		custmer = new Customer(102,"Sanu","","Kumar","Sanu.wipro","72685","Datia", listOne);
		listUpdate = new ArrayList<Account>();
		listUpdate.add(new Account(1212,"Saving",100000.00));
		listUpdate.add(new Account(1213,"Demat",120000.00));
		listUpdate.add(new Account(1216,"Current",1500000.00));
		updateCust = new Customer(101,"Ramu","Kaka","Das","Ramu.wipro","7003635862","UP",listUpdate);
		listUpdate.get(0).setCust(updateCust);
		listUpdate.get(1).setCust(updateCust);
		listUpdate.get(2).setCust(updateCust);
		customers = new ArrayList<Customer>();
		customers.add(cust);
		customers.add(custmer);
		customerListByName = new ArrayList<Customer>();
		customerListByName.add(cust);
	}

	@Test
	void testAddCustomer() {
		when(repository.save(cust)).thenReturn(custm);
		
		customeractual = service.addCustomer(cust);
		assertEquals(custm, customeractual);
	}

	@Test
	void testGetAllCustomer() {
		when(repository.findAll()).thenReturn(customers);
		
		custListActual = service.getAllCustomer();
		
		assertEquals(customers.size(), custListActual.size());
		
	}

	@Test
	void testGetCustomerById() {
		when(repository.findById(custId)).thenReturn(optionalcust);
		
		customeractual = service.getCustomerById(custId);
		
		assertEquals(cust, customeractual);
	}

	@Test
	void testUpdateCustomer() {
		when(repository.findById(custId)).thenReturn(optionalcust);
		
		when(acntrepository.findById(accountOneId)).thenReturn(optionalAcntOne);
		when(acntrepository.findById(accountTwoId)).thenReturn(optionalAcntTwo);
		when(acntrepository.save(accountOne)).thenReturn(acntOne);
		when(acntrepository.save(accountTwo)).thenReturn(acntTwo);
		when(repository.save(cust)).thenReturn(updateCust);
		System.out.println(updateCust);
		customeractual = service.updateCustomer(custId, updateCust);
		System.out.println(customeractual);
		
		assertEquals(updateCust.getCusId(), customeractual.getCusId());
	}

	@Test
	void testDeleteById() {
		when(repository.findById(custId)).thenReturn(optionalcust);
		doNothing().when(repository).delete(cust);
		
		customeractual = service.deleteById(custId);
		
		assertEquals(cust, customeractual);
	}

	@Test
	void testDeleteAll() {
		doNothing().when(repository).deleteAll();
		
		assertTrue(service.deleteAll());
	}

	@Test
	void testGetCustomerByFirstName() {
		when(repository.getCustomerBycusFirstName(custFirstName)).thenReturn(customerListByName);
		
		custListActual = service.getCustomerByFirstName("Bankim");
		
		assertEquals(customerListByName.size(), custListActual.size());
	}

	@Test
	void testGetCustomerByLastName() {
		when(repository.getCustomerBycusLastName(custLastName)).thenReturn(customerListByName);
		
		custListActual = service.getCustomerByLastName("Singh");
		
		assertEquals(customerListByName.size(), custListActual.size());
	}

	@Test
	void testUpdateCustomerByFirstName() {
		when(repository.getCustomerBycusFirstName(custFirstName)).thenReturn(customerListByName);
		
		when(acntrepository.findById(accountOneId)).thenReturn(optionalAcntOne);
		when(acntrepository.findById(accountTwoId)).thenReturn(optionalAcntTwo);
		when(acntrepository.save(accountOne)).thenReturn(acntOne);
		when(acntrepository.save(accountTwo)).thenReturn(acntTwo);
		when(repository.save(cust)).thenReturn(updateCust);
		
		customeractual = service.updateCustomerByFirstName("Bankim", updateCust);
		
		assertEquals(updateCust.getCusId(), customeractual.getCusId());
	}

	@Test
	void testGetCustomerByEmail() {
		when(repository.getCustomerBycusEmail(custEmail)).thenReturn(customerListByName);
		
		custListActual = service.getCustomerByEmail("bankim.wipro");
		
		assertEquals(customerListByName.size(), custListActual.size());
	}




	

}
