package com.wipro.capstrone_springboot;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.wipro.capstrone_springboot.model.Account;
import com.wipro.capstrone_springboot.model.Customer;



@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT,classes = CapstroneSpringbootApplication.class)
class ApplicationIntegrationTest {

	@LocalServerPort
	private int localport;
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	
	
	@Test
	public void testGetAllCustomers() {
		List customers = testRestTemplate.getForObject("http://localhost:"+localport+"/customer/getAll", List.class);
		
		assertThat(customers.size()).isGreaterThan(0);
	}
	
	@Test
	public void testGetCustomerById() {
		
		Customer cust= testRestTemplate.getForObject("http://localhost:"+localport+"/customer/get/801168", Customer.class);
		
		assertThat(cust).isNotNull();
		assertThat(cust.getCusId()).isEqualTo(801168);
	}
	
	
	
	
	/*
	 * @Test public void testAddCustomer(){ Customer c = getCustomer();
	 * ResponseEntity<?> responseEntity =
	 * testRestTemplate.postForEntity("http://localhost:"+localport+"/customer/add",
	 * c, Customer.class);
	 * 
	 * assertEquals(201,responseEntity.getStatusCodeValue());
	 * 
	 * }
	 * 
	 */
	 
	
	
	
		/*
		 * @Test public void testUpdateCustomerById() { Customer updatedcust =
		 * getUpdatedCustomer(); ResponseEntity<?> responseEntity =
		 * testRestTemplate.exchange("http://localhost:"+localport+
		 * "/customer/update/801151", HttpMethod.PUT,new
		 * HttpEntity<>(updatedcust),Customer.class);
		 * 
		 * assertEquals(HttpStatus.OK,responseEntity.getStatusCode()); }
		 */
	 
	 
	
	
	/*
	 * @Test public void testDeleteCustomerById() { ResponseEntity<?> c =
	 * testRestTemplate.exchange("http://localhost:"+localport+
	 * "/customer/delete/801145", HttpMethod.DELETE,new
	 * HttpEntity<>(""),Customer.class); assertEquals(HttpStatus.OK,
	 * c.getStatusCode()); }
	 */
	
	@Test
	public void testGetCustomerByName() {
		List cust= testRestTemplate.getForObject("http://localhost:"+localport+"/customer/getbyName/Bankim", List.class);
		
		assertThat(cust).isNotNull();
	}
	
	@Test
	public void testGetCustomerByLastName() {
		List cust= testRestTemplate.getForObject("http://localhost:"+localport+"/customer/getbyLastName/Singh", List.class);
		assertThat(cust.size()).isEqualTo(4);
	}
	
	@Test
	public void testGetCustomerByEmail() {
		List cust= testRestTemplate.getForObject("http://localhost:"+localport+"/customer/getbyEmail/bankim.yahoo", List.class);
		assertThat(cust.size()).isEqualTo(2);
	}
	
	
	
	/*
	 * @Test public void testUpdateCustomerByFirstName() { Customer cust =
	 * getUpdatedCustomer(); ResponseEntity<?> responseEntity =
	 * testRestTemplate.exchange("http://localhost:"+localport+
	 * "/customer/updatebyfirstname/Shyam", HttpMethod.PUT,new
	 * HttpEntity<>(cust),Customer.class);
	 * 
	 * assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
	 * 
	 * 
	 * }
	 */
	 
	
	
	@Test
	public void testAddAccount() {
		
		Account acc = getAccount();
		ResponseEntity<?> response = testRestTemplate.postForEntity("http://localhost:"+localport+"/customer/account/801168/addAccount", acc, Account.class);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
	}
	

	
	@Test
	public void testGetAllAccount() {
		List accnts = testRestTemplate.getForObject("http://localhost:"+localport+"/customer/account/getAllAccount", List.class);
		
		assertThat(accnts.size()).isGreaterThan(0);
	}
	
	//http://localhost:"+localport+"/customer/account/fundTransfer/8112646/8112650/500
	@Test
	public void testFundTransfer() {
		ResponseEntity<?> actual  = testRestTemplate.exchange("http://localhost:"+localport+"/customer/account/fundtransfer/80116793/80116794/500",
				HttpMethod.PUT,new HttpEntity<>(""),String.class);
		assertEquals("success",actual.getBody());
	}
	
	
	public void testGetAccountById() {
		ResponseEntity<?> actual = testRestTemplate.exchange("http://localhost:"+localport+"/customer/account/getbyacntno/8112651", 
				HttpMethod.GET,new HttpEntity<>(""),Account.class);
		assertEquals(200, actual.getStatusCodeValue());
	}
	
	private Customer getCustomer() {
		Customer c = new Customer();
		c.setCusFirstName("Shamu");
		c.setCusMiddleName("");
		c.setCusLastName("Kumar");
		c.setCusEmail("shamu.wipro");
		c.setCusPhn("700356526");
		
		c.setCusAddress("Bihar, IND");
		
		List<Account> list = new ArrayList<>();
		Account acnt01 = new Account("Savings",1678.00,c);
		Account acnt02 = new Account("Demat",1812.00,c);
		list.add(acnt01);
		list.add(acnt02);
		
		c.setAcc(list);
		
		return c;
		
	}
	
	private Customer getUpdatedCustomer() {
		
		Customer c = new Customer();
		c.setCusFirstName("Ramu");
		c.setCusMiddleName("Kumar");
		c.setCusLastName("Sen");
		c.setCusEmail("ramu.wipro");
		c.setCusPhn("700362858");
		
		c.setCusAddress("Kolkata, IND");
		
		List<Account> list = new ArrayList<>();
		Account acnt01 = new Account(8112640,"Savings",1800.00,c);
		Account acnt02 = new Account(8112641,"Demat",2123.00,c);
		list.add(acnt01);
		list.add(acnt02);
		
		c.setAcc(list);
		
		return c;
	}
	
	private Account getAccount() {
		Account acnt = new Account();
		
		acnt.setAccBal(125000.00);
		acnt.setAccType("Loan");
		acnt.setCust(getUpdatedCustomer());
		
		return acnt;
	}
	

}


