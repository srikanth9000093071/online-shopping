package info.cds.shoppingbackend.dao;

import java.util.List;

import info.cds.shoppingbackend.dto.Category;

public interface CategoryDAO {
	
	public List<Category> list();

	public Category get(int id);

}
