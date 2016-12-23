package nl.tinkoczy.villa.view;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import nl.tinkoczy.villa.util.WerkDatumUtil;

public class SelecteerWerkDatumDialogController {

	final static Logger logger = LoggerFactory.getLogger(SelecteerWerkDatumDialogController.class);

	@FXML
	private DatePicker werkDatumPicker;

	private Stage dialogStage;
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
	 * Sets the werkDatum to be edited in the dialog.
	 *
	 * @param werkDatum
	 */
	public void setWerkDatum(final LocalDate werkDatum) {
		logger.debug("setWerkDatum, werkDatum: " + werkDatum);
		if (werkDatum != null) {
			werkDatumPicker.setValue(werkDatum);
		} else {
			werkDatumPicker.setValue(LocalDate.now());
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
		logger.debug("handleOk, nieuwe werkDatum: " + werkDatumPicker.getValue());
		WerkDatumUtil.setVillaWerkDatum(werkDatumPicker.getValue());

		okClicked = true;
		dialogStage.close();
	}

	/**
	 * Called when the user clicks cancel.
	 */
	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

}
