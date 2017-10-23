package info.sroman.SOBS.Prisoner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.util.StringConverter;
import info.sroman.SOBS.IComponent;

public class PrisonerFieldsComponent implements IComponent {
	
	TilePane container = new TilePane();
	
	HBox personIdBox;
	Label personIdLabel;
	TextField personIdField;

	HBox firstNameBox;
	Label firstNameLabel;
	TextField firstNameField;

	HBox lastNameBox;
	Label lastNameLabel;
	TextField lastNameField;

	HBox heightBox;
	Label heightLabel;
	TextField heightFeetField;
	ComboBox heightInchesCombo;

	HBox weightBox;
	Label weightLabel;
	TextField weightField;

	HBox dobBox;
	Label dobLabel;
	DatePicker dobPicker;

	HBox raceBox;
	Label raceLabel;
	ComboBox raceCombo;

	HBox prisonerIdBox;
	Label prisonerIdLabel;
	TextField prisonerIdField;

	HBox arrestDateBox;
	Label arrestDateLabel;
	DatePicker arrestDatePicker;

	HBox releaseDateBox;
	Label releaseDateLabel;
	DatePicker releaseDatePicker;

	HBox bunkIdBox;
	Label bunkIdLabel;
	TextField bunkIdField;

	public HBox submitResetBox;
	Button submitBtn;
	Button resetBtn;
	
	public PrisonerFieldsComponent() {
		
		container.setPrefColumns(4);
		
		// create controls
		personIdBox = new HBox();
		personIdLabel = new Label("Person ID");
		personIdField = new TextField();

		firstNameBox = new HBox();
		firstNameLabel = new Label("First Name");
		firstNameField = new TextField();

		lastNameBox = new HBox();
		lastNameLabel = new Label("Last Name");
		lastNameField = new TextField();

		heightBox = new HBox();
		heightLabel = new Label("Height");
		heightFeetField = new TextField();
		heightInchesCombo = new ComboBox();

		weightBox = new HBox();
		weightLabel = new Label("Weight");
		weightField = new TextField();

		dobBox = new HBox();
		dobLabel = new Label("Date of Birth");
		dobPicker = new DatePicker();

		raceBox = new HBox();
		raceLabel = new Label("Race");
		raceCombo = new ComboBox();

		prisonerIdBox = new HBox();
		prisonerIdLabel = new Label("Prisoner ID");
		prisonerIdField = new TextField();

		arrestDateBox = new HBox();
		arrestDateLabel = new Label("Arrest Date");
		arrestDatePicker = new DatePicker();

		releaseDateBox = new HBox();
		releaseDateLabel = new Label("Release Date");
		releaseDatePicker = new DatePicker();

		bunkIdBox = new HBox();
		bunkIdLabel = new Label("Bunk ID");
		bunkIdField = new TextField();

		submitResetBox = new HBox(20);
		submitBtn = new Button("Submit");
		resetBtn = new Button("Reset");
		
		// group controls
		personIdBox.getChildren().addAll(personIdLabel, personIdField);
		firstNameBox.getChildren().addAll(firstNameLabel, firstNameField);
		lastNameBox.getChildren().addAll(lastNameLabel, lastNameField);
		heightBox.getChildren().addAll(heightLabel, heightFeetField, heightInchesCombo);
		weightBox.getChildren().addAll(weightLabel, weightField);
		dobBox.getChildren().addAll(dobLabel, dobPicker);
		raceBox.getChildren().addAll(raceLabel, raceCombo);
		prisonerIdBox.getChildren().addAll(prisonerIdLabel, prisonerIdField);
		arrestDateBox.getChildren().addAll(arrestDateLabel, arrestDatePicker);
		releaseDateBox.getChildren().addAll(releaseDateLabel, releaseDatePicker);
		bunkIdBox.getChildren().addAll(bunkIdLabel, bunkIdField);
		submitResetBox.getChildren().addAll(submitBtn, resetBtn);
		
		// add controls to container
		container.getChildren().addAll(personIdBox, firstNameBox,
				lastNameBox, heightBox, weightBox, dobBox,
				raceBox, prisonerIdBox, arrestDateBox,
				releaseDateBox, bunkIdBox, submitResetBox);
				
		// Configure controls
		heightFeetField.setPrefWidth(50);
		heightInchesCombo.setItems(
				FXCollections.observableArrayList(
						"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"
				)
		);
		heightInchesCombo.setMinSize(10, heightBox.getWidth() * 0.25);
		heightFeetField.setMinWidth(heightBox.getWidth() * 0.75);

		raceCombo.setItems(
				FXCollections.observableArrayList(
						"Black", "White", "Hispanic"
				)
		);
		raceCombo.setMinSize(10, raceBox.getWidth());

		
		dobPicker.setMinHeight(12);
		dobPicker.setMaxWidth(170);
		dobPicker.setShowWeekNumbers(true);
		configDOBPickerDateFormat();

		arrestDatePicker.setMinHeight(12);
		arrestDatePicker.setMaxWidth(170);
		arrestDatePicker.setShowWeekNumbers(true);
		configPrisonerArrestDatePickerDateFormat();
		
		releaseDatePicker.setMinHeight(12);
		releaseDatePicker.setMaxWidth(170);
		releaseDatePicker.setShowWeekNumbers(true);
		configPrisonerReleaseDatePickerDateFormat();
		
		resetBtn.setOnAction(e -> {
			personIdField.setText("");
			firstNameField.setText("");
			lastNameField.setText("");
			heightFeetField.setText("");
			heightInchesCombo.setValue("");
			weightField.setText("");
			dobPicker.setValue(null);
			raceCombo.setValue("");
			prisonerIdField.setText("");
			arrestDatePicker.setValue(null);
			releaseDatePicker.setValue(null);
			bunkIdField.setText("");
		});
	}
	
	public void styleControls() {
		
		// style controls
		personIdBox.getStyleClass().add("search-control-group");
		firstNameBox.getStyleClass().add("search-control-group");
		lastNameBox.getStyleClass().add("search-control-group");
		heightBox.getStyleClass().add("search-control-group");
		weightBox.getStyleClass().add("search-control-group");
		dobBox.getStyleClass().add("search-control-group");
		raceBox.getStyleClass().add("search-control-group");
		prisonerIdBox.getStyleClass().add("search-control-group");
		arrestDateBox.getStyleClass().add("search-control-group");
		releaseDateBox.getStyleClass().add("search-control-group");
		bunkIdBox.getStyleClass().add("search-control-group");
		
		submitResetBox.getStyleClass().add("search-control-group");
	}
	
	private void configDOBPickerDateFormat() {
		dobPicker.setConverter(createStringConverter());
	}
	
	private void configPrisonerArrestDatePickerDateFormat() {
		arrestDatePicker.setConverter(createStringConverter());
	}
	
	private void configPrisonerReleaseDatePickerDateFormat() {
		releaseDatePicker.setConverter(createStringConverter());
	}
	
	private StringConverter createStringConverter() {
		return new StringConverter<LocalDate>() {
			String pattern = "yyyy-MM-dd";
			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
			
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

	public TextField getPersonIdField() {
		return personIdField;
	}

	public TextField getFirstNameField() {
		return firstNameField;
	}

	public TextField getLastNameField() {
		return lastNameField;
	}

	public TextField getHeightFeetField() {
		return heightFeetField;
	}

	public ComboBox getHeightInchesCombo() {
		return heightInchesCombo;
	}

	public TextField getWeightField() {
		return weightField;
	}

	public DatePicker getDOBPicker() {
		return dobPicker;
	}

	public ComboBox getRaceCombo() {
		return raceCombo;
	}

	public TextField getPrisonerIdField() {
		return prisonerIdField;
	}

	public DatePicker getArrestDatePicker() {
		return arrestDatePicker;
	}

	public DatePicker getReleaseDatePicker() {
		return releaseDatePicker;
	}

	public TextField getBunkIdField() {
		return bunkIdField;
	}
	
	public Button getSubmitBtn() {
		return submitBtn;
	}

	public void setPersonIdField(String personId) {
		this.personIdField.setText(personId);
	}

	public void setFirstNameField(String firstName) {
		this.firstNameField.setText(firstName);
	}

	public void setLastNameField(String lastName) {
		this.lastNameField.setText(lastName);
	}

	public void setHeightFeetField(String heightFeet) {
		this.heightFeetField.setText(heightFeet);
	}

	public void setHeightInchesCombo(String heightInches) {
		this.heightInchesCombo.setValue(heightInches);
	}

	public void setWeightField(String weight) {
		this.weightField.setText(weight);
	}

	public void setDobPicker(String dob) {
		this.dobPicker.setValue(convertStringToLocalDate(dob));
	}

	public void setRaceCombo(String race) {
		this.raceCombo.setValue(race);
	}

	public void setPrisonerIdField(String prisonerId) {
		this.prisonerIdField.setText(prisonerId);
	}

	public void setArrestDatePicker(String arrestDate) {
		this.arrestDatePicker.setValue(convertStringToLocalDate(arrestDate));
	}

	public void setReleaseDatePicker(String releaseDate) {
		this.releaseDatePicker.setValue(convertStringToLocalDate(releaseDate));
	}

	public void setBunkIdField(String bunkId) {
		this.bunkIdField.setText(bunkId);
	}
	
	private LocalDate convertStringToLocalDate(String string) {
		int year = Integer.valueOf(string.substring(0, 4));
		int month = Integer.valueOf(string.substring(5, 7));
		int day = Integer.valueOf(string.substring(8, 10));
		LocalDate ld = LocalDate.of(year, month, day);
		return ld;
	}
	
	@Override
	public TilePane getPane() {
		return container;
	}
}
