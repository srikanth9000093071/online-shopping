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
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("info.cds.shoppingbackend");
		context.refresh();
		categoryDAO = (CategoryDAO)context.getBean("categoryDAO");
	}
	
	
	/*@Test
	public void testAddCategory() {
		
		category = new Category();
		
		category.setName("Laptop");
		category.setDescription("This is some description for laptop!");
		category.setImageURL("CAT_105.png");
		
		assertEquals("Successfully added a category inside the table!",true,categoryDAO.add(category));
		
		
	}*/
	
	/*@Test
	public void testGetCategory(){
		 category = categoryDAO.get(1);
		 assertEquals("successfully fetched the record based on the id","Laptop",category.getName());
		
	}*/
	
	/*@Test
	public void testUpdateCategory(){
		 category = categoryDAO.get(1);
		 category.setName("Tablet");
		 assertEquals("successfully updated the record based on the id",true,categoryDAO.update(category));
		
	}*/
	
	/*@Test
	public void testDeleteCategory(){
		 category = categoryDAO.get(1);
		 assertEquals("successfully deleted the record based on the id",true,categoryDAO.delete(category));
		
	}*/
	
	/*@Test
	public void testListCategory(){
		 assertEquals("successfully fetched list of the records",1,categoryDAO.list().size());
		
	}*/
	
	@Test
	public void testCRUDCategory(){
		category = new Category();
		
		//Add operation
		category.setName("Mouse");
		category.setDescription("This is some description for Mouse!");
		category.setImageURL("CAT_1.png");
		assertEquals("Successfully added a category inside the table!",true,categoryDAO.add(category));
		
		
		 Category cat2=new Category();
		 cat2.setName("Television");
		 cat2.setDescription("This is some description for Television!");
		 cat2.setImageURL("CAT_2.png");
		assertEquals("Successfully added a category inside the table!",true,categoryDAO.add(cat2));
		
		//Fetching and updating the category
		Category cat3=new Category();
		cat3 = categoryDAO.get(3);
		cat3.setName("Modified Telivision");
		 assertEquals("successfully updated the record based on the id",true,categoryDAO.update(cat3));
		
		// Deleting the category
		 Category cat4=new Category();
		 cat4 = categoryDAO.get(2);
		 assertEquals("successfully deleted the record based on the id",true,categoryDAO.delete(cat4));
		 
		 //getting the list category
		 assertEquals("successfully fetched list of the records",2,categoryDAO.list().size());
	
	}
	
	
}