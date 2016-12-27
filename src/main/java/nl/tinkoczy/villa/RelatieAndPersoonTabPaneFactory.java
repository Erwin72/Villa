package nl.tinkoczy.villa;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import nl.tinkoczy.villa.view.RelatieOverviewController;
import nl.tinkoczy.villa.view.RelatiePersoonOverviewController;

public class RelatieAndPersoonTabPaneFactory {

	TabPane tabPane;

	private RelatieOverviewController relatieOverviewController;
	private RelatiePersoonOverviewController relatiePersoonOverviewController;

	// Reference to the main application.
	private VillaApp villaApp;

	public RelatieAndPersoonTabPaneFactory() {
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

		Tab relatieTab = new Tab("Relaties", getRelatieOverview());
		Tab relatiePersoonTab = new Tab("Personen", getRelatiePersoonOverview());

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

	/**
	 * Gets the relaties overview to show inside a tabPane.
	 */
	public AnchorPane getRelatieOverview() {
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
	public AnchorPane getRelatiePersoonOverview() {
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

}
