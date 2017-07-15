package info.cds.onlineshopping.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import info.cds.onlineshopping.util.FileUploadUtility;
import info.cds.onlineshopping.validator.ProductValidator;
import info.cds.shoppingbackend.dao.CategoryDAO;
import info.cds.shoppingbackend.dao.ProductDAO;
import info.cds.shoppingbackend.dto.Category;
import info.cds.shoppingbackend.dto.Product;

@Controller
@RequestMapping("/manage")
public class ManagementController {
	
	private static final Logger logger= LoggerFactory.getLogger(ManagementController.class);
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	@RequestMapping(value="/products",method=RequestMethod.GET)
	public String showManageProducts(Model model,@RequestParam(value="operation", required=false) String operation){
		
		model.addAttribute("title", "Manage Products");
		model.addAttribute("userClickManageProducts", true);
		
		Product nProduct= new Product();
		
		//Set few of the fields
		nProduct.setSupplierId(1);
		nProduct.setActive(true);
		model.addAttribute("product", nProduct);
		
		if(operation != null){
			if(operation.equals("product")){
				model.addAttribute("message", "Product Submitted Successfully");
			}
		}
		
		return "page";
	}
	
	
	@RequestMapping(value="/{id}/product",method=RequestMethod.GET)
	public String showEditProduct(@PathVariable int id,Model model){
		model.addAttribute("title", "Manage Products");
		model.addAttribute("userClickManageProducts", true);
		//fetch the product from database
		Product nProduct= productDAO.get(id);
		//Set the product fetched from database
		model.addAttribute("product", nProduct);
		return "page";
	}
	
	
	@RequestMapping(value="product/{id}/activation",method=RequestMethod.POST)
	@ResponseBody
	public String handleProductActivation(@PathVariable int id){
		//is going to fetch the product from the database
		Product product = productDAO.get(id);
		boolean isActive= product.isActive();
		//activating and deactivating based on the value of active field
		product.setActive(!product.isActive());
		//updating the product
		productDAO.update(product);
		
		
		return (isActive)? "You have successfully deactivated the product with id "+product.getId() : 
			               "You have successfully activated the product with id "+product.getId();
	}
	
	
	//returning categories for all the request mapping
	@ModelAttribute("categories")
	public List<Category> getCategories(){
		return categoryDAO.list();
	}
	
	//handling product submission
	@RequestMapping(value="/products",method=RequestMethod.POST)
	public String handleProductSubmission(@Valid @ModelAttribute("product") Product mProduct,BindingResult result,Model model,
			HttpServletRequest request){
		if(mProduct.getId()==0){
		new ProductValidator().validate(mProduct, result);
		}
		else{
			if(!mProduct.getFile().getOriginalFilename().equals("")){
				new ProductValidator().validate(mProduct, result);
			}
		}
		//check if there are any errors
		if(result.hasErrors()){
			model.addAttribute("title", "Manage Products");
			model.addAttribute("userClickManageProducts", true);
			model.addAttribute("message", "Validation Failed for product submission !");
			return "page";
		}
		
		logger.info(mProduct.toString());
		if(mProduct.getId() == 0){
		//create a new product record if id is 0
		productDAO.add(mProduct);
		}
		else{
			//update the product if id is not 0
			productDAO.update(mProduct);
		}
		
		
		if(!mProduct.getFile().getOriginalFilename().equals("")){
			FileUploadUtility.uploadFile(request,mProduct.getFile(),mProduct.getCode());
		}
		
		
		return "redirect:/manage/products?operation=product";
	}

}
