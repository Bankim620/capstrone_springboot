package com.wipro.capstrone_springboot.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wipro.capstrone_springboot.model.Customer;

@Service
public interface ICustomerService {
	public Customer addCustomer(Customer cus);
	public List<Customer> getAllCustomer();
	public Customer getCustomerById(int id);
	public Customer updateCustomer(int id,Customer cust);
	public Customer deleteById(int id);
	public boolean deleteAll();
	public List<Customer> getCustomerByFirstName(String custFirstName);
	public List<Customer> getCustomerByLastName(String custLastName);
	public Customer updateCustomerByFirstName(String cusFirstName, Customer cust);
	public List<Customer> getCustomerByEmail(String custEmail);
	

}
