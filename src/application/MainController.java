package application;


import java.io.IOException;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class MainController implements Initializable{
	

	
	@FXML
	private Label textField;
	
	@FXML
	private TextField userField;
	
	@FXML
	private TextField passField; 
	
	@FXML
	private Button loginButton;

	@FXML 
	private AnchorPane mainPane;
	
	@FXML
	private AnchorPane shopPane;
	
	@FXML
	private AnchorPane offersPane;
	
	@FXML
	private AnchorPane termsPane;

	@FXML
	private Label carNameLabel;
	@FXML
	private Label productionYearLabel;
	@FXML
	private Label HPLabel;
	@FXML
	private Label topSpeedLabel;
	@FXML
	private Label doorsLabel;
	@FXML
	private Label seatsLabel;
	@FXML
	private Label colorsLabel;




	
	@Override
	public void initialize(java.net.URL arg0, ResourceBundle arg1) {
	}
	
	public void Login(ActionEvent event) throws Exception {

		
		if(userField.getText().equals("user") && passField.getText().equals("pass")) {
			
			Parent root = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
			
			Stage window = (Stage) loginButton.getScene().getWindow();
			window.setScene(new Scene(root));
		}
	}


	// main 
	@FXML
	private void loadShopMain(ActionEvent event) throws Exception {
		AnchorPane shopPane = FXMLLoader.load(getClass().getResource("Shop.FXML"));
		mainPane.getChildren().setAll(shopPane);
	}
	
	@FXML
	private void loadOffersMain(ActionEvent event) throws Exception {
		AnchorPane offersPane = FXMLLoader.load(getClass().getResource("Offers.FXML"));
		mainPane.getChildren().setAll(offersPane);
	}
	
	@FXML
	private void loadTermsMain(ActionEvent event) throws Exception {
		AnchorPane termsPane = FXMLLoader.load(getClass().getResource("Terms.FXML"));
		mainPane.getChildren().setAll(termsPane);
	}
	
	@FXML
	private void loadCartMain(ActionEvent event) throws Exception {
		AnchorPane cartPane = FXMLLoader.load(getClass().getResource("Cart.FXML"));
		mainPane.getChildren().setAll(cartPane);
	}

	
	//offers
	@FXML
	private void loadMainOffers(ActionEvent event) throws Exception {
		AnchorPane mainPane = FXMLLoader.load(getClass().getResource("Main.FXML"));
		offersPane.getChildren().setAll(mainPane);
	}
	
	@FXML
	private void loadShopOffers(ActionEvent event) throws Exception {
		AnchorPane shopPane = FXMLLoader.load(getClass().getResource("Shop.FXML"));
		offersPane.getChildren().setAll(shopPane);
	}
	
	@FXML
	private void loadTermsOffers(ActionEvent event) throws Exception {
		AnchorPane termsPane = FXMLLoader.load(getClass().getResource("Terms.FXML"));
		offersPane.getChildren().setAll(termsPane);
	}
	
	@FXML
	private void loadCartOffers(ActionEvent event) throws Exception {
		AnchorPane cartPane = FXMLLoader.load(getClass().getResource("Cart.FXML"));
		offersPane.getChildren().setAll(cartPane);
	}
	
	//terms
	@FXML
	private void loadMainTerms(ActionEvent event) throws Exception {
		AnchorPane mainPane = FXMLLoader.load(getClass().getResource("Main.FXML"));
		termsPane.getChildren().setAll(mainPane);
	}
	
	@FXML
	private void loadShopTerms(ActionEvent event) throws Exception {
		AnchorPane shopPane = FXMLLoader.load(getClass().getResource("Shop.FXML"));
		termsPane.getChildren().setAll(shopPane);
	}
	
	@FXML
	private void loadOffersTerms(ActionEvent event) throws Exception {
		AnchorPane offersPane = FXMLLoader.load(getClass().getResource("Offers.FXML"));
		termsPane.getChildren().setAll(offersPane);
	}
	@FXML
	private void loadCartTerms(ActionEvent event) throws Exception {
		AnchorPane cartPane = FXMLLoader.load(getClass().getResource("Cart.FXML"));
		termsPane.getChildren().setAll(cartPane);
	}
}
