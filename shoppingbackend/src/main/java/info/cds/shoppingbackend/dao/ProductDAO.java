package info.cds.shoppingbackend.dao;

import java.util.List;

import info.cds.shoppingbackend.dto.Product;

public interface ProductDAO {
	
	public Product get(int id);
	public List<Product> list();
	public boolean add(Product product);
	public boolean update(Product product);
	public boolean delete(Product product);

	
	//Business methods
	public List<Product> listActiveProducts();
	public List<Product> listActiveProductsByCategory(int categoryId);
	public List<Product> getLatestActiveProducts(int count);
}
