package com.wipro.velocity.obs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wipro.velocity.obs.model.Account;
import com.wipro.velocity.obs.model.Account;
import com.wipro.velocity.obs.repository.AccountRepository;

@Service
@Transactional
public class AccountService {

	@Autowired
	private AccountRepository arepo;
	
	public List<Account> listAll() {
        return arepo.findAll(); // defined in JPA repo
    }
    
    public void save(Account account) { // save is user defined method in service class
        arepo.save(account); // save method defined in JPA repo
    }
    
    public Account get(long id) {
        return arepo.findById(id).get();  // defined in JPA repo
    }
    
    public void delete(long id) {
        arepo.deleteById(id);  // defined in JPA repo
    }
}
