package nl.tinkoczy.villa.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import nl.tinkoczy.villa.VillaApp;
import nl.tinkoczy.villa.util.WerkDatumUtil;

public class RootLayoutController {

	@FXML
	ToolBar toolbar;

	@FXML
	Label statusWerkDatumLabel;

	// Reference to the main application
	private VillaApp villaApp;

	public RootLayoutController() {

	}

	@FXML
	public void initialize() {
		toolbar.getItems().add(createWerkDatumButton());
		statusWerkDatumLabel.textProperty().bind(WerkDatumUtil.getVillaWerkDatumAsString());
	}

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

	private Button createWerkDatumButton() {
		Image imageCalendar = new Image(getClass().getResourceAsStream("/images/iconmonstr-calendar-5-32.png"));
		Button werkDatumButton = new Button("", new ImageView(imageCalendar));
		werkDatumButton.setId("selecteer_werkdatum");
		werkDatumButton.setOnAction(event -> villaApp.showSelecteerWerkDatumDialog());
		return werkDatumButton;
	}

}
