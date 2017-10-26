package info.sroman.SOBS.Prisoner;

import info.sroman.SOBS.Database;
import info.sroman.SOBS.IComponent;
import info.sroman.SOBS.InputView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;

public class PrisonerAddComponent extends InputView implements IComponent {

	PrisonerSearchModel model;
	PrisonerAddController controller;

	FlowPane container;

	TextField firstNameField;
	TextField lastNameField;
	TextField heightFeetField;
	ComboBox heightInchesCombo;
	TextField weightField;
	DatePicker dobPicker;
	ComboBox raceCombo;
	DatePicker arrestDatePicker;
	DatePicker releaseDatePicker;
	TextField bunkIdField;
	Button submitBtn;
	Button resetBtn;

	public PrisonerAddComponent(PrisonerAddController controller) {
		this.controller = controller;
	
		firstNameField = new TextField();
		lastNameField = new TextField();
		heightFeetField = new TextField();
		heightInchesCombo = new ComboBox();
		weightField = new TextField();
		dobPicker = new DatePicker();
		raceCombo = new ComboBox();
		arrestDatePicker = new DatePicker();
		releaseDatePicker = new DatePicker();
		bunkIdField = new TextField();
		submitBtn = new Button("Submit");
		resetBtn = new Button("Reset");

		container = new FlowPane();
		container.getChildren().addAll(firstNameField, lastNameField, heightFeetField,
				heightInchesCombo, weightField, dobPicker, raceCombo, arrestDatePicker,
				releaseDatePicker, bunkIdField, submitBtn, resetBtn
		);

		firstNameField.setPromptText("First Name");
		firstNameField.setPrefWidth(100);
		
		lastNameField.setPromptText("Last Name");
		lastNameField.setPrefWidth(100);
		
		heightFeetField.setPromptText("Feet");
		heightFeetField.setPrefWidth(50);
		
		addComboBoxOptions(heightInchesCombo,
				"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"
		);
		heightInchesCombo.setMinHeight(10);
		heightInchesCombo.setValue("0");
		
		weightField.setPromptText("Weight");
		weightField.setPrefWidth(70);
		
		addComboBoxOptions(raceCombo, "Black", "Hispanic", "White");
		raceCombo.setMinHeight(10);
		raceCombo.setPromptText("Race");
		
		configPickerDateFormat(dobPicker);
		dobPicker.setMinHeight(12);
		dobPicker.setMaxWidth(150);
		dobPicker.setPromptText("DOB");

		configPickerDateFormat(arrestDatePicker);
		arrestDatePicker.setMinHeight(12);
		arrestDatePicker.setMaxWidth(150);
		arrestDatePicker.setPromptText("Arrest Date");
		
		configPickerDateFormat(releaseDatePicker);
		releaseDatePicker.setMinHeight(12);
		releaseDatePicker.setMaxWidth(150);
		releaseDatePicker.setPromptText("Release Date");
		
		bunkIdField.setPromptText("Bunk ID");
		bunkIdField.setPrefWidth(100);
		
		container.setPadding(new Insets(20));
		container.setAlignment(Pos.CENTER);
		
		submitBtn.setOnAction(e -> {
			this.model = new PrisonerSearchModel(
				Integer.toString(Database.assignPersonID()),
				firstNameField.getText(),
				lastNameField.getText(),
				stringifyHeightFields(heightFeetField, heightInchesCombo),
				weightField.getText(),
				getPickerValueString(dobPicker),
				getComboValueString(raceCombo),
				Integer.toString(Database.assignPrisonerID()),
				getPickerValueString(arrestDatePicker),
				getPickerValueString(releaseDatePicker),
				bunkIdField.getText(),
				false
			);
			
			firstNameField.setText("");
			lastNameField.setText("");
			heightFeetField.setText("");
			heightInchesCombo.setValue("");
			weightField.setText("");
			dobPicker.setValue(null);
			raceCombo.setValue("");
			arrestDatePicker.setValue(null);
			releaseDatePicker.setValue(null);
			bunkIdField.setText("");
			
			this.controller.makeInsert(this.model);
		});

		resetBtn.setOnAction(e -> {
			firstNameField.setText("");
			lastNameField.setText("");
			heightFeetField.setText("");
			heightInchesCombo.setValue("0");
			weightField.setText("");
			dobPicker.setValue(null);
			raceCombo.setValue("");
			arrestDatePicker.setValue(null);
			releaseDatePicker.setValue(null);
			bunkIdField.setText("");
		});
	}

	public Button getSubmitBtn() {
		return submitBtn;
	}

	@Override
	public FlowPane getPane() {
		return container;
	}
}
