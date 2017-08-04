package info.cds.onlineshopping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	
	
	@RequestMapping("/show")
	public String showCart(Model model){
		model.addAttribute("title", "User Cart");
		model.addAttribute("userClickShowCart", true);
		model.addAttribute("cartLines", null);
		return "page";
	}

}
