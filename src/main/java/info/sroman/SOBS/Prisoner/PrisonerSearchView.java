package info.sroman.SOBS.Prisoner;

import info.sroman.SOBS.Model.Prisoner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import info.sroman.SOBS.IComponent;

public class PrisonerSearchView implements IComponent {

	VBox container;

	TilePane prisonerSearchContainer;
	PrisonerFieldsComponent prisonerFields;
	TableView prisonerSearchResults;

	PrisonerSearchModel model;
	PrisonerSearchController controller;

	public PrisonerSearchView(PrisonerSearchController controller) {

		this.controller = controller;

		prisonerSearchResults = new TableView();

		prisonerFields = new PrisonerFieldsComponent();
		prisonerSearchContainer = (TilePane) prisonerFields.getPane();

		prisonerFields.getSubmitBtn().setOnAction(e -> {
			this.prisonerSearchResults.getItems().clear();

			model = new PrisonerSearchModel(
					prisonerFields.getPersonIdField().getText(),
					prisonerFields.getFirstNameField().getText(),
					prisonerFields.getLastNameField().getText(),
					stringifyHeightFields(),
					prisonerFields.getWeightField().getText(),
					getDOBPickerValue(),
					getRaceComboValue(),
					prisonerFields.getPrisonerIdField().getText(),
					getArrestDatePickerValue(),
					getReleaseDatePickerValue(),
					prisonerFields.getBunkIdField().getText()
			);
			PrisonerSearchModel receivedModel = controller.submitBtn(model, e);
			this.prisonerSearchResults.getItems().addAll(receivedModel.getResultsList());
		});

		setSearchResultsCols();

		// Style search container
		prisonerSearchContainer.getStyleClass().add("search-container");

		
		container = new VBox(10);
		container.getChildren().addAll(prisonerSearchContainer, prisonerSearchResults);
	}

	private String stringifyHeightFields() {
		String feet = prisonerFields.getHeightFeetField().getText();
		String inches;
		if (prisonerFields.getHeightInchesCombo().getValue() == null) {
			inches = "";
		} else {
			inches = prisonerFields.getHeightInchesCombo().getValue().toString();
		}
		return feet.concat(inches);
	}

	private String getRaceComboValue() {
		if (prisonerFields.getRaceCombo().getValue() == null) {
			return "";
		}
		return prisonerFields.getRaceCombo().getValue().toString();
	}

	private String getDOBPickerValue() {
		if (prisonerFields.getDOBPicker().getValue() == null) {
			return "";
		}
		return prisonerFields.getDOBPicker().getValue().toString();
	}

	private String getArrestDatePickerValue() {
		if (prisonerFields.getArrestDatePicker().getValue() == null) {
			return "";
		}
		return prisonerFields.getArrestDatePicker().toString();
	}

	private String getReleaseDatePickerValue() {
		if (prisonerFields.getReleaseDatePicker().getValue() == null) {
			return "";
		}
		return prisonerFields.getReleaseDatePicker().getValue().toString();
	}

	private void setSearchResultsCols() {
		TableColumn<Prisoner, String> personIDCol = new TableColumn<>("Person ID");
		personIDCol.setCellValueFactory(new PropertyValueFactory("PERSON_ID"));
		personIDCol.prefWidthProperty().bind(prisonerSearchResults.widthProperty().multiply(.09));

		TableColumn<Prisoner, String> firstNameCol = new TableColumn<>("First Name");
		firstNameCol.setCellValueFactory(new PropertyValueFactory("firstName"));
		firstNameCol.prefWidthProperty().bind(prisonerSearchResults.widthProperty().multiply(.09));

		TableColumn<Prisoner, String> lastNameCol = new TableColumn<>("Last Name");
		lastNameCol.setCellValueFactory(new PropertyValueFactory("lastName"));
		lastNameCol.prefWidthProperty().bind(prisonerSearchResults.widthProperty().multiply(.09));

		TableColumn<Prisoner, Integer> heightCol = new TableColumn<>("Height");
		heightCol.setCellValueFactory(new PropertyValueFactory("HEIGHT"));
		heightCol.prefWidthProperty().bind(prisonerSearchResults.widthProperty().multiply(.09));

		TableColumn<Prisoner, Integer> weightCol = new TableColumn<>("Weight");
		weightCol.setCellValueFactory(new PropertyValueFactory("weight"));
		weightCol.prefWidthProperty().bind(prisonerSearchResults.widthProperty().multiply(.09));

		TableColumn<Prisoner, String> DOBCol = new TableColumn<>("DOB");
		DOBCol.setCellValueFactory(new PropertyValueFactory("DOB"));
		DOBCol.prefWidthProperty().bind(prisonerSearchResults.widthProperty().multiply(.09));

		TableColumn<Prisoner, String> raceCol = new TableColumn<>("Race");
		raceCol.setCellValueFactory(new PropertyValueFactory("RACE"));
		raceCol.prefWidthProperty().bind(prisonerSearchResults.widthProperty().multiply(.09));

		TableColumn<Prisoner, String> prisonerIDCol = new TableColumn<>("Prisoner ID");
		prisonerIDCol.setCellValueFactory(new PropertyValueFactory("PRISONER_ID"));
		prisonerIDCol.prefWidthProperty().bind(prisonerSearchResults.widthProperty().multiply(.09));

		TableColumn<Prisoner, String> bunkIDCol = new TableColumn<>("Bunk ID");
		bunkIDCol.setCellValueFactory(new PropertyValueFactory("bunkID"));
		bunkIDCol.prefWidthProperty().bind(prisonerSearchResults.widthProperty().multiply(.09));

		TableColumn<Prisoner, String> arrestDateCol = new TableColumn<>("Arrest Date");
		arrestDateCol.setCellValueFactory(new PropertyValueFactory("ARREST_DATE"));
		arrestDateCol.prefWidthProperty().bind(prisonerSearchResults.widthProperty().multiply(.09));

		TableColumn<Prisoner, String> releaseDateCol = new TableColumn<>("Release Date");
		releaseDateCol.setCellValueFactory(new PropertyValueFactory("releaseDate"));
		releaseDateCol.prefWidthProperty().bind(prisonerSearchResults.widthProperty().multiply(.09));

		prisonerSearchResults.getColumns().setAll(personIDCol, firstNameCol,
				lastNameCol, heightCol, weightCol, DOBCol, raceCol, prisonerIDCol,
				bunkIDCol, arrestDateCol, releaseDateCol);
	}

	@Override
	public VBox getPane() {
		return container;
	}
}
