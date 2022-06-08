package com.wipro.velocity.obs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.velocity.obs.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

	
	
}
