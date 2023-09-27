package com.wipro.capstrone_springboot.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.capstrone_springboot.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer>{

	public List<Customer> getCustomerBycusEmail(String custEmail);
	public List<Customer>getCustomerBycusLastName(String custLastName);
	public List<Customer>getCustomerBycusFirstName(String custFirstName);

}
