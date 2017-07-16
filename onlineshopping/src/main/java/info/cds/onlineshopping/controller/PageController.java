package info.cds.onlineshopping.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import info.cds.onlineshopping.exception.ProductNotFoundException;
import info.cds.shoppingbackend.dao.CategoryDAO;
import info.cds.shoppingbackend.dao.ProductDAO;
import info.cds.shoppingbackend.dto.Category;
import info.cds.shoppingbackend.dto.Product;

@Controller
public class PageController {
	
	private static final Logger logger=LoggerFactory.getLogger(PageController.class);
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	@RequestMapping(value={"/","/home","/index"})
	public String index(Model model){
		model.addAttribute("title", "Home");
		
		//passing the list-of-categories
		model.addAttribute("categories", categoryDAO.list());
		
		logger.info("Inside the page-controller index page - INFO");
		logger.debug("Inside the page-controller index page - DEBUG");
		
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
	
	/*
	 * 
	 * Viewing a Single Product
	 */
	@RequestMapping(value="/show/{id}/product")
	public String showSingleProduct(@PathVariable int id,Model model) throws ProductNotFoundException{
		
		Product product = productDAO.get(id);
		
		if(product == null) throw new ProductNotFoundException();
		
		//Updating the views count
		product.setViews(product.getViews()+1);
		productDAO.update(product);
		
		//-------------------
		model.addAttribute("title", product.getName());
		model.addAttribute("product", product);
		model.addAttribute("userClickShowProduct", true);
		
		return "page";
	}
	
	
	//testing with the same url
	
	@RequestMapping(value="register")
	public String register(Model model){
		model.addAttribute("title", "About Us");
		model.addAttribute("userClickAbout", true);
		return "page";
	}
	
	
}
