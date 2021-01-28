package application;

import javafx.beans.property.SimpleStringProperty;

public class CartCars { // clasa care contine masinile din tabel
	private SimpleStringProperty rentCarName, rentCarColors;
	private int amount, totalPrice;
	
	public CartCars(int amount, String rentCarName, String rentCarColor, int totalPrice) {
		super();
		this.amount = amount;
		this.rentCarName = new SimpleStringProperty(rentCarName);
		this.rentCarColors = new SimpleStringProperty(rentCarColor);
		this.totalPrice = totalPrice;
	}

	public String getRentCarName() {
		return rentCarName.get();
	}

	public void setRentCarName(String rentCarName) {
		this.rentCarName = new SimpleStringProperty(rentCarName);
	}

	public String getRentCarColors() {
		return rentCarColors.get();
	}

	public void setRentCarColors(String rentCarColors) {
		this.rentCarColors = new SimpleStringProperty(rentCarColors);
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	

}
