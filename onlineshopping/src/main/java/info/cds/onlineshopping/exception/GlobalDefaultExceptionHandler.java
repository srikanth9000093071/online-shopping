package info.cds.onlineshopping.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public String handlerNoHandlerFoundException(Model model){
		model.addAttribute("errorTitle", "The Page is not constructed!");
		model.addAttribute("errorDescription", "The page you are looking for is not available now!");
		model.addAttribute("title", "404 Error Page");
		return "error";
	}
	
	@ExceptionHandler(ProductNotFoundException.class)
	public String handlerProductNotFoundException(Model model){
		model.addAttribute("errorTitle", "Product Not Available!");
		model.addAttribute("errorDescription", "The product you are looking for is not available right now!");
		model.addAttribute("title", "Product Unavailable");
		return "error";
	}
	
	@ExceptionHandler(Exception.class)
	public String handlerException(Exception ex,Model model){
		model.addAttribute("errorTitle", "Contact Your Administrator!!");
		
		 /**Only for Debugging purpose not in the production environment **/
		StringWriter sw = new StringWriter();
		PrintWriter pw= new PrintWriter(sw);
		ex.printStackTrace(pw);
				
		
		
		model.addAttribute("errorDescription", sw.toString());
		model.addAttribute("title", "Error");
		return "error";
	}

}
