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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CarsViewController implements Initializable {

	// STOCK
	static private int[] carsStock = { 2, 2, 1, 3, 3, 2, 1, 1, 2, 3, 1, 2 }; // stock-ul masinilor, apare in detaliile fiecareia - dinamic
	private int[] baseStock = carsStock; // stock-ul de "baza" - static - pentru a putea determina cate masini au fost cumparate

	// Cars ID's
	private int toyota = 0, renaultClio = 1, opelCorsa = 2, opelAstra = 3, nissanDuke = 4, nissanQashqai = 5,
			opelInsignia = 6, opelMokka = 7, opelVivaro = 8, mazdaSedan = 9, VWJetta = 10, skodaOctavia = 11; // id-uri pentru fiecare masina pentru usurarea citirii si interpretarii codului in array-uri

	// Others
	static private int reducedPrice;

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
	@FXML private ImageView rentPhoto;
	@FXML private Button priceCheckButton;
	@FXML private TextField daysToRentTextField;
	@FXML private Label daysRentedLabel;
	@FXML private Label totalRentPriceLabel;
	@FXML private Label rentPriceADay;
	@FXML private AnchorPane rentErrorShape;
	@FXML private Button rentRentButton;
	@FXML private Button rentViewDiscountsButton;
	@FXML private Button rentGoBackButton;
	@FXML private Label rentStockStatus;
	@FXML private Label stockLabel;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}

	public void goBack(ActionEvent event) throws IOException {  // buton de intoarcere din meniul cu detalii al fiecarei masini

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("Shop.fxml"));
		Parent carsViewParent = loader.load();
		Scene carsViewScene = new Scene(carsViewParent, 900, 651);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(carsViewScene);
		window.show();

	}

	public void rentButton(ActionEvent event) throws IOException { // butonul de "rent"

		if (checkValidity(daysToRentTextField)) {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("Cart.fxml"));
			Parent carsViewParent = loader.load();
			Scene carsViewScene = new Scene(carsViewParent, 900, 651);
			CartController controller = loader.getController();
			refreshStock(carNameLabel.getText());

			controller.addRentCar(carNameLabel, reducePrice(daysToRentTextField, rentPriceADay));
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			window.setScene(carsViewScene);
			window.show();

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.initStyle(StageStyle.UTILITY);
			alert.setTitle("Car rented!");
			alert.setHeaderText(null);
			alert.setContentText("You have rented a " + carNameLabel.getText() + " come at our shop to pick it up!");
			alert.showAndWait();
		} else
			rentErrorShape.setStyle("-fx-opacity:1");
	}

	public boolean checkValidity(TextField input) { // verifica validitatea zilelor introduse in "daysToRentTextField"
		int index;
		if (input.getText().matches("[0-9]+") == true) {

			index = Integer.parseInt(input.getText());

			if (index > 0 && index <= 30)
				return true;
			else
				return false;
		} else
			return false;
	}

	public int reducePrice(TextField daysToRent, Label priceADay) { // metoda pentru reducerea pretului, in functie de numarul de zile
		int days = Integer.parseInt(daysToRent.getText());
		int price = Integer.parseInt(priceADay.getText().replaceAll("[^0-9]", ""));
		int newPrice = price * days;

		if (days > 0 && days <= 30) {
			if (days >= 5 && days < 14)
				newPrice = (int) (newPrice * 0.9);
			if (days >= 14 && days < 21)
				newPrice = (int) (newPrice * 0.8);
			if (days >= 21 && days <= 30)
				newPrice = (int) (newPrice * 0.7);
		}
		return newPrice;
	}

	public void checkPrice(ActionEvent event) { // butonul care verifica pretul si il afiseaza pe loc, inainte de inchirierea propriu-zisa

		if (checkValidity(daysToRentTextField) == true) {
			int days = Integer.parseInt(daysToRentTextField.getText());
			daysRentedLabel.setText(Integer.toString(days));
			int newPrice = reducePrice(daysToRentTextField, rentPriceADay);
			reducedPrice = newPrice;
			totalRentPriceLabel.setText(Integer.toString(newPrice) + " € with discounts included");
			rentErrorShape.setStyle("-fx-opacity:0");
		} else
			rentErrorShape.setStyle("-fx-opacity:1");

	}

	public int refreshStock(String stock) { // metoda pentru reimprospatarea stock-ului dupa inchirierea unei masini
		int boughtCarAmount = 0;
		switch (stock) {
		case "Toyota Aygo":
			carsStock[toyota]--;
			boughtCarAmount = baseStock[toyota] - carsStock[toyota];
			break;
		case "Renault Clio":
			carsStock[renaultClio]--;
			boughtCarAmount = baseStock[renaultClio] - carsStock[renaultClio];
			break;
		case "Opel Corsa":
			carsStock[opelCorsa]--;
			boughtCarAmount = baseStock[opelCorsa] - carsStock[opelCorsa];
			break;
		case "Opel Astra":
			carsStock[opelAstra]--;
			boughtCarAmount = baseStock[opelAstra] - carsStock[opelAstra];
			break;
		case "Nissan Duke":
			carsStock[nissanDuke]--;
			boughtCarAmount = baseStock[nissanDuke] - carsStock[nissanDuke];
			break;
		case "Nissan Qashqai":
			carsStock[nissanQashqai]--;
			boughtCarAmount = baseStock[nissanQashqai] - carsStock[nissanQashqai];
			break;
		case "Opel Insignia":
			carsStock[opelInsignia]--;
			boughtCarAmount = baseStock[opelInsignia] - carsStock[opelInsignia];
			break;
		case "Opel Mokka":
			carsStock[opelMokka]--;
			boughtCarAmount = baseStock[opelMokka] - carsStock[opelMokka];
			break;
		case "Opel Vivaro":
			carsStock[opelVivaro]--;
			boughtCarAmount = baseStock[opelVivaro] - carsStock[opelVivaro];
			break;
		case "Mazda Sedan":
			carsStock[mazdaSedan]--;
			boughtCarAmount = baseStock[mazdaSedan] - carsStock[mazdaSedan];
			break;
		case "VW Jetta":
			carsStock[VWJetta]--;
			boughtCarAmount = baseStock[VWJetta] - carsStock[VWJetta];
			break;
		case "Skoda Octavia":
			carsStock[skodaOctavia]--;
			boughtCarAmount = baseStock[skodaOctavia] - carsStock[skodaOctavia];
			break;

		}
		return boughtCarAmount;
	}

	// butoanele din shop care duc la meniul detaliat al masinilor
	
	public void btn1(ActionEvent event) throws IOException { 
		Cars masina = new Cars("Toyota Aygo", "Red", 2018, 95, 170, 4, 5, new Image("/application/images/toyotaaygo.png"), 50,
				0);
		masina.setStock(carsStock[toyota]);

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("CarsView.fxml"));
		Parent carsViewParent = loader.load();
		Scene carsViewScene = new Scene(carsViewParent);
		CarsViewController controller = loader.getController();
		controller.setData(masina, carsStock[toyota]);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(carsViewScene);
		window.show();
	}

	public void btn2(ActionEvent event) throws IOException {
		Cars masina = new Cars("Renault Clio", "White", 2020, 110, 180, 4, 5, new Image("/application/images/renaultClio.png"),
				60, 0);
		masina.setStock(carsStock[renaultClio]);

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("CarsView.fxml"));
		Parent carsViewParent = loader.load();
		Scene carsViewScene = new Scene(carsViewParent);
		CarsViewController controller = loader.getController();
		controller.setData(masina, carsStock[renaultClio]);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(carsViewScene);
		window.show();
	}

	public void btn3(ActionEvent event) throws IOException {
		Cars masina = new Cars("Opel Corsa", "Blue", 2019, 94, 170, 4, 5, new Image("/application/images/opelCorsa.png"), 63,
				0);
		masina.setStock(carsStock[2]);

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("CarsView.fxml"));
		Parent carsViewParent = loader.load();
		Scene carsViewScene = new Scene(carsViewParent);
		CarsViewController controller = loader.getController();
		controller.setData(masina, carsStock[2]);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(carsViewScene);
		window.show();
	}

	public void btn4(ActionEvent event) throws IOException {
		Cars masina = new Cars("Opel Astra", "Alb", 2017, 101, 180, 4, 5, new Image("/application/images/opelAstra.png"), 60,
				0);
		masina.setStock(carsStock[opelAstra]);

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("CarsView.fxml"));
		Parent carsViewParent = loader.load();
		Scene carsViewScene = new Scene(carsViewParent);
		CarsViewController controller = loader.getController();
		controller.setData(masina, carsStock[opelAstra]);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(carsViewScene);
		window.show();
	}

	public void btn5(ActionEvent event) throws IOException {
		Cars masina = new Cars("Nissan Duke", "Alb", 2019, 135, 210, 4, 5, new Image("/application/images/nissanDuke.png"), 60,
				0);
		masina.setStock(carsStock[nissanDuke]);

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("CarsView.fxml"));
		Parent carsViewParent = loader.load();
		Scene carsViewScene = new Scene(carsViewParent);
		CarsViewController controller = loader.getController();
		controller.setData(masina, carsStock[nissanDuke]);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(carsViewScene);
		window.show();
	}

	public void btn6(ActionEvent event) throws IOException {
		Cars masina = new Cars("Nissan Qashqai", "Alb", 2016, 143, 220, 4, 5, new Image("/application/images/nissanQashqai.png"), 60, 0);
		masina.setStock(carsStock[nissanQashqai]);

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("CarsView.fxml"));
		Parent carsViewParent = loader.load();
		Scene carsViewScene = new Scene(carsViewParent);
		CarsViewController controller = loader.getController();
		controller.setData(masina, carsStock[nissanQashqai]);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(carsViewScene);
		window.show();
	}

	public void btn7(ActionEvent event) throws IOException {
		Cars masina = new Cars("Opel Insignia", "Alb", 2018, 117, 190, 4, 5, new Image("/application/images/opelInsignia.png"),
				60, 0);
		masina.setStock(carsStock[opelInsignia]);

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("CarsView.fxml"));
		Parent carsViewParent = loader.load();
		Scene carsViewScene = new Scene(carsViewParent);
		CarsViewController controller = loader.getController();
		controller.setData(masina, carsStock[opelInsignia]);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(carsViewScene);
		window.show();
	}

	public void btn8(ActionEvent event) throws IOException {
		Cars masina = new Cars("Opel Mokka", "Alb", 2018, 112, 190, 4, 5, new Image("/application/images/opelMokka.png"), 60,
				0);
		masina.setStock(carsStock[opelMokka]);

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("CarsView.fxml"));
		Parent carsViewParent = loader.load();
		Scene carsViewScene = new Scene(carsViewParent);
		CarsViewController controller = loader.getController();
		controller.setData(masina, carsStock[opelMokka]);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(carsViewScene);
		window.show();
	}

	public void btn9(ActionEvent event) throws IOException {
		Cars masina = new Cars("Opel Vivaro", "Alb", 2017, 109, 190, 5, 9, new Image("/application/images/opelVivaro.png"), 60,
				0);
		masina.setStock(carsStock[opelVivaro]);

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("CarsView.fxml"));
		Parent carsViewParent = loader.load();
		Scene carsViewScene = new Scene(carsViewParent);
		CarsViewController controller = loader.getController();
		controller.setData(masina, carsStock[opelVivaro]);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(carsViewScene);
		window.show();
	}

	public void btn10(ActionEvent event) throws IOException {
		Cars masina = new Cars("Mazda Sedan", "Alb", 2016, 103, 190, 4, 5, new Image("/application/images/mazdaSedan.png"), 60,
				0);
		masina.setStock(carsStock[mazdaSedan]);

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("CarsView.fxml"));
		Parent carsViewParent = loader.load();
		Scene carsViewScene = new Scene(carsViewParent);
		CarsViewController controller = loader.getController();
		controller.setData(masina, carsStock[mazdaSedan]);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(carsViewScene);
		window.show();
	}

	public void btn11(ActionEvent event) throws IOException {
		Cars masina = new Cars("VW Jetta", "Alb", 2017, 99, 180, 4, 5, new Image("/application/images/VWJetta.png"), 60, 0);
		masina.setStock(carsStock[VWJetta]);

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("CarsView.fxml"));
		Parent carsViewParent = loader.load();
		Scene carsViewScene = new Scene(carsViewParent);
		CarsViewController controller = loader.getController();
		controller.setData(masina, carsStock[VWJetta]);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(carsViewScene);
		window.show();
	}

	public void btn12(ActionEvent event) throws IOException {
		Cars masina = new Cars("Skoda Octavia", "Alb", 2017, 105, 240, 4, 5, new Image("/application/images/skodaOctavia.png"),
				60, 0);
		masina.setStock(carsStock[skodaOctavia]);

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("CarsView.fxml"));
		Parent carsViewParent = loader.load();
		Scene carsViewScene = new Scene(carsViewParent);
		CarsViewController controller = loader.getController();
		controller.setData(masina, carsStock[skodaOctavia]);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(carsViewScene);
		window.show();
	}

	public void setData(Cars masina, int stock) throws IOException { // setarea datelor pentru fiecare masina 
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
		if (stock < 1) {
			rentRentButton.setVisible(false);
			rentStockStatus.setVisible(true);
			rentStockStatus.setText("OUT OF STOCK!");
		}

	}

	//butoanele din header, pentru shop
	// shop
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
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
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

}
