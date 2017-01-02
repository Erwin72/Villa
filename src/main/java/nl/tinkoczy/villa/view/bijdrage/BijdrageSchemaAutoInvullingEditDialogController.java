package nl.tinkoczy.villa.view.bijdrage;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.beans.property.ObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import nl.tinkoczy.villa.model.Bijdrage;
import nl.tinkoczy.villa.model.BijdrageFrequentie;
import nl.tinkoczy.villa.model.BijdrageSchema;
import nl.tinkoczy.villa.service.IBijdrageFrequentieService;
import nl.tinkoczy.villa.service.IBijdrageService;
import nl.tinkoczy.villa.service.impl.BijdrageFrequentieService;
import nl.tinkoczy.villa.service.impl.BijdrageService;
import nl.tinkoczy.villa.util.DatumUtil;
import nl.tinkoczy.villa.util.WerkDatumUtil;

public class BijdrageSchemaAutoInvullingEditDialogController {

	private static final int NUMBER_OF_MONTHS_IN_YEAR = 12;

	final static Logger logger = LoggerFactory.getLogger(BijdrageSchemaAutoInvullingEditDialogController.class);

	@FXML
	private GridPane radioGridPane;
	@FXML
	private DatePicker startDatumPicker;
	@FXML
	private DatePicker eindDatumPicker;
	@FXML
	private TextField bedragField;

	private ToggleGroup group;
	private Stage dialogStage;
	private boolean okClicked = false;

	private BijdrageSchema bijdrageSchema;

	private IBijdrageFrequentieService bijdrageFrequentieService;
	private IBijdrageService bijdrageService;

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		bijdrageFrequentieService = new BijdrageFrequentieService();
		bijdrageService = new BijdrageService();
		initRadioButtonGroup();
		initFields();
	}

	public void setBijdrageSchema(final BijdrageSchema bijdrageSchema) {
		this.bijdrageSchema = bijdrageSchema;
	}

	private void initRadioButtonGroup() {

		List<BijdrageFrequentie> freqList = bijdrageFrequentieService.getAllBijdrageFrequenties();
		group = new ToggleGroup();

		boolean first = true;
		int rowIndex = 1;

		for (BijdrageFrequentie freq : freqList) {
			RadioButton rb = new RadioButton(freq.getBijdrageFrequentieOmschrijving());
			rb.setUserData(freq.bijdrageFrequentieAantalBetaalmomentenProperty());
			rb.setToggleGroup(group);
			radioGridPane.addRow(rowIndex, rb);
			rb.setSelected(first);
			first = false;
			rowIndex++;
		}
	}

	private void initFields() {
		startDatumPicker.setValue(DatumUtil.getFistDayOfYear());
		eindDatumPicker.setValue(DatumUtil.getLastDayOfYear());
		bedragField.setText("0.00");
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

			createBijdragenForBijdrageSchema();

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
	 * Validates the user input in the fields.
	 *
	 * @return true if the input is valid
	 */
	private boolean isInputValid() {
		String errorMessage = "";

		if (bedragField.getText() == null || bedragField.getText().length() == 0) {
			errorMessage += "Geen bedrag ingevuld!\n";
		} else {
			// try to parse the bedrag into an BigDecimal.
			try {
				new BigDecimal(bedragField.getText());
			} catch (NumberFormatException e) {
				errorMessage += "Geen geldige waarde voor bedrag ingevuld (moet een decimaal getal zijn)!\n";
			}
		}
		if (startDatumPicker.getValue() == null) {
			errorMessage += "Geen startdatum ingevuld!\n";
		}
		if (startDatumPicker.getValue().getYear() != WerkDatumUtil.getVillaWerkDatum().getYear()) {
			errorMessage += "Startdatum ligt niet in de periode van de boekhouding!\n";
		}
		if (eindDatumPicker.getValue() == null) {
			errorMessage += "Geen einddatum ingevuld!\n";
		}
		if (eindDatumPicker.getValue().getYear() != WerkDatumUtil.getVillaWerkDatum().getYear()) {
			errorMessage += "Einddatum ligt niet in de periode van de boekhouding!\n";
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

	@SuppressWarnings("unchecked")
	private void createBijdragenForBijdrageSchema() {
		ObjectProperty<Integer> bijdrageFrequentieAantalBetaalmomenten = (ObjectProperty<Integer>) group
				.getSelectedToggle().getUserData();
		logger.debug("Geselecteerde aantal betaalmomenten: " + bijdrageFrequentieAantalBetaalmomenten.get());
		LocalDate startDatum = startDatumPicker.getValue();
		logger.debug("Geselecteerde startDatum: " + startDatum);
		LocalDate eindDatum = eindDatumPicker.getValue();
		logger.debug("Geselecteerde eindDatum: " + eindDatum);
		BigDecimal bedrag = new BigDecimal(bedragField.getText());
		logger.debug("Geselecteerde bedrag: " + bedrag);

		int maandenResterend = NUMBER_OF_MONTHS_IN_YEAR - startDatum.getMonthValue();
		int factor = NUMBER_OF_MONTHS_IN_YEAR / bijdrageFrequentieAantalBetaalmomenten.get();
		int aantalBijdrageRecords = maandenResterend / factor;
		for (int i = 0; i <= aantalBijdrageRecords; i++) {
			Bijdrage bijdrage = new Bijdrage();
			bijdrage.setBijdrageId(null);
			bijdrage.setBijdrageDatum(startDatum.plusMonths(factor * i));
			bijdrage.setBijdrageBedrag(bedrag);
			bijdrage.setBijdrageVoldaan(false);
			bijdrage.setBijdrageSchemaNaam(bijdrageSchema.getBijdrageSchemaNaam());
			bijdrageService.saveOrUpdateBijdrageWithBijdrageSchema(bijdrage, bijdrageSchema.getBijdrageSchemaNaam());
		}
	}
}
