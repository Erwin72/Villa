package nl.tinkoczy.villa.view;

import javafx.fxml.FXML;
import javafx.scene.Node;
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
		createToolBar();

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

	/**
	 * Voor de meest voorkomende functies in het hoofdmenu zijn 10 knoppen te
	 * vinden net onder het hoofdmenu. Met deze knoppen kunt u snel een aantal
	 * belangrijke functies in villa bereiken. Al deze functies zijn ook via het
	 * menu te kiezen. Deze functies zijn:
	 * <ul>
	 * <li>Instellen werkdatum.</li>
	 * <li>Gegevens appartementen.</li>
	 * <li>Selecteren van appartementen voor het printen van overzichten.</li>
	 * <li>Invoeren en beheer van boekstukken en boekingen.</li>
	 * <li>Invoeren en beheer van fakturen.</li>
	 * <li>Selecteren van fakturen voor het printen van overzichten.</li>
	 * <li>Beheren van rubrieken en posten.</li>
	 * <li>Overzicht van de saldi van uw rekeningen.</li>
	 * <li>Beheren van relaties.</li>
	 * <li>Verlaat villa.</li>
	 * </ul>
	 * Icons are found at http://iconmonstr.com/business/
	 */
	private void createToolBar() {
		toolbar.getItems().add(createInstellenWerkDatumButton());
		toolbar.getItems().add(createGegevensAppartementenButton());
	}

	private Button createInstellenWerkDatumButton() {
		Image imageCalendar = new Image(getClass().getResourceAsStream("/images/iconmonstr-calendar-5-32.png"));
		Button werkDatumButton = new Button("", new ImageView(imageCalendar));
		werkDatumButton.setId("selecteer_werkdatum");
		werkDatumButton.setOnAction(event -> villaApp.showSelecteerWerkDatumDialog());
		return werkDatumButton;
	}

	private Node createGegevensAppartementenButton() {
		Image imageHome = new Image(getClass().getResourceAsStream("/images/iconmonstr-home-9-32.png"));
		Button gegevensAppartementenButton = new Button("", new ImageView(imageHome));
		gegevensAppartementenButton.setId("gegevens_appartementen");
		gegevensAppartementenButton.setOnAction(event -> villaApp.showGegevensAppartementenDialog());
		return gegevensAppartementenButton;
	}
}
