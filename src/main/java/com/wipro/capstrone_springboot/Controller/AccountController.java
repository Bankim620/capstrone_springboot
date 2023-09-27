package com.wipro.capstrone_springboot.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.capstrone_springboot.Exception.AccountCreationException;
import com.wipro.capstrone_springboot.Exception.AccountNotFoundException;
import com.wipro.capstrone_springboot.Exception.CustomerNotFoundException;
import com.wipro.capstrone_springboot.Exception.FundTransferException;
import com.wipro.capstrone_springboot.Service.IAccountService;
import com.wipro.capstrone_springboot.Service.ICustomerService;
import com.wipro.capstrone_springboot.model.Account;

@RestController
@RequestMapping("/customer/account")
public class AccountController {
	@Autowired
	ICustomerService cusservice;
	
	@Autowired
	IAccountService acntservice;
	
	@PostMapping("/{cusId}/addAccount")
	public ResponseEntity<?> addAccount(@PathVariable("cusId") int custId,@RequestBody Account acnt)throws Exception{
		//int cusId;
		if(cusservice.getCustomerById(custId)!=null) {
			Account acc = acntservice.addAccount(custId,acnt);
			if(acc!=null) {
				return new ResponseEntity<>(acc,HttpStatus.CREATED);
			}
			else {
				throw new AccountCreationException();
				
			}
			
		}
		else {
			throw new CustomerNotFoundException();
			
		}
	}
	@GetMapping("/getAllAccount")
	public List<Account>getAllAccount(){
		List<Account> acnts=acntservice.getAllAccounts();
		if(acnts!=null) {
			return acnts;
		}
		return null;
		
	}
	@PutMapping("/fundtransfer/{fromaccount}/{toaccount}/{amount}")
	public ResponseEntity<?> fundTransfer(@PathVariable("fromaccount")int from,
			@PathVariable("toaccount")int to,
			@PathVariable("amount")double amount)throws Exception{
		String msg=acntservice.transferFunds(from,to,amount);
		if(msg.equals("success"))	{
			return new ResponseEntity<>(msg,HttpStatus.OK);
			
		}
		throw new FundTransferException(msg);
		
			}
	@GetMapping("/getbyacntno/{acntno}")
	public ResponseEntity<?> getByAcntno(@PathVariable("acntno")int acntno)  throws Exception{
		Account acnt=acntservice.getBalanceOf(acntno);
		if(acnt!=null) {
			return new ResponseEntity<>(acnt,HttpStatus.OK);
			
		}
		throw new AccountNotFoundException();
	}
}
