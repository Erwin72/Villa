package nl.tinkoczy.villa.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import nl.tinkoczy.villa.VillaApp;
import nl.tinkoczy.villa.model.Rubriek;
import nl.tinkoczy.villa.service.IRubriekService;
import nl.tinkoczy.villa.service.RubriekService;

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
		boolean okClicked = villaApp.showRubriekEditDialog(tempRubriek);
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
			boolean okClicked = villaApp.showRubriekEditDialog(selectedRubriek);
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
}
