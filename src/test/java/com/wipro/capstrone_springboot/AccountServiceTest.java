package com.wipro.capstrone_springboot;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
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
import com.wipro.capstrone_springboot.Service.AccountService;
import com.wipro.capstrone_springboot.model.Account;
import com.wipro.capstrone_springboot.model.Customer;
@ExtendWith(SpringExtension.class)
class AccountServiceTest {
	
	
	@Mock
	AccountRepository repositroy;
	
	
	@Mock
	CustomerRepository crepo;
	@InjectMocks
	AccountService service;
	
	int acntNo,from_acctNo,to_acctNo,custId;
	Account acc_01,acc_02,from_accnt,to_accnt,actualaccnt;
	Optional<Account> from_accnt_opt, to_accnt_opt;
	Optional<Customer> custopt;
	Customer cust,custm;
	List<Account> acnts,actualListacnt,acntsm;
	
	@BeforeEach
	void setUp() throws Exception {
		custId = 101;
		acntNo = 1212;
		from_acctNo = acntNo;
		to_acctNo = 1213;
		acc_01 = new Account(1212,"Saving",150000.00);
		acc_02 = new Account(1213,"Demat",180000.00);
		
		acnts = new ArrayList<Account>();
		acnts.add(acc_01);
		acnts.add(acc_02);
		acntsm = new ArrayList<Account>();
		acntsm.add(acc_01);
		acntsm.add(acc_02);
		cust = new Customer(101,"Bankim","Kumar","Singh","bankim.wipro","7003323732","Patna BH",acnts);
		acnts.get(0).setCust(cust);
		acnts.get(1).setCust(cust);
		custopt = Optional.of(cust);
		custm = new Customer(101,"Bankim","Kumar","Singh","bankim.wipro","7003323732","Patna BH",acntsm);
		acntsm.get(0).setCust(custm);
		acntsm.get(1).setCust(custm);
		from_accnt = acc_01;
		to_accnt = acc_02;
		from_accnt_opt = Optional.of(from_accnt);
		to_accnt_opt = Optional.of(to_accnt);
		crepo.save(cust);
		
		
		
		
		
		
	}
	
	
	@Test
	void testAddAccount() {
		
		when(crepo.findById(custId)).thenReturn(custopt);
		when(repositroy.save(from_accnt)).thenReturn(acc_01);
		
		
		
		Account acc = service.addAccount(101, acc_01);
		
		assertThat(acc).isNotNull();
		assertThat(acc.getAccNo()).isEqualTo(1212);
	}

	@Test
	void testGetAllAccounts() {
		when(repositroy.findAll()).thenReturn(acnts);
		
		actualListacnt = service.getAllAccounts();
		
		assertEquals(acnts.size(),actualListacnt.size());
	}

	@Test
	void testTransferFunds() {
		when(repositroy.findById(from_acctNo)).thenReturn(from_accnt_opt);
		when(repositroy.findById(to_acctNo)).thenReturn(to_accnt_opt);
		when(repositroy.save(from_accnt)).thenReturn(acc_01);
		when(repositroy.save(to_accnt)).thenReturn(acc_02);
		
		String result = service.transferFunds(1212, 1213, 500.00);
		
		assertEquals("success",result);
	}

	@Test
	void testGetBalanceof() {
		when(repositroy.findById(from_acctNo)).thenReturn(from_accnt_opt);
		
		actualaccnt = service.getBalanceOf(1212);
		
		assertEquals(acc_01, actualaccnt);
	}




	

}
