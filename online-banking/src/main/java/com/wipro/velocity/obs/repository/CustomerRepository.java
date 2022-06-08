package com.wipro.velocity.obs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.velocity.obs.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
