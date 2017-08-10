package info.cds.onlineshopping.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import info.cds.onlineshopping.model.UserModel;
import info.cds.shoppingbackend.dao.CartLineDAO;
import info.cds.shoppingbackend.dto.Cart;
import info.cds.shoppingbackend.dto.CartLine;

@Service("cartService")
public class CartService {
	
	@Autowired
	private CartLineDAO cartLineDAO;
	
	@Autowired
	private HttpSession session;
	
	//Return the cart of the current LoggedIN User
	private Cart getCart(){
		return ((UserModel)session.getAttribute("userModel")).getCart();
		
	}
	
	//returns the entire cartLines
	public List<CartLine> getCartLines(){
		return cartLineDAO.list(this.getCart().getId());
	}

}
