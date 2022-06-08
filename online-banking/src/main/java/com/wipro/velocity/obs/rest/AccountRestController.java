package com.wipro.velocity.obs.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.velocity.obs.model.Account;
import com.wipro.velocity.obs.model.Account;
import com.wipro.velocity.obs.exception.ResourceNotFoundException;
import com.wipro.velocity.obs.repository.AccountRepository;

@RestController
@RequestMapping(value="/api")
@CrossOrigin(origins="http://localhost:4200")
public class AccountRestController {

	@Autowired
	private AccountRepository arepo;
	
	@GetMapping("/accounts")
    public List<Account> getAllAccounts() {
         return arepo.findAll();   
    }
	
	@GetMapping("/accounts/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable(value = "id") Long aId)
            throws ResourceNotFoundException {
        Account account = arepo.findById(aId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found for this id :: " + aId));
        return ResponseEntity.ok().body(account);
    }
	
	 @PostMapping("/accounts")
     public Account saveAccount(@Validated @RequestBody Account account) {
      return arepo.save(account)  ;
                     
     }
	 
	 @DeleteMapping("/accounts/{id}")
	    public Map<String, Boolean> deleteAccount(@PathVariable(value = "id") Long aId)
	            throws ResourceNotFoundException{
	     Account account = arepo.findById(aId)
	                .orElseThrow(() -> new ResourceNotFoundException("Account not found for this id :: " + aId));
	        arepo.delete(account);
	       
	        Map<String, Boolean> response = new HashMap<>();
	        response.put("Deleted", Boolean.TRUE);
	        return response;
	 }
	 
	 @PutMapping("/accounts/{id}")  //Update Mapping
	    public ResponseEntity<Account> updateAccount(@PathVariable(value = "id") Long aId,
	            @Validated @RequestBody Account a) throws ResourceNotFoundException {
	    
	     Account account = arepo.findById(aId)
	                .orElseThrow(() -> new ResourceNotFoundException("account not found for this id :: " + aId));
	    
	                   
	   
	        account.setBalance(a.getBalance());
	        
	       
	        final Account updatedAccount = arepo.save(account);
	        return ResponseEntity.ok(updatedAccount);
	    }
}
