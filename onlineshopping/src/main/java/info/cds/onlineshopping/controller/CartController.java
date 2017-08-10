package info.cds.onlineshopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import info.cds.onlineshopping.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@RequestMapping("/show")
	public String showCart(Model model,@RequestParam(name="result", required=false)String result){
		if(result!=null){
			switch (result) {
			case "updated":
				         model.addAttribute("message", "CartLine has been updated successfully.");
				         break;
			case "error":
		         model.addAttribute("message", "Something went wrong!...");
		         break;
			case "deleted":
		         model.addAttribute("message", "CartLine has been removed successfully.");
		         break;
			}
		}
		model.addAttribute("title", "User Cart");
		model.addAttribute("userClickShowCart", true);
		model.addAttribute("cartLines", cartService.getCartLines());
		return "page";
	}
	
	@RequestMapping("/{cartLineId}/update")
	public String updateCart(@PathVariable int cartLineId,@RequestParam int count){
		String response = cartService.updateCartLine(cartLineId,count);
		return "redirect:/cart/show?"+response;
	}
	
	@RequestMapping("/{cartLineId}/delete")
	public String updateCart(@PathVariable int cartLineId){
		String response = cartService.deleteCartLine(cartLineId);
		return "redirect:/cart/show?"+response;
	}

}
