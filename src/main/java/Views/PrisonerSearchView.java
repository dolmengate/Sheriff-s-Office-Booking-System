package Views;

import Controllers.PrisonerController;
import info.sroman.SOBS.Entities.Prisoner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import info.sroman.SOBS.IComponent;
import Models.PrisonerSearchModel;
import info.sroman.SOBS.SearchView;
import java.sql.SQLException;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class PrisonerSearchView extends SearchView implements IComponent {
	
	PrisonerFieldsComponent prisonerSearchComponent;
	PrisonerAddComponent prisonerAddComponent;
	RowContextMenu rowContextMenu;
	Prisoner selectedPrisoner;
	Text messageText;

	public PrisonerSearchView(PrisonerController controller) {
		super(controller);

		prisonerSearchComponent = new PrisonerFieldsComponent(controller);
		prisonerAddComponent = new PrisonerAddComponent(controller);

		addControlsToContainers();
		styleControls();
		configureControls();
		setSearchResultsCols();
		
		messageText = new Text();
	}

	@Override
	public void styleControls() {
		this.searchInputsContainer.getStyleClass().add("search-container");
	}

	@Override
	public void configureControls() {

		// configure submit button for search
		prisonerSearchComponent.getSubmitBtn().setOnAction(e -> {
			
			// clear the results list in preparation of a new search
			this.searchResults.getItems().clear();

			// create the search model to be passed to the controller
			this.model = new PrisonerSearchModel(
					prisonerSearchComponent.getPersonIdField().getText(),
					prisonerSearchComponent.getFirstNameField().getText(),
					prisonerSearchComponent.getLastNameField().getText(),
					stringifyHeightFields(prisonerSearchComponent.getHeightFeetField(),
							prisonerSearchComponent.getHeightInchesCombo()
					),
					prisonerSearchComponent.getWeightField().getText(),
					getPickerValueString(prisonerSearchComponent.getDOBPicker()),
					getComboValueString(prisonerSearchComponent.getRaceCombo()),
					prisonerSearchComponent.getPrisonerIdField().getText(),
					getPickerValueString(prisonerSearchComponent.getArrestDatePicker()),
					getPickerValueString(prisonerSearchComponent.getReleaseDatePicker()),
					prisonerSearchComponent.getBunkIdField().getText(),
					getComboValueString(prisonerSearchComponent.getCellBlockCombo()), 
					false
			);
			
			try {
				PrisonerSearchModel receivedModel = (PrisonerSearchModel) controller.search(this.model);
				this.searchResults.getItems().addAll(receivedModel.getResultsList());
			} catch (SQLException ex) {
				
			}
		}); // end submit button behavior
		
		// on right click create context menu for editing/deleting prisoner
		this.searchResults.setOnContextMenuRequested(e -> {
			
			// disallow displaying more than one context menu
			if (rowContextMenu != null) rowContextMenu.hide();
			
			// find selected prisoner to be edited or deleted
			selectedPrisoner = (Prisoner) searchResults.getSelectionModel().getSelectedItems().get(0);

			// create and style the Edit Modal for display
			PrisonerEditModal editModal = new PrisonerEditModal(controller);
			VBox editModalContainer = editModal.getPane();
			editModalContainer.getStylesheets().add("search-container");

			// create and style Delete Modal for display
			VBox deleteModalContainer = new VBox(10);
			Button deleteBtn = new Button("Delete");
			Label deleteRecordLabel = new Label("Delete Record?");
			Label messageLabel = new Label();
			deleteRecordLabel.setStyle("-fx-font-weight: bold;");
			
			deleteModalContainer.getChildren().addAll(deleteRecordLabel, deleteBtn, messageLabel);

			// define Delete button behavior for Delete Modal
			deleteBtn.setOnAction(deleteEvent -> {
				try {
					this.controller.remove(constructDeleteModalPrisonerModel());
					messageLabel.setText("Record Deleted!");
				} catch (SQLException | NumberFormatException ex) {
					System.out.println(ex.getMessage());
					editModal.getMessageText().setText("Deletion Failed!");
				}
			});
			
			// fill user input controls in the Edit Modal with selected prisoner
			// data to allow user to change it
			PrisonerFieldsComponent fields = editModal.getPrisonerFields();

			fields.setPersonIdField(Integer.toString(selectedPrisoner.getPERSON_ID()));
			fields.setFirstNameField(selectedPrisoner.getFirstName());
			fields.setLastNameField(selectedPrisoner.getLastName());
			fields.setHeightFeetField(getFeet(Integer.toString(selectedPrisoner.getHEIGHT())));
			fields.setHeightInchesCombo(getInches(Integer.toString(selectedPrisoner.getHEIGHT())));
			fields.setWeightField(Integer.toString(selectedPrisoner.getWeight()));
			fields.setDobPicker(selectedPrisoner.getDOB());
			fields.setRaceCombo(selectedPrisoner.getRACE());
			fields.setPrisonerIdField(Integer.toString(selectedPrisoner.getPRISONER_ID()));
			fields.setArrestDatePicker(selectedPrisoner.getARREST_DATE());
			fields.setReleaseDatePicker(selectedPrisoner.getReleaseDate());
			fields.setBunkIdField(Integer.toString(selectedPrisoner.getBunkID()));
			
			// refresh the model to reflect the values now displayed in the fields
			editModal.createModel();
			
			rowContextMenu = new RowContextMenu(editModalContainer, deleteModalContainer);
			
			// display the contextmenu at the coordinates of the user's right-click
			rowContextMenu.show(this.searchResults, e.getX(), e.getY());
		});
	}

	@Override
	public void addControlsToContainers() {
		this.searchInputsContainer = (TilePane) prisonerSearchComponent.getPane();
		this.container.getChildren().setAll(this.searchInputsContainer, this.searchResults, prisonerAddComponent.getPane());
	}

	@Override
	public void setSearchResultsCols() {
		TableColumn<Prisoner, String> personIDCol = new TableColumn<>("Person ID");
		personIDCol.setCellValueFactory(new PropertyValueFactory("PERSON_ID"));
		personIDCol.prefWidthProperty().bind(this.searchResults.widthProperty().multiply(.08));

		TableColumn<Prisoner, String> firstNameCol = new TableColumn<>("First Name");
		firstNameCol.setCellValueFactory(new PropertyValueFactory("firstName"));
		firstNameCol.prefWidthProperty().bind(this.searchResults.widthProperty().multiply(.08));

		TableColumn<Prisoner, String> lastNameCol = new TableColumn<>("Last Name");
		lastNameCol.setCellValueFactory(new PropertyValueFactory("lastName"));
		lastNameCol.prefWidthProperty().bind(this.searchResults.widthProperty().multiply(.08));

		TableColumn<Prisoner, Integer> heightCol = new TableColumn<>("Height");
		heightCol.setCellValueFactory(new PropertyValueFactory("HEIGHT"));
		heightCol.prefWidthProperty().bind(this.searchResults.widthProperty().multiply(.08));

		TableColumn<Prisoner, Integer> weightCol = new TableColumn<>("Weight");
		weightCol.setCellValueFactory(new PropertyValueFactory("weight"));
		weightCol.prefWidthProperty().bind(this.searchResults.widthProperty().multiply(.08));

		TableColumn<Prisoner, String> DOBCol = new TableColumn<>("DOB");
		DOBCol.setCellValueFactory(new PropertyValueFactory("DOB"));
		DOBCol.prefWidthProperty().bind(this.searchResults.widthProperty().multiply(.08));

		TableColumn<Prisoner, String> raceCol = new TableColumn<>("Race");
		raceCol.setCellValueFactory(new PropertyValueFactory("RACE"));
		raceCol.prefWidthProperty().bind(this.searchResults.widthProperty().multiply(.08));

		TableColumn<Prisoner, String> prisonerIDCol = new TableColumn<>("Prisoner ID");
		prisonerIDCol.setCellValueFactory(new PropertyValueFactory("PRISONER_ID"));
		prisonerIDCol.prefWidthProperty().bind(this.searchResults.widthProperty().multiply(.08));

		TableColumn<Prisoner, String> arrestDateCol = new TableColumn<>("Arrest Date");
		arrestDateCol.setCellValueFactory(new PropertyValueFactory("ARREST_DATE"));
		arrestDateCol.prefWidthProperty().bind(this.searchResults.widthProperty().multiply(.08));

		TableColumn<Prisoner, String> releaseDateCol = new TableColumn<>("Release Date");
		releaseDateCol.setCellValueFactory(new PropertyValueFactory("releaseDate"));
		releaseDateCol.prefWidthProperty().bind(this.searchResults.widthProperty().multiply(.08));
		
		TableColumn<Prisoner, String> bunkIDCol = new TableColumn<>("Bunk ID");
		bunkIDCol.setCellValueFactory(new PropertyValueFactory("bunkID"));
		bunkIDCol.prefWidthProperty().bind(this.searchResults.widthProperty().multiply(.08));
		
		TableColumn<Prisoner, String> cellBlockCol = new TableColumn<>("Cell Block");
		cellBlockCol.setCellValueFactory(new PropertyValueFactory("cellBlock"));
		cellBlockCol.prefWidthProperty().bind(this.searchResults.widthProperty().multiply(.08));

		this.searchResults.getColumns().setAll(personIDCol, firstNameCol,
				lastNameCol, heightCol, weightCol, DOBCol, raceCol, prisonerIDCol,
				bunkIDCol, arrestDateCol, releaseDateCol, cellBlockCol);
	}

	/**
	 * Extract and return the inches part of the height String returned by the
	 * Prisoner's getHEIGHT() method.
	 * @param height String representing the combined height measurement 
	 *				 (feet and inches)
	 * @return 
	 */
	private String getInches(String height) {
		return height.substring(1);
	}

	/**
	 * Extract and return the feet part of the height String returned by the
	 * Prisoner's getHEIGHT() method.
	 * @param height String representing the combined height measurement 
	 *				 (feet and inches)
	 * @return 
	 */
	private String getFeet(String height) {
		return height.substring(0, 1);
	}
	
	/**
	 * Construct the model required for the Delete button in the Delete Modal
	 * to know which Prisoner to delete
	 * @return 
	 */
	private PrisonerSearchModel constructDeleteModalPrisonerModel() {
		PrisonerSearchModel psm = new PrisonerSearchModel();
		psm.setPrisonerId(Integer.toString(selectedPrisoner.getPRISONER_ID()));
		return psm;
	}

	@Override
	public VBox getPane() {
		return container;
	}
}
