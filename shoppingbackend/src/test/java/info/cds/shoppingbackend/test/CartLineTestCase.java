package info.cds.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import info.cds.shoppingbackend.dao.CartLineDAO;
import info.cds.shoppingbackend.dao.ProductDAO;
import info.cds.shoppingbackend.dao.UserDAO;
import info.cds.shoppingbackend.dto.Cart;
import info.cds.shoppingbackend.dto.CartLine;
import info.cds.shoppingbackend.dto.Product;
import info.cds.shoppingbackend.dto.User;

public class CartLineTestCase {

private static AnnotationConfigApplicationContext context;
	
	
	private static CartLineDAO cartLineDAO;
	private static ProductDAO productDAO;
	private static UserDAO userDAO;
	
	private User user = null;
	private Product product = null;
	private Cart cart = null;
	private CartLine cartLine = null;
	
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("info.cds.shoppingbackend");
		context.refresh();
		cartLineDAO = (CartLineDAO)context.getBean("cartLineDAO");
		productDAO = (ProductDAO)context.getBean("productDAO");
		userDAO = (UserDAO)context.getBean("userDAO");
	}
	
	@Test
	public void testAddCartLine() {
		
	// 1. get the user
	user = userDAO.getByEmail("srikanth9000093071@gmail.com");	
	//2. fetch the cart
	cart = user.getCart();
	//3.get the product
	product = productDAO.get(1);
	
	//4.create a new cart line
	cartLine = new  CartLine();
	
	cartLine.setBuyingPrice(product.getUnitPrice());
	
	cartLine.setProductCount(cartLine.getProductCount()+1);

	cartLine.setTotal(cartLine.getProductCount() * product.getUnitPrice());
	
	cartLine.setAvailable(true);
	
	cartLine.setId(cart.getId());
	
	cartLine.setProduct(product);
	
	assertEquals("Failed to add the CartLine!",true, cartLineDAO.add(cartLine));
	
	//update the cart
	cart.setGrandTotal(cart.getGrandTotal() + cartLine.getTotal());
	cart.setCartLines(cart.getCartLines()+1);
	assertEquals("Failed to update the cart!",true, cartLineDAO.updateCart(cart));
	
	
	
	
	
	
	
	
	
	
	
	
	}
	
}
