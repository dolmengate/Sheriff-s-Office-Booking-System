package info.sroman.SOBS;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

public abstract class InputView {

	protected String stringifyHeightFields(
			TextField personHeightFeetField, ComboBox personHeightInchesCombo) {
		String feet = personHeightFeetField.getText();
		String inches;
		if (personHeightInchesCombo.getValue() == null) {
			inches = "";
		} else {
			inches = personHeightInchesCombo.getValue().toString();
		}
		return feet.concat(inches);
	}

	protected String getComboValueString(ComboBox combo) {
		if (combo.getValue() == null) {
			return "";
		}
		return combo.getValue().toString();
	}

	protected void addComboBoxOptions(ComboBox combo, String... option) {
		combo.setItems(
				FXCollections.observableArrayList(
						Arrays.asList(option)
				)
		);
	}

	protected void configPickerDateFormat(DatePicker picker) {
		picker.setConverter(createStringConverter());
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

	protected String getPickerValueString(DatePicker picker) {
		if (picker.getValue() == null) {
			return "";
		}
		return picker.getValue().toString();

	}

	protected void restrictToDigitInput(TextField... fields) {
		for (TextField field : fields) {
			field.setOnKeyReleased(e -> {
				if (field.isFocused() && !e.getCode().isDigitKey()) {
					field.deletePreviousChar();
				}
			});
		}
	}

	protected void restrictToLetterInput(TextField... fields) {
		for (TextField field : fields) {
			field.setOnKeyReleased(e -> {
				if (field.isFocused() && !e.getCode().isLetterKey()) {
					field.deletePreviousChar();
				}
			});
		}
	}
}
