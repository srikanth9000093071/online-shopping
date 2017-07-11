package info.cds.onlineshopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import info.cds.shoppingbackend.dao.CategoryDAO;
import info.cds.shoppingbackend.dto.Category;

@Controller
public class PageController {
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@RequestMapping(value={"/","/home","/index"})
	public String index(Model model){
		model.addAttribute("title", "Home");
		
		//passing the list-of-categories
		model.addAttribute("categories", categoryDAO.list());
		
		
		model.addAttribute("userClickHome", true);
		return "page";
	}
	
	@RequestMapping(value="about")
	public String about(Model model){
		model.addAttribute("title", "About Us");
		model.addAttribute("userClickAbout", true);
		return "page";
	}
	
	@RequestMapping(value="contact")
	public String contact(Model model){
		model.addAttribute("title", "Contact Us");
		model.addAttribute("userClickContact", true);
		return "page";
	}

	
	
	/*
	 * Method to load all the products and based on the category 
	 */
	
	@RequestMapping(value="show/all/products")
	public String showAllProducts(Model model){
		model.addAttribute("title", "All Products");
		//passing the list-of-categories
		model.addAttribute("categories", categoryDAO.list());
		model.addAttribute("userClickAllProducts", true);
		return "page";
	}
	
	
	@RequestMapping(value="show/category/{id}/products")
	public String showCategoryProducts(@PathVariable("id") int id ,Model model){
		
		
		Category category=null;
		category = categoryDAO.get(id);
		
		model.addAttribute("title", category.getName());
		//passing the list-of-categories
		model.addAttribute("categories", categoryDAO.list());
		//passing the single category object
		model.addAttribute("category", category);
		model.addAttribute("userClickCategoryProducts", true);
		return "page";
	}
	
	
}
