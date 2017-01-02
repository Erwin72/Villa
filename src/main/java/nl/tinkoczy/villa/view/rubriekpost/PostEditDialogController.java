package nl.tinkoczy.villa.view.rubriekpost;

import java.math.BigDecimal;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import nl.tinkoczy.villa.model.Post;

public class PostEditDialogController {

	@FXML
	private TextField postNummerField;
	@FXML
	private TextField postOmschrijvingField;
	@FXML
	private TextField postPassivaRekeningField;
	@FXML
	private TextField postStandaardBedragField;
	@FXML
	private TextField postStandaardBoekingOmschrijvingField;

	private Stage dialogStage;
	private Post post;
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
	 * Sets the post to be edited in the dialog.
	 *
	 * @param post
	 */
	public void setPost(final Post post) {
		this.post = post;

		if (post.getPostNummer() != null) {
			postNummerField.setText(Integer.toString(post.getPostNummer()));
		}
		postOmschrijvingField.setText(post.getPostOmschrijving());
		if (post.getPostPassivaRekening() != null) {
			postPassivaRekeningField.setText(Integer.toString(post.getPostPassivaRekening()));
		}
		if (post.getPostStandaardBedrag() != null) {
			postStandaardBedragField.setText(post.getPostStandaardBedrag().toString());
		}
		postStandaardBoekingOmschrijvingField.setText(post.getPostStandaardBoekingOmschrijving());
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
			post.setPostNummer(Integer.valueOf(postNummerField.getText()));
			post.setPostOmschrijving(postOmschrijvingField.getText());
			post.setPostPassivaRekening(Integer.valueOf(postPassivaRekeningField.getText()));
			post.setPostStandaardBedrag(new BigDecimal(postStandaardBedragField.getText()));
			post.setPostStandaardBoekingOmschrijving(postStandaardBoekingOmschrijvingField.getText());

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

		if (postNummerField.getText() == null || postNummerField.getText().length() == 0) {
			errorMessage += "Geen postnummer ingevuld!\n";
		} else {
			// try to parse the postnummer code into an int.
			try {
				Integer.parseInt(postNummerField.getText());
			} catch (NumberFormatException e) {
				errorMessage += "Geen geldig postnummer ingevuld (moet een getal zijn)!\n";
			}
		}
		if (postOmschrijvingField.getText() == null || postOmschrijvingField.getText().length() == 0) {
			errorMessage += "Geen post omschrijving ingevuld!\n";
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
