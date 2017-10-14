package info.sroman.SOBS;

import info.sroman.SOBS.Model.Prisoner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

public class PrisonerSearchComponent {

	VBox container;

	TilePane prisonerSearchContainer;

	HBox prisonerPersonIdBox;
	Label prisonerPersonIdLabel;
	TextField prisonerPersonIdField;

	HBox prisonerFirstNameBox;
	Label prisonerFirstNameLabel;
	TextField prisonerFirstNameField;

	HBox prisonerLastNameBox;
	Label prisonerLastNameLabel;
	TextField prisonerLastNameField;

	HBox prisonerHeightBox;
	Label prisonerHeightLabel;
	TextField prisonerHeightFeetField;
	ComboBox prisonerHeightInchesCombo;

	HBox prisonerWeightBox;
	Label prisonerWeightLabel;
	TextField prisonerWeightField;

	HBox prisonerDOBBox;
	Label prisonerDOBLabel;
	DatePicker prisonerDOBPicker;

	HBox prisonerRaceBox;
	Label prisonerRaceLabel;
	ComboBox prisonerRaceCombo;

	HBox prisonerPrisonerIdBox;
	Label prisonerPrisonerIdLabel;
	TextField prisonerPrisonerIdField;

	HBox prisonerArrestDateBox;
	Label prisonerArrestDateLabel;
	DatePicker prisonerArrestDatePicker;

	HBox prisonerReleaseDateBox;
	Label prisonerReleaseDateLabel;
	TextField prisonerReleaseDateField;

	HBox prisonerBunkIdBox;
	Label prisonerBunkIdLabel;
	TextField prisonerBunkIdField;

	HBox prisonerSubmitResetBox;
	Button prisonerSubmitBtn;
	Button prisonerResetBtn;

	VBox prisonerContentContainer;
	TableView prisonerSearchResults;

	PrisonerSearchModel model;
	PrisonerSearchController controller;

	public PrisonerSearchComponent(PrisonerSearchController controller) {

		this.controller = controller;

		prisonerSearchResults = new TableView();

		prisonerContentContainer = new VBox(10);

		prisonerSearchContainer = new TilePane();
		prisonerSearchContainer.setPrefColumns(4);

		prisonerPersonIdBox = new HBox();
		prisonerPersonIdLabel = new Label("Person ID");
		prisonerPersonIdField = new TextField();

		prisonerFirstNameBox = new HBox();
		prisonerFirstNameLabel = new Label("First Name");
		prisonerFirstNameField = new TextField();

		prisonerLastNameBox = new HBox();
		prisonerLastNameLabel = new Label("Last Name");
		prisonerLastNameField = new TextField();

		prisonerHeightBox = new HBox();
		prisonerHeightLabel = new Label("Height");
		prisonerHeightFeetField = new TextField();
		prisonerHeightInchesCombo = new ComboBox();

		prisonerWeightBox = new HBox();
		prisonerWeightLabel = new Label("Weight");
		prisonerWeightField = new TextField();

		prisonerDOBBox = new HBox();
		prisonerDOBLabel = new Label("Date of Birth");
		prisonerDOBPicker = new DatePicker();

		prisonerRaceBox = new HBox();
		prisonerRaceLabel = new Label("Race");
		prisonerRaceCombo = new ComboBox();

		prisonerPrisonerIdBox = new HBox();
		prisonerPrisonerIdLabel = new Label("Prisoner ID");
		prisonerPrisonerIdField = new TextField();

		prisonerArrestDateBox = new HBox();
		prisonerArrestDateLabel = new Label("Arrest Date");
		prisonerArrestDatePicker = new DatePicker();

		prisonerReleaseDateBox = new HBox();
		prisonerReleaseDateLabel = new Label("Release Date");
		prisonerReleaseDateField = new TextField();

		prisonerBunkIdBox = new HBox();
		prisonerBunkIdLabel = new Label("Bunk ID");
		prisonerBunkIdField = new TextField();

		prisonerSubmitResetBox = new HBox();
		prisonerSubmitBtn = new Button("Submit");
		prisonerResetBtn = new Button("Reset");

		prisonerSubmitBtn.setOnAction(e -> {
			this.prisonerSearchResults.getItems().clear();

			model = new PrisonerSearchModel(
					prisonerPersonIdField.getText(),
					prisonerFirstNameField.getText(),
					prisonerLastNameField.getText(),
					stringifyHeightFields(),
					prisonerWeightField.getText(),
					getDOBPickerValue(),
					getRaceComboValue(),
					prisonerPrisonerIdField.getText(),
					getArrestDatePickerValue(),
					prisonerReleaseDateField.getText(),
					prisonerBunkIdField.getText()
			);
			PrisonerSearchModel sentModel = controller.submitBtn(model, e);
			this.prisonerSearchResults.getItems().addAll(sentModel.getResultsList());
		});

		// Style controls
		prisonerPersonIdBox.getStyleClass().add("search-control-group");
		prisonerFirstNameBox.getStyleClass().add("search-control-group");
		prisonerLastNameBox.getStyleClass().add("search-control-group");
		prisonerHeightBox.getStyleClass().add("search-control-group");
		prisonerWeightBox.getStyleClass().add("search-control-group");
		prisonerDOBBox.getStyleClass().add("search-control-group");
		prisonerRaceBox.getStyleClass().add("search-control-group");
		prisonerPrisonerIdBox.getStyleClass().add("search-control-group");
		prisonerArrestDateBox.getStyleClass().add("search-control-group");
		prisonerReleaseDateBox.getStyleClass().add("search-control-group");
		prisonerBunkIdBox.getStyleClass().add("search-control-group");
		prisonerSubmitResetBox.getStyleClass().add("search-control-group");
		prisonerSearchContainer.getStyleClass().add("search-container");

		// Configure controls
		setSearchResultsCols();

		prisonerHeightFeetField.setPrefWidth(50);
		prisonerHeightInchesCombo.setItems(
				FXCollections.observableArrayList(
						"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"
				)
		);
		prisonerHeightInchesCombo.setMinSize(10, prisonerHeightBox.getWidth() * 0.25);
		prisonerHeightFeetField.setMinWidth(prisonerHeightBox.getWidth() * 0.75);

		prisonerRaceCombo.setItems(
				FXCollections.observableArrayList(
						"White", "Black", "Hispanic"
				)
		);
		prisonerRaceCombo.setMinSize(10, prisonerRaceBox.getWidth());

		prisonerDOBPicker.setMinHeight(12);
		prisonerDOBPicker.setMaxWidth(170);
		prisonerDOBPicker.setShowWeekNumbers(true);
		configPrisonerDOBPickerDateFormat();
		

		prisonerArrestDatePicker.setMinHeight(12);
		prisonerArrestDatePicker.setMaxWidth(170);
		prisonerArrestDatePicker.setShowWeekNumbers(true);
		configPrisonerArrestDatePickerDateFormat();
		

		// Add controls to container boxes and set style classes
		prisonerPersonIdBox.getChildren().addAll(prisonerPersonIdLabel, prisonerPersonIdField);
		prisonerFirstNameBox.getChildren().addAll(prisonerFirstNameLabel, prisonerFirstNameField);
		prisonerLastNameBox.getChildren().addAll(prisonerLastNameLabel, prisonerLastNameField);
		prisonerHeightBox.getChildren().addAll(prisonerHeightLabel, prisonerHeightFeetField, prisonerHeightInchesCombo);
		prisonerWeightBox.getChildren().addAll(prisonerWeightLabel, prisonerWeightField);
		prisonerDOBBox.getChildren().addAll(prisonerDOBLabel, prisonerDOBPicker);
		prisonerRaceBox.getChildren().addAll(prisonerRaceLabel, prisonerRaceCombo);
		prisonerPrisonerIdBox.getChildren().addAll(prisonerPrisonerIdLabel, prisonerPrisonerIdField);
		prisonerArrestDateBox.getChildren().addAll(prisonerArrestDateLabel, prisonerArrestDatePicker);
		prisonerReleaseDateBox.getChildren().addAll(prisonerReleaseDateLabel, prisonerReleaseDateField);
		prisonerBunkIdBox.getChildren().addAll(prisonerBunkIdLabel, prisonerBunkIdField);
		prisonerSubmitResetBox.getChildren().addAll(prisonerSubmitBtn, prisonerResetBtn);

		prisonerSearchContainer.getChildren().addAll(prisonerPersonIdBox, prisonerFirstNameBox,
				prisonerLastNameBox, prisonerHeightBox, prisonerWeightBox, prisonerDOBBox,
				prisonerRaceBox, prisonerPrisonerIdBox, prisonerArrestDateBox,
				prisonerReleaseDateBox, prisonerBunkIdBox, prisonerSubmitResetBox);

		container = new VBox(10);
		container.getChildren().addAll(prisonerSearchContainer, prisonerSearchResults);
	}

	public Node getNode() {
		return container;
	}

	private String stringifyHeightFields() {
		String feet = prisonerHeightFeetField.getText();
		String inches;
		if (prisonerHeightInchesCombo.getValue() == null) {
			inches = "";
		} else {
			inches = prisonerHeightInchesCombo.getValue().toString();
		}
		return feet.concat(inches);
	}

	private String getRaceComboValue() {
		if (prisonerRaceCombo.getValue() == null) {
			return "";
		}
		return prisonerRaceCombo.getValue().toString();
	}

	private String getDOBPickerValue() {
		if (prisonerDOBPicker.getValue() == null) {
			return "";
		}
		return prisonerDOBPicker.getValue().toString();
	}

	private String getArrestDatePickerValue() {
		if (prisonerArrestDatePicker.getValue() == null) {
			return "";
		}
		return prisonerArrestDatePicker.getValue().toString();
	}
	
	private void configPrisonerDOBPickerDateFormat() {
		prisonerDOBPicker.setConverter(creatStringConverter());
	}
	
	private void configPrisonerArrestDatePickerDateFormat() {
		prisonerArrestDatePicker.setConverter(creatStringConverter());
	}
	
	private StringConverter creatStringConverter() {
		return new StringConverter<LocalDate>() {
			String pattern = "yyyy-MM-dd";
			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
			{
				prisonerArrestDatePicker.setPromptText(pattern.toLowerCase());
			}
			@Override
			public String toString(LocalDate date) {
				if (date != null) {
					return dateFormatter.format(date);
				} else {
					return "";
				}
			}

			@Override
			public LocalDate fromString(String string) {
				if (string != null && !string.isEmpty()) {
					return LocalDate.parse(string, dateFormatter);
				} else {
					return null;
				}
			}
		};
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

//		prisonerSearchResults.
	}

}
