package com.wipro.velocity.obs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wipro.velocity.obs.model.Customer;
import com.wipro.velocity.obs.repository.CustomerRepository;
import com.wipro.velocity.obs.repository.UserRepository;

@Service
@Transactional
public class CustomerService {

	@Autowired
	private CustomerRepository crepo;
	
	@Autowired
	private UserRepository urepo;
	
	public void saveCustomer(Customer customer) {
		
		crepo.save(customer);      //invokes save() method of JpaRepository
	}
	
	public Customer findByEmail(String email) {
		return urepo.findByEmail(email);
	}
}
