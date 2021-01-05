package application;

import javafx.beans.property.SimpleStringProperty;

public class Cars {

	private SimpleStringProperty carName, colors;
	private int productionYear, horsePower, topSpeed, doors, seats;
	
	public Cars(String carName, String colors, int productionYear, int horsePower,
			int topSpeed, int doors, int seats) {
		this.carName = new SimpleStringProperty(carName);
		this.colors = new SimpleStringProperty(colors);
		this.productionYear = productionYear;
		this.horsePower = horsePower;
		this.topSpeed = topSpeed;
		this.doors = doors;
		this.seats = seats;
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
