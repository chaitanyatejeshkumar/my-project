package com.wipro.velocity.obs.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.velocity.obs.model.Address;
import com.wipro.velocity.obs.model.Customer;
import com.wipro.velocity.obs.model.CustomerAddress;
import com.wipro.velocity.obs.exception.ResourceNotFoundException;
import com.wipro.velocity.obs.model.Customer;
import com.wipro.velocity.obs.model.CustomerAddress;
import com.wipro.velocity.obs.model.Customer;
import com.wipro.velocity.obs.repository.UserRepository;

@RestController
@RequestMapping(value="/api")
@CrossOrigin(origins="http://localhost:4200")
public class CustomerRestController {

	@Autowired
	private UserRepository urepo;
	
	@PostMapping("/customer")
    public Boolean loginCustomer(@Validated @RequestBody Customer customer) throws ResourceNotFoundException
    {
        Boolean a=false;;
        String email=customer.getEmail();
        String password=customer.getPassword();
        //System.out.println(email+password);
        Customer c = urepo.findByEmail(email);//.orElseThrow(() -> new ResourceNotFoundException("Account not found for this id :: "));
    //    System.out.println(d.getEmail() +d.getPassword() );
       
        if(email.equals(c.getEmail()) && password.equals(c.getPassword()))
                {
        //    System.out.println(d.getEmail() +d.getPassword() );
            a=true;
           
                }
        return a;
    }
	@GetMapping("/customerbyemail/{email}")
    public ResponseEntity<Customer> getCustomerByEmail(@PathVariable("email") String email)
           // throws ResourceNotFoundException 
    {
		System.out.println(email);
		Customer customer = urepo.findByEmail(email);
               // .orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + uEmail));
        return ResponseEntity.ok().body(customer);
    }
	
	//Open Postman and make POST request - http://localhost:8085/ims/api/dealers
    //Under body tab --> raw --> Text --> Json and type the json data to be saved
        
    @PostMapping("/customers")
    public CustomerAddress createCustomer(@Validated @RequestBody CustomerAddress customer) throws ResourceNotFoundException{

 

        /*if(urepo.findByEmail(dealer.getEmail())!=null)
        {            
            new ResourceNotFoundException("User Already Exists");
        }*/
        System.out.println("Hello "+ customer.getEmail()+" " +customer.getPassword());
        Customer c=new Customer();
        c.setEmail(customer.getEmail());
        c.setFname(customer.getFname());
        c.setLname(customer.getLname());
        c.setPassword(customer.getPassword());
        System.out.println("Hello "+ customer.getEmail()+" " +customer.getPassword());
        c.setDob(customer.getDob());
        c.setPhoneNo(customer.getPhoneNo());
        c.setAccountNo(customer.getAccountNo());
        
        Address a=new Address();
        a.setStreet(customer.getStreet());
        a.setCity(customer.getCity());
        a.setPincode(customer.getPincode());
        
                
        c.setAddress(a);
       // a.setCustomer(c);
    //    lservice.saveDealer(dealer);
         urepo.save(c);
         return customer;
    }
    @GetMapping("/customers")
    public List<CustomerAddress> getAllCustomers() {
         return  urepo.fetchCustomerInnerJoin();
    }
}
