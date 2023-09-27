package com.wipro.capstrone_springboot.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.capstrone_springboot.Repository.AccountRepository;
import com.wipro.capstrone_springboot.Repository.CustomerRepository;
import com.wipro.capstrone_springboot.model.Account;
import com.wipro.capstrone_springboot.model.Customer;

@Service
public class AccountService implements IAccountService {
	
	@Autowired 
	AccountRepository repository;
	
	@Autowired
	CustomerRepository crepo;
	
	
	


	@Override
	public Account addAccount(int id, Account acc) {
		Optional<Customer>c=crepo.findById(id);
		if(c.isPresent()) {
			Customer cust=c.get();
			List<Account> acnts=cust.getAcc();
			acnts.add(acc);
			cust.setAcc(acnts);
			
			acc.setCust(cust);
			repository.save(acc);
			crepo.save(cust);
			return acc;
			
		}
		return null;
	}

	@Override
	public List<Account> getAllAccounts() {
		List<Account>accounts=repository.findAll();
		if(!accounts.isEmpty())
			return accounts;
		return null;
	}

	@Override
	public String transferFunds(int from, int to, double amount) {
		if(amount>0) {
			Optional<Account> fromacnt=repository.findById(from);
			Optional<Account> toacnt=repository.findById(to);
			if(fromacnt.isEmpty() || toacnt.isEmpty()) {
				return "ID MisMatch";
			}
			if(fromacnt.get().getAccBal()< amount) {
				return "Insufficient fund";
			}
			else {
				double frombalance=fromacnt.get().getAccBal();
				double tobalance =toacnt.get().getAccBal();
				frombalance-=amount;
				tobalance+=amount;
				fromacnt.get().setAccBal(frombalance);
				toacnt.get().setAccBal(tobalance);
				repository.save(fromacnt.get());
				repository.save(toacnt.get());
				return "success";
				
				
			}
			
		}
		return "Money Can't be negative";
	}

	@Override
	public Account getBalanceOf(int acntno) {
		Optional<Account> acnt=repository.findById(acntno);
		if(!acnt.isEmpty()) {
			return acnt.get();
			
		}
		return null;
	}

}
