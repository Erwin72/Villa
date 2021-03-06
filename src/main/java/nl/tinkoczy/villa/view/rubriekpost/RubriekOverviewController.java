package nl.tinkoczy.villa.view.rubriekpost;

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
import nl.tinkoczy.villa.config.ApplicationConfiguration;
import nl.tinkoczy.villa.config.ConfigFacade;
import nl.tinkoczy.villa.model.Rubriek;
import nl.tinkoczy.villa.service.IRubriekService;
import nl.tinkoczy.villa.service.impl.RubriekService;

public class RubriekOverviewController {

	final static Logger logger = LoggerFactory.getLogger(RubriekOverviewController.class);

	@FXML
	private TableView<Rubriek> rubriekTable;
	@FXML
	private TableColumn<Rubriek, Integer> rubriekNummerColumn;
	@FXML
	private TableColumn<Rubriek, String> rubriekOmschrijvingColumn;

	@FXML
	private Label rubriekNummerLabel;
	@FXML
	private Label rubriekOmschrijvingLabel;
	@FXML
	private Label rubriekTypeLabel;
	@FXML
	private Label rubriekInUitLabel;
	@FXML
	private Label rubriekInExploRekeningLabel;

	private IRubriekService service;

	private ObservableList<Rubriek> oListRubriek = FXCollections.observableArrayList();

	// Reference to the main application.
	private VillaApp villaApp;

	public RubriekOverviewController() {

	}

	@FXML
	private void initialize() {
		logger.debug("Initialize RubriekOverviewController");
		service = new RubriekService();
		// Initialize the person table with the two columns.
		rubriekNummerColumn.setCellValueFactory(cellData -> cellData.getValue().rubriekNummerProperty());
		rubriekOmschrijvingColumn.setCellValueFactory(cellData -> cellData.getValue().rubriekOmschrijvingProperty());

		// Clear rubiek details.
		showRubriekDetail(null);

		logger.debug("Initialized RubriekOverviewController");
	}

	/**
	 * Fills all text fields to show details about the rubriek. If the specified
	 * rubriek is null, all text fields are cleared.
	 *
	 * @param rubriek
	 *            the rubriek or null
	 */
	public void showRubriekDetail(final Rubriek rubriek) {
		if (rubriek != null) {
			// Fill the labels with info from the rubriek object.
			rubriekNummerLabel.setText(Integer.toString(rubriek.getRubriekNummer()));
			rubriekOmschrijvingLabel.setText(rubriek.getRubriekOmschrijving());
			rubriekTypeLabel.setText(rubriek.getRubriekType());
			rubriekInUitLabel.setText(rubriek.getRubriekInUit());
			rubriekInExploRekeningLabel.setText(rubriek.isRubriekInExploRekening().toString());
		} else {
			// Rubriek is null, remove all the text.
			rubriekNummerLabel.setText("");
			rubriekOmschrijvingLabel.setText("");
			rubriekTypeLabel.setText("");
			rubriekInUitLabel.setText("");
			rubriekInExploRekeningLabel.setText("");
		}
	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 *
	 * @param mainApp
	 */
	public void setVillaApp(final VillaApp villaApp) {
		this.villaApp = villaApp;

		oListRubriek = FXCollections.observableArrayList(service.getAllRubrieken());
		rubriekTable.setItems(oListRubriek);
	}

	/**
	 * Called when the user clicks on the delete button.
	 */
	@FXML
	private void handleDeleteRubriek() {
		Rubriek selectedRubriek = rubriekTable.getSelectionModel().getSelectedItem();
		if (selectedRubriek != null) {
			rubriekTable.getItems().remove(selectedRubriek);
			service.deleteRubriek(selectedRubriek);
			refreshTable();
		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(villaApp.getPrimaryStage());
			alert.setTitle("Geen selectie");
			alert.setHeaderText("Geen Rubriek geselecteerd");
			alert.setContentText("Selecteer een rubriek in the tabel a.u.b.");

			alert.showAndWait();
		}
	}

	/**
	 * Called when the user clicks the new button. Opens a dialog to edit
	 * details for a new rubriek.
	 */
	@FXML
	private void handleNewRubriek() {
		Rubriek tempRubriek = new Rubriek();
		boolean okClicked = showRubriekEditDialog(tempRubriek);
		if (okClicked) {
			// villaApp.getRubriekData().add(tempRubriek);
			service.saveOrUpdateRubriek(tempRubriek);
			showRubriekDetail(tempRubriek);
			refreshTable();
		}
	}

	/**
	 * Called when the user clicks the edit button. Opens a dialog to edit
	 * details for the selected rubriek.
	 */
	@FXML
	private void handleEditRubriek() {
		Rubriek selectedRubriek = rubriekTable.getSelectionModel().getSelectedItem();
		if (selectedRubriek != null) {
			boolean okClicked = showRubriekEditDialog(selectedRubriek);
			if (okClicked) {
				service.saveOrUpdateRubriek(selectedRubriek);
				showRubriekDetail(selectedRubriek);
				refreshTable();
			}

		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(villaApp.getPrimaryStage());
			alert.setTitle("Geen selectie");
			alert.setHeaderText("Geen Rubriek geselecteerd");
			alert.setContentText("Selecteer een rubriek in the tabel a.u.b.");

			alert.showAndWait();
		}
	}

	private void refreshTable() {
		oListRubriek = FXCollections.observableArrayList(service.getAllRubrieken());
		rubriekTable.getItems().clear();
		rubriekTable.setItems(oListRubriek);
	}

	public TableView<Rubriek> getRubriekTable() {
		return this.rubriekTable;
	}

	/**
	 * Opens a dialog to edit details for the specified rubriek. If the user
	 * clicks OK, the changes are saved into the provided rubriek object and
	 * true is returned.
	 *
	 * @param rubriek
	 *            the rubriek object to be edited
	 * @return true if the user clicked OK, false otherwise.
	 */
	public boolean showRubriekEditDialog(final Rubriek rubriek) {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(VillaApp.class
					.getResource(ConfigFacade.getStringValue(ApplicationConfiguration.FXML_RUBRIEK_EDIT_DIALOG)));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Wijzig Rubriek");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(villaApp.getPrimaryStage());
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the rubriek into the controller.
			RubriekEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setRubriek(rubriek);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}
