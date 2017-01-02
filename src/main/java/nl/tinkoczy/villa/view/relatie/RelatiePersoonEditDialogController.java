package nl.tinkoczy.villa.view.relatie;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import nl.tinkoczy.villa.model.RelatiePersoon;

public class RelatiePersoonEditDialogController {

	@FXML
	private TextField relatiePersoonVoornaamField;
	@FXML
	private TextField relatiePersoonTussenvoegselField;
	@FXML
	private TextField relatiePersoonAchternaamField;
	@FXML
	private TextField relatiePersoonOmschrijvingField;
	@FXML
	private TextField relatiePersoonTelefoonField;
	@FXML
	private TextField relatiePersoonMobielField;
	@FXML
	private TextField relatiePersoonEmailField;
	@FXML
	private TextField relatiePersoonInternetField;
	@FXML
	private TextField relatiePersoonAantekeningField;

	private Stage dialogStage;
	private RelatiePersoon relatiePersoon;
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
	 * Sets the relatiePersoon to be edited in the dialog.
	 *
	 * @param relatiePersoon
	 */
	public void setRelatiePersoon(final RelatiePersoon relatiePersoon) {
		this.relatiePersoon = relatiePersoon;

		relatiePersoonVoornaamField.setText(relatiePersoon.getRelatiePersoonVoornaam());
		relatiePersoonTussenvoegselField.setText(relatiePersoon.getRelatiePersoonTussenvoegsel());
		relatiePersoonAchternaamField.setText(relatiePersoon.getRelatiePersoonAchternaam());
		relatiePersoonOmschrijvingField.setText(relatiePersoon.getRelatiePersoonOmschrijving());
		relatiePersoonTelefoonField.setText(relatiePersoon.getRelatiePersoonTelefoon());
		relatiePersoonMobielField.setText(relatiePersoon.getRelatiePersoonMobiel());
		relatiePersoonEmailField.setText(relatiePersoon.getRelatiePersoonEmail());
		relatiePersoonInternetField.setText(relatiePersoon.getRelatiePersoonInternet());
		relatiePersoonAantekeningField.setText(relatiePersoon.getRelatiePersoonAantekening());
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
			relatiePersoon.setRelatiePersoonVoornaam(relatiePersoonVoornaamField.getText());
			relatiePersoon.setRelatiePersoonTussenvoegsel(relatiePersoonTussenvoegselField.getText());
			relatiePersoon.setRelatiePersoonAchternaam(relatiePersoonAchternaamField.getText());
			relatiePersoon.setRelatiePersoonOmschrijving(relatiePersoonOmschrijvingField.getText());
			relatiePersoon.setRelatiePersoonTelefoon(relatiePersoonTelefoonField.getText());
			relatiePersoon.setRelatiePersoonMobiel(relatiePersoonMobielField.getText());
			relatiePersoon.setRelatiePersoonEmail(relatiePersoonEmailField.getText());
			relatiePersoon.setRelatiePersoonInternet(relatiePersoonInternetField.getText());
			relatiePersoon.setRelatiePersoonAantekening(relatiePersoonAantekeningField.getText());

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

		if (relatiePersoonVoornaamField.getText() == null || relatiePersoonVoornaamField.getText().length() == 0
				|| relatiePersoonAchternaamField.getText() == null
				|| relatiePersoonAchternaamField.getText().length() == 0) {
			errorMessage += "Geen naam ingevuld!\n";
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
