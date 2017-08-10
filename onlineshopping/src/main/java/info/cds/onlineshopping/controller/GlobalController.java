package info.cds.onlineshopping.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import info.cds.onlineshopping.model.UserModel;
import info.cds.shoppingbackend.dao.UserDAO;
import info.cds.shoppingbackend.dto.User;

@ControllerAdvice
public class GlobalController {
		
	@Autowired
	private HttpSession session;
	
	@Autowired
	private UserDAO userDAO;
	
	private UserModel userModel=null;
	
	@ModelAttribute("userModel")
	public UserModel getUserModel(){
		
		if(session.getAttribute("userModel")==null){
			//add the user model 
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			User user = userDAO.getByEmail(authentication.getName());
			if(user != null){
				//create a new UserModel object  to pass the user details
				userModel = new UserModel();
				userModel.setId(user.getId());
				userModel.setEmail(user.getEmail());
				userModel.setFullName(user.getFirstName()+ " "+user.getLastName());
				userModel.setRole(user.getRole());
				
				
				if(user.getRole().equals("USER")){
					//set the cart if the user is only a buyer
					userModel.setCart(user.getCart());
					System.err.println("Hello user grandTotal ::"+user.getCart().getGrandTotal());
				}
				//set the user model in the session
				session.setAttribute("userModel", userModel);
				return userModel;
			}
			
		}
		
		return (UserModel)session.getAttribute("userModel");
	}
	
	
	
	
	
	
	
	
}
