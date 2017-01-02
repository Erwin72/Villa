package nl.tinkoczy.villa.view.relatie;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import nl.tinkoczy.villa.model.Relatie;

public class RelatieEditDialogController {

	@FXML
	private TextField relatieCodeField;
	@FXML
	private TextField relatieOmschrijvingField;
	@FXML
	private TextField relatieNaamField;
	@FXML
	private TextField relatieAdresStraatField;
	@FXML
	private TextField relatieAdresPostcodeField;
	@FXML
	private TextField relatieAdresPlaatsField;
	@FXML
	private TextField relatiePostbusNummerField;
	@FXML
	private TextField relatiePostbusPostcodeField;
	@FXML
	private TextField relatiePostbusPlaatsField;
	@FXML
	private TextField relatieBankNaamField;
	@FXML
	private TextField relatieBankIBANField;
	@FXML
	private TextField relatieAantekeningField;

	private Stage dialogStage;
	private Relatie relatie;
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
	 * Sets the relatie to be edited in the dialog.
	 *
	 * @param relatie
	 */
	public void setRelatie(final Relatie relatie) {
		this.relatie = relatie;

		relatieCodeField.setText(relatie.getRelatieCode());
		relatieOmschrijvingField.setText(relatie.getRelatieOmschrijving());
		relatieNaamField.setText(relatie.getRelatieNaam());
		relatieAdresStraatField.setText(relatie.getRelatieAdresStraat());
		relatieAdresPostcodeField.setText(relatie.getRelatieAdresPostcode());
		relatieAdresPlaatsField.setText(relatie.getRelatieAdresPlaats());
		relatiePostbusNummerField.setText(relatie.getRelatiePostbusNummer());
		relatiePostbusPostcodeField.setText(relatie.getRelatiePostbusPostcode());
		relatiePostbusPlaatsField.setText(relatie.getRelatiePostbusPlaats());
		relatieBankNaamField.setText(relatie.getRelatieBankNaam());
		relatieBankIBANField.setText(relatie.getRelatieBankIBAN());
		relatieAantekeningField.setText(relatie.getRelatieAantekening());
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
			relatie.setRelatieCode(relatieCodeField.getText());
			relatie.setRelatieOmschrijving(relatieOmschrijvingField.getText());
			relatie.setRelatieNaam(relatieNaamField.getText());
			relatie.setRelatieAdresStraat(relatieAdresStraatField.getText());
			relatie.setRelatieAdresPostcode(relatieAdresPostcodeField.getText());
			relatie.setRelatieAdresPlaats(relatieAdresPlaatsField.getText());
			relatie.setRelatiePostbusNummer(relatiePostbusNummerField.getText());
			relatie.setRelatiePostbusPostcode(relatiePostbusPostcodeField.getText());
			relatie.setRelatiePostbusPlaats(relatiePostbusPlaatsField.getText());
			relatie.setRelatieBankNaam(relatieBankNaamField.getText());
			relatie.setRelatieBankIBAN(relatieBankIBANField.getText());
			relatie.setRelatieAantekening(relatieAantekeningField.getText());

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

		if (relatieCodeField.getText() == null || relatieCodeField.getText().length() == 0) {
			errorMessage += "Geen relatiecode ingevuld!\n";
		}
		if (relatieNaamField.getText() == null || relatieNaamField.getText().length() == 0) {
			errorMessage += "Geen relatienaam ingevuld!\n";
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
