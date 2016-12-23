package nl.tinkoczy.villa.view;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
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
	 * <li>Invoeren en beheer van rubrieken en posten.</li>
	 * <li>Overzicht van de saldi van uw rekeningen.</li>
	 * <li>Invoeren en beheer van relaties.</li>
	 * <li>Verlaat villa.</li>
	 * </ul>
	 * Icons are found at http://iconmonstr.com/business/
	 */
	private void createToolBar() {
		toolbar.getItems().add(createInstellenWerkDatumButton());
		toolbar.getItems().add(new Separator());
		toolbar.getItems().add(createGegevensAppartementenButton());
		toolbar.getItems().add(createSelecteerAppartementenButton());
		toolbar.getItems().add(new Separator());
		toolbar.getItems().add(createInvoerEnBeheerBoekstukkenEnBoekingenButton());
		toolbar.getItems().add(new Separator());
		toolbar.getItems().add(createInvoerEnBeheerFakturenButton());
		toolbar.getItems().add(createSelecteerFakturenButton());
		toolbar.getItems().add(new Separator());
		toolbar.getItems().add(createInvoerEnBeheerRubriekenEnPostenButton());
		toolbar.getItems().add(new Separator());
		toolbar.getItems().add(createGegevensSaldiRekeningenButton());
		toolbar.getItems().add(new Separator());
		toolbar.getItems().add(createInvoerEnBeheerRelatiesButton());
		toolbar.getItems().add(new Separator());
		toolbar.getItems().add(createCloseVillaButton());
	}

	private Button createInstellenWerkDatumButton() {
		Image image = new Image(getClass().getResourceAsStream("/images/iconmonstr-calendar-5-32.png"));
		Button werkDatumButton = new Button("", new ImageView(image));
		werkDatumButton.setId("selecteer_werkdatum");
		werkDatumButton.setOnAction(event -> villaApp.showSelecteerWerkDatumDialog());
		return werkDatumButton;
	}

	private Node createGegevensAppartementenButton() {
		Image image = new Image(getClass().getResourceAsStream("/images/iconmonstr-user-9-32.png"));
		Button gegevensAppartementenButton = new Button("", new ImageView(image));
		gegevensAppartementenButton.setId("gegevens_appartementen");
		gegevensAppartementenButton.setOnAction(event -> villaApp.showGegevensAppartementenDialog());
		return gegevensAppartementenButton;
	}

	private Node createSelecteerAppartementenButton() {
		Image image = new Image(getClass().getResourceAsStream("/images/iconmonstr-user-22-32.png"));
		Button selecteerAppartementenButton = new Button("", new ImageView(image));
		selecteerAppartementenButton.setId("selecteer_appartementen");
		selecteerAppartementenButton.setOnAction(event -> villaApp.showGegevensAppartementenDialog());
		return selecteerAppartementenButton;
	}

	private Node createInvoerEnBeheerBoekstukkenEnBoekingenButton() {
		Image image = new Image(getClass().getResourceAsStream("/images/iconmonstr-book-18-32.png"));
		Button invoerEnBeheerBoekstukkenEnBoekingenButton = new Button("", new ImageView(image));
		invoerEnBeheerBoekstukkenEnBoekingenButton.setId("invoer_beheer_boekstukken_boekingen");
		invoerEnBeheerBoekstukkenEnBoekingenButton.setOnAction(event -> villaApp.showGegevensAppartementenDialog());
		return invoerEnBeheerBoekstukkenEnBoekingenButton;
	}

	private Node createInvoerEnBeheerFakturenButton() {
		Image image = new Image(getClass().getResourceAsStream("/images/iconmonstr-note-29-32.png"));
		Button invoerEnBeheerFakturenButton = new Button("", new ImageView(image));
		invoerEnBeheerFakturenButton.setId("invoer_beheer_fakturen");
		invoerEnBeheerFakturenButton.setOnAction(event -> villaApp.showGegevensAppartementenDialog());
		return invoerEnBeheerFakturenButton;
	}

	private Node createSelecteerFakturenButton() {
		Image image = new Image(getClass().getResourceAsStream("/images/iconmonstr-note-20-32.png"));
		Button selecteerFakturenButton = new Button("", new ImageView(image));
		selecteerFakturenButton.setId("selecteer_fakturen");
		selecteerFakturenButton.setOnAction(event -> villaApp.showGegevensAppartementenDialog());
		return selecteerFakturenButton;
	}

	private Node createInvoerEnBeheerRubriekenEnPostenButton() {
		Image image = new Image(getClass().getResourceAsStream("/images/iconmonstr-archive-8-32.png"));
		Button invoerEnBeheerRubriekenEnPostenButton = new Button("", new ImageView(image));
		invoerEnBeheerRubriekenEnPostenButton.setId("invoer_beheer_rubrieken_posten");
		invoerEnBeheerRubriekenEnPostenButton.setOnAction(event -> villaApp.showGegevensAppartementenDialog());
		return invoerEnBeheerRubriekenEnPostenButton;
	}

	private Node createGegevensSaldiRekeningenButton() {
		Image image = new Image(getClass().getResourceAsStream("/images/iconmonstr-checkout-4-32.png"));
		Button gegevensSaldiRekeningenButton = new Button("", new ImageView(image));
		gegevensSaldiRekeningenButton.setId("gegevens_saldi_rekeningen");
		gegevensSaldiRekeningenButton.setOnAction(event -> villaApp.showGegevensAppartementenDialog());
		return gegevensSaldiRekeningenButton;
	}

	private Node createInvoerEnBeheerRelatiesButton() {
		Image image = new Image(getClass().getResourceAsStream("/images/iconmonstr-id-card-22-32.png"));
		Button invoerEnBeheerRelatiesButton = new Button("", new ImageView(image));
		invoerEnBeheerRelatiesButton.setId("invoer_beheer_relaties");
		invoerEnBeheerRelatiesButton.setOnAction(event -> villaApp.showGegevensAppartementenDialog());
		return invoerEnBeheerRelatiesButton;
	}

	private Node createCloseVillaButton() {
		Image image = new Image(getClass().getResourceAsStream("/images/iconmonstr-x-mark-5-32.png"));
		Button closeVillaButton = new Button("", new ImageView(image));
		closeVillaButton.setId("close_villa");
		closeVillaButton.setOnAction(event -> villaApp.closeVilla());
		return closeVillaButton;
	}
}
