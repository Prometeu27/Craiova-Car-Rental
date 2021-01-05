package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CarsViewController implements Initializable{
		
	@FXML private AnchorPane shopPane;
	@FXML private AnchorPane mainPane;
	@FXML private AnchorPane rentPane;
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

	
	public void btn1(ActionEvent event) throws IOException {
		Cars masina = new Cars("Toyota", "red", 2010, 95, 180, 4, 5);
		
	    FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(getClass().getResource("CarsView.fxml"));
	    Parent carsViewParent = loader.load();
	    Scene carsViewScene = new Scene(carsViewParent); 
	    CarsViewController controller = loader.getController();
	    controller.setData(masina); 
	    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
	    window.setScene(carsViewScene);
	    window.show();		

	}

	public void btn2(ActionEvent event) throws IOException {

			
	}
	public void btn3(ActionEvent event) throws IOException {
		
	}

	
	public void setData(Cars masina) throws IOException {
		carNameLabel.setText(masina.getCarName());
		productionYearLabel.setText(Integer.toString((masina.getProductionYear())));
		colorsLabel.setText(masina.getColors());
		HPLabel.setText(Integer.toString((masina.getHorsePower())));
		topSpeedLabel.setText(Integer.toString((masina.getTopSpeed())));
		doorsLabel.setText(Integer.toString((masina.getDoors())));
		seatsLabel.setText(Integer.toString((masina.getSeats())));
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
		AnchorPane offersPane = FXMLLoader.load(getClass().getResource("Offers.FXML"));
		shopPane.getChildren().setAll(offersPane);
	}

	@FXML
	private void loadTermsShop(ActionEvent event) throws Exception {
		AnchorPane termsPane = FXMLLoader.load(getClass().getResource("Terms.FXML"));
		shopPane.getChildren().setAll(termsPane);
	}

}
