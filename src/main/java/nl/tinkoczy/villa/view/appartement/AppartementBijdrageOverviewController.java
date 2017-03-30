package nl.tinkoczy.villa.view.appartement;

import java.io.IOException;
import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import nl.tinkoczy.villa.VillaApp;
import nl.tinkoczy.villa.config.ApplicationConfiguration;
import nl.tinkoczy.villa.config.ConfigFacade;
import nl.tinkoczy.villa.model.Appartement;
import nl.tinkoczy.villa.model.Bijdrage;
import nl.tinkoczy.villa.model.BijdrageSchema;
import nl.tinkoczy.villa.model.Boeking;
import nl.tinkoczy.villa.model.Post;
import nl.tinkoczy.villa.service.IAppartementService;
import nl.tinkoczy.villa.service.IBijdrageSchemaService;
import nl.tinkoczy.villa.service.IBijdrageService;
import nl.tinkoczy.villa.service.IBoekingService;
import nl.tinkoczy.villa.service.impl.AppartementService;
import nl.tinkoczy.villa.service.impl.BijdrageSchemaService;
import nl.tinkoczy.villa.service.impl.BijdrageService;
import nl.tinkoczy.villa.service.impl.BoekingService;
import nl.tinkoczy.villa.util.BijdrageSchemaStringConverter;
import nl.tinkoczy.villa.view.rubriekpost.PostEditDialogController;

public class AppartementBijdrageOverviewController {

	final static Logger logger = LoggerFactory.getLogger(AppartementBijdrageOverviewController.class);

	@FXML
	private Label beheerderNaamLabel;

	@FXML
	private TableView<Bijdrage> verschuldigdBijdrageTable;
	@FXML
	private TableColumn<Bijdrage, String> bijdrageDatumColumn;
	@FXML
	private TableColumn<Bijdrage, BigDecimal> bijdrageBedragColumn;

	@FXML
	private TableView<Boeking> betaaldBoekingTable;
	@FXML
	private TableColumn<Boeking, String> boekingDatumColumn;
	@FXML
	private TableColumn<Boeking, BigDecimal> boekingBedragColumn;

	@FXML
	private TextField beginSaldoField;

	@FXML
	private Label betaaldLabel;
	@FXML
	private Label subTotaalLabel;
	@FXML
	private Label verschuldigdLabel;
	@FXML
	private Label totaalLabel;

	@FXML
	private ComboBox<BijdrageSchema> bijdrageSchemaNaamComboBox;

	// Reference to the selected Appartement.
	private Appartement selectedAppartement;

	private IAppartementService appartementService;
	private IBoekingService boekingService;
	private IBijdrageService bijdrageService;
	private IBijdrageSchemaService bijdrageSchemaService;

	private ObservableList<Bijdrage> oListVerschuldigdBijdrage = FXCollections.observableArrayList();
	private ObservableList<Boeking> oListBetaaldBoeking = FXCollections.observableArrayList();
	private ObservableList<BijdrageSchema> bijdrageSchemas = FXCollections.observableArrayList();

	// Reference to the main application.
	private VillaApp villaApp;

	public AppartementBijdrageOverviewController() {

	}

	@FXML
	private void initialize() {
		logger.debug("Initialize AppartementBijdrageOverviewController");
		appartementService = new AppartementService();
		boekingService = new BoekingService();
		bijdrageService = new BijdrageService();
		bijdrageSchemaService = new BijdrageSchemaService();
		bijdrageSchemas = FXCollections.observableArrayList(bijdrageSchemaService.getAllBijdrageSchemas());

		// Initialize the tables
		bijdrageDatumColumn.setCellValueFactory(cellData -> cellData.getValue().bijdrageDatumAsStringProperty());
		bijdrageBedragColumn.setCellValueFactory(cellData -> cellData.getValue().bijdrageBedragProperty());

		boekingDatumColumn.setCellValueFactory(cellData -> cellData.getValue().boekingDatumAsStringProperty());
		boekingBedragColumn.setCellValueFactory(cellData -> cellData.getValue().boekingBedragProperty());

		// Clear AppartementBijdrage details.
		showAppartementBijdrageDetail(null);

		logger.debug("Initialized AppartementBijdrageOverviewController");
	}

	/**
	 * Fills all text fields to show details about the appartement. If the
	 * specified appartement is null, all text fields are cleared.
	 *
	 * @param appartement
	 *            the appartement or null
	 */
	public void showAppartementBijdrageDetail(final Appartement appartement) {
		bijdrageSchemaNaamComboBox.setEditable(false);
		if (appartement != null) {
			// Fill the labels with info from the appartement object.
			beginSaldoField.setText(new BigDecimal(0.00).toString());

			bijdrageSchemaNaamComboBox.setItems(bijdrageSchemas);
			BijdrageSchema bijdrageSchema = bijdrageSchemaService
					.getBijdrageSchemaById(appartement.getBijdrageSchemaFk());
			BijdrageSchemaStringConverter converter = new BijdrageSchemaStringConverter();
			bijdrageSchemaNaamComboBox.setConverter(converter);
			bijdrageSchemaNaamComboBox.setValue(bijdrageSchema);
			bijdrageSchemaNaamComboBox.getSelectionModel().select(bijdrageSchema);

			calculateTotals();
		} else {
			// Appartement is null, remove all the text.
			beginSaldoField.setText("");
			bijdrageSchemaNaamComboBox.setValue(null);
		}
	}

	private void calculateTotals() {
		String zero = new BigDecimal(0.00).toString();
		beginSaldoField.setText(zero);

		betaaldLabel.setText(zero);
		BigDecimal betaaldTotal = new BigDecimal(0.00);
		for (Boeking boeking : oListBetaaldBoeking) {
			betaaldTotal.add(boeking.getBoekingBedrag());
		}
		betaaldLabel.setText(betaaldTotal.toString());

		subTotaalLabel.setText((new BigDecimal(0.00).add(betaaldTotal)).toString());

		verschuldigdLabel.setText(zero);
		BigDecimal verschuldigdTotal = new BigDecimal(0.00);
		for (Bijdrage bijdrage : oListVerschuldigdBijdrage) {
			verschuldigdTotal.add(bijdrage.getBijdrageBedrag());
		}
		verschuldigdLabel.setText(verschuldigdTotal.toString());

		totaalLabel.setText((new BigDecimal(0.00).add(betaaldTotal).subtract(verschuldigdTotal)).toString());
	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 *
	 * @param mainApp
	 */
	public void setVillaApp(final VillaApp villaApp) {
		this.villaApp = villaApp;

		// oListVerschuldigdBijdrage = FXCollections
		// .observableArrayList(bijdrageService.getAllBijdragenByAppartementId(appartementId));
		// verschuldigdBijdrageTable.setItems(oListVerschuldigdBijdrage);
		//
		// oListBetaaldBoeking =
		// FXCollections.observableArrayList(service.getAllAppartementen());
		// betaaldBoekingTable.setItems(oListBetaaldBoeking);
	}

	/**
	 * Called when the user clicks the edit button. Opens a dialog to edit
	 * details for the selected Appartement-Bijdrage.
	 */
	@FXML
	private void handleEditAppartementBijdrage() {
		// Post selectedPost = postTable.getSelectionModel().getSelectedItem();
		//
		// if (selectedPost != null && selectedRubriek != null) {
		// selectedPost.setRubriekNummer(selectedRubriek.getRubriekNummer());
		// boolean okClicked = showPostEditDialog(selectedPost);
		// if (okClicked) {
		// service.saveOrUpdatePostWithRubriek(selectedPost,
		// selectedRubriek.getRubriekId());
		// showPostDetail(selectedPost);
		// setSelection(selectedRubriek.getRubriekNummer());
		// }
		//
		// } else {
		// // Nothing selected.
		// Alert alert = new Alert(AlertType.WARNING);
		// alert.initOwner(villaApp.getPrimaryStage());
		// alert.setTitle("Geen selectie");
		// alert.setHeaderText("Geen Post geselecteerd");
		// alert.setContentText("Selecteer een post in the tabel a.u.b.");
		//
		// alert.showAndWait();
		// }
	}

	public void setSelection(final Appartement appartement) {
		selectedAppartement = appartement;

		oListVerschuldigdBijdrage = FXCollections
				.observableArrayList(bijdrageService.getAllBijdragenByAppartementId(appartement.getAppartementId()));
		verschuldigdBijdrageTable.getItems().clear();
		verschuldigdBijdrageTable.setItems(oListVerschuldigdBijdrage);
		verschuldigdBijdrageTable.getSelectionModel().select(0);

		oListBetaaldBoeking = FXCollections.observableArrayList(
				boekingService.getAllBoekingenBijdragenByAppartementId(appartement.getAppartementId()));
		betaaldBoekingTable.getItems().clear();
		betaaldBoekingTable.setItems(oListBetaaldBoeking);
		betaaldBoekingTable.getSelectionModel().select(0);

		showAppartementBijdrageDetail(appartement);
	}

	public TableView<Bijdrage> getVerschuldigdBijdrageTable() {
		return this.verschuldigdBijdrageTable;
	}

	public TableView<Boeking> getBetaaldBoekingTable() {
		return this.betaaldBoekingTable;
	}

	/**
	 * Opens a dialog to edit details for the specified post. If the user clicks
	 * OK, the changes are saved into the provided post object and true is
	 * returned.
	 *
	 * @param post
	 *            the post object to be edited
	 * @return true if the user clicked OK, false otherwise.
	 */
	public boolean showPostEditDialog(final Post post) {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(VillaApp.class
					.getResource(ConfigFacade.getStringValue(ApplicationConfiguration.FXML_POST_EDIT_DIALOG)));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Wijzig Post");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(villaApp.getPrimaryStage());
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the post into the controller.
			PostEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setPost(post);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}
