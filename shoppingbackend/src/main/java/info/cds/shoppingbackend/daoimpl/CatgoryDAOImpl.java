package info.cds.shoppingbackend.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import info.cds.shoppingbackend.dao.CategoryDAO;
import info.cds.shoppingbackend.dto.Category;

@Repository("categoryDAO")
public class CatgoryDAOImpl implements CategoryDAO {
	
	private static List<Category> categories= new ArrayList<Category>();
	
	static{
		Category category=new Category();
		//adding the first category 
		category.setId(1);
		category.setName("Television");
		category.setDescription("This is some description for the television");
		category.setImageURL("CAT_1.png");
		
		categories.add(category);
		
		//adding the second category 
		category=new Category();
		category.setId(2);
		category.setName("Mobile");
		category.setDescription("This is some description for the Mobile");
		category.setImageURL("CAT_2.png");
		
		categories.add(category);
		
		//adding the third category 
				category=new Category();
				category.setId(3);
				category.setName("Laptop");
				category.setDescription("This is some description for the Laptop");
				category.setImageURL("CAT_3.png");
				
				categories.add(category);
		
	}
	

	@Override
	public List<Category> list() {
		return categories;
	}
	
	@Override
	public Category get(int id) {
		//enhanced for loop 
		for (Category category : categories) {
			if(category.getId()==id) 
				return category;
		}
		return null;
		
	}

}
