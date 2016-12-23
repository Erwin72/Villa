package nl.tinkoczy.villa;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import nl.tinkoczy.villa.view.PostOverviewController;
import nl.tinkoczy.villa.view.RubriekOverviewController;

public class RubriekAndPostTabPaneFactory {

	TabPane tabPane;

	private RubriekOverviewController rubriekOverviewController;
	private PostOverviewController postOverviewController;

	// Reference to the main application.
	private VillaApp villaApp;

	public RubriekAndPostTabPaneFactory() {
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

		Tab rubriekTab = new Tab("Rubrieken", getRubriekOverview());
		Tab postTab = new Tab("Posten", getPostOverview());

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

	/**
	 * Gets the rubrieken overview to show inside a tabPane.
	 */
	public AnchorPane getRubriekOverview() {
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
	public AnchorPane getPostOverview() {
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

}
