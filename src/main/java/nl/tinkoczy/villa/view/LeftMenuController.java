package nl.tinkoczy.villa.view;

import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import nl.tinkoczy.villa.VillaApp;

public class LeftMenuController {

	@FXML
	private Accordion accordion;

	// Reference to the main application.
	private VillaApp villaApp;

	/**
	 * Is called by the main application to give a reference back to itself.
	 *
	 * @param mainApp
	 */
	public void setVillaApp(final VillaApp villaApp) {
		this.villaApp = villaApp;

		// Add observable list data to the table
		// personTable.setItems(mainApp.getPersonData());
	}

	@FXML
	private void handleOpenRubrieken() {
		villaApp.showRubriekOverview();
	}

	@FXML
	private void handleOpenPosten() {
		villaApp.showPostOverview();
	}
}
