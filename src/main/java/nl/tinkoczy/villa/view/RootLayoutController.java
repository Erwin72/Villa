package nl.tinkoczy.villa.view;

import javafx.fxml.FXML;
import nl.tinkoczy.villa.VillaApp;

public class RootLayoutController {

	// Reference to the main application
	private VillaApp villaApp;

	/**
	 * Is called by the main application to give a reference back to itself.
	 *
	 * @param villaApp
	 */
	public void setVillaApp(final VillaApp villaApp) {
		this.villaApp = villaApp;
	}

	/**
	 * Closes the application.
	 */
	@FXML
	private void handleExit() {
		System.exit(0);
	}
}
