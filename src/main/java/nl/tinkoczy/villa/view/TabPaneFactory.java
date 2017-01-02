package nl.tinkoczy.villa.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import nl.tinkoczy.villa.VillaApp;
import nl.tinkoczy.villa.config.ConfigFacade;
import nl.tinkoczy.villa.config.UserText;

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
		// TODO
		// Tab relatiePersoonTab = new
		// Tab(ConfigFacade.getStringValue(UserText.TAB_RELATIE_PERSOON),
		// getRelatiePersoonOverview());

		tabPane.getTabs().addAll(appartementTab);// , relatiePersoonTab);
		tabPane.getSelectionModel().select(appartementTab);

		appartementOverviewController.getAppartementTable().getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> {
					appartementOverviewController.showAppartementDetail(newValue);
					// TODO
					// if (newValue != null && newValue.getRelatieCode() !=
					// null) {
					// relatiePersoonOverviewController.setSelection(newValue.getRelatieCode());
					// }
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
			loader.setLocation(VillaApp.class.getResource("view/RelatieOverview.fxml"));
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
			loader.setLocation(VillaApp.class.getResource("view/RelatiePersoonOverview.fxml"));
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
			loader.setLocation(VillaApp.class.getResource("view/AppartementOverview.fxml"));
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
	 * Gets the rubrieken overview to show inside a tabPane.
	 */
	private AnchorPane getRubriekOverview() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(VillaApp.class.getResource("view/RubriekOverview.fxml"));
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
			loader.setLocation(VillaApp.class.getResource("view/PostOverview.fxml"));
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
			loader.setLocation(VillaApp.class.getResource("view/BijdrageSchemaOverview.fxml"));
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
			loader.setLocation(VillaApp.class.getResource("view/BijdrageOverview.fxml"));
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
