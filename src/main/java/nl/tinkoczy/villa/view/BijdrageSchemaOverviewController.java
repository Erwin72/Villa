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
import nl.tinkoczy.villa.model.BijdrageRente;
import nl.tinkoczy.villa.model.BijdrageSchema;
import nl.tinkoczy.villa.service.IBijdrageSchemaService;
import nl.tinkoczy.villa.service.impl.BijdrageRenteService;
import nl.tinkoczy.villa.service.impl.BijdrageSchemaService;

public class BijdrageSchemaOverviewController {

	final static Logger logger = LoggerFactory.getLogger(BijdrageSchemaOverviewController.class);

	@FXML
	private TableView<BijdrageSchema> bijdrageSchemaTable;
	@FXML
	private TableColumn<BijdrageSchema, String> bijdrageSchemaNaamColumn;

	@FXML
	private Label bijdrageSchemaNaamLabel;
	@FXML
	private Label bijdrageRentePercentageLabel;
	@FXML
	private Label bijdrageRenteDagenNaVervaldatumLabel;

	private IBijdrageSchemaService service;

	private ObservableList<BijdrageSchema> oListBijdrageSchema = FXCollections.observableArrayList();

	// Reference to the main application.
	private VillaApp villaApp;

	public BijdrageSchemaOverviewController() {

	}

	@FXML
	private void initialize() {
		logger.debug("Initialize BijdrageSchemaOverviewController");
		service = new BijdrageSchemaService();
		// Initialize the table
		bijdrageSchemaNaamColumn.setCellValueFactory(cellData -> cellData.getValue().bijdrageSchemaNaamProperty());

		// Clear BijdrageSchema details.
		showBijdrageSchemaDetail(null);

		logger.debug("Initialized BijdrageSchemaOverviewController");
	}

	/**
	 * Fills all text fields to show details about the BijdrageSchema. If the
	 * specified BijdrageSchema is null, all text fields are cleared.
	 *
	 * @param bijdrageSchema
	 *            the bijdrageSchema or null
	 */
	public void showBijdrageSchemaDetail(final BijdrageSchema bijdrageSchema) {
		if (bijdrageSchema != null) {
			// Fill the labels with info from the bijdrageSchema object.
			bijdrageSchemaNaamLabel.setText(bijdrageSchema.getBijdrageSchemaNaam());
			if (bijdrageSchema.getBijdrageRenteFk() != null) {
				BijdrageRente bijdrageRente = new BijdrageRenteService()
						.getBijdrageRenteById((bijdrageSchema.getBijdrageRenteFk()));
				bijdrageRentePercentageLabel.setText(bijdrageRente.getBijdrageRentePercentage().toString());
				bijdrageRenteDagenNaVervaldatumLabel
						.setText(Integer.toString(bijdrageRente.getBijdrageRenteNaVervaldatum()));
			} else {
				bijdrageRentePercentageLabel.setText("");
				bijdrageRenteDagenNaVervaldatumLabel.setText("");
			}
		} else {
			// bijdrageSchema is null, remove all the text.
			bijdrageSchemaNaamLabel.setText("");
			bijdrageRentePercentageLabel.setText("");
			bijdrageRenteDagenNaVervaldatumLabel.setText("");
		}
	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 *
	 * @param mainApp
	 */
	public void setVillaApp(final VillaApp villaApp) {
		this.villaApp = villaApp;

		oListBijdrageSchema = FXCollections.observableArrayList(service.getAllBijdrageSchemas());
		bijdrageSchemaTable.setItems(oListBijdrageSchema);
	}

	/**
	 * Called when the user clicks on the delete button.
	 */
	@FXML
	private void handleDeleteBijdrageSchema() {
		BijdrageSchema selectedBijdrageSchema = bijdrageSchemaTable.getSelectionModel().getSelectedItem();
		if (selectedBijdrageSchema != null) {
			bijdrageSchemaTable.getItems().remove(selectedBijdrageSchema);
			service.deleteBijdrageSchema(selectedBijdrageSchema);
			refreshTable();
		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(villaApp.getPrimaryStage());
			alert.setTitle("Geen selectie");
			alert.setHeaderText("Geen BijdrageSchema geselecteerd");
			alert.setContentText("Selecteer een bijdrageschema in the tabel a.u.b.");

			alert.showAndWait();
		}
	}

	/**
	 * Called when the user clicks the new button. Opens a dialog to edit
	 * details for a new bijdrageschema.
	 */
	@FXML
	private void handleNewBijdrageSchema() {
		BijdrageSchema tempBijdrageSchema = new BijdrageSchema();
		boolean okClicked = showBijdrageSchemaEditDialog(tempBijdrageSchema);
		if (okClicked) {
			service.saveOrUpdateBijdrageSchema(tempBijdrageSchema);
			showBijdrageSchemaDetail(tempBijdrageSchema);
			refreshTable();
		}
	}

	/**
	 * Called when the user clicks the edit button. Opens a dialog to edit
	 * details for the selected bijdrageschema.
	 */
	@FXML
	private void handleEditBijdrageSchema() {
		BijdrageSchema selectedBijdrageSchema = bijdrageSchemaTable.getSelectionModel().getSelectedItem();
		if (selectedBijdrageSchema != null) {
			boolean okClicked = showBijdrageSchemaEditDialog(selectedBijdrageSchema);
			if (okClicked) {
				service.saveOrUpdateBijdrageSchema(selectedBijdrageSchema);
				showBijdrageSchemaDetail(selectedBijdrageSchema);
				refreshTable();
			}

		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(villaApp.getPrimaryStage());
			alert.setTitle("Geen selectie");
			alert.setHeaderText("Geen BijdrageSchema geselecteerd");
			alert.setContentText("Selecteer een bijdrageschema in the tabel a.u.b.");

			alert.showAndWait();
		}
	}

	private void refreshTable() {
		oListBijdrageSchema = FXCollections.observableArrayList(service.getAllBijdrageSchemas());
		bijdrageSchemaTable.getItems().clear();
		bijdrageSchemaTable.setItems(oListBijdrageSchema);
	}

	public TableView<BijdrageSchema> getBijdrageSchemaTable() {
		return this.bijdrageSchemaTable;
	}

	/**
	 * Opens a dialog to edit details for the specified bijdrageSchema. If the
	 * user clicks OK, the changes are saved into the provided bijdrageSchema
	 * object and true is returned.
	 *
	 * @param bijdrageSchema
	 *            the bijdrageSchema object to be edited
	 * @return true if the user clicked OK, false otherwise.
	 */
	public boolean showBijdrageSchemaEditDialog(final BijdrageSchema bijdrageSchema) {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(VillaApp.class.getResource("view/BijdrageSchemaEditDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Wijzig BijdrageSchema");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(villaApp.getPrimaryStage());
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the bijdrageSchema into the controller.
			BijdrageSchemaEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setBijdrageSchema(bijdrageSchema);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}
