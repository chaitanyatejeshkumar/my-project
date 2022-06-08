package com.wipro.velocity.obs.controller;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wipro.velocity.obs.model.Customer;
import com.wipro.velocity.obs.model.Account;
import com.wipro.velocity.obs.model.Address;
import com.wipro.velocity.obs.model.Customer;
import com.wipro.velocity.obs.model.Customer;
import com.wipro.velocity.obs.service.CustomerService;

@Controller
public class LoginController {
	
	@Autowired
	private CustomerService cservice;

	@RequestMapping("/")
	public String viewHomePage() {
		return "index";
	}
	
	// Request --> FC--> Controller ---> Response(views- jsp)
    @RequestMapping("/register")
    public String viewRegisterPage(Model model) {
         Customer customer = new Customer();
            model.addAttribute("customer", customer);
              return "register"; //model+view 
    
    }
 
    @PostMapping("/saveCustomer")
    public String saveCustomer(HttpServletRequest req,@ModelAttribute("customer") Customer customer) {
        String s=req.getParameter("street");
        String c=req.getParameter("city");
        int p=Integer.parseInt(req.getParameter("pincode"));
       
        Address a=new Address();
        a.setStreet(s);
        a.setCity(c);
        a.setPincode(p);
       
        customer.setAddress(a);
        a.setCustomer(customer);
       
        cservice.saveCustomer(customer); // interacts with service layer for db logic implementation
        return "index";
    }
    @GetMapping("/login")
    public String showLoginForm(Model theModel) {
       
        return "login";
    }
    @PostMapping("/loginCustomer")
    public ModelAndView loginCustomer(HttpServletRequest req,@ModelAttribute("customer") Customer customer) {
        String email=req.getParameter("email");
        String pass=req.getParameter("password");
        String pass2=encryptPass(pass);  //invokes encryptPass() method
       
        StringTokenizer st = new StringTokenizer(email, "@"); // breaks the email id based on '@' token
        String s2 = st.nextToken(); //email id without domain
       
         ModelAndView mav=null;
         Customer c = cservice.findByEmail(email); //fetch record/object from 2 tables matching email id fr
        
         if(c==null) {
             mav= new ModelAndView("login");
                mav.addObject("error", "User Doesn't Exists");
         }
         else  if(email.equals(c.getEmail()) && pass2.equals(c.getPassword()))
         {
                    
         req.getSession().setAttribute("user", s2);    // creating a session
        
          mav = new ModelAndView("account");
         mav.addObject("dealer", c);
        
            // Displays List of product as soon as Dealer logins
            //List<Account> listAccounts = aservice.listAll();
           // mav.addObject("listaccounts", listAccounts);
                    
         }
        
         else
         {mav= new ModelAndView("login");
            mav.addObject("error", "Invalid  Password");
         }
        
         return mav;
    }
    private String encryptPass(String pass) {
        Base64.Encoder encoder = Base64.getEncoder();
        String normalString = pass;
        String encodedString = encoder.encodeToString(
        normalString.getBytes(StandardCharsets.UTF_8) );
        return encodedString;
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest req) {
        req.getSession().removeAttribute("user");
        req.getSession().invalidate();
        return  "index";
    }
}


