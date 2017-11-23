package info.sroman.SOBS;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.util.StringConverter;

public abstract class InputView {
	
	protected Controller controller;
	
	public <C extends Controller> InputView (C controller) {
		this.controller = controller;
	}

	/**
	 * Returns a formatted String from user inputs of the given input boxes. 
	 * @param personHeightFeetField		TextField with the person's feet
	 * @param personHeightInchesCombo	ComboBox with the person's inches
	 * @return							a formatted String of the combined feet 
	 *									and inches of the format FII
	 */
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

	/**
	 * Returns a string of the value of the given ComboBox or an empty string if
	 * the box has no input.
	 * @param combo	the ComboBox to get the value of
	 * @return		a String representation of combo 's value
	 */
	protected String getComboValueString(ComboBox combo) {
		if (combo.getValue() == null) {
			return "";
		}
		return combo.getValue().toString();
	}

	/**
	 * Adds options to a ComboBox.
	 * @param combo	 the ComboBox to add options to
	 * @param options a List of Strings, the options to be set
	 */
	protected void addComboBoxOptions(ComboBox combo, String... options) {
		combo.setItems(
				FXCollections.observableArrayList(
						Arrays.asList(options)
				)
		);
	}

	/**
	 * Configures a DatePicker to display the proper date format.
	 * @param picker	the DatePicker to configure
	 * @param converter the StringConverter to use to configure the picker
	 */
	protected void configPickerDateFormat(DatePicker picker, StringConverter converter) {
		picker.setConverter(converter);
	}

	/**
	 * Returns a StringConverter of the given pattern
	 * @param pattern a String of the pattern to which Strings will be converted to
	 * @return		  a StringConverter of the specified pattern
	 */
	protected StringConverter createStringConverter(String pattern) {
		return new StringConverter<LocalDate>() {
			
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

	/**
	 * Returns a String of the given value of the given DatePicker.
	 * @param picker DatePicker to get value of
	 * @return		 a String representation of the DatePicker's value
	 */
	protected String getPickerValueString(DatePicker picker) {
		if (picker.getValue() == null) {
			return "";
		}
		return picker.getValue().toString();

	}

	/**
	 * Configure any number of TextFields to take only numeric inputs.
	 * @param fields TextFields to be configured
	 */
	protected void restrictToDigitInput(TextField... fields) {
		for (TextField field : fields) {
			field.setOnKeyReleased(e -> {
				if (field.isFocused() && !e.getCode().isDigitKey()) {
					field.deletePreviousChar();
				}
			});
		}
	}

	/**
	 * Configure any number of TextFields to take only letters as input.
	 * @param fields TextFields to be configured
	 */
	protected void restrictToLetterInput(TextField... fields) {
		for (TextField field : fields) {
			field.setOnKeyReleased(e -> {
				if (field.isFocused() && !e.getCode().isLetterKey() && e.getCode() != KeyCode.SHIFT) {
					field.deletePreviousChar();
				}
			});
		}
	}
}
