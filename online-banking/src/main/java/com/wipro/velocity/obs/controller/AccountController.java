package com.wipro.velocity.obs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wipro.velocity.obs.model.Account;
import com.wipro.velocity.obs.model.Account;
import com.wipro.velocity.obs.service.AccountService;

@Controller
public class AccountController {

	@Autowired
	private AccountService aservice;
	
	@RequestMapping("/accounts")
    public String viewHomePage(Model model) {
        List<Account> listAccounts = aservice.listAll();
        model.addAttribute("listaccounts", listAccounts);
        
        return "accounts";
    }
	
	@RequestMapping("/new")
    public String showNewProductPage(Model model) {
        Account account = new Account();
        model.addAttribute("account", account);
        
        return "new_account";
    }
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveAccount(@ModelAttribute("account") Account account) {
        aservice.save(account);
      
        return "redirect:accounts";
    }
	
}
