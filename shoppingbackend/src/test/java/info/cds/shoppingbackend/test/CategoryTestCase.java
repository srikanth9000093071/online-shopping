package info.cds.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import info.cds.shoppingbackend.dao.CategoryDAO;
import info.cds.shoppingbackend.dto.Category;

public class CategoryTestCase {
	
	private static AnnotationConfigApplicationContext context;
	
	private static CategoryDAO categoryDAO;
	
	private Category category;
	
	@BeforeClass
	public static void init(){
		context = new AnnotationConfigApplicationContext();
		context.scan("info.cds.shoppingbackend");
		context.refresh();
		
		categoryDAO= (CategoryDAO)context.getBean("categoryDAO");
	}
	
	@Test
	public void testAddCategory(){
		category = new Category();
		
		category.setName("Television");
		category.setDescription("This is some description for the television");
		category.setImageURL("CAT_1.png");
		
		assertEquals("succeesfully added the category inside the table", true,categoryDAO.add(category));
		
	}

}
