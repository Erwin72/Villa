package nl.tinkoczy.villa.view.bijdrage;

import java.math.BigDecimal;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import nl.tinkoczy.villa.model.BijdrageFrequentie;
import nl.tinkoczy.villa.model.BijdrageRente;
import nl.tinkoczy.villa.model.BijdrageSchema;
import nl.tinkoczy.villa.service.IBijdrageFrequentieService;
import nl.tinkoczy.villa.service.IBijdrageRenteService;
import nl.tinkoczy.villa.service.impl.BijdrageFrequentieService;
import nl.tinkoczy.villa.service.impl.BijdrageRenteService;

public class BijdrageSchemaEditDialogController {

	@FXML
	private TextField bijdrageSchemaNaamField;
	@FXML
	private TextField bijdrageRentePercentageField;
	@FXML
	private TextField bijdrageRenteDagenNaVervaldatumField;

	private Stage dialogStage;
	private BijdrageSchema bijdrageSchema;
	private boolean okClicked = false;

	private final IBijdrageRenteService bijdrageRenteService = new BijdrageRenteService();
	private final IBijdrageFrequentieService bijdrageFrequentieService = new BijdrageFrequentieService();

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
	 * Sets the bijdrageSchema to be edited in the dialog.
	 *
	 * @param bijdrageSchema
	 */
	public void setBijdrageSchema(final BijdrageSchema bijdrageSchema) {
		this.bijdrageSchema = bijdrageSchema;

		bijdrageSchemaNaamField.setText(bijdrageSchema.getBijdrageSchemaNaam());
		if (bijdrageSchema.getBijdrageRenteFk() != null) {
			BijdrageRente bijdrageRente = bijdrageRenteService
					.getBijdrageRenteById(bijdrageSchema.getBijdrageRenteFk());
			bijdrageRentePercentageField.setText(bijdrageRente.getBijdrageRentePercentage().toString());
			bijdrageRenteDagenNaVervaldatumField
					.setText(Integer.toString(bijdrageRente.getBijdrageRenteNaVervaldatum()));
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
			bijdrageSchema.setBijdrageSchemaNaam(bijdrageSchemaNaamField.getText());

			BigDecimal bijdrageRentePercentage = new BigDecimal(bijdrageRentePercentageField.getText());
			int bijdrageRenteDagenNaVervaldatum = Integer.valueOf(bijdrageRenteDagenNaVervaldatumField.getText());
			BijdrageRente bijdrageRente = bijdrageRenteService.getBijdrageRenteByRentePercentageAndNaVervaldatum(
					bijdrageRentePercentage, bijdrageRenteDagenNaVervaldatum);

			long bijdrageRenteId;

			if (bijdrageRente == null) {
				BijdrageRente newBijdrageRente = new BijdrageRente(null, bijdrageRentePercentage,
						bijdrageRenteDagenNaVervaldatum);
				bijdrageRenteId = bijdrageRenteService.saveBijdrageRente(newBijdrageRente);
			} else {
				// we do not need to update an existing record of bijdrageRente
				// bijdrageRenteService.saveOrUpdateBijdrageRente(bijdrageRente);
				bijdrageRenteId = bijdrageRente.getBijdrageRenteId();
			}
			bijdrageSchema.setBijdrageRenteFk(bijdrageRenteId);

			BijdrageFrequentie bijdrageFrequentie = bijdrageFrequentieService.getAllBijdrageFrequenties().get(0);
			bijdrageSchema.setBijdrageFrequentieCode(bijdrageFrequentie.getBijdrageFrequentieCode());

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

		if (bijdrageSchemaNaamField.getText() == null || bijdrageSchemaNaamField.getText().length() == 0) {
			errorMessage += "Geen schemanaam ingevuld!\n";
		}
		if (bijdrageRentePercentageField.getText() == null || bijdrageRentePercentageField.getText().length() == 0) {
			errorMessage += "Geen rente percentage ingevuld!\n";
		} else {
			// try to parse the rente percentage into an BigDecimal.
			try {
				new BigDecimal(bijdrageRentePercentageField.getText());
			} catch (NumberFormatException e) {
				errorMessage += "Geen geldige waarde voor rente percentage ingevuld (moet een decimaal getal zijn)!\n";
			}
		}
		if (bijdrageRenteDagenNaVervaldatumField.getText() == null
				|| bijdrageRenteDagenNaVervaldatumField.getText().length() == 0) {
			errorMessage += "Geen dagen na vervaldatum ingevuld!\n";
		} else {
			// try to parse the bijdrageRenteDagenNaVervaldatum into an int.
			try {
				Integer.parseInt(bijdrageRenteDagenNaVervaldatumField.getText());
			} catch (NumberFormatException e) {
				errorMessage += "Geen geldige waarde voor dagen na vervaldatum ingevuld (moet een getal zijn)!\n";
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
