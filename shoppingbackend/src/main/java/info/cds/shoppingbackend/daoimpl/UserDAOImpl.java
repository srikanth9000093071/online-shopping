package info.cds.shoppingbackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import info.cds.shoppingbackend.dao.UserDAO;
import info.cds.shoppingbackend.dto.Address;
import info.cds.shoppingbackend.dto.Cart;
import info.cds.shoppingbackend.dto.User;

@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean addUser(User user) {
		try{
			
			sessionFactory.getCurrentSession().persist(user);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean addAddress(Address address) {
		try{
			sessionFactory.getCurrentSession().persist(address);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateCart(Cart cart) {
		try{
			sessionFactory.getCurrentSession().update(cart);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public User getByEmail(String email) {
		String query = "FROM User WHERE email=:email";
		try{
		return  sessionFactory.getCurrentSession().createQuery(query,User.class)
								.setParameter("email",email)
									.getSingleResult();
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/*@Override
	public Address getBillingAddress(User user) {
		String query="FROM Address WHERE user=:user AND billing=:billing";
		try{
			return sessionFactory.getCurrentSession().createQuery(query,Address.class)
									.setParameter("user", user)
									.setParameter("billing", true)
										.getSingleResult();
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	
	@Override
	public List<Address> listShippingAddresses(User user) {
		String query="FROM Address WHERE user=:user AND shipping=:shipping";
		try{
			return sessionFactory.getCurrentSession().createQuery(query,Address.class)
									.setParameter("user", user)
									.setParameter("shipping", true)
										.getResultList();
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}*/
	
	
	@Override
	public Address getBillingAddress(int userId) {
		String selectQuery = "FROM Address WHERE userId = :userId AND billing = :billing";
		
		try {
			return sessionFactory.getCurrentSession()
						.createQuery(selectQuery, Address.class)
							.setParameter("userId", userId)
							.setParameter("billing", true)
							.getSingleResult();
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
		
	}
	
	
	
	

	@Override
	public List<Address> listShippingAddresses(int userId) {
		String selectQuery = "FROM Address WHERE userId = :userId AND shipping = :shipping";
		
		try {
			
			return sessionFactory.getCurrentSession()
						.createQuery(selectQuery, Address.class)
							.setParameter("userId", userId)
							.setParameter("shipping", true)
							.getResultList();
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
		
	}

	
	
	
	
	
}
