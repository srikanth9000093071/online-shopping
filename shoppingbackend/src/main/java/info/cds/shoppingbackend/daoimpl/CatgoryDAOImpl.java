package info.cds.shoppingbackend.daoimpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import info.cds.shoppingbackend.dao.CategoryDAO;
import info.cds.shoppingbackend.dto.Category;

@Repository("categoryDAO")
@Transactional
public class CatgoryDAOImpl implements CategoryDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	/*private static List<Category> categories= new ArrayList<Category>();
	
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
		
	}*/
	

	@Override
	public List<Category> list() {
		String selectActiveCategory = "FROM Category WHERE active =:active";
		Query query =  sessionFactory.getCurrentSession().createQuery(selectActiveCategory);
		query.setParameter("active", true);
		return query.getResultList();
	}
	
	//Getting single category based on Id
	@Override
	public Category get(int id) {
		/*//enhanced for loop 
		for (Category category : categories) {
			if(category.getId()==id) 
				return category;
		}*/
		
		
		return sessionFactory.getCurrentSession().get(Category.class,Integer.valueOf(id));
		
	}
	
	@Override
	@Transactional
	public boolean add(Category category) {
		try{
			//add the category to the database table
			sessionFactory.getCurrentSession().persist(category);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	//Updating the single category
	@Override
	public boolean update(Category category) {
		try{
			//update the category to the database table
			sessionFactory.getCurrentSession().update(category);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	//dont ever delete record from DB just inactive the
	@Override
	public boolean delete(Category category) {
		category.setActive(false);
		try{
			//update the category to the database table
			sessionFactory.getCurrentSession().update(category);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
