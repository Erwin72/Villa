package nl.tinkoczy.villa;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import nl.tinkoczy.villa.view.BijdrageOverviewController;
import nl.tinkoczy.villa.view.BijdrageSchemaOverviewController;

public class DefinieerBijdrageSchemaTabPaneFactory {

	TabPane tabPane;

	private BijdrageSchemaOverviewController bijdrageSchemaOverviewController;
	private BijdrageOverviewController bijdrageOverviewController;

	// Reference to the main application.
	private VillaApp villaApp;

	public DefinieerBijdrageSchemaTabPaneFactory() {
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

	public TabPane createAndGet() {
		tabPane = new TabPane();

		Tab bijdrageSchemaTab = new Tab("Schema", getBijdrageSchemaOverview());
		Tab bijdragenPersoonTab = new Tab("Bijdragen", getBijdrageOverview());

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
	 * Gets the bijdrageschema overview to show inside a tabPane.
	 */
	public AnchorPane getBijdrageSchemaOverview() {
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
	public AnchorPane getBijdrageOverview() {
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
