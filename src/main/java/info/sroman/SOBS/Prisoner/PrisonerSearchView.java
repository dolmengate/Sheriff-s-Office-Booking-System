package info.sroman.SOBS.Prisoner;

import info.sroman.SOBS.Entity.Prisoner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import info.sroman.SOBS.IComponent;
import info.sroman.SOBS.RowContextMenu;
import info.sroman.SOBS.SearchView;
import java.sql.SQLException;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class PrisonerSearchView extends SearchView implements IComponent {
	
	// TODO input validation / formatting restrictions

	PrisonerFieldsComponent prisonerFields;
	RowContextMenu rowContextMenu;
	PrisonerEditController editModalController;
	Prisoner selectedPrisoner;

	public PrisonerSearchView(PrisonerSearchController controller, PrisonerEditController editModalController) {
		super(controller);
		this.editModalController = (PrisonerEditController) editModalController;

		prisonerFields = new PrisonerFieldsComponent();

		addControlsToContainers();
		styleControls();
		configureControls();
		setSearchResultsCols();
	}

	@Override
	public void styleControls() {
		this.searchInputsContainer.getStyleClass().add("search-container");
	}

	@Override
	public void configureControls() {

		prisonerFields.getSubmitBtn().setOnAction(e -> {
			this.searchResults.getItems().clear();

			this.model = new PrisonerSearchModel(
					prisonerFields.getPersonIdField().getText(),
					prisonerFields.getFirstNameField().getText(),
					prisonerFields.getLastNameField().getText(),
					stringifyHeightFields(
							prisonerFields.getHeightFeetField(),
							prisonerFields.getHeightInchesCombo()
					),
					prisonerFields.getWeightField().getText(),
					getPickerValueString(prisonerFields.getDOBPicker()),
					getComboValueString(prisonerFields.getRaceCombo()),
					prisonerFields.getPrisonerIdField().getText(),
					getPickerValueString(prisonerFields.getArrestDatePicker()),
					getPickerValueString(prisonerFields.getReleaseDatePicker()),
					prisonerFields.getBunkIdField().getText()
			);
			PrisonerSearchModel receivedModel = (PrisonerSearchModel) controller.makeSelect(this.model);
			this.searchResults.getItems().addAll(receivedModel.getResultsList());
		});
		
		// TODO implement delete record

		this.searchResults.setOnContextMenuRequested(e -> {
			
			if (rowContextMenu != null) rowContextMenu.hide();
			
			selectedPrisoner = (Prisoner) searchResults.getSelectionModel().getSelectedItems().get(0);

			PrisonerEditModal editModal = new PrisonerEditModal(editModalController);
			VBox editModalContainer = editModal.getPane();
			editModalContainer.getStylesheets().add("search-container");

			VBox deleteModalContainer = new VBox(10);
			Button deleteBtn = new Button("Delete");
			Label deleteRecordLabel = new Label("Delete Record?");
			Label messageLabel = new Label();
			deleteRecordLabel.setStyle("-fx-font-weight: bold;");
			
			deleteModalContainer.getChildren().addAll(deleteRecordLabel, deleteBtn, messageLabel);

			deleteBtn.setOnAction(deleteEvent -> {
				try {
					editModalController.makeDelete(selectedPrisoner.getPRISONER_ID());
					messageLabel.setText("Record Deleted!");
				} catch (SQLException ex) {
					System.out.println(ex.getMessage());
					editModal.getMessageLabel().setText("Deletion Failed!");
				}
			});
			
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
			
			editModal.createModel();
			
			rowContextMenu = new RowContextMenu(editModalContainer, deleteModalContainer);
			rowContextMenu.show(this.searchResults, e.getX(), e.getY());
		});
	}

	@Override
	public void addControlsToContainers() {
		this.searchInputsContainer = (TilePane) prisonerFields.getPane();
		this.container.getChildren().setAll(this.searchInputsContainer, this.searchResults);
	}

	@Override
	public void setSearchResultsCols() {
		TableColumn<Prisoner, String> personIDCol = new TableColumn<>("Person ID");
		personIDCol.setCellValueFactory(new PropertyValueFactory("PERSON_ID"));
		personIDCol.prefWidthProperty().bind(this.searchResults.widthProperty().multiply(.09));

		TableColumn<Prisoner, String> firstNameCol = new TableColumn<>("First Name");
		firstNameCol.setCellValueFactory(new PropertyValueFactory("firstName"));
		firstNameCol.prefWidthProperty().bind(this.searchResults.widthProperty().multiply(.09));

		TableColumn<Prisoner, String> lastNameCol = new TableColumn<>("Last Name");
		lastNameCol.setCellValueFactory(new PropertyValueFactory("lastName"));
		lastNameCol.prefWidthProperty().bind(this.searchResults.widthProperty().multiply(.09));

		TableColumn<Prisoner, Integer> heightCol = new TableColumn<>("Height");
		heightCol.setCellValueFactory(new PropertyValueFactory("HEIGHT"));
		heightCol.prefWidthProperty().bind(this.searchResults.widthProperty().multiply(.09));

		TableColumn<Prisoner, Integer> weightCol = new TableColumn<>("Weight");
		weightCol.setCellValueFactory(new PropertyValueFactory("weight"));
		weightCol.prefWidthProperty().bind(this.searchResults.widthProperty().multiply(.09));

		TableColumn<Prisoner, String> DOBCol = new TableColumn<>("DOB");
		DOBCol.setCellValueFactory(new PropertyValueFactory("DOB"));
		DOBCol.prefWidthProperty().bind(this.searchResults.widthProperty().multiply(.09));

		TableColumn<Prisoner, String> raceCol = new TableColumn<>("Race");
		raceCol.setCellValueFactory(new PropertyValueFactory("RACE"));
		raceCol.prefWidthProperty().bind(this.searchResults.widthProperty().multiply(.09));

		TableColumn<Prisoner, String> prisonerIDCol = new TableColumn<>("Prisoner ID");
		prisonerIDCol.setCellValueFactory(new PropertyValueFactory("PRISONER_ID"));
		prisonerIDCol.prefWidthProperty().bind(this.searchResults.widthProperty().multiply(.09));

		TableColumn<Prisoner, String> bunkIDCol = new TableColumn<>("Bunk ID");
		bunkIDCol.setCellValueFactory(new PropertyValueFactory("bunkID"));
		bunkIDCol.prefWidthProperty().bind(this.searchResults.widthProperty().multiply(.09));

		TableColumn<Prisoner, String> arrestDateCol = new TableColumn<>("Arrest Date");
		arrestDateCol.setCellValueFactory(new PropertyValueFactory("ARREST_DATE"));
		arrestDateCol.prefWidthProperty().bind(this.searchResults.widthProperty().multiply(.09));

		TableColumn<Prisoner, String> releaseDateCol = new TableColumn<>("Release Date");
		releaseDateCol.setCellValueFactory(new PropertyValueFactory("releaseDate"));
		releaseDateCol.prefWidthProperty().bind(this.searchResults.widthProperty().multiply(.09));

		this.searchResults.getColumns().setAll(personIDCol, firstNameCol,
				lastNameCol, heightCol, weightCol, DOBCol, raceCol, prisonerIDCol,
				bunkIDCol, arrestDateCol, releaseDateCol);
	}

	private String getInches(String height) {
		return height.substring(1);
	}

	private String getFeet(String height) {
		return height.substring(0, 1);
	}

	@Override
	public VBox getPane() {
		return container;
	}
}
