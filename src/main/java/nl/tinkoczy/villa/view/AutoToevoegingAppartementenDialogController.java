package nl.tinkoczy.villa.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import nl.tinkoczy.villa.model.Appartement;
import nl.tinkoczy.villa.model.BijdrageSchema;
import nl.tinkoczy.villa.service.IAppartementService;
import nl.tinkoczy.villa.service.IBijdrageSchemaService;
import nl.tinkoczy.villa.service.impl.AppartementService;
import nl.tinkoczy.villa.service.impl.BijdrageSchemaService;

public class AutoToevoegingAppartementenDialogController {

	final static Logger logger = LoggerFactory.getLogger(AutoToevoegingAppartementenDialogController.class);

	private final static String AANTAL_SPINNER = "aantalSpinner";
	private final static int AANTAL_SPINNER_MIN_VALUE = 1;
	private final static int AANTAL_SPINNER_MAX_VALUE = 1000;
	private final static int AANTAL_SPINNER_DEFAULT_VALUE = 3;
	private final static String NUMMER_OPHOGING_SPINNER = "nummerOphogingSpinner";
	private final static int NUMMER_OPHOGING_SPINNER_MIN_VALUE = 1;
	private final static int NUMMER_OPHOGING_SPINNER_MAX_VALUE = 10;
	private final static int NUMMER_OPHOGING_SPINNER_DEFAULT_VALUE = 2;
	private final static String NUMMER_POSITIES_SPINNER = "nummerPositiesSpinner";
	private final static int NUMMER_POSITIES_SPINNER_MIN_VALUE = 1;
	private final static int NUMMER_POSITIES_SPINNER_MAX_VALUE = 4;
	private final static int NUMMER_POSITIES_SPINNER_DEFAULT_VALUE = 2;
	private final static String STRAATNUMMER_OPHOGING_SPINNER = "straatnummerOphogingSpinner";
	private final static int STRAATNUMMER_OPHOGING_SPINNER_MIN_VALUE = 1;
	private final static int STRAATNUMMER_OPHOGING_SPINNER_MAX_VALUE = 10;
	private final static int STRAATNUMMER_OPHOGING_SPINNER_DEFAULT_VALUE = 2;

	@FXML
	private Spinner<Integer> aantalSpinner;
	@FXML
	private TextField constanteField;
	@FXML
	private TextField nummerField;
	@FXML
	private Spinner<Integer> nummerOphogingSpinner;
	@FXML
	private Spinner<Integer> nummerPositiesSpinner;
	@FXML
	private CheckBox nummerUitvullenCheckBox;
	@FXML
	private TextField resultaatField;
	@FXML
	private TextField postcodeField;
	@FXML
	private TextField plaatsField;
	@FXML
	private TextField straatField;
	@FXML
	private TextField vanafStraatnummerField;
	@FXML
	private Spinner<Integer> straatnummerOphogingSpinner;
	@FXML
	private ComboBox<BijdrageSchema> bijdrageSchemaNaamComboBox;

	private Stage dialogStage;
	private boolean okClicked = false;

	private Map<Integer, String> straatNummerCodeNummerMap = new TreeMap<>();

	private IBijdrageSchemaService bijdrageSchemaService;

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		bijdrageSchemaService = new BijdrageSchemaService();

		resultaatField.setEditable(false);

		initAantalSpinner();
		initNummerOphogingSpinner();
		initNummerPositiesSpinner();
		initStraatnummerOphogingSpinner();
		initBijdrageSchemaNaamComboBox();
		initVanafStraatnummerField();
		initConstanteField();
		initNummerField();
		initNummerUitvullenCheckBox();

		updateResultaatField();
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

			voegAppartementenToe();

			okClicked = true;
			dialogStage.close();
		}
	}

	private void voegAppartementenToe() {
		IAppartementService service = new AppartementService();

		for (Map.Entry<Integer, String> entry : straatNummerCodeNummerMap.entrySet()) {
			Appartement appartement = new Appartement();
			appartement.setAppartementCode(entry.getValue());
			appartement.setAppartementTransportdatum(null);
			appartement.setAppartementAdresStraat(straatField.getText() + " " + entry.getKey());
			appartement.setAppartementAdresPostcode(postcodeField.getText());
			appartement.setAppartementAdresPlaats(plaatsField.getText());
			if (bijdrageSchemaNaamComboBox.getSelectionModel().getSelectedItem() != null) {
				Long bijdrageSchemaId = bijdrageSchemaNaamComboBox.getSelectionModel().getSelectedItem()
						.getBijdrageSchemaId();
				service.saveOrUpdatePostWithBijdrageSchema(appartement, bijdrageSchemaId);
			} else {
				service.saveOrUpdateAppartement(appartement);
			}
		}
	}

	/**
	 * Called when the user clicks cancel.
	 */
	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

	private String isInputValidForResultaat() {
		String errorMessage = StringUtils.EMPTY;

		if (aantalSpinner.getValue() == null || !inBounds(aantalSpinner)) {
			errorMessage += "Geen Aantal ingevuld!\n";
		} else {
			try {
				new Integer(aantalSpinner.getValue());
			} catch (NumberFormatException e) {
				errorMessage += "Geen geldige waarde voor Aantal ingevuld (moet een getal zijn)!\n";
			}
		}
		if (nummerOphogingSpinner.getValue() == null || !inBounds(nummerOphogingSpinner)) {
			errorMessage += "Geen Ophoging ingevuld!\n";
		} else {
			try {
				new Integer(nummerOphogingSpinner.getValue());
			} catch (NumberFormatException e) {
				errorMessage += "Geen geldige waarde voor Ophoging ingevuld (moet een getal zijn)!\n";
			}
		}
		if (nummerPositiesSpinner.getValue() == null || !inBounds(nummerPositiesSpinner)) {
			errorMessage += "Geen Posities ingevuld!\n";
		} else {
			try {
				new Integer(nummerPositiesSpinner.getValue());
			} catch (NumberFormatException e) {
				errorMessage += "Geen geldige waarde voor Posities ingevuld (moet een getal zijn)!\n";
			}
		}
		if (straatnummerOphogingSpinner.getValue() == null || !inBounds(straatnummerOphogingSpinner)) {
			errorMessage += "Geen Straatophoging ingevuld!\n";
		} else {
			try {
				new Integer(straatnummerOphogingSpinner.getValue());
			} catch (NumberFormatException e) {
				errorMessage += "Geen geldige waarde voor Straatophoging ingevuld (moet een getal zijn)!\n";
			}
		}
		if (vanafStraatnummerField.getText() == null || StringUtils.isEmpty(vanafStraatnummerField.getText())) {
			errorMessage += "Geen vanaf straatnummer ingevuld!\n";
		}

		return errorMessage;
	}

	/**
	 * Validates the user input in the fields.
	 *
	 * @return true if the input is valid
	 */
	private boolean isInputValid() {
		String errorMessage = isInputValidForResultaat();

		if (postcodeField.getText() == null || StringUtils.isEmpty(postcodeField.getText())) {
			errorMessage += "Geen postcode ingevuld!\n";
		}
		if (plaatsField.getText() == null || StringUtils.isEmpty(plaatsField.getText())) {
			errorMessage += "Geen plaats ingevuld!\n";
		}
		if (straatField.getText() == null || StringUtils.isEmpty(straatField.getText())) {
			errorMessage += "Geen straat ingevuld!\n";
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

	private boolean inBounds(final Spinner<Integer> spinner) {
		int intValue = spinner.getValue();
		switch (spinner.getId()) {
		case AANTAL_SPINNER:
			return (intValue >= AANTAL_SPINNER_MIN_VALUE && intValue <= AANTAL_SPINNER_MAX_VALUE);
		case NUMMER_OPHOGING_SPINNER:
			return (intValue >= NUMMER_OPHOGING_SPINNER_MIN_VALUE && intValue <= NUMMER_OPHOGING_SPINNER_MAX_VALUE);
		case NUMMER_POSITIES_SPINNER:
			return (intValue >= NUMMER_POSITIES_SPINNER_MIN_VALUE && intValue <= NUMMER_POSITIES_SPINNER_MAX_VALUE);
		case STRAATNUMMER_OPHOGING_SPINNER:
			return (intValue >= STRAATNUMMER_OPHOGING_SPINNER_MIN_VALUE
					&& intValue <= STRAATNUMMER_OPHOGING_SPINNER_MAX_VALUE);
		default:
			return false;
		}
	}

	private void initAantalSpinner() {
		aantalSpinner.setId(AANTAL_SPINNER);
		aantalSpinner.setEditable(true);
		SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(
				AANTAL_SPINNER_MIN_VALUE, AANTAL_SPINNER_MAX_VALUE, AANTAL_SPINNER_DEFAULT_VALUE);
		IntegerStringConverter converter = new IntegerStringConverter();
		valueFactory.setConverter(converter);
		aantalSpinner.setValueFactory(valueFactory);
		aantalSpinner.getEditor().setOnAction(action -> {
			String text = aantalSpinner.getEditor().getText();
			SpinnerValueFactory<Integer> valueFactoryS = aantalSpinner.getValueFactory();
			Integer value = null;
			if (valueFactory != null) {
				value = valueFactoryS.getConverter().fromString(text);
				valueFactory.setValue(value);
			}
		});
		aantalSpinner.valueProperty().addListener((obs, oldValue, newValue) -> {
			logger.debug("Waarde invoer aantalSpinner: " + newValue);
			updateResultaatField();
		});
	}

	private void updateResultaatField() {
		if (StringUtils.isEmpty(isInputValidForResultaat())) {
			straatNummerCodeNummerMap = new TreeMap<>();

			recalculateNummers();

			resultaatField.setText(getResultaat());

			for (Map.Entry<Integer, String> entry : straatNummerCodeNummerMap.entrySet()) {
				logger.debug("Straatnummer: " + entry.getKey() + ", met codenummer: " + entry.getValue());
			}
		}
	}

	private String getResultaat() {
		String resultaat = StringUtils.EMPTY;
		String TEM = " t/m ";

		List<String> list = new ArrayList<String>(straatNummerCodeNummerMap.values());

		if (list.size() > 0) {
			resultaat = list.get(0);
		}
		if (list.size() > 1) {
			resultaat += (TEM + list.get(list.size() - 1));
		}

		return resultaat;
	}

	private void recalculateNummers() {

		int aantal = aantalSpinner.getValue();
		int straatNummer = Integer.parseInt(vanafStraatnummerField.getText());
		int straatnummerOphoging = straatnummerOphogingSpinner.getValue();

		int nummer = Integer.parseInt(nummerField.getText());
		int nummerOphoging = nummerOphogingSpinner.getValue();

		for (int i = 0; i < aantal; i++) {
			String nummerStr = Integer.toString(nummer);
			StringBuffer appartementCode = new StringBuffer(StringUtils.EMPTY);
			String constante = constanteField.getText();

			if (StringUtils.isNotEmpty(constante)) {
				appartementCode.append(constante);
			}
			if (nummerUitvullenCheckBox.isSelected()) {
				int uitvullenAantalPosities = nummerPositiesSpinner.getValue();
				while (nummerStr.length() < uitvullenAantalPosities) {
					nummerStr = "0" + nummerStr;
				}
			}
			appartementCode.append(nummerStr);

			straatNummerCodeNummerMap.put(straatNummer, appartementCode.toString());

			// increase for next iteration
			straatNummer += straatnummerOphoging;
			nummer += nummerOphoging;
		}
	}

	private void initNummerOphogingSpinner() {
		nummerOphogingSpinner.setId(NUMMER_OPHOGING_SPINNER);
		nummerOphogingSpinner.setEditable(true);
		SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(
				NUMMER_OPHOGING_SPINNER_MIN_VALUE, NUMMER_OPHOGING_SPINNER_MAX_VALUE,
				NUMMER_OPHOGING_SPINNER_DEFAULT_VALUE);
		IntegerStringConverter converter = new IntegerStringConverter();
		valueFactory.setConverter(converter);
		nummerOphogingSpinner.setValueFactory(valueFactory);
		nummerOphogingSpinner.getEditor().setOnAction(action -> {
			String text = nummerOphogingSpinner.getEditor().getText();
			SpinnerValueFactory<Integer> valueFactoryS = nummerOphogingSpinner.getValueFactory();
			Integer value = null;
			if (valueFactory != null) {
				value = valueFactoryS.getConverter().fromString(text);
				valueFactory.setValue(value);
			}
		});
		nummerOphogingSpinner.valueProperty().addListener((obs, oldValue, newValue) -> {
			logger.debug("Waarde invoer nummerOphogingSpinner: " + newValue);
			updateResultaatField();
		});
	}

	private void initNummerPositiesSpinner() {
		nummerPositiesSpinner.setId(NUMMER_POSITIES_SPINNER);
		nummerPositiesSpinner.setEditable(true);
		SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(
				NUMMER_POSITIES_SPINNER_MIN_VALUE, NUMMER_POSITIES_SPINNER_MAX_VALUE,
				NUMMER_POSITIES_SPINNER_DEFAULT_VALUE);
		IntegerStringConverter converter = new IntegerStringConverter();
		valueFactory.setConverter(converter);
		nummerPositiesSpinner.setValueFactory(valueFactory);
		nummerPositiesSpinner.getEditor().setOnAction(action -> {
			String text = nummerPositiesSpinner.getEditor().getText();
			SpinnerValueFactory<Integer> valueFactoryS = nummerPositiesSpinner.getValueFactory();
			Integer value = null;
			if (valueFactory != null) {
				value = valueFactoryS.getConverter().fromString(text);
				valueFactory.setValue(value);
			}
		});
		nummerPositiesSpinner.valueProperty().addListener((obs, oldValue, newValue) -> {
			logger.debug("Waarde invoer nummerPositiesSpinner: " + newValue);
			updateResultaatField();
		});
	}

	private void initStraatnummerOphogingSpinner() {
		straatnummerOphogingSpinner.setId(STRAATNUMMER_OPHOGING_SPINNER);
		straatnummerOphogingSpinner.setEditable(true);
		SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(
				STRAATNUMMER_OPHOGING_SPINNER_MIN_VALUE, STRAATNUMMER_OPHOGING_SPINNER_MAX_VALUE,
				STRAATNUMMER_OPHOGING_SPINNER_DEFAULT_VALUE);
		IntegerStringConverter converter = new IntegerStringConverter();
		valueFactory.setConverter(converter);
		straatnummerOphogingSpinner.setValueFactory(valueFactory);
		straatnummerOphogingSpinner.getEditor().setOnAction(action -> {
			String text = straatnummerOphogingSpinner.getEditor().getText();
			SpinnerValueFactory<Integer> valueFactoryS = straatnummerOphogingSpinner.getValueFactory();
			Integer value = null;
			if (valueFactory != null) {
				value = valueFactoryS.getConverter().fromString(text);
				valueFactory.setValue(value);
			}
		});
		straatnummerOphogingSpinner.valueProperty().addListener((obs, oldValue, newValue) -> {
			logger.debug("Waarde invoer straatnummerOphogingSpinner: " + newValue);
			updateResultaatField();
		});
	}

	private void initBijdrageSchemaNaamComboBox() {
		List<BijdrageSchema> bijdrageSchemaList = bijdrageSchemaService.getAllBijdrageSchemas();
		ObservableList<BijdrageSchema> oListBijdrageSchema = FXCollections.observableArrayList(bijdrageSchemaList);
		BijdrageSchemaStringConverter converter = new BijdrageSchemaStringConverter();
		bijdrageSchemaNaamComboBox.setConverter(converter);
		bijdrageSchemaNaamComboBox.setItems(oListBijdrageSchema);
	}

	private void initVanafStraatnummerField() {
		vanafStraatnummerField.setText("1");
		vanafStraatnummerField.textProperty().addListener((obs, oldValue, newValue) -> {
			logger.debug("Waarde invoer vanafStraatnummerField: " + newValue);
			updateResultaatField();
		});
	}

	private void initNummerUitvullenCheckBox() {
		nummerUitvullenCheckBox.setSelected(false);
		nummerUitvullenCheckBox.selectedProperty().addListener((obs, oldValue, newValue) -> {
			logger.debug("Waarde invoer nummerUitvullenCheckBox: " + newValue);
			updateResultaatField();
		});
	}

	private void initNummerField() {
		nummerField.setText(StringUtils.EMPTY);
		nummerField.setText("1");
		nummerField.textProperty().addListener((obs, oldValue, newValue) -> {
			logger.debug("Waarde invoer nummerField: " + newValue);
			updateResultaatField();
		});
	}

	private void initConstanteField() {
		constanteField.setText(StringUtils.EMPTY);
		constanteField.textProperty().addListener((obs, oldValue, newValue) -> {
			logger.debug("Waarde invoer constanteField: " + newValue);
			updateResultaatField();
		});
	}

	/**
	 * Converts String to Integer
	 */
	class IntegerStringConverter extends StringConverter<Integer> {

		@Override
		public String toString(final Integer object) {
			return object + "";
		}

		@Override
		public Integer fromString(final String string) {
			try {
				return Integer.parseInt(string);
			} catch (NumberFormatException e) {
				logger.debug("Waarde invoer geen integer: " + string);
				return 0;
			}
		}
	}

	/**
	 * Converts String to BijdrageSchema
	 */
	class BijdrageSchemaStringConverter extends StringConverter<BijdrageSchema> {

		@Override
		public String toString(final BijdrageSchema object) {
			return object.getBijdrageSchemaNaam();
		}

		@Override
		public BijdrageSchema fromString(final String string) {
			return null;
		}
	}
}
