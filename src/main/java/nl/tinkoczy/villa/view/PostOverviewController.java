package nl.tinkoczy.villa.view;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import nl.tinkoczy.villa.VillaApp;
import nl.tinkoczy.villa.model.Post;
import nl.tinkoczy.villa.model.Rubriek;
import nl.tinkoczy.villa.service.IPostService;
import nl.tinkoczy.villa.service.impl.PostService;
import nl.tinkoczy.villa.service.impl.RubriekService;

public class PostOverviewController {

	final static Logger logger = LoggerFactory.getLogger(PostOverviewController.class);

	@FXML
	private TableView<Post> postTable;
	@FXML
	private TableColumn<Post, Integer> postNummerColumn;
	@FXML
	private TableColumn<Post, String> postOmschrijvingColumn;

	@FXML
	private Label postNummerLabel;
	@FXML
	private Label postOmschrijvingLabel;
	@FXML
	private Label postPassivaRekeningLabel;
	@FXML
	private Label postStandaardBedragLabel;
	@FXML
	private Label postStandaardBoekingOmschrijvingLabel;

	private IPostService service;

	private ObservableList<Post> oListPost = FXCollections.observableArrayList();

	// Reference to the main application.
	private VillaApp villaApp;

	// Reference to the selected rubriek.
	private Rubriek selectedRubriek;

	public PostOverviewController() {

	}

	@FXML
	private void initialize() {
		logger.debug("Initialize PostOverviewController");
		service = new PostService();
		// Initialize the person table with the two columns.
		postNummerColumn.setCellValueFactory(cellData -> cellData.getValue().postNummerProperty());
		postOmschrijvingColumn.setCellValueFactory(cellData -> cellData.getValue().postOmschrijvingProperty());

		// Clear post details.
		showPostDetail(null);

		// Listen for selection changes and show the person details when
		// changed.
		postTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showPostDetail(newValue));

		logger.debug("Initialized PostOverviewController");
	}

	/**
	 * Fills all text fields to show details about the post. If the specified
	 * post is null, all text fields are cleared.
	 *
	 * @param post
	 *            the post or null
	 */
	private void showPostDetail(final Post post) {
		if (post != null) {
			// Fill the labels with info from the rubriek object.
			postNummerLabel.setText(Integer.toString(post.getPostNummer()));
			postOmschrijvingLabel.setText(post.getPostOmschrijving());
			postPassivaRekeningLabel.setText(Integer.toString(post.getPostPassivaRekening()));
			postStandaardBedragLabel.setText(post.getPostStandaardBedrag().toString());
			postStandaardBoekingOmschrijvingLabel.setText(post.getPostStandaardBoekingOmschrijving());
		} else {
			// Post is null, remove all the text.
			postNummerLabel.setText("");
			postOmschrijvingLabel.setText("");
			postPassivaRekeningLabel.setText("");
			postStandaardBedragLabel.setText("");
			postStandaardBoekingOmschrijvingLabel.setText("");
		}
	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 *
	 * @param mainApp
	 */
	public void setVillaApp(final VillaApp villaApp) {
		this.villaApp = villaApp;

		// Add observable list data to the table
		oListPost = FXCollections.observableArrayList(service.getAllPosten());
		postTable.setItems(oListPost);
	}

	/**
	 * Called when the user clicks on the delete button.
	 */
	@FXML
	private void handleDeletePost() {
		Post selectedPost = postTable.getSelectionModel().getSelectedItem();
		if (selectedPost != null) {
			postTable.getItems().remove(selectedPost);
			service.deletePost(selectedPost);
			setSelection(selectedRubriek.getRubriekNummer());
		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(villaApp.getPrimaryStage());
			alert.setTitle("Geen selectie");
			alert.setHeaderText("Geen Post geselecteerd");
			alert.setContentText("Selecteer een post in the tabel a.u.b.");

			alert.showAndWait();
		}
	}

	/**
	 * Called when the user clicks the new button. Opens a dialog to edit
	 * details for a new post.
	 */
	@FXML
	private void handleNewPost() {
		Post tempPost = new Post();
		tempPost.setRubriekNummer(selectedRubriek.getRubriekNummer());
		boolean okClicked = showPostEditDialog(tempPost);
		if (okClicked) {
			service.saveOrUpdatePostWithRubriek(tempPost, selectedRubriek.getRubriekId());
			showPostDetail(tempPost);
			setSelection(selectedRubriek.getRubriekNummer());
		}
	}

	/**
	 * Called when the user clicks the edit button. Opens a dialog to edit
	 * details for the selected post.
	 */
	@FXML
	private void handleEditPost() {
		Post selectedPost = postTable.getSelectionModel().getSelectedItem();

		if (selectedPost != null && selectedRubriek != null) {
			selectedPost.setRubriekNummer(selectedRubriek.getRubriekNummer());
			boolean okClicked = showPostEditDialog(selectedPost);
			if (okClicked) {
				service.saveOrUpdatePostWithRubriek(selectedPost, selectedRubriek.getRubriekId());
				showPostDetail(selectedPost);
				setSelection(selectedRubriek.getRubriekNummer());
			}

		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(villaApp.getPrimaryStage());
			alert.setTitle("Geen selectie");
			alert.setHeaderText("Geen Post geselecteerd");
			alert.setContentText("Selecteer een post in the tabel a.u.b.");

			alert.showAndWait();
		}
	}

	public void setSelection(final int rubriekNummer) {
		oListPost = FXCollections.observableArrayList(service.getPostenByRubriekNummer(rubriekNummer));
		postTable.getItems().clear();
		postTable.setItems(oListPost);
		postTable.getSelectionModel().select(0);
		selectedRubriek = new RubriekService().getRubriekByRubriekNummer(rubriekNummer);
	}

	public TableView<Post> getPostTable() {
		return this.postTable;
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
			dialogStage.initOwner(villaApp.getPrimaryStage());
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
}
