package nl.tinkoczy.villa.view;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import nl.tinkoczy.villa.VillaApp;
import nl.tinkoczy.villa.config.ConfigFacade;
import nl.tinkoczy.villa.config.UserText;
import nl.tinkoczy.villa.util.WerkDatumUtil;

public class RootLayoutController {

	@FXML
	private Menu bestandMenu;
	@FXML
	private Menu definitieMenu;
	@FXML
	private Menu appartementenMenu;
	@FXML
	private Menu boekingenMenu;
	@FXML
	private Menu relatiesMenu;
	@FXML
	private Menu overzichtenMenu;
	@FXML
	private Menu helpMenu;

	@FXML
	private ToolBar toolbar;

	@FXML
	private Label statusWerkDatumLabel;

	// Reference to the main application
	private VillaApp villaApp;

	public RootLayoutController() {

	}

	@FXML
	public void initialize() {
		createMenus();
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

	private void createMenus() {
		createBestandMenu();
		createDefinitieMenu();
		createAppartementenMenu();
		createBoekingenMenu();
		createRelatiesMenu();
		createOverzichtenMenu();
		createHelpMenu();
	}

	private void createBestandMenu() {
		bestandMenu.setText(ConfigFacade.getStringValue(UserText.MENU_LABEL_BESTAND));
		bestandMenu.getItems().clear();
	}

	private void createDefinitieMenu() {
		definitieMenu.setText(ConfigFacade.getStringValue(UserText.MENU_LABEL_DEFINITIE));
		definitieMenu.getItems().clear();
		MenuItem definieerBijdrageSchemaMenuItem = new MenuItem(
				ConfigFacade.getStringValue(UserText.MENU_DEFINITIE_ITEM_DEF_BIJDRAGESCHEMA));
		definieerBijdrageSchemaMenuItem.setOnAction(event -> villaApp.showDefinieerBijdrageSchemaTab());
		definitieMenu.getItems().add(definieerBijdrageSchemaMenuItem);
	}

	private void createAppartementenMenu() {
		appartementenMenu.setText(ConfigFacade.getStringValue(UserText.MENU_LABEL_APPARTEMENTEN));
		appartementenMenu.getItems().clear();
	}

	private void createBoekingenMenu() {
		boekingenMenu.setText(ConfigFacade.getStringValue(UserText.MENU_LABEL_BOEKINGEN));
		boekingenMenu.getItems().clear();
	}

	private void createRelatiesMenu() {
		relatiesMenu.setText(ConfigFacade.getStringValue(UserText.MENU_LABEL_RELATIES));
		relatiesMenu.getItems().clear();
	}

	private void createOverzichtenMenu() {
		overzichtenMenu.setText(ConfigFacade.getStringValue(UserText.MENU_LABEL_OVERZICHTEN));
		overzichtenMenu.getItems().clear();
	}

	private void createHelpMenu() {
		helpMenu.setText(ConfigFacade.getStringValue(UserText.MENU_LABEL_HELP));
		helpMenu.getItems().clear();
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
		Button button = new Button("", new ImageView(image));
		button.setId("selecteer_werkdatum");
		button.setTooltip(new Tooltip(ConfigFacade.getStringValue(UserText.TOOLBAR_TOOLTIP_INSTELLEN_WERKDATUM)));
		button.setOnAction(event -> villaApp.showSelecteerWerkDatumDialog());
		return button;
	}

	private Node createGegevensAppartementenButton() {
		Image image = new Image(getClass().getResourceAsStream("/images/iconmonstr-user-9-32.png"));
		Button button = new Button("", new ImageView(image));
		button.setId("gegevens_appartementen");
		button.setTooltip(new Tooltip(ConfigFacade.getStringValue(UserText.TOOLBAR_TOOLTIP_BEHEER_APPARTEMENTEN)));
		button.setOnAction(event -> villaApp.showGegevensAppartementenDialog());
		return button;
	}

	private Node createSelecteerAppartementenButton() {
		Image image = new Image(getClass().getResourceAsStream("/images/iconmonstr-user-22-32.png"));
		Button button = new Button("", new ImageView(image));
		button.setId("selecteer_appartementen");
		button.setTooltip(new Tooltip(ConfigFacade.getStringValue(UserText.TOOLBAR_TOOLTIP_SELECTEER_APPARTEMENTEN)));
		button.setOnAction(event -> villaApp.showGegevensAppartementenDialog());
		return button;
	}

	private Node createInvoerEnBeheerBoekstukkenEnBoekingenButton() {
		Image image = new Image(getClass().getResourceAsStream("/images/iconmonstr-book-18-32.png"));
		Button button = new Button("", new ImageView(image));
		button.setId("invoer_beheer_boekstukken_boekingen");
		button.setTooltip(new Tooltip(ConfigFacade.getStringValue(UserText.TOOLBAR_TOOLTIP_BEHEER_BOEKSTUKKEN)));
		button.setOnAction(event -> villaApp.showGegevensAppartementenDialog());
		return button;
	}

	private Node createInvoerEnBeheerFakturenButton() {
		Image image = new Image(getClass().getResourceAsStream("/images/iconmonstr-note-29-32.png"));
		Button button = new Button("", new ImageView(image));
		button.setId("invoer_beheer_fakturen");
		button.setTooltip(new Tooltip(ConfigFacade.getStringValue(UserText.TOOLBAR_TOOLTIP_BEHEER_FAKTUREN)));
		button.setOnAction(event -> villaApp.showGegevensAppartementenDialog());
		return button;
	}

	private Node createSelecteerFakturenButton() {
		Image image = new Image(getClass().getResourceAsStream("/images/iconmonstr-note-20-32.png"));
		Button button = new Button("", new ImageView(image));
		button.setId("selecteer_fakturen");
		button.setTooltip(new Tooltip(ConfigFacade.getStringValue(UserText.TOOLBAR_TOOLTIP_SELECTEER_FAKTUREN)));
		button.setOnAction(event -> villaApp.showGegevensAppartementenDialog());
		return button;
	}

	private Node createInvoerEnBeheerRubriekenEnPostenButton() {
		Image image = new Image(getClass().getResourceAsStream("/images/iconmonstr-archive-8-32.png"));
		Button button = new Button("", new ImageView(image));
		button.setId("invoer_beheer_rubrieken_posten");
		button.setTooltip(new Tooltip(ConfigFacade.getStringValue(UserText.TOOLBAR_TOOLTIP_BEHEER_RUBRIEKEN)));
		button.setOnAction(event -> villaApp.showRubriekAndPostTab());
		return button;
	}

	private Node createGegevensSaldiRekeningenButton() {
		Image image = new Image(getClass().getResourceAsStream("/images/iconmonstr-checkout-4-32.png"));
		Button button = new Button("", new ImageView(image));
		button.setId("gegevens_saldi_rekeningen");
		button.setTooltip(new Tooltip(ConfigFacade.getStringValue(UserText.TOOLBAR_TOOLTIP_OVERZICHT_SALDI)));
		button.setOnAction(event -> villaApp.showGegevensAppartementenDialog());
		return button;
	}

	private Node createInvoerEnBeheerRelatiesButton() {
		Image image = new Image(getClass().getResourceAsStream("/images/iconmonstr-id-card-22-32.png"));
		Button button = new Button("", new ImageView(image));
		button.setId("invoer_beheer_relaties");
		button.setTooltip(new Tooltip(ConfigFacade.getStringValue(UserText.TOOLBAR_TOOLTIP_BEHEER_RELATIES)));
		button.setOnAction(event -> villaApp.showRelatieAndPersoonTab());
		return button;
	}

	private Node createCloseVillaButton() {
		Image image = new Image(getClass().getResourceAsStream("/images/iconmonstr-x-mark-5-32.png"));
		Button closeVillaButton = new Button("", new ImageView(image));
		closeVillaButton.setId("close_villa");
		closeVillaButton.setTooltip(new Tooltip(ConfigFacade.getStringValue(UserText.TOOLBAR_TOOLTIP_CLOSE_VILLA)));
		closeVillaButton.setOnAction(event -> villaApp.closeVilla());
		return closeVillaButton;
	}
}
