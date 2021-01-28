package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.layout.AnchorPane;

import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;

public class CartController implements Initializable {

	private static int[] carExistent = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }; // variabila aceasta verifica in acelasi timp daca una din masini a fost inchiriata, pentru a o putea afisa in cart, precum reprezinta si numarul de masini de un tip inchiriate
	private static int[] carPrice = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }; // pretul fiecarei masini afisate in cart
	private static int totalPrice = 0; // pretul total al cartului
	private int toyota = 0, renaultClio = 1, opelCorsa = 2, opelAstra = 3, nissanDuke = 4, nissanQashqai = 5,
			opelInsignia = 6, opelMokka = 7, opelVivaro = 8, mazdaSedan = 9, VWJetta = 10, skodaOctavia = 11;
	private String[] rentedCarsName = { "Toyota Aygo", "Renault Clio", "Opel Corsa", "Opel Astra", "Nissan Duke",
			"Nissan Qashqai", "Opel Insignia", "Opel Mokka", "Opel Vivaro", "Mazda Sedan", "VW Jetta",
			"Skoda Octavia" };
	private String[] rentedCarsColor = { "White", "White", "Blue", "White", "Cream", "White", "White", "White", "Blue",
			"Red", "Black", "Red" };

	@FXML private AnchorPane cartPane;
    @FXML private TableView<CartCars> cartTableView;
    @FXML private TableColumn<CartCars, Integer> amountColumn;
    @FXML private TableColumn<CartCars, String> carNameColumn;
    @FXML private TableColumn<CartCars, String> carColorColumn;
    @FXML private TableColumn<CartCars, Integer> daysRentedColumn;
    @FXML private TableColumn<CartCars, Integer> totalPriceColumn;    
    @FXML private Label totalPriceLabel;
	
	public void addRentCar(Label label, int price) { // trimiterea unei masini in cart, dupa apasarea butonului de "Rent"
		String str = label.getText();

		switch (str) {
		case "Toyota Aygo":
			carExistent[toyota]++; // cresterea numarului de masini cumparate - daca nu exista niciuna, masina respectiva nu e afisata in cart
			carPrice[toyota] += price; // pretul total al masinilor de acelasi tip inchiriate
			break;

		case "Renault Clio":
			carExistent[renaultClio]++;
			carPrice[renaultClio] += price;
			break;

		case "Opel Corsa":
			carExistent[opelCorsa]++;
			carPrice[opelCorsa] += price;
			break;

		case "Opel Astra":
			carExistent[opelAstra]++;
			carPrice[opelAstra] += price;
			break;

		case "Nissan Duke":
			carExistent[nissanDuke]++;
			carPrice[nissanDuke] += price;
			break;

		case "Nissan Qashqai":
			carExistent[nissanQashqai]++;
			carPrice[nissanQashqai] += price;
			break;

		case "Opel Insignia":
			carExistent[opelInsignia]++;
			carPrice[opelInsignia] += price;
			break;

		case "Opel Mokka":
			carExistent[opelMokka]++;
			carPrice[opelMokka] += price;
			break;

		case "Opel Vivaro":
			carExistent[opelVivaro]++;
			carPrice[opelVivaro] += price;
			break;

		case "Mazda Sedan":
			carExistent[mazdaSedan]++;
			carPrice[mazdaSedan] += price;
			break;

		case "VW Jetta":
			carExistent[VWJetta]++;
			carPrice[VWJetta] += price;
			break;

		case "Skoda Octavia":
			carExistent[skodaOctavia]++;
			carPrice[skodaOctavia] += price;
			break;
		}
		totalPrice += price;
		totalPriceLabel.setText(Integer.toString(totalPrice) + " €");
		cartTableView.setItems(getCarList()); // actualizarea masinilor din tabel

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) { // crearea tabelului
		cartTableView.setPlaceholder(new Label("Your shopping cart is empty!"));
		amountColumn.setCellValueFactory(new PropertyValueFactory<CartCars, Integer>("amount"));
		carNameColumn.setCellValueFactory(new PropertyValueFactory<CartCars, String>("rentCarName"));
		carColorColumn.setCellValueFactory(new PropertyValueFactory<CartCars, String>("rentCarColors"));
		totalPriceColumn.setCellValueFactory(new PropertyValueFactory<CartCars, Integer>("totalPrice"));
		cartTableView.setItems(getCarList());
	}

	ObservableList<CartCars> getCarList() { // introducerea masinilor inchiriate in tabel
		ObservableList<CartCars> rentedCars = FXCollections.observableArrayList();
		for (int i = 0; i < 12; i++) {
			if (carExistent[i] != 0)
				rentedCars.add(new CartCars(carExistent[i], rentedCarsName[i], rentedCarsColor[i], carPrice[i]));
		}
		return rentedCars;
	}
	
	//butoanele din header-ul paginii "Cart"
	
	// cart
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
