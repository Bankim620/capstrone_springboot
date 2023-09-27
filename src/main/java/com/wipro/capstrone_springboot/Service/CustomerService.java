package com.wipro.capstrone_springboot.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.capstrone_springboot.Repository.AccountRepository;
import com.wipro.capstrone_springboot.Repository.CustomerRepository;
import com.wipro.capstrone_springboot.model.Account;

//import org.springframework.stereotype.Service;

import com.wipro.capstrone_springboot.model.Customer;

@Service
public class CustomerService implements ICustomerService {
	
	
	@Autowired
	AccountRepository arepo;
	
	@Autowired
	CustomerRepository crepo;

	@Override
	public Customer addCustomer(Customer cus) {
		
		Customer c= crepo.save(cus);
		List<Account> acnts = cus.getAcc();
		for(Account ac:acnts) {
			ac.setCust(cus);
			arepo.save(ac);
		}
		if(c!=null) { return c;}
		return null;
	}

	@Override
	public List<Customer> getAllCustomer() {
		List<Customer> list=crepo.findAll();
		if(!list.isEmpty())
			return list;
		
		return null;
	}

	@Override
	public Customer getCustomerById(int id) {
		Optional<Customer> c= crepo.findById(id);
		if(!c.isEmpty())
			return (Customer)c.get();
			
		return null;
	}

	@Override
	public Customer updateCustomer(int id, Customer cust) {
		Customer c= getCustomerById(id);
		if(c!=null) {
			c.setCusFirstName(cust.getCusFirstName());
			c.setCusMiddleName(cust.getCusMiddleName());
			c.setCusLastName(cust.getCusLastName());
			c.setCusEmail(cust.getCusEmail());
			c.setCusPhn(cust.getCusPhn());
			c.setCusAddress(cust.getCusAddress());
			c.setAcc(cust.getAcc());
			crepo.save(c);
			List<Account> acnts=c.getAcc();
			for(Account acnt:acnts) {
				Optional <Account> accountopt=arepo.findById(acnt.getAccNo());
				if(accountopt.isPresent()) {
					Account account=accountopt.get();
					Account.serAcnType(acnt.getAccType());
					account.setAccBal(acnt.getAccBal());
					account.setCust(c);
					arepo.save(account);
				}
				
				
			}
			return c;
		}
		
		
		return null;
	}

	@Override
	public Customer deleteById(int id) {
		Customer c=getCustomerById(id);
		if(c!=null) {
			crepo.delete(c);
			return c;
		}
		
		return null;
	}

	@Override
	public boolean deleteAll() {
		crepo.deleteAll();
		arepo.deleteAll();
		if(getAllCustomer()==null) {
			return true;
		}
		
		
		return false;
	}

	@Override
	public List<Customer> getCustomerByFirstName(String custFirstName) {
		List<Customer>list=crepo.getCustomerBycusFirstName(custFirstName);
		// TODO Auto-generated method stub
		return list;
	}

	@Override
	public List<Customer> getCustomerByLastName(String custLastName) {
		List<Customer>list=crepo.getCustomerBycusLastName(custLastName);
		return list;
		
		
	}

	@Override
	public Customer updateCustomerByFirstName(String cusFirstName, Customer cust) {
		List<Customer>list=getCustomerByFirstName(cusFirstName);
		
		if(list!=null) {
			for(Customer customer:list) {
				if(customer.getCusId()==cust.getCusId()) {
					customer.setCusFirstName(cust.getCusFirstName());
					customer.setCusMiddleName(cust.getCusMiddleName());
					customer.setCusLastName(cust.getCusLastName());
					customer.setCusEmail(cust.getCusEmail());
					customer.setCusPhn(cust.getCusPhn());
					customer.setCusAddress(cust.getCusAddress());
					customer.setAcc(cust.getAcc());
					crepo.save(customer);
					List<Account> acnts=customer.getAcc();
					for(Account acnt:acnts) {
						Optional<Account> accountopt=arepo.findById(acnt.getAccNo());
						if(accountopt.isPresent()) {
							Account account =accountopt.get();
							account.setAccType(acnt.getAccType());
							account.setAccBal(acnt.getAccBal());
							account.setCust(customer);
							arepo.save(account);
							
						}
						
						
					}
					return customer;
					
					
				}
				
			}
			
		}
		return null;
	}

	@Override
	public List<Customer> getCustomerByEmail(String custEmail) {
		List<Customer>custs=crepo.getCustomerBycusEmail(custEmail);
		return custs;
	}

}
