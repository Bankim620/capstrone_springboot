package com.wipro.capstrone_springboot;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.wipro.capstrone_springboot.Repository.AccountRepository;
import com.wipro.capstrone_springboot.Repository.CustomerRepository;
import com.wipro.capstrone_springboot.model.Account;
import com.wipro.capstrone_springboot.model.Customer;


@ExtendWith(SpringExtension.class)
@DataJpaTest
class AccountRepositoryTest {
	
	@Autowired
	TestEntityManager entitymanager;
	
	@Autowired
	AccountRepository accountrepo;
	
	@Autowired
	CustomerRepository repo;
	
	

	

	@Test
	public void testSave() {
		
		Account ac = getAccountOne();
		Customer cust = getCustomer();
		repo.save(cust);
		Account c = accountrepo.save(ac);
		
		assertThat(c).isNotNull();
		
	}
	
	
	


	@Test
	public void testFindAll() {
		Account acc_01 = getAccountOne();
		

		
		entitymanager.persist(acc_01);
		
		//accountrepo.save(acc_01);

		
		List<Account> accounts = accountrepo.findAll();
		
		assertThat(accounts.size()).isEqualTo(1);
		
	}
	
	@Test
	public void testFindById() {
		
		Account acc_01 = getAccountOne();
		Account acc_02 = getAccountTwo();
		
		entitymanager.persist(acc_01);
		entitymanager.persist(acc_02);
		
		Account account = accountrepo.findById(acc_01.getAccNo()).get();
		
		assertEquals(account.getAccNo(),acc_01.getAccNo());
		
		
	}
	
	
	@Test
	public void testDeleteById() {
		
		Account acc_01 = getAccountOne();
		Account acc_02 = getAccountTwo();
		
		entitymanager.persist(acc_01);
		entitymanager.persist(acc_02);
		
		accountrepo.deleteById(acc_01.getAccNo());
		
		List<Account> result = new ArrayList<Account>();
		
		accountrepo.findAll().forEach(a->result.add(a));
		
		assertThat(result.size()).isEqualTo(1);
		
	}
	
	@Test
	public void testDeleteAll() {
		Account acc_01 = getAccountOne();
		Account acc_02 = getAccountTwo();
		
		entitymanager.persist(acc_01);
		entitymanager.persist(acc_02);
		
		accountrepo.deleteAll();
		
        List<Account> result = new ArrayList<Account>();
		
		accountrepo.findAll().forEach(a->result.add(a));
		
		assertThat(result.size()).isEqualTo(0);
	}
	
	private Account getAccountOne() {
		Account acnt = new Account();
		acnt.setAccType("Savings");
		acnt.setAccBal(1500.50);
		acnt.setCust(getCustomer());
		return acnt;
	}
	
	private Account getAccountTwo() {
		Account acnt = new Account();
		acnt.setAccType("Demat");
		acnt.setAccBal(1780.50);
		acnt.setCust(getCustomer());
		return acnt;
	}
	
	
	private Customer getCustomer() {
		Customer c =  new Customer();
		repo.save(c);
		return c;
	}
	
	

	

}
