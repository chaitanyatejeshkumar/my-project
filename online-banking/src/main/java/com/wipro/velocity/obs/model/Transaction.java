package com.wipro.velocity.obs.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Transaction {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	 private String accountNo;
     private String name;
     private float balance;
	public Transaction() {
		
	}
	
	
	public Transaction(Long id, String accountNo, String name, float balance) {
		super();
		this.id = id;
		this.accountNo = accountNo;
		this.name = name;
		this.balance = balance;
	}



	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "Transaction [id=" + id + ", accountNo=" + accountNo + ", name=" + name + ", balance=" + balance + "]";
	}


	public Object getEmail() {
		// TODO Auto-generated method stub
		return null;
	}


	public void setEmail(Object email) {
		// TODO Auto-generated method stub
		
	}
	
	
     
     
}

