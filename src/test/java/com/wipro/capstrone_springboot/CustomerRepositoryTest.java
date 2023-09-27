package com.wipro.capstrone_springboot;

import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
//import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.wipro.capstrone_springboot.Repository.CustomerRepository;
import com.wipro.capstrone_springboot.model.Account;
import com.wipro.capstrone_springboot.model.Customer;


@ExtendWith(SpringExtension.class)
@DataJpaTest
class CustomerRepositoryTest {
	
	@Autowired
	private TestEntityManager entitymanager;
	
	
	@Autowired
	CustomerRepository repo;
	
	
	

	
	
	@Test
	public void testSave() {
		Customer custmer = getCustomer(); 
		Customer savedCust = repo.save(custmer);
		
		assertThat(savedCust).isNotNull();
	}

	@Test
	public void testFindAll() {
		Customer c = getCustomer();
		
		entitymanager.persist(c);
		List<Customer> custmers = repo.findAll();
		
		assertThat(custmers.size()).isEqualTo(1);
		
	}
	
	@Test
	public void testFindById() {
		Customer c = getCustomer();
		entitymanager.persist(c);
		
		Customer result = repo.findById(c.getCusId()).get();
		
		assertThat(result.getCusId()).isEqualTo(c.getCusId());
	}
	
	@Test
	public void testDeleteById() {
		Customer c = getCustomer();
		Customer c2 = getCustomerSecond();
		entitymanager.persist(c);
		entitymanager.persist(c2);
		
		repo.deleteById(c.getCusId());
		
		List<Customer> list = new ArrayList<Customer>();
		
		repo.findAll().forEach(e->list.add(e));
		
		assertThat(list.size()).isEqualTo(1);
	}
	
	@Test
	public void testDeleteAll() {
		Customer c = getCustomer();
		Customer c2 = getCustomerSecond();
		entitymanager.persist(c);
		entitymanager.persist(c2);
		
		repo.deleteAll();
		
		List<Customer> list = new ArrayList<Customer>();
		
		repo.findAll().forEach(e->list.add(e));
		
		assertThat(list.size()).isEqualTo(0);
	}
	
	@Test
	public void testGetCustomerByFirstName() {
		Customer c = getCustomer();
		Customer c2 = getCustomerSecond();
		entitymanager.persist(c);
		entitymanager.persist(c2);
		
		List<Customer> result = repo.getCustomerBycusFirstName(c.getCusFirstName());
		
		assertThat(result).contains(c);
	}
	
	@Test
	public void testGetCustomerByLastName() {
		Customer c = getCustomer();
		Customer c2 = getCustomerSecond();
		entitymanager.persist(c);
		entitymanager.persist(c2);
		
		List<Customer> result = repo.getCustomerBycusEmail(c.getCusFirstName());
		
		assertThat(result.size()).isEqualTo(0);
	}
	
	
	@Test
	public void testGetCustomerByEmail() {
		Customer c = getCustomer();
		Customer c2 = getCustomerSecond();
		entitymanager.persist(c);
		entitymanager.persist(c2);
		
		List<Customer> result = repo.getCustomerBycusEmail(c.getCusEmail());
		
		assertThat(result.size()).isEqualTo(1);
	}
	
	
	
	
	private Customer getCustomer() {
		Customer c = new Customer();
		c.setCusFirstName("Bankim");
		c.setCusMiddleName("");
		c.setCusLastName("Singh");
		c.setCusEmail("bankim.wipro");
		c.setCusPhn("678546729");
		
		c.setCusAddress("Patna Bihar");
		
		List<Account> list = new ArrayList<>();
		Account acnt01 = new Account("Savings",1500.00,c);
		Account acnt02 = new Account("Demat",1800.00,c);
		list.add(acnt01);
		list.add(acnt02);
		
		c.setAcc(list);
		
		return c;
		
	}


	private Customer getCustomerSecond() {
		Customer c = new Customer();
		c.setCusFirstName("Samiran");
		c.setCusMiddleName("");
		c.setCusLastName("Sen");
		c.setCusEmail("smiran.wipro");
		c.setCusPhn("721528526");
		
		c.setCusAddress("Patna BH");
		
		List<Account> list = new ArrayList<>();
		Account acnt01 = new Account("Savings",1530.00,c);
		Account acnt02 = new Account("Demat",1980.00,c);
		list.add(acnt01);
		list.add(acnt02);
		
		c.setAcc(list);
		
		return c;
	}
	
	




	

}
