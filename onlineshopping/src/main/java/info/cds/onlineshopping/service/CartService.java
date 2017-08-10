package info.cds.onlineshopping.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import info.cds.onlineshopping.model.UserModel;
import info.cds.shoppingbackend.dao.CartLineDAO;
import info.cds.shoppingbackend.dto.Cart;
import info.cds.shoppingbackend.dto.CartLine;
import info.cds.shoppingbackend.dto.Product;

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

	public String updateCartLine(int cartLineId, int count) {
		//fetch the cartLine
		CartLine cartLine = cartLineDAO.get(cartLineId);
		if(cartLine == null){
			return "result=error";
		}
		else{
			Product product = cartLine.getProduct();
			double oldTotal = cartLine.getTotal();
			if(product.getQuantity() <=  count){
				count=product.getQuantity();
			}
			cartLine.setProductCount(count);
			cartLine.setBuyingPrice(product.getUnitPrice());
			cartLine.setTotal(product.getUnitPrice()*count);
			cartLineDAO.update(cartLine);
			Cart cart = this.getCart();
			cart.setGrandTotal(cart.getGrandTotal()-oldTotal + cartLine.getTotal());
			cartLineDAO.updateCart(cart);
			return "result=updated";
		}
		
	}

	public String deleteCartLine(int cartLineId) {
		//fetch the cartLine
		CartLine cartLine = cartLineDAO.get(cartLineId);
		if(cartLine==null){
			return "result=error";
		}
		//update the cart
		Cart cart = this.getCart();
		cart.setGrandTotal(cart.getGrandTotal() - cartLine.getTotal());
		cart.setCartLines(cart.getCartLines() - 1);
		
		//remove the cartLine
		cartLineDAO.remove(cartLine);
		return "result=deleted";
	}

}
