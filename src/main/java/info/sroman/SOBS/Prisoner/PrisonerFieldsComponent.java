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
	DatePicker prisonerReleaseDatePicker;

	HBox prisonerBunkIdBox;
	Label prisonerBunkIdLabel;
	TextField prisonerBunkIdField;

	HBox prisonerSubmitResetBox;
	Button prisonerSubmitBtn;
	Button prisonerResetBtn;
	
	public PrisonerFieldsComponent() {
		
		container.setPrefColumns(4);
		
		// create controls
		
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
		prisonerReleaseDatePicker = new DatePicker();

		prisonerBunkIdBox = new HBox();
		prisonerBunkIdLabel = new Label("Bunk ID");
		prisonerBunkIdField = new TextField();

		prisonerSubmitResetBox = new HBox(20);
		prisonerSubmitBtn = new Button("Submit");
		prisonerResetBtn = new Button("Reset");
		
		// group controls
		
		prisonerPersonIdBox.getChildren().addAll(prisonerPersonIdLabel, prisonerPersonIdField);
		prisonerFirstNameBox.getChildren().addAll(prisonerFirstNameLabel, prisonerFirstNameField);
		prisonerLastNameBox.getChildren().addAll(prisonerLastNameLabel, prisonerLastNameField);
		prisonerHeightBox.getChildren().addAll(prisonerHeightLabel, prisonerHeightFeetField, prisonerHeightInchesCombo);
		prisonerWeightBox.getChildren().addAll(prisonerWeightLabel, prisonerWeightField);
		prisonerDOBBox.getChildren().addAll(prisonerDOBLabel, prisonerDOBPicker);
		prisonerRaceBox.getChildren().addAll(prisonerRaceLabel, prisonerRaceCombo);
		prisonerPrisonerIdBox.getChildren().addAll(prisonerPrisonerIdLabel, prisonerPrisonerIdField);
		prisonerArrestDateBox.getChildren().addAll(prisonerArrestDateLabel, prisonerArrestDatePicker);
		prisonerReleaseDateBox.getChildren().addAll(prisonerReleaseDateLabel, prisonerReleaseDatePicker);
		prisonerBunkIdBox.getChildren().addAll(prisonerBunkIdLabel, prisonerBunkIdField);
		prisonerSubmitResetBox.getChildren().addAll(prisonerSubmitBtn, prisonerResetBtn);
		
		// add controls to container

		container.getChildren().addAll(prisonerPersonIdBox, prisonerFirstNameBox,
				prisonerLastNameBox, prisonerHeightBox, prisonerWeightBox, prisonerDOBBox,
				prisonerRaceBox, prisonerPrisonerIdBox, prisonerArrestDateBox,
				prisonerReleaseDateBox, prisonerBunkIdBox, prisonerSubmitResetBox);
		
		// style controls
		
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
		
		// Configure controls
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
		configDOBPickerDateFormat();

		prisonerArrestDatePicker.setMinHeight(12);
		prisonerArrestDatePicker.setMaxWidth(170);
		prisonerArrestDatePicker.setShowWeekNumbers(true);
		configPrisonerArrestDatePickerDateFormat();
		
		prisonerReleaseDatePicker.setMinHeight(12);
		prisonerReleaseDatePicker.setMaxWidth(170);
		prisonerReleaseDatePicker.setShowWeekNumbers(true);
		configPrisonerReleaseDatePickerDateFormat();
		
		prisonerResetBtn.setOnAction(e -> {
			prisonerPersonIdField.setText("");
			prisonerFirstNameField.setText("");
			prisonerLastNameField.setText("");
			prisonerHeightFeetField.setText("");
			prisonerHeightInchesCombo.setValue("");
			prisonerWeightField.setText("");
			prisonerDOBPicker.setValue(null);
			prisonerRaceCombo.setValue("");
			prisonerPrisonerIdField.setText("");
			prisonerArrestDatePicker.setValue(null);
			prisonerReleaseDatePicker.setValue(null);
			prisonerBunkIdField.setText("");
		});
	}
	
	private void configDOBPickerDateFormat() {
		prisonerDOBPicker.setConverter(createStringConverter());
	}
	
	private void configPrisonerArrestDatePickerDateFormat() {
		prisonerArrestDatePicker.setConverter(createStringConverter());
	}
	
	private void configPrisonerReleaseDatePickerDateFormat() {
		prisonerReleaseDatePicker.setConverter(createStringConverter());
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
		return prisonerPersonIdField;
	}

	public TextField getFirstNameField() {
		return prisonerFirstNameField;
	}

	public TextField getLastNameField() {
		return prisonerLastNameField;
	}

	public TextField getHeightFeetField() {
		return prisonerHeightFeetField;
	}

	public ComboBox getHeightInchesCombo() {
		return prisonerHeightInchesCombo;
	}

	public TextField getWeightField() {
		return prisonerWeightField;
	}

	public DatePicker getDOBPicker() {
		return prisonerDOBPicker;
	}

	public ComboBox getRaceCombo() {
		return prisonerRaceCombo;
	}

	public TextField getPrisonerIdField() {
		return prisonerPrisonerIdField;
	}

	public DatePicker getArrestDatePicker() {
		return prisonerArrestDatePicker;
	}

	public DatePicker getReleaseDatePicker() {
		return prisonerReleaseDatePicker;
	}

	public TextField getBunkIdField() {
		return prisonerBunkIdField;
	}
	
	public Button getSubmitBtn() {
		return prisonerSubmitBtn;
	}
	
	@Override
	public TilePane getPane() {
		return container;
	}
}
