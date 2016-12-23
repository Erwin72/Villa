package nl.tinkoczy.villa;

import java.io.IOException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import nl.tinkoczy.villa.config.ApplicationConfiguration;
import nl.tinkoczy.villa.config.ConfigFacade;
import nl.tinkoczy.villa.model.Post;
import nl.tinkoczy.villa.model.Rubriek;
import nl.tinkoczy.villa.service.DataBroker;
import nl.tinkoczy.villa.service.IPostService;
import nl.tinkoczy.villa.service.IRubriekService;
import nl.tinkoczy.villa.service.PostService;
import nl.tinkoczy.villa.service.RubriekService;
import nl.tinkoczy.villa.util.WerkDatumUtil;
import nl.tinkoczy.villa.view.PostEditDialogController;
import nl.tinkoczy.villa.view.PostOverviewController;
import nl.tinkoczy.villa.view.RootLayoutController;
import nl.tinkoczy.villa.view.RubriekEditDialogController;
import nl.tinkoczy.villa.view.RubriekOverviewController;
import nl.tinkoczy.villa.view.SelecteerWerkDatumDialogController;

public class VillaApp extends Application {

	final static Logger logger = LoggerFactory.getLogger(VillaApp.class);

	private URL applicationProperties;

	private Stage primaryStage;
	private BorderPane rootLayout;

	private final ObservableList<Rubriek> rubriekData = FXCollections.observableArrayList();
	private final ObservableList<Post> postData = FXCollections.observableArrayList();

	private IRubriekService rService;
	private IPostService pService;

	public VillaApp() {
		// Add some sample data
		rubriekData.add(new Rubriek(null, 100, "Inkomsten"));
		rubriekData.add(new Rubriek(null, 110, "Restitutie inkomsten"));
		rubriekData.add(new Rubriek(null, 200, "Huisvestingskosten"));
		rubriekData.add(new Rubriek(null, 210, "Restitutie huisvestingskosten"));
		rubriekData.add(new Rubriek(null, 300, "Grootonderhoud kosten"));
		rubriekData.add(new Rubriek(null, 310, "Restitutie grootonderhoud kosten"));
		rubriekData.add(new Rubriek(null, 400, "Administratiekosten"));
		rubriekData.add(new Rubriek(null, 410, "Restitutie administratiekosten"));
		rubriekData.add(new Rubriek(null, 900, "Kruisposten inkomsten"));
		rubriekData.add(new Rubriek(null, 910, "Kruisposten uitgaven"));

		postData.add(new Post(null, 101, "inkomst"));
		postData.add(new Post(null, 102, "inkomst-2"));
		postData.add(new Post(null, 201, "uitgave"));
		postData.add(new Post(null, 202, "uitgave-2"));
	}

	@Override
	public void start(final Stage primaryStage) throws Exception {
		logger.debug("Starting VillaApp");

		startConfig();
		initDBBroker();

		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Villa");

		// Set the application icon.
		this.primaryStage.getIcons().add(new Image("file:src/main/resources/images/address_book_32.png"));

		initRootLayout();

		rService = new RubriekService();
		for (Rubriek rubriek : getRubriekData()) {
			rService.saveOrUpdateRubriek(rubriek);
		}
		pService = new PostService();
		for (Post post : getPostData()) {
			pService.saveOrUpdatePost(post);
		}
		logger.debug("Started VillaApp");
	}

	public void closeVilla() {
		// TODO dialog met vraag om bevestiging om af te sluiten
		primaryStage.close();
	}

	private void startConfig() {
		ConfigFacade.initConfigLoader();
		ClassLoader classLoader = getClass().getClassLoader();
		ConfigFacade.loadConfig(ApplicationConfiguration.class, classLoader.getResource("application.properties"));
	}

	private void initDBBroker() {
		// initialize the DataBroker so the services may use it
		DataBroker.initDataBroker();
	}

	/**
	 * Returns the main stage.
	 *
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(final String[] args) {
		launch(args);
	}

	/**
	 * Initializes the root layout and tries to load the last opened person
	 * file.
	 */
	public void initRootLayout() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(VillaApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			// scene.getStylesheets().add("/DarkTheme.css");
			primaryStage.setScene(scene);

			// Give the controller access to the main app.
			RootLayoutController controller = loader.getController();
			controller.setVillaApp(this);

			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showRubriekAndPostTab() {
		TabPane tabPane = new TabPane();

		Tab rubriekTab = new Tab("Rubrieken", getRubriekOverview());
		Tab postTab = new Tab("Posten", getPostOverview());

		tabPane.getTabs().addAll(rubriekTab, postTab);

		rootLayout.setCenter(tabPane);
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
			RubriekOverviewController controller = loader.getController();
			controller.setVillaApp(this);

			return rubriekOverview;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * Opens a dialog to edit details for the specified rubriek. If the user
	 * clicks OK, the changes are saved into the provided rubriek object and
	 * true is returned.
	 *
	 * @param rubriek
	 *            the rubriek object to be edited
	 * @return true if the user clicked OK, false otherwise.
	 */
	public boolean showRubriekEditDialog(final Rubriek rubriek) {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(VillaApp.class.getResource("view/RubriekEditDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Wijzig Rubriek");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the rubriek into the controller.
			RubriekEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setRubriek(rubriek);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
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
			PostOverviewController controller = loader.getController();
			controller.setVillaApp(this);
			return postOverview;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Opens a dialog to edit details for the specified post. If the user clicks
	 * OK, the changes are saved into the provided post object and true is
	 * returned.
	 *
	 * @param post
	 *            the post object to be edited
	 * @return true if the user clicked OK, false otherwise.
	 */
	public boolean showPostEditDialog(final Post post) {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(VillaApp.class.getResource("view/PostEditDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Wijzig Post");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the post into the controller.
			PostEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setPost(post);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public ObservableList<Rubriek> getRubriekData() {
		return rubriekData;
	}

	public ObservableList<Post> getPostData() {
		return postData;
	}

	/**
	 * Opens a dialog to edit the current werkdatum.
	 *
	 * @return true if the user clicked OK, false otherwise.
	 */
	public boolean showSelecteerWerkDatumDialog() {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(VillaApp.class.getResource("view/SelecteerWerkDatumDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Werkdatum");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the post into the controller.
			SelecteerWerkDatumDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setWerkDatum(WerkDatumUtil.getVillaWerkDatum());

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Opens a dialog to show data about the appartementen.
	 *
	 * @return true if the user clicked OK, false otherwise.
	 */
	public boolean showGegevensAppartementenDialog() {
		return false;
	}

}
