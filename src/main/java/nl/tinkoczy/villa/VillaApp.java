package nl.tinkoczy.villa;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import nl.tinkoczy.villa.config.ApplicationConfiguration;
import nl.tinkoczy.villa.config.ConfigFacade;
import nl.tinkoczy.villa.config.UserText;
import nl.tinkoczy.villa.service.impl.DataBroker;
import nl.tinkoczy.villa.util.InitVillaData;
import nl.tinkoczy.villa.view.RootLayoutController;

public class VillaApp extends Application {

	private static final Logger logger = LoggerFactory.getLogger(VillaApp.class);

	private Stage primaryStage;
	private BorderPane rootLayout;

	public VillaApp() {
		// default constructor
	}

	@Override
	public void start(final Stage primaryStage) throws Exception {
		logger.debug("Starting VillaApp");

		startConfig();
		initDBBroker();

		this.primaryStage = primaryStage;
		this.primaryStage.setTitle(ConfigFacade.getStringValue(UserText.APPLICATIETITEL));

		// Set the application icon.
		this.primaryStage.getIcons().add(new Image("file:src/main/resources/images/address_book_32.png"));

		initRootLayout();

		// load default data
		InitVillaData.parseData();
		InitVillaData.persistData();

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
		ConfigFacade.loadConfig(UserText.class, classLoader.getResource("usertext.properties"));
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
	 * Initializes the root layout file.
	 */
	public void initRootLayout() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(
					VillaApp.class.getResource(ConfigFacade.getStringValue(ApplicationConfiguration.FXML_ROOT_LAYOUT)));
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
			logger.error("Unable to read rootlayout", e);
		}
	}

	public BorderPane getRootLayout() {
		return rootLayout;
	}
}
