package nl.tinkoczy.villa.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import nl.tinkoczy.villa.model.Rubriek;

public class RubriekEditDialogController {

	@FXML
	private TextField rubriekNummerField;
	@FXML
	private TextField rubriekOmschrijvingField;
	@FXML
	private TextField rubriekTypeField;
	@FXML
	private TextField rubriekInUitField;
	@FXML
	private TextField rubriekInExploRekeningField;

	private Stage dialogStage;
	private Rubriek rubriek;
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
	 * Sets the rubriek to be edited in the dialog.
	 *
	 * @param rubriek
	 */
	public void setRubriek(final Rubriek rubriek) {
		this.rubriek = rubriek;

		if (rubriek.getRubriekNummer() != null) {
			rubriekNummerField.setText(Integer.toString(rubriek.getRubriekNummer()));
		}
		rubriekOmschrijvingField.setText(rubriek.getRubriekOmschrijving());
		rubriekTypeField.setText(rubriek.getRubriekType());
		rubriekInUitField.setText(rubriek.getRubriekInUit());
		if (rubriek.isRubriekInExploRekening() != null) {
			rubriekInExploRekeningField.setText(rubriek.isRubriekInExploRekening().toString());
		}
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
			rubriek.setRubriekNummer(Integer.valueOf(rubriekNummerField.getText()));
			rubriek.setRubriekOmschrijving(rubriekOmschrijvingField.getText());
			rubriek.setRubriekType(rubriekTypeField.getText());
			rubriek.setRubriekInUit(rubriekInUitField.getText());
			rubriek.setRubriekInExploRekening(Boolean.valueOf(rubriekInExploRekeningField.getText()));

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

		if (rubriekNummerField.getText() == null || rubriekNummerField.getText().length() == 0) {
			errorMessage += "Geen rubrieknummer ingevuld!\n";
		} else {
			// try to parse the rubrieknummer code into an int.
			try {
				Integer.parseInt(rubriekNummerField.getText());
			} catch (NumberFormatException e) {
				errorMessage += "Geen geldig rubrieknummer ingevuld (moet een getal zijn)!\n";
			}
		}
		if (rubriekOmschrijvingField.getText() == null || rubriekOmschrijvingField.getText().length() == 0) {
			errorMessage += "Geen rubriek omschrijving ingevuld!\n";
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
