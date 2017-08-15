package info.cds.onlineshopping.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import info.cds.onlineshopping.model.UserModel;
import info.cds.shoppingbackend.dao.CartLineDAO;
import info.cds.shoppingbackend.dao.ProductDAO;
import info.cds.shoppingbackend.dto.Cart;
import info.cds.shoppingbackend.dto.CartLine;
import info.cds.shoppingbackend.dto.Product;

@Service("cartService")
public class CartService {
	
	@Autowired
	private CartLineDAO cartLineDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
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

	public String addCartLine(int productId) {
		String response = null;
		
		Cart cart =this.getCart();
		
		CartLine cartLine = cartLineDAO.getByCartAndProduct(cart.getId(), productId);
		if(cartLine == null){
			//Add a new CartLine
			cartLine = new CartLine();
			//Fetch the product
			Product product = productDAO.get(productId);
			cartLine.setCartId(cart.getId());
			cartLine.setProduct(product);
			cartLine.setBuyingPrice(product.getUnitPrice());
			cartLine.setProductCount(1);
			cartLine.setTotal(product.getUnitPrice());
			cartLine.setAvailable(true);
			
			cartLineDAO.add(cartLine);
			cart.setCartLines(cart.getCartLines() + 1);
			cart.setGrandTotal(cart.getGrandTotal() + cartLine.getTotal());
			cartLineDAO.updateCart(cart);
			response = "result=added";
		}
		return response;
	}

}
