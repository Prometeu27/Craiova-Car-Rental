package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CarsViewController implements Initializable{
		
	// STOCK

	static private int toyotaStock = 3;
	private int baseToyotaStock = toyotaStock;
	static private int mazdaStock = 1;
	private int baseMazdaStock = mazdaStock;
	
	@FXML private AnchorPane shopPane;
	@FXML private AnchorPane mainPane;
	@FXML private AnchorPane rentPane;
	@FXML private AnchorPane cartPane;
	@FXML private GridPane gridPane;
	@FXML private Label carNameLabel;
	@FXML private Label productionYearLabel;
	@FXML private Label HPLabel;
	@FXML private Label topSpeedLabel;
	@FXML private Label doorsLabel;
	@FXML private Label seatsLabel;
	@FXML private Label colorsLabel;
	@FXML private Label successLabel;
	@FXML private Label successLabel1;
	@FXML private Label successLabel2;
	@FXML private ImageView rentPhoto;
    @FXML private Button priceCheckButton;
    @FXML private TextField priceTextField;
    @FXML private Label daysRentedLabel;
    @FXML private Label totalRentPriceLabel;
    @FXML private Label rentPriceADay;
    @FXML private AnchorPane rentErrorShape;    
    @FXML private Button rentRentButton;
    @FXML private Button rentViewDiscountsButton;
    @FXML private Button rentGoBackButton;
    @FXML private Label rentStockStatus;
    @FXML private Label stockLabel;
    @FXML private TableView<Cars> cartTableView;
    @FXML private TableColumn<Cars, Integer> amountColumn;
    @FXML private TableColumn<Cars, String> carNameColumn;
    @FXML private TableColumn<Cars, String> carColorColumn;
    @FXML private TableColumn<Cars, Integer> daysRentedColumn;
    @FXML private TableColumn<Cars, Integer> totalPriceColumn;
    
	
    public void goBack(ActionEvent event) throws IOException {

	    FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(getClass().getResource("Shop.fxml"));
	    Parent carsViewParent = loader.load();
	    Scene carsViewScene = new Scene(carsViewParent, 900, 651); 
	    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
	    window.setScene(carsViewScene);
	    window.show();	     
		
    }
    
    public void rentButton(ActionEvent event) throws IOException {
    	
    	if(checkValidity(priceTextField)) {
	    FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(getClass().getResource("Shop.fxml"));
	    Parent carsViewParent = loader.load();
	    Scene carsViewScene = new Scene(carsViewParent, 900, 651); 
	    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
	    window.setScene(carsViewScene);
	    window.show();	  
	    
	    refreshStock(carNameLabel.getText());
	    Alert alert = new Alert(AlertType.INFORMATION);
	    alert.initStyle(StageStyle.UTILITY);
	    alert.setTitle("Car rented!");
	    alert.setHeaderText(null);
	    alert.setContentText("You have rented a " + carNameLabel.getText() + " come at our shop to pick it up!");

	    alert.showAndWait();
    	}
    	else rentErrorShape.setStyle("-fx-opacity:1");
    		
    }
    
    public boolean checkValidity(TextField input) {
    	int index;
    	if(input.getText().matches("[0-9]+") == true) {
    		
    		index = Integer.parseInt(input.getText());
    		
    		if(index > 0 && index <= 30) return true;
    			else return false;
    	} else return false;
    }
	public void checkPrice(ActionEvent event) {
		
		if(checkValidity(priceTextField) == true){
			int days = Integer.parseInt(priceTextField.getText());
			
			if(days > 0 && days <= 30) {
				if(days >= 5 && days < 14) days = (int) (days * 0.9);
				if(days >= 14 && days < 21) days = (int) (days * 0.8);
				if(days >= 21 && days <= 30) days = (int) (days * 0.7);
				
			int price = Integer.parseInt(rentPriceADay.getText().replaceAll("[^0-9]", ""));
			
			totalRentPriceLabel.setText(Integer.toString(price * days) + " € with discounts included");
			rentErrorShape.setStyle("-fx-opacity:0");
			daysRentedLabel.setText(Integer.toString(days));
			}
			else rentErrorShape.setStyle("-fx-opacity:1");
		}
		else {
			rentErrorShape.setStyle("-fx-opacity:1");
		}
	}
	
	public void refreshStock(String stock) {
		switch(stock) {
		case "Toyota":
			toyotaStock--;
			break;
		case "Mazda":
			mazdaStock--;
			break;
		}
	}
	
	public void btn1(ActionEvent event) throws IOException {
		Cars toyota = new Cars("Toyota", "red", 2010, 95, 180, 4, 5, new Image("/application/toyotaaygo.png"), 50, 0, 0);
		toyota.setStock(toyotaStock);
	    FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(getClass().getResource("CarsView.fxml"));
	    Parent carsViewParent = loader.load();
	    Scene carsViewScene = new Scene(carsViewParent); 
	    CarsViewController controller = loader.getController();
	    controller.setData(toyota, toyotaStock);
	    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
	    window.setScene(carsViewScene);
	    window.show();	      
	}

	public void btn2(ActionEvent event) throws IOException {
		Cars mazda = new Cars("Mazda", "green", 2020, 100, 210, 4, 5, new Image("/application/mazda.png"), 60, 0, 0);
		mazda.setStock(mazdaStock);
		
	    FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(getClass().getResource("CarsView.fxml"));
	    Parent carsViewParent = loader.load();
	    Scene carsViewScene = new Scene(carsViewParent); 
	    CarsViewController controller = loader.getController();
	    controller.setData(mazda, mazdaStock);
	    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
	    window.setScene(carsViewScene);
	    window.show();	 
	}
	
	public void btn3(ActionEvent event) throws IOException {
		
	}


	public void setData(Cars masina, int stock) throws IOException {
		carNameLabel.setText(masina.getCarName());
		productionYearLabel.setText(Integer.toString((masina.getProductionYear())));
		colorsLabel.setText(masina.getColors());
		HPLabel.setText(Integer.toString((masina.getHorsePower())));
		topSpeedLabel.setText(Integer.toString((masina.getTopSpeed())));
		doorsLabel.setText(Integer.toString((masina.getDoors())));
		seatsLabel.setText(Integer.toString((masina.getSeats())));
		rentPhoto.setImage(masina.getImage());
		rentPriceADay.setText(Integer.toString((masina.getPrice())) + " € a day");
		stockLabel.setText(Integer.toString((masina.getStock())));

		rentStockStatus.setVisible(false);
		if(stock < 1) { 
			rentRentButton.setVisible(false);
			rentStockStatus.setVisible(true);
			rentStockStatus.setText("OUT OF STOCK!");
		}
		
		}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}
	
	//shop
	@FXML
	private void loadMainShop(ActionEvent event) throws Exception {
		AnchorPane mainPane = FXMLLoader.load(getClass().getResource("Main.FXML"));
		shopPane.getChildren().setAll(mainPane);
	}

	@FXML
	private void loadOffersShop(ActionEvent event) throws Exception {
	    FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(getClass().getResource("Offers.fxml"));
	    Parent carsViewParent = loader.load();
	    Scene carsViewScene = new Scene(carsViewParent, 900, 651); 
	    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
	    window.setScene(carsViewScene);
	    window.show();	
	}

	@FXML
	private void loadTermsShop(ActionEvent event) throws Exception {
		AnchorPane termsPane = FXMLLoader.load(getClass().getResource("Terms.FXML"));
		shopPane.getChildren().setAll(termsPane);
	}
	
	@FXML
	private void loadCartShop(ActionEvent event) throws Exception {
		AnchorPane cartPane = FXMLLoader.load(getClass().getResource("Cart.FXML"));
		shopPane.getChildren().setAll(cartPane);
	}
	
	//cart
	@FXML
	private void loadMainCart(ActionEvent event) throws Exception {
		AnchorPane mainPane = FXMLLoader.load(getClass().getResource("Main.FXML"));
		cartPane.getChildren().setAll(mainPane);
	}
	
	@FXML
	private void loadShopCart(ActionEvent event) throws Exception {
		AnchorPane shopPane = FXMLLoader.load(getClass().getResource("Shop.FXML"));
		cartPane.getChildren().setAll(shopPane);
	}
	
	@FXML
	private void loadOffersCart(ActionEvent event) throws Exception {
		AnchorPane offersPane = FXMLLoader.load(getClass().getResource("Offers.FXML"));
		cartPane.getChildren().setAll(offersPane);
	}
	
	@FXML
	private void loadTermsCart(ActionEvent event) throws Exception {
		AnchorPane termsPane = FXMLLoader.load(getClass().getResource("Terms.FXML"));
		cartPane.getChildren().setAll(termsPane);
	}
	
}
