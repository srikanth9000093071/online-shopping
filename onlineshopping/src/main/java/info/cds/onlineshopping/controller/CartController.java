package info.cds.onlineshopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import info.cds.onlineshopping.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@RequestMapping("/show")
	public String showCart(Model model){
		model.addAttribute("title", "User Cart");
		model.addAttribute("userClickShowCart", true);
		model.addAttribute("cartLines", cartService.getCartLines());
		return "page";
	}

}
