package com.wipro.capstrone_springboot.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wipro.capstrone_springboot.model.Account;

@Service
public interface IAccountService {
	public Account addAccount(int id,Account acc);
	public List<Account> getAllAccounts();
	public String transferFunds(int from,int to,double amount);
	public Account getBalanceOf(int acntno);
	
	

}
