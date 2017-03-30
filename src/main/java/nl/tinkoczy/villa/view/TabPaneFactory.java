package nl.tinkoczy.villa.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import nl.tinkoczy.villa.VillaApp;
import nl.tinkoczy.villa.config.ApplicationConfiguration;
import nl.tinkoczy.villa.config.ConfigFacade;
import nl.tinkoczy.villa.config.UserText;
import nl.tinkoczy.villa.view.appartement.AppartementBijdrageOverviewController;
import nl.tinkoczy.villa.view.appartement.AppartementOverviewController;
import nl.tinkoczy.villa.view.bijdrage.BijdrageOverviewController;
import nl.tinkoczy.villa.view.bijdrage.BijdrageSchemaOverviewController;
import nl.tinkoczy.villa.view.relatie.RelatieOverviewController;
import nl.tinkoczy.villa.view.relatie.RelatiePersoonOverviewController;
import nl.tinkoczy.villa.view.rubriekpost.PostOverviewController;
import nl.tinkoczy.villa.view.rubriekpost.RubriekOverviewController;

/**
 * Factory class that recreates controller loaded tabpanes for functional use
 *
 */
public class TabPaneFactory {

	TabPane tabPane;

	private RelatieOverviewController relatieOverviewController;
	private RelatiePersoonOverviewController relatiePersoonOverviewController;
	private RubriekOverviewController rubriekOverviewController;
	private PostOverviewController postOverviewController;
	private BijdrageSchemaOverviewController bijdrageSchemaOverviewController;
	private BijdrageOverviewController bijdrageOverviewController;
	private AppartementOverviewController appartementOverviewController;
	private AppartementBijdrageOverviewController appartementBijdrageOverviewController;

	// Reference to the main application.
	private VillaApp villaApp;

	public TabPaneFactory() {
		super();
	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 *
	 * @param mainApp
	 */
	public void setVillaApp(final VillaApp villaApp) {
		this.villaApp = villaApp;
	}

	public TabPane createAndGetRelatieTabPane() {
		tabPane = new TabPane();

		Tab relatieTab = new Tab(ConfigFacade.getStringValue(UserText.TAB_RELATIE), getRelatieOverview());
		Tab relatiePersoonTab = new Tab(ConfigFacade.getStringValue(UserText.TAB_RELATIE_PERSOON),
				getRelatiePersoonOverview());

		tabPane.getTabs().addAll(relatieTab, relatiePersoonTab);
		tabPane.getSelectionModel().select(relatieTab);

		relatieOverviewController.getRelatieTable().getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> {
					relatieOverviewController.showRelatieDetail(newValue);
					if (newValue != null && newValue.getRelatieCode() != null) {
						relatiePersoonOverviewController.setSelection(newValue.getRelatieCode());
					}
				});

		relatieOverviewController.getRelatieTable().getSelectionModel().select(0);

		return tabPane;
	}

	public TabPane createAndGetAppartementTabPane() {
		tabPane = new TabPane();

		Tab appartementTab = new Tab(ConfigFacade.getStringValue(UserText.TAB_APPARTEMENT), getAppartementOverview());
		Tab appartementBijdrageTab = new Tab(ConfigFacade.getStringValue(UserText.TAB_APPARTEMENT_BIJDRAGE),
				getAppartementBijdrageOverview());

		tabPane.getTabs().addAll(appartementTab, appartementBijdrageTab);
		tabPane.getSelectionModel().select(appartementTab);

		appartementOverviewController.getAppartementTable().getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> {
					appartementOverviewController.showAppartementDetail(newValue);
					if (newValue != null) {
						appartementBijdrageOverviewController.setSelection(newValue);
					}
				});

		appartementOverviewController.getAppartementTable().getSelectionModel().select(0);

		return tabPane;
	}

	public TabPane createAndGetRubriekTabPane() {
		tabPane = new TabPane();

		Tab rubriekTab = new Tab(ConfigFacade.getStringValue(UserText.TAB_RUBRIEK), getRubriekOverview());
		Tab postTab = new Tab(ConfigFacade.getStringValue(UserText.TAB_POST), getPostOverview());

		tabPane.getTabs().addAll(rubriekTab, postTab);
		tabPane.getSelectionModel().select(rubriekTab);

		rubriekOverviewController.getRubriekTable().getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> {
					rubriekOverviewController.showRubriekDetail(newValue);
					if (newValue != null && newValue.getRubriekNummer() != null) {
						postOverviewController.setSelection(newValue.getRubriekNummer());
					}
				});

		rubriekOverviewController.getRubriekTable().getSelectionModel().select(0);

		return tabPane;
	}

	public TabPane createAndGetBijdrageSchemaTabPane() {
		tabPane = new TabPane();

		Tab bijdrageSchemaTab = new Tab(ConfigFacade.getStringValue(UserText.TAB_BIJDRAGESCHEMA),
				getBijdrageSchemaOverview());
		Tab bijdragenPersoonTab = new Tab(ConfigFacade.getStringValue(UserText.TAB_BIJDRAGE), getBijdrageOverview());

		tabPane.getTabs().addAll(bijdrageSchemaTab, bijdragenPersoonTab);
		tabPane.getSelectionModel().select(bijdrageSchemaTab);

		bijdrageSchemaOverviewController.getBijdrageSchemaTable().getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> {
					bijdrageSchemaOverviewController.showBijdrageSchemaDetail(newValue);
					if (newValue != null && newValue.getBijdrageSchemaNaam() != null) {
						bijdrageOverviewController.setSelection(newValue.getBijdrageSchemaNaam());
					}
				});

		bijdrageSchemaOverviewController.getBijdrageSchemaTable().getSelectionModel().select(0);

		return tabPane;
	}

	/**
	 * Gets the relaties overview to show inside a tabPane.
	 */
	private AnchorPane getRelatieOverview() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(VillaApp.class
					.getResource(ConfigFacade.getStringValue(ApplicationConfiguration.FXML_RELATIE_OVERVIEW)));
			AnchorPane relatieOverview = (AnchorPane) loader.load();

			// Give the controller access to the main app.
			relatieOverviewController = loader.getController();
			relatieOverviewController.setVillaApp(villaApp);

			return relatieOverview;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * Gets the personen overview to show inside a tabPane.
	 */
	private AnchorPane getRelatiePersoonOverview() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(VillaApp.class
					.getResource(ConfigFacade.getStringValue(ApplicationConfiguration.FXML_RELATIE_PERSOON_OVERVIEW)));

			AnchorPane relatiePersoonOverview = (AnchorPane) loader.load();

			// Give the controller access to the main app.
			relatiePersoonOverviewController = loader.getController();
			relatiePersoonOverviewController.setVillaApp(villaApp);

			return relatiePersoonOverview;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Gets the appartementen overview to show inside a tabPane.
	 */
	private AnchorPane getAppartementOverview() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(VillaApp.class
					.getResource(ConfigFacade.getStringValue(ApplicationConfiguration.FXML_APPARTEMENT_OVERVIEW)));
			AnchorPane appartementOverview = (AnchorPane) loader.load();

			// Give the controller access to the main app.
			appartementOverviewController = loader.getController();
			appartementOverviewController.setVillaApp(villaApp);

			return appartementOverview;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * Gets the appartement with bijdrage and paid bijdrage (=boeking) overview
	 * to show inside a tabPane.
	 */
	private AnchorPane getAppartementBijdrageOverview() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(VillaApp.class.getResource(
					ConfigFacade.getStringValue(ApplicationConfiguration.FXML_APPARTEMENT_BIJDRAGE_OVERVIEW)));
			AnchorPane appartementOverview = (AnchorPane) loader.load();

			// Give the controller access to the main app.
			appartementBijdrageOverviewController = loader.getController();
			appartementBijdrageOverviewController.setVillaApp(villaApp);

			return appartementOverview;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * Gets the rubrieken overview to show inside a tabPane.
	 */
	private AnchorPane getRubriekOverview() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(VillaApp.class
					.getResource(ConfigFacade.getStringValue(ApplicationConfiguration.FXML_RUBRIEK_OVERVIEW)));
			AnchorPane rubriekOverview = (AnchorPane) loader.load();

			// Give the controller access to the main app.
			rubriekOverviewController = loader.getController();
			rubriekOverviewController.setVillaApp(villaApp);

			return rubriekOverview;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * Gets the posten overview to show inside a tabPane.
	 */
	private AnchorPane getPostOverview() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(VillaApp.class
					.getResource(ConfigFacade.getStringValue(ApplicationConfiguration.FXML_POST_OVERVIEW)));
			AnchorPane postOverview = (AnchorPane) loader.load();

			// Give the controller access to the main app.
			postOverviewController = loader.getController();
			postOverviewController.setVillaApp(villaApp);

			return postOverview;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Gets the bijdrageschema overview to show inside a tabPane.
	 */
	private AnchorPane getBijdrageSchemaOverview() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(VillaApp.class
					.getResource(ConfigFacade.getStringValue(ApplicationConfiguration.FXML_BIJDRAGE_SCHEMA_OVERVIEW)));
			AnchorPane bijdrageSchemaOverview = (AnchorPane) loader.load();

			// Give the controller access to the main app.
			bijdrageSchemaOverviewController = loader.getController();
			bijdrageSchemaOverviewController.setVillaApp(villaApp);

			return bijdrageSchemaOverview;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * Gets the bijdragen overview to show inside a tabPane.
	 */
	private AnchorPane getBijdrageOverview() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(VillaApp.class
					.getResource(ConfigFacade.getStringValue(ApplicationConfiguration.FXML_BIJDRAGE_OVERVIEW)));
			AnchorPane bijdrageOverview = (AnchorPane) loader.load();

			// Give the controller access to the main app.
			bijdrageOverviewController = loader.getController();
			bijdrageOverviewController.setVillaApp(villaApp);

			return bijdrageOverview;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
