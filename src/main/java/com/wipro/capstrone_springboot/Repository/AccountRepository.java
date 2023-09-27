package com.wipro.capstrone_springboot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.capstrone_springboot.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer>{

}
