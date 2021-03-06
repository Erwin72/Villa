package nl.tinkoczy.villa.view.relatie;

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
import nl.tinkoczy.villa.model.Relatie;
import nl.tinkoczy.villa.model.RelatiePersoon;
import nl.tinkoczy.villa.service.IRelatiePersoonService;
import nl.tinkoczy.villa.service.impl.RelatiePersoonService;
import nl.tinkoczy.villa.service.impl.RelatieService;

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
		boolean okClicked = showRelatiePersoonEditDialog(tempRelatiePersoon);
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

			boolean okClicked = showRelatiePersoonEditDialog(selectedRelatiePersoon);
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

	/**
	 * Opens a dialog to edit details for the specified relatiepersoon. If the
	 * user clicks OK, the changes are saved into the provided relatiepersoon
	 * object and true is returned.
	 *
	 * @param relatiePersoon
	 *            the relatiePersoon object to be edited
	 * @return true if the user clicked OK, false otherwise.
	 */
	public boolean showRelatiePersoonEditDialog(final RelatiePersoon relatiePersoon) {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(VillaApp.class.getResource(
					ConfigFacade.getStringValue(ApplicationConfiguration.FXML_RELATIE_PERSOON_EDIT_DIALOG)));

			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Wijzig Relatiepersoon");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(villaApp.getPrimaryStage());
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the relatiePersoon into the controller.
			RelatiePersoonEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setRelatiePersoon(relatiePersoon);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}
