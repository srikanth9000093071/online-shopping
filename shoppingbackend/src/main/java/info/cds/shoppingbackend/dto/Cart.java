package info.cds.shoppingbackend.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	//***************
		@OneToOne
		@JoinColumn(name="uid")
		private User user;
	//*********************
	@Column(name = "grand_total")	
	private double grandTotal;
	
	@Column(name = "cart_lines")
	private int cartLines;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public double getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(double grandTotal) {
		this.grandTotal = grandTotal;
	}

	public int getCartLines() {
		return cartLines;
	}

	public void setCartLines(int cartLines) {
		this.cartLines = cartLines;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	

	/*
	 * toString for logging and debugging activity
	 * */
	@Override
	public String toString() {
		return "Cart [id=" + id + ", user=" + user + ", grandTotal=" + grandTotal + ", cartLines=" + cartLines + "]";
	}
	
	
	
	
	
	

}