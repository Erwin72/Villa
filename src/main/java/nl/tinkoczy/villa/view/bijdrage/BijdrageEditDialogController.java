package nl.tinkoczy.villa.view.bijdrage;

import java.math.BigDecimal;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import nl.tinkoczy.villa.model.Bijdrage;

public class BijdrageEditDialogController {

	@FXML
	private DatePicker bijdrageDatumPicker;
	@FXML
	private TextField bijdrageBedragField;

	private Stage dialogStage;
	private Bijdrage bijdrage;
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
	 * Sets the bijdrage to be edited in the dialog.
	 *
	 * @param bijdrage
	 */
	public void setBijdrage(final Bijdrage bijdrage) {
		this.bijdrage = bijdrage;

		if (bijdrage.getBijdrageDatum() != null) {
			bijdrageDatumPicker.setValue(bijdrage.getBijdrageDatum());
		}
		if (bijdrage.getBijdrageBedrag() != null) {
			bijdrageBedragField.setText(bijdrage.getBijdrageBedrag().toString());
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

			bijdrage.setBijdrageBedrag(new BigDecimal(bijdrageBedragField.getText()));
			bijdrage.setBijdrageDatum(bijdrageDatumPicker.getValue());

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

		if (bijdrageDatumPicker.getValue() == null) {
			errorMessage += "Geen datum ingevuld!\n";
		}
		if (bijdrageBedragField.getText() == null || bijdrageBedragField.getText().length() == 0) {
			errorMessage += "Geen bedrag ingevuld!\n";
		} else {
			// try to parse the bijdrageBedragField into an BigDecimal.
			try {
				new BigDecimal(bijdrageBedragField.getText());
			} catch (NumberFormatException e) {
				errorMessage += "Geen geldige waarde voor bedrag ingevuld (moet een decimaal getal zijn)!\n";
			}
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
