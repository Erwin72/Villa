package nl.tinkoczy.villa.view.appartement;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import nl.tinkoczy.villa.model.Appartement;

public class AppartementEditDialogController {

	@FXML
	private TextField appartementCodeField;
	@FXML
	private DatePicker appartementTransportdatumPicker;
	@FXML
	private TextField appartementStraatField;
	@FXML
	private TextField appartementPostcodeField;
	@FXML
	private TextField appartementPlaatsField;

	private Stage dialogStage;
	private Appartement appartement;
	private boolean okClicked = false;

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
	}

	/**
	 * Sets the stage of this dialog.
	 *
	 * @param dialogStage
	 */
	public void setDialogStage(final Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	/**
	 * Sets the appartement to be edited in the dialog.
	 *
	 * @param appartement
	 */
	public void setAppartement(final Appartement appartement) {
		this.appartement = appartement;

		appartementCodeField.setText(appartement.getAppartementCode());
		if (appartement.getAppartementTransportdatum() != null) {
			appartementTransportdatumPicker.setValue(appartement.getAppartementTransportdatum());
		}
		appartementStraatField.setText(appartement.getAppartementAdresStraat());
		appartementPostcodeField.setText(appartement.getAppartementAdresPostcode());
		appartementPlaatsField.setText(appartement.getAppartementAdresPlaats());
	}

	/**
	 * Returns true if the user clicked OK, false otherwise.
	 *
	 * @return
	 */
	public boolean isOkClicked() {
		return okClicked;
	}

	/**
	 * Called when the user clicks ok.
	 */
	@FXML
	private void handleOk() {
		if (isInputValid()) {
			appartement.setAppartementCode(appartementCodeField.getText());
			appartement.setAppartementTransportdatum(appartementTransportdatumPicker.getValue());
			appartement.setAppartementAdresStraat(appartementStraatField.getText());
			appartement.setAppartementAdresPostcode(appartementPostcodeField.getText());
			appartement.setAppartementAdresPlaats(appartementPlaatsField.getText());

			okClicked = true;
			dialogStage.close();
		}
	}

	/**
	 * Called when the user clicks cancel.
	 */
	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

	/**
	 * Validates the user input in the text fields.
	 *
	 * @return true if the input is valid
	 */
	private boolean isInputValid() {
		String errorMessage = "";

		if (appartementCodeField.getText() == null || appartementCodeField.getText().length() == 0) {
			errorMessage += "Geen appartementcode ingevuld!\n";
		}
		if (appartementStraatField.getText() == null || appartementStraatField.getText().length() == 0) {
			errorMessage += "Geen straatnaam ingevuld!\n";
		}
		if (appartementPostcodeField.getText() == null || appartementPostcodeField.getText().length() == 0) {
			errorMessage += "Geen postcode ingevuld!\n";
		}
		if (appartementPlaatsField.getText() == null || appartementPlaatsField.getText().length() == 0) {
			errorMessage += "Geen plaats ingevuld!\n";
		}

		if (errorMessage.length() == 0) {
			return true;
		} else {
			// Show the error message.
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("Ongeldige invoer");
			alert.setHeaderText("Corrigeer de ongeldige gegevens a.u.b.");
			alert.setContentText(errorMessage);

			alert.showAndWait();

			return false;
		}
	}
}
