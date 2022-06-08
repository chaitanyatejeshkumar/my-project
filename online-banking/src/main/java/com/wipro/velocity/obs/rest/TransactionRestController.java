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

import com.wipro.velocity.obs.exception.ResourceNotFoundException;
import com.wipro.velocity.obs.model.Transaction;
import com.wipro.velocity.obs.repository.TransactionRepository;

@RestController
@RequestMapping(value="/api")
@CrossOrigin(origins="http://localhost:4200")
public class TransactionRestController {

	@Autowired
	private TransactionRepository trepo;
	
	@GetMapping("/transactions")
    public List<Transaction> getAllTransactions() {
         return trepo.findAll();   
    }
	@GetMapping("/transactions/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable(value = "id") Long tId)
            throws ResourceNotFoundException {
		Transaction transaction = trepo.findById(tId)
                .orElseThrow(() -> new ResourceNotFoundException("Transtraction not found for this id :: " + tId));
        return ResponseEntity.ok().body(transaction);
    }
	
	 @PostMapping("/transactions")
     public Transaction saveProduct(@Validated @RequestBody Transaction transaction) {
      return trepo.save(transaction)  ;
                     
     }
	 
	 @DeleteMapping("/transactions/{id}")
	    public Map<String, Boolean> deleteProduct(@PathVariable(value = "id") Long tId)
	            throws ResourceNotFoundException{
		 Transaction transaction = trepo.findById(tId)
	                .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + tId));
	        trepo.delete(transaction);
	       
	        Map<String, Boolean> response = new HashMap<>();
	        response.put("Deleted", Boolean.TRUE);
	        return response;
	 }
	 
	 @PutMapping("/transactions/{id}")  //Update Mapping
	    public ResponseEntity<Transaction> updateTransaction(@PathVariable(value = "id") Long tId,
	            @Validated @RequestBody Transaction t) throws ResourceNotFoundException {
	    
		 Transaction transaction = trepo.findById(tId)
	                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found for this id :: " + tId));
	    
	                   
		 transaction.setId(t.getId());
		 transaction.setAccountNo(t.getAccountNo());
		 transaction.setName(t.getName());
		 transaction.setEmail(t.getEmail());
		 transaction.setBalance(t.getBalance());
	       
	        final Transaction updatedTransaction = trepo.save(transaction);
	        return ResponseEntity.ok(updatedTransaction);
	    }
}
