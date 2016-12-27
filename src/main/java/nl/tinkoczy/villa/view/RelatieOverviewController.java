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
import nl.tinkoczy.villa.model.Relatie;
import nl.tinkoczy.villa.service.IRelatieService;
import nl.tinkoczy.villa.service.impl.RelatieService;

public class RelatieOverviewController {

	final static Logger logger = LoggerFactory.getLogger(RelatieOverviewController.class);

	@FXML
	private TableView<Relatie> relatieTable;
	@FXML
	private TableColumn<Relatie, String> relatieCodeColumn;
	@FXML
	private TableColumn<Relatie, String> relatieNaamColumn;

	@FXML
	private Label relatieCodeLabel;
	@FXML
	private Label relatieOmschrijvingLabel;
	@FXML
	private Label relatieNaamLabel;
	@FXML
	private Label relatieAdresStraatLabel;
	@FXML
	private Label relatieAdresPostcodeLabel;
	@FXML
	private Label relatieAdresPlaatsLabel;
	@FXML
	private Label relatiePostbusNummerLabel;
	@FXML
	private Label relatiePostbusPostcodeLabel;
	@FXML
	private Label relatiePostbusPlaatsLabel;
	@FXML
	private Label relatieBankNaamLabel;
	@FXML
	private Label relatieBankIBANLabel;
	@FXML
	private Label relatieAantekeningLabel;

	private IRelatieService service;

	private ObservableList<Relatie> oListRelatie = FXCollections.observableArrayList();

	// Reference to the main application.
	private VillaApp villaApp;

	public RelatieOverviewController() {

	}

	@FXML
	private void initialize() {
		logger.debug("Initialize RelatieOverviewController");
		service = new RelatieService();
		// Initialize the person table with the two columns.
		relatieCodeColumn.setCellValueFactory(cellData -> cellData.getValue().relatieCodeProperty());
		relatieNaamColumn.setCellValueFactory(cellData -> cellData.getValue().relatieNaamProperty());

		// Clear relatie details.
		showRelatieDetail(null);

		logger.debug("Initialized RelatieOverviewController");
	}

	/**
	 * Fills all text fields to show details about the relatie. If the specified
	 * relatie is null, all text fields are cleared.
	 *
	 * @param relatie
	 *            the relatie or null
	 */
	public void showRelatieDetail(final Relatie relatie) {
		if (relatie != null) {
			// Fill the labels with info from the relatie object.
			relatieCodeLabel.setText(relatie.getRelatieCode());
			relatieOmschrijvingLabel.setText(relatie.getRelatieOmschrijving());
			relatieNaamLabel.setText(relatie.getRelatieNaam());
			relatieAdresStraatLabel.setText(relatie.getRelatieAdresStraat());
			relatieAdresPostcodeLabel.setText(relatie.getRelatieAdresPostcode());
			relatieAdresPlaatsLabel.setText(relatie.getRelatieAdresPlaats());
			relatiePostbusNummerLabel.setText(relatie.getRelatiePostbusNummer());
			relatiePostbusPostcodeLabel.setText(relatie.getRelatiePostbusPostcode());
			relatiePostbusPlaatsLabel.setText(relatie.getRelatiePostbusPlaats());
			relatieBankNaamLabel.setText(relatie.getRelatieBankNaam());
			relatieBankIBANLabel.setText(relatie.getRelatieBankIBAN());
			relatieAantekeningLabel.setText(relatie.getRelatieAantekening());
		} else {
			// Relatie is null, remove all the text.
			relatieCodeLabel.setText("");
			relatieOmschrijvingLabel.setText("");
			relatieNaamLabel.setText("");
			relatieAdresStraatLabel.setText("");
			relatieAdresPostcodeLabel.setText("");
			relatieAdresPlaatsLabel.setText("");
			relatiePostbusNummerLabel.setText("");
			relatiePostbusPostcodeLabel.setText("");
			relatiePostbusPlaatsLabel.setText("");
			relatieBankNaamLabel.setText("");
			relatieBankIBANLabel.setText("");
			relatieAantekeningLabel.setText("");
		}
	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 *
	 * @param mainApp
	 */
	public void setVillaApp(final VillaApp villaApp) {
		this.villaApp = villaApp;

		oListRelatie = FXCollections.observableArrayList(service.getAllRelaties());
		relatieTable.setItems(oListRelatie);
	}

	/**
	 * Called when the user clicks on the delete button.
	 */
	@FXML
	private void handleDeleteRelatie() {
		Relatie selectedRelatie = relatieTable.getSelectionModel().getSelectedItem();
		if (selectedRelatie != null) {
			relatieTable.getItems().remove(selectedRelatie);
			service.deleteRelatie(selectedRelatie);
			refreshTable();
		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(villaApp.getPrimaryStage());
			alert.setTitle("Geen selectie");
			alert.setHeaderText("Geen Relatie geselecteerd");
			alert.setContentText("Selecteer een relatie in the tabel a.u.b.");

			alert.showAndWait();
		}
	}

	/**
	 * Called when the user clicks the new button. Opens a dialog to edit
	 * details for a new relatie.
	 */
	@FXML
	private void handleNewRelatie() {
		Relatie tempRelatie = new Relatie();
		boolean okClicked = villaApp.showRelatieEditDialog(tempRelatie);
		if (okClicked) {
			service.saveOrUpdateRelatie(tempRelatie);
			showRelatieDetail(tempRelatie);
			refreshTable();
		}
	}

	/**
	 * Called when the user clicks the edit button. Opens a dialog to edit
	 * details for the selected relatie.
	 */
	@FXML
	private void handleEditRelatie() {
		Relatie selectedRelatie = relatieTable.getSelectionModel().getSelectedItem();
		if (selectedRelatie != null) {
			boolean okClicked = villaApp.showRelatieEditDialog(selectedRelatie);
			if (okClicked) {
				service.saveOrUpdateRelatie(selectedRelatie);
				showRelatieDetail(selectedRelatie);
				refreshTable();
			}

		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(villaApp.getPrimaryStage());
			alert.setTitle("Geen selectie");
			alert.setHeaderText("Geen Relatie geselecteerd");
			alert.setContentText("Selecteer een relatie in the tabel a.u.b.");

			alert.showAndWait();
		}
	}

	private void refreshTable() {
		oListRelatie = FXCollections.observableArrayList(service.getAllRelaties());
		relatieTable.getItems().clear();
		relatieTable.setItems(oListRelatie);
	}

	public TableView<Relatie> getRelatieTable() {
		return this.relatieTable;
	}
}
