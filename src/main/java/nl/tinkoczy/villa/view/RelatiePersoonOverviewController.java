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
import nl.tinkoczy.villa.model.RelatiePersoon;
import nl.tinkoczy.villa.service.IRelatiePersoonService;
import nl.tinkoczy.villa.service.RelatiePersoonService;
import nl.tinkoczy.villa.service.RelatieService;

public class RelatiePersoonOverviewController {

	final static Logger logger = LoggerFactory.getLogger(RelatiePersoonOverviewController.class);

	@FXML
	private TableView<RelatiePersoon> relatiePersoonTable;
	@FXML
	private TableColumn<RelatiePersoon, String> relatiePersoonVoornaamColumn;
	@FXML
	private TableColumn<RelatiePersoon, String> relatiePersoonTussenvoegselColumn;
	@FXML
	private TableColumn<RelatiePersoon, String> relatiePersoonAchternaamColumn;

	@FXML
	private Label relatiePersoonVoornaamLabel;
	@FXML
	private Label relatiePersoonTussenvoegselLabel;
	@FXML
	private Label relatiePersoonAchternaamLabel;
	@FXML
	private Label relatiePersoonOmschrijvingLabel;
	@FXML
	private Label relatiePersoonTelefoonLabel;
	@FXML
	private Label relatiePersoonMobielLabel;
	@FXML
	private Label relatiePersoonEmailLabel;
	@FXML
	private Label relatiePersoonInternetLabel;
	@FXML
	private Label relatiePersoonAantekeningLabel;

	private IRelatiePersoonService service;

	private ObservableList<RelatiePersoon> oListRelatiePersoon = FXCollections.observableArrayList();

	// Reference to the main application.
	private VillaApp villaApp;

	// Reference to the selected relatie.
	private Relatie selectedRelatie;

	public RelatiePersoonOverviewController() {

	}

	@FXML
	private void initialize() {
		logger.debug("Initialize RelatiePersoonOverviewController");
		service = new RelatiePersoonService();
		// Initialize the person table with the two columns.
		relatiePersoonVoornaamColumn
				.setCellValueFactory(cellData -> cellData.getValue().relatiePersoonVoornaamProperty());
		relatiePersoonTussenvoegselColumn
				.setCellValueFactory(cellData -> cellData.getValue().relatiePersoonTussenvoegselProperty());
		relatiePersoonAchternaamColumn
				.setCellValueFactory(cellData -> cellData.getValue().relatiePersoonAchternaamProperty());

		// Clear relatiepersoon details.
		showRelatiePersoonDetail(null);

		logger.debug("Initialized RelatiePersoonOverviewController");
	}

	/**
	 * Fills all text fields to show details about the relatiepersoon. If the
	 * specified relatiepersoon is null, all text fields are cleared.
	 *
	 * @param relatiePersoon
	 *            the relatiePersoon or null
	 */
	public void showRelatiePersoonDetail(final RelatiePersoon relatiePersoon) {
		if (relatiePersoon != null) {
			// Fill the labels with info from the relatiePersoon object.
			relatiePersoonVoornaamLabel.setText(relatiePersoon.getRelatiePersoonVoornaam());
			relatiePersoonTussenvoegselLabel.setText(relatiePersoon.getRelatiePersoonTussenvoegsel());
			relatiePersoonAchternaamLabel.setText(relatiePersoon.getRelatiePersoonAchternaam());
			relatiePersoonOmschrijvingLabel.setText(relatiePersoon.getRelatiePersoonOmschrijving());
			relatiePersoonTelefoonLabel.setText(relatiePersoon.getRelatiePersoonTelefoon());
			relatiePersoonMobielLabel.setText(relatiePersoon.getRelatiePersoonMobiel());
			relatiePersoonEmailLabel.setText(relatiePersoon.getRelatiePersoonEmail());
			relatiePersoonInternetLabel.setText(relatiePersoon.getRelatiePersoonInternet());
			relatiePersoonAantekeningLabel.setText(relatiePersoon.getRelatiePersoonAantekening());
		} else {
			// RelatiePersoon is null, remove all the text.
			relatiePersoonVoornaamLabel.setText("");
			relatiePersoonTussenvoegselLabel.setText("");
			relatiePersoonAchternaamLabel.setText("");
			relatiePersoonOmschrijvingLabel.setText("");
			relatiePersoonTelefoonLabel.setText("");
			relatiePersoonMobielLabel.setText("");
			relatiePersoonEmailLabel.setText("");
			relatiePersoonInternetLabel.setText("");
			relatiePersoonAantekeningLabel.setText("");
		}
	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 *
	 * @param mainApp
	 */
	public void setVillaApp(final VillaApp villaApp) {
		this.villaApp = villaApp;

		oListRelatiePersoon = FXCollections.observableArrayList(service.getAllRelatiePersonen());
		relatiePersoonTable.setItems(oListRelatiePersoon);
	}

	/**
	 * Called when the user clicks on the delete button.
	 */
	@FXML
	private void handleDeleteRelatiePersoon() {
		RelatiePersoon selectedRelatiePersoon = relatiePersoonTable.getSelectionModel().getSelectedItem();
		if (selectedRelatiePersoon != null) {
			relatiePersoonTable.getItems().remove(selectedRelatiePersoon);
			service.deleteRelatiePersoon(selectedRelatiePersoon);
			setSelection(selectedRelatie.getRelatieCode());
		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(villaApp.getPrimaryStage());
			alert.setTitle("Geen selectie");
			alert.setHeaderText("Geen Relatiepersoon geselecteerd");
			alert.setContentText("Selecteer een persoon in the tabel a.u.b.");

			alert.showAndWait();
		}
	}

	/**
	 * Called when the user clicks the new button. Opens a dialog to edit
	 * details for a new relatiepersoon.
	 */
	@FXML
	private void handleNewRelatiePersoon() {
		RelatiePersoon tempRelatiePersoon = new RelatiePersoon();
		boolean okClicked = villaApp.showRelatiePersoonEditDialog(tempRelatiePersoon);
		if (okClicked) {
			service.saveOrUpdateRelatiePersoonWithRelatie(tempRelatiePersoon, selectedRelatie.getRelatieId());
			showRelatiePersoonDetail(tempRelatiePersoon);
			setSelection(selectedRelatie.getRelatieCode());
		}
	}

	/**
	 * Called when the user clicks the edit button. Opens a dialog to edit
	 * details for the selected relatiepersoon.
	 */
	@FXML
	private void handleEditRelatiePersoon() {
		RelatiePersoon selectedRelatiePersoon = relatiePersoonTable.getSelectionModel().getSelectedItem();
		if (selectedRelatiePersoon != null) {
			selectedRelatiePersoon.setRelatieCode(selectedRelatie.getRelatieCode());

			boolean okClicked = villaApp.showRelatiePersoonEditDialog(selectedRelatiePersoon);
			if (okClicked) {
				service.saveOrUpdateRelatiePersoonWithRelatie(selectedRelatiePersoon, selectedRelatie.getRelatieId());
				showRelatiePersoonDetail(selectedRelatiePersoon);
				setSelection(selectedRelatie.getRelatieCode());
			}

		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(villaApp.getPrimaryStage());
			alert.setTitle("Geen selectie");
			alert.setHeaderText("Geen Relatiepersoon geselecteerd");
			alert.setContentText("Selecteer een persoon in the tabel a.u.b.");

			alert.showAndWait();
		}
	}

	public void setSelection(final String relatieCode) {
		oListRelatiePersoon = FXCollections
				.observableArrayList(service.getAllRelatiePersonenByRelatieCode(relatieCode));
		relatiePersoonTable.getItems().clear();
		relatiePersoonTable.setItems(oListRelatiePersoon);
		relatiePersoonTable.getSelectionModel().select(0);
		selectedRelatie = new RelatieService().getRelatieByRelatieCode(relatieCode);
	}

	public TableView<RelatiePersoon> getRelatiePersoonTable() {
		return this.relatiePersoonTable;
	}
}
