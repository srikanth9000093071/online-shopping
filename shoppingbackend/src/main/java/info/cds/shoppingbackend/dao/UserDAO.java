package info.cds.shoppingbackend.dao;

import java.util.List;

import info.cds.shoppingbackend.dto.Address;
import info.cds.shoppingbackend.dto.Cart;
import info.cds.shoppingbackend.dto.User;

public interface UserDAO {
	//Add an user
	public boolean addUser(User user);
	
	public User getByEmail(String email);
/*	
	public Address getBillingAddress(User user);
	
	public List<Address> listShippingAddresses(User user);
	*/
	//Add an Address
	public boolean addAddress(Address address);
	
	// alternative
	public	 Address getBillingAddress(int userId);
	public	 List<Address> listShippingAddresses(int userId);
		 
		 
	//Update a cart
	public boolean updateCart(Cart cart);

}
