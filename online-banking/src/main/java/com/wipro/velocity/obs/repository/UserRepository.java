package com.wipro.velocity.obs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.wipro.velocity.obs.model.CustomerAddress;
import com.wipro.velocity.obs.model.Customer;
import com.wipro.velocity.obs.model.CustomerAddress;
import com.wipro.velocity.obs.model.Customer;

public interface UserRepository extends CrudRepository<Customer, Long> {

	public Customer findByEmail(String email);


	//Custom queries using jpql in crud repo
    @Query("SELECT new com.wipro.velocity.obs.model.CustomerAddress(c.id,c.email,c.fname,c.lname,"
            + "c.password,c.dob,c.phoneNo,c.accountNo,a.street,a.city,a.pincode) "
            + "FROM Customer c INNER JOIN c.address a")
    List<CustomerAddress> fetchCustomerInnerJoin();
}
