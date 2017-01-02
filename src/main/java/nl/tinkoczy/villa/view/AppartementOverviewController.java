package nl.tinkoczy.villa.view;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import nl.tinkoczy.villa.VillaApp;
import nl.tinkoczy.villa.model.Appartement;
import nl.tinkoczy.villa.service.IAppartementService;
import nl.tinkoczy.villa.service.impl.AppartementService;

public class AppartementOverviewController {

	final static Logger logger = LoggerFactory.getLogger(AppartementOverviewController.class);

	@FXML
	private TableView<Appartement> appartementTable;
	@FXML
	private TableColumn<Appartement, String> appartementCodeColumn;

	@FXML
	private Label appartementCodeLabel;
	@FXML
	private Label appartementTransportdatumLabel;
	@FXML
	private Label appartementStraatLabel;
	@FXML
	private Label appartementPostcodeLabel;
	@FXML
	private Label appartementPlaatsLabel;

	private IAppartementService service;

	private ObservableList<Appartement> oListAppartement = FXCollections.observableArrayList();

	// Reference to the main application.
	private VillaApp villaApp;

	public AppartementOverviewController() {

	}

	@FXML
	private void initialize() {
		logger.debug("Initialize AppartementOverviewController");
		service = new AppartementService();
		// Initialize the table
		appartementCodeColumn.setCellValueFactory(cellData -> cellData.getValue().appartementCodeProperty());

		// Clear Appartement details.
		showAppartementDetail(null);

		logger.debug("Initialized AppartementOverviewController");
	}

	/**
	 * Fills all text fields to show details about the appartement. If the
	 * specified appartement is null, all text fields are cleared.
	 *
	 * @param appartement
	 *            the appartement or null
	 */
	public void showAppartementDetail(final Appartement appartement) {
		if (appartement != null) {
			// Fill the labels with info from the appartement object.
			appartementCodeLabel.setText(appartement.getAppartementCode());
			if (appartement.getAppartementTransportdatum() != null) {
				appartementTransportdatumLabel.setText(appartement.getAppartementTransportdatumAsString());
			}
			appartementStraatLabel.setText(appartement.getAppartementAdresStraat());
			appartementPostcodeLabel.setText(appartement.getAppartementAdresPostcode());
			appartementPlaatsLabel.setText(appartement.getAppartementAdresPlaats());
		} else {
			// Appartement is null, remove all the text.
			appartementCodeLabel.setText("");
			appartementTransportdatumLabel.setText("");
			appartementStraatLabel.setText("");
			appartementPostcodeLabel.setText("");
			appartementPlaatsLabel.setText("");
		}
	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 *
	 * @param mainApp
	 */
	public void setVillaApp(final VillaApp villaApp) {
		this.villaApp = villaApp;

		oListAppartement = FXCollections.observableArrayList(service.getAllAppartementen());
		appartementTable.setItems(oListAppartement);
	}

	/**
	 * Called when the user clicks on the delete button.
	 */
	@FXML
	private void handleDeleteAppartement() {
		Appartement selectedAppartement = appartementTable.getSelectionModel().getSelectedItem();
		if (selectedAppartement != null) {
			appartementTable.getItems().remove(selectedAppartement);
			service.deleteAppartement(selectedAppartement);
			refreshTable();
		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(villaApp.getPrimaryStage());
			alert.setTitle("Geen selectie");
			alert.setHeaderText("Geen Appartement geselecteerd");
			alert.setContentText("Selecteer een appartement in the tabel a.u.b.");

			alert.showAndWait();
		}
	}

	/**
	 * Called when the user clicks the new button. Opens a dialog to edit
	 * details for a new appartement.
	 */
	@FXML
	private void handleNewAppartement() {
		Appartement tempAppartement = new Appartement();
		boolean okClicked = showAppartementEditDialog(tempAppartement);
		if (okClicked) {
			service.saveOrUpdateAppartement(tempAppartement);
			showAppartementDetail(tempAppartement);
			refreshTable();
		}
	}

	/**
	 * Called when the user clicks the edit button. Opens a dialog to edit
	 * details for the selected appartement.
	 */
	@FXML
	private void handleEditAppartement() {
		Appartement selectedAppartement = appartementTable.getSelectionModel().getSelectedItem();
		if (selectedAppartement != null) {
			boolean okClicked = showAppartementEditDialog(selectedAppartement);
			if (okClicked) {
				service.saveOrUpdateAppartement(selectedAppartement);
				showAppartementDetail(selectedAppartement);
				refreshTable();
			}

		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(villaApp.getPrimaryStage());
			alert.setTitle("Geen selectie");
			alert.setHeaderText("Geen Appartement geselecteerd");
			alert.setContentText("Selecteer een appartment in the tabel a.u.b.");

			alert.showAndWait();
		}
	}

	private void refreshTable() {
		oListAppartement = FXCollections.observableArrayList(service.getAllAppartementen());
		appartementTable.getItems().clear();
		appartementTable.setItems(oListAppartement);
	}

	public TableView<Appartement> getAppartementTable() {
		return this.appartementTable;
	}

	/**
	 * Opens a dialog to edit details for the specified appartement. If the user
	 * clicks OK, the changes are saved into the provided appartement object and
	 * true is returned.
	 *
	 * @param appartement
	 *            the appartement object to be edited
	 * @return true if the user clicked OK, false otherwise.
	 */
	public boolean showAppartementEditDialog(final Appartement appartement) {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(VillaApp.class.getResource("view/AppartementEditDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Wijzig Appartement");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(villaApp.getPrimaryStage());
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the appartement into the controller.
			AppartementEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setAppartement(appartement);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}
