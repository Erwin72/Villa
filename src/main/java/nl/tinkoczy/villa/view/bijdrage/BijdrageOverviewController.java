package nl.tinkoczy.villa.view.bijdrage;

import java.io.IOException;
import java.math.BigDecimal;

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
import nl.tinkoczy.villa.model.Bijdrage;
import nl.tinkoczy.villa.model.BijdrageSchema;
import nl.tinkoczy.villa.service.IBijdrageService;
import nl.tinkoczy.villa.service.impl.BijdrageSchemaService;
import nl.tinkoczy.villa.service.impl.BijdrageService;

public class BijdrageOverviewController {

	final static Logger logger = LoggerFactory.getLogger(BijdrageOverviewController.class);

	@FXML
	private TableView<Bijdrage> bijdrageTable;
	@FXML
	private TableColumn<Bijdrage, String> bijdrageDatumColumn;
	@FXML
	private TableColumn<Bijdrage, BigDecimal> bijdrageBedragColumn;

	@FXML
	private Label bijdrageDatumLabel;
	@FXML
	private Label bijdrageBedragLabel;

	private IBijdrageService service;

	private ObservableList<Bijdrage> oListBijdrage = FXCollections.observableArrayList();

	// Reference to the main application.
	private VillaApp villaApp;

	// Reference to the selected BijdrageSchema.
	private BijdrageSchema selectedBijdrageSchema;

	public BijdrageOverviewController() {

	}

	@FXML
	private void initialize() {
		logger.debug("Initialize BijdrageOverviewController");
		service = new BijdrageService();
		// Initialize the person table with the two columns.
		bijdrageDatumColumn.setCellValueFactory(cellData -> cellData.getValue().bijdrageDatumAsStringProperty());
		bijdrageBedragColumn.setCellValueFactory(cellData -> cellData.getValue().bijdrageBedragProperty());

		// Clear Bijdrage details.
		showBijdrageDetail(null);

		// Listen for selection changes and show the Bijdrage details when
		// changed.
		bijdrageTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showBijdrageDetail(newValue));

		logger.debug("Initialized BijdrageOverviewController");
	}

	/**
	 * Fills all text fields to show details about the Bijdrage. If the
	 * specified Bijdrage is null, all text fields are cleared.
	 *
	 * @param bijdrage
	 *            the bijdrage or null
	 */
	private void showBijdrageDetail(final Bijdrage bijdrage) {
		if (bijdrage != null) {
			// Fill the labels with info from the bijdrage object.
			bijdrageDatumLabel.setText(bijdrage.getBijdrageDatumAsString());
			bijdrageBedragLabel.setText(bijdrage.getBijdrageBedrag().toString());
		} else {
			// Bijdrage is null, remove all the text.
			bijdrageDatumLabel.setText("");
			bijdrageBedragLabel.setText("");
		}
	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 *
	 * @param mainApp
	 */
	public void setVillaApp(final VillaApp villaApp) {
		this.villaApp = villaApp;

		// Add observable list data to the table
		oListBijdrage = FXCollections.observableArrayList(service.getAllBijdragen());
		bijdrageTable.setItems(oListBijdrage);
	}

	/**
	 * Called when the user clicks on the autovul button.
	 */
	@FXML
	private void handleAutoVulBijdrage() {
		if (showBijdrageSchemaAutoInvullingEditDialog()) {
			setSelection(selectedBijdrageSchema.getBijdrageSchemaNaam());
		}
	}

	/**
	 * Called when the user clicks on the autovul button. Opens a dialog to
	 * automatically fill bijdragen for the bijdrageschema.
	 *
	 * @return true if the user clicked OK, false otherwise.
	 */
	@FXML
	public boolean showBijdrageSchemaAutoInvullingEditDialog() {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(VillaApp.class.getResource(ConfigFacade
					.getStringValue(ApplicationConfiguration.FXML_BIJDRAGE_SCHEMA_AUTOINVULLING_EDIT_DIALOG)));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Auto invulling bijdrageschema");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(villaApp.getPrimaryStage());
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the post into the controller.
			BijdrageSchemaAutoInvullingEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setBijdrageSchema(selectedBijdrageSchema);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Called when the user clicks on the delete button.
	 */
	@FXML
	private void handleDeleteBijdrage() {
		Bijdrage selectedBijdrage = bijdrageTable.getSelectionModel().getSelectedItem();
		if (selectedBijdrage != null) {
			bijdrageTable.getItems().remove(selectedBijdrage);
			service.deleteBijdrage(selectedBijdrage);
			setSelection(selectedBijdrageSchema.getBijdrageSchemaNaam());
		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(villaApp.getPrimaryStage());
			alert.setTitle("Geen selectie");
			alert.setHeaderText("Geen Bijdrage geselecteerd");
			alert.setContentText("Selecteer een bijdrage in the tabel a.u.b.");

			alert.showAndWait();
		}
	}

	/**
	 * Called when the user clicks the new button. Opens a dialog to edit
	 * details for a new bijdrage.
	 */
	@FXML
	private void handleNewBijdrage() {
		Bijdrage tempBijdrage = new Bijdrage();
		tempBijdrage.setBijdrageSchemaNaam(selectedBijdrageSchema.getBijdrageSchemaNaam());
		boolean okClicked = showBijdrageEditDialog(tempBijdrage);
		if (okClicked) {
			service.saveOrUpdateBijdrageWithBijdrageSchema(tempBijdrage,
					selectedBijdrageSchema.getBijdrageSchemaNaam());
			showBijdrageDetail(tempBijdrage);
			setSelection(selectedBijdrageSchema.getBijdrageSchemaNaam());
		}
	}

	/**
	 * Called when the user clicks the edit button. Opens a dialog to edit
	 * details for the selected bijdrage.
	 */
	@FXML
	private void handleEditBijdrage() {
		Bijdrage selectedBijdrage = bijdrageTable.getSelectionModel().getSelectedItem();

		if (selectedBijdrage != null && selectedBijdrageSchema != null) {
			selectedBijdrage.setBijdrageSchemaNaam(selectedBijdrageSchema.getBijdrageSchemaNaam());
			boolean okClicked = showBijdrageEditDialog(selectedBijdrage);
			if (okClicked) {
				service.saveOrUpdateBijdrageWithBijdrageSchema(selectedBijdrage,
						selectedBijdrageSchema.getBijdrageSchemaNaam());
				showBijdrageDetail(selectedBijdrage);
				setSelection(selectedBijdrageSchema.getBijdrageSchemaNaam());
			}

		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(villaApp.getPrimaryStage());
			alert.setTitle("Geen selectie");
			alert.setHeaderText("Geen Bijdrage geselecteerd");
			alert.setContentText("Selecteer een bijdrage in the tabel a.u.b.");

			alert.showAndWait();
		}
	}

	public void setSelection(final String bijdrageSchemaNaam) {
		oListBijdrage = FXCollections.observableArrayList(service.getAllBijdragenByBijdrageSchema(bijdrageSchemaNaam));
		bijdrageTable.getItems().clear();
		bijdrageTable.setItems(oListBijdrage);
		bijdrageTable.getSelectionModel().select(0);
		selectedBijdrageSchema = new BijdrageSchemaService().getBijdrageSchemaByBijdrageSchemaNaam(bijdrageSchemaNaam);
	}

	public TableView<Bijdrage> getBijdrageTable() {
		return this.bijdrageTable;
	}

	/**
	 * Opens a dialog to edit details for the specified bijdrage. If the user
	 * clicks OK, the changes are saved into the provided bijdrage object and
	 * true is returned.
	 *
	 * @param bijdrage
	 *            the bijdrage object to be edited
	 * @return true if the user clicked OK, false otherwise.
	 */
	public boolean showBijdrageEditDialog(final Bijdrage bijdrage) {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(VillaApp.class
					.getResource(ConfigFacade.getStringValue(ApplicationConfiguration.FXML_BIJDRAGE_EDIT_DIALOG)));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Wijzig bijdrage");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(villaApp.getPrimaryStage());
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the post into the controller.
			BijdrageEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setBijdrage(bijdrage);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}
