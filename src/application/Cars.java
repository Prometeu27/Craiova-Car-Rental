package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;

public class Cars { // clasa care contine masinile din 'shop'

	private SimpleStringProperty carName, colors;
	private int productionYear, horsePower, topSpeed, doors, seats, price, stock;
	private Image photo;
	
	public Cars(String carName, String colors, int productionYear, int horsePower,
			int topSpeed, int doors, int seats, int price, int stock) {
		this.carName = new SimpleStringProperty(carName);
		this.colors = new SimpleStringProperty(colors);
		this.productionYear = productionYear;
		this.horsePower = horsePower;
		this.topSpeed = topSpeed;
		this.doors = doors;
		this.seats = seats;
		photo = new Image("/application/logologin.png");
		this.price = price;
		this.stock = stock;
	}

	public Cars(String carName, String colors, int productionYear, int horsePower,
			int topSpeed, int doors, int seats, Image photo, int price, int stock) {
		this.carName = new SimpleStringProperty(carName);
		this.colors = new SimpleStringProperty(colors);
		this.productionYear = productionYear;
		this.horsePower = horsePower;
		this.topSpeed = topSpeed;
		this.doors = doors;
		this.seats = seats;
		this.photo = photo;
		this.price = price;
		this.stock = stock;
	}

	public Image getImage() {
		return photo;
	}

	public void setImage(Image newPhoto) {
		this.photo = newPhoto;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getCarName() {
		return carName.get();
	}

	public void setCarName(String carName) {
		this.carName = new SimpleStringProperty(carName);
	}

	public String getColors() {
		return colors.get();
	}

	public void setColors(String colors) {
		this.colors = new SimpleStringProperty(colors);
	}

	public int getProductionYear() {
		return productionYear;
	}

	public void setProductionYear(int productionYear) {
		this.productionYear = productionYear;
	}

	public int getHorsePower() {
		return horsePower;
	}

	public void setHorsePower(int horsePower) {
		this.horsePower = horsePower;
	}

	public int getTopSpeed() {
		return topSpeed;
	}

	public void setTopSpeed(int topSpeed) {
		this.topSpeed = topSpeed;
	}

	public int getDoors() {
		return doors;
	}

	public void setDoors(int doors) {
		this.doors = doors;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}
	
	
	

}
