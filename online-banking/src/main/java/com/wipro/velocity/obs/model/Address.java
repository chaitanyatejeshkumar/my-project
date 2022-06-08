package com.wipro.velocity.obs.model;

import javax.persistence.*;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wipro.velocity.obs.model.Customer;
import com.wipro.velocity.obs.model.Customer;

@Entity
@Table(name="address")
public class Address {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long addressId;
   
    private String street;
    private String city;
    private int pincode;
   
    /*
    one-to-one relationship,  between Dealer and address entities.
    Implementing With a Foreign Key in JPA

    @OneToOne annotation on the related entity field, Address.
    we need to place the @JoinColumn annotation to configure the name of the column in the Address table that maps to the primary key in the Dealer table.
    whoever owns the foreign key column gets the @JoinColumn annotation.
    */
   
        @OneToOne
        @JoinColumn(name = "customer_id")
        private Customer customer;

		public Address() {
			// TODO Auto-generated constructor stub
		}

		public Long getAddressId() {
			return addressId;
		}

		public void setAddressId(Long addressId) {
			this.addressId = addressId;
		}

		public String getStreet() {
			return street;
		}

		public void setStreet(String street) {
			this.street = street;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public int getPincode() {
			return pincode;
		}

		public void setPincode(int pincode) {
			this.pincode = pincode;
		}

		/*public Customer getCustomer() {
			return customer;
		}

		public void setCustomer(Customer customer) {
			this.customer = customer;
		}*/

		
}
