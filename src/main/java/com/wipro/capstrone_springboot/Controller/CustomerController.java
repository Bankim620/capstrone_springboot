package com.wipro.capstrone_springboot.Controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.wipro.capstrone_springboot.Exception.CustomerNotFoundException;
import com.wipro.capstrone_springboot.Service.ICustomerService;
import com.wipro.capstrone_springboot.model.Customer;


@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	ICustomerService custservice;
	
	
	
	
	
	
	@PostMapping("/add")
	public ResponseEntity<?> add(@RequestBody Customer cust)throws Exception{
			Customer c = custservice.addCustomer(cust);
			if(c!=null) {
				return new ResponseEntity<>(c,HttpStatus.CREATED);
			}
			else {
				 //return new ResponseEntity<>("Customer Not Added",HttpStatus.BAD_REQUEST);
				throw new Exception();
			}
		
	}
	
	@GetMapping("/getAll")
	public List<Customer> getAll(){
		List<Customer> list = custservice.getAllCustomer();
		if(!list.isEmpty()) {
			return list;
		}
		return null;
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<?> getbyId(@PathVariable("id") int id) throws Exception{
		Customer c = custservice.getCustomerById(id);
		if(c!=null) {
			return new ResponseEntity<>(c,HttpStatus.OK);
		}
		//return new ResponseEntity<>("Customer Not Found",HttpStatus.NOT_FOUND);
		throw new CustomerNotFoundException();
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody Customer cust)throws Exception{
		Customer c = custservice.updateCustomer(id, cust);
		if(c!=null) {
			return new ResponseEntity<>(c,HttpStatus.OK);
		}
		else {
			//return new ResponseEntity<>("Customer Details Not Updated",HttpStatus.INTERNAL_SERVER_ERROR);
			throw new CustomerNotFoundException();
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public Customer deleteById(@PathVariable("id")int id){
		Customer c = custservice.deleteById(id);
		if(c!=null) {
			return c;
		}
		return null;
		//throw new DeleteIdNotFoundException("Provided Id Not Found");
	}
	
	@DeleteMapping("/deleteAll")
	public String deleteAll() {
		boolean b = custservice.deleteAll();
		if(b) {
			return "All Details Deleted";
		}
		return "Internal Error Delete Operation can't be done";
	}
	
	@GetMapping("/getbyName/{custFirstName}")
	public ResponseEntity<?> getBycustFirstName(@PathVariable()String custFirstName) throws Exception{
		List<Customer> list = custservice.getCustomerByFirstName(custFirstName);
		if(!list.isEmpty()) {
			return new ResponseEntity<>(list,HttpStatus.OK);
		}
		//return new ResponseEntity<>("Customer not Found",HttpStatus.NOT_FOUND);
		throw new CustomerNotFoundException();
	}
	
	@GetMapping("/getbyLastName/{custLastName}")
	public ResponseEntity<?> getBycustLastName(@PathVariable()String custLastName) throws Exception{
		List<Customer> list = custservice.getCustomerByLastName(custLastName);
		if(!list.isEmpty()) {
			return new ResponseEntity<>(list,HttpStatus.OK);
		}
		//return new ResponseEntity<>("Customer not Found",HttpStatus.NOT_FOUND);
		throw new CustomerNotFoundException();
	}
	
	
	@PutMapping("/updatebyfirstname/{custFirstName}")
	public ResponseEntity<?> updateByFirstName(@PathVariable("custFirstName") String custFirstName,@RequestBody Customer cust)throws Exception{
		Customer c = custservice.updateCustomerByFirstName(custFirstName, cust);
		if(c!=null) {
			return new ResponseEntity<>(c,HttpStatus.OK);
		}
		//return new ResponseEntity<>("Customer Not Updated",HttpStatus.INTERNAL_SERVER_ERROR);
		throw new CustomerNotFoundException();
	}
	
	
	@GetMapping("/getbyEmail/{custEmail}")
	public ResponseEntity<?> getBycustEmail(@PathVariable()String custEmail)throws Exception{
		List<Customer> list = custservice.getCustomerByEmail(custEmail);
		if(!list.isEmpty()) {
			return new ResponseEntity<>(list,HttpStatus.OK);
		}
		//return new ResponseEntity<>("Customer not Found",HttpStatus.NOT_FOUND);
		throw new CustomerNotFoundException();
	}

}
