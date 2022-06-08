package com.wipro.velocity.obs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.velocity.obs.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
