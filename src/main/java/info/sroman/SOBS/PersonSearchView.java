package info.sroman.SOBS;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

public abstract class PersonSearchView extends SearchView {
	
	public PersonSearchView(SearchController controller) {
		super(controller);
	}
	
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

	protected String getRaceComboValue(ComboBox personRaceCombo) {
		if (personRaceCombo.getValue() == null) {
			return "";
		}
		return personRaceCombo.getValue().toString();
	}

	protected String getDOBPickerValue(DatePicker personDOBPicker) {
		if (personDOBPicker.getValue() == null) {
			return "";
		}
		return personDOBPicker.getValue().toString();
	}
	
	protected void configDOBPickerDateFormat(DatePicker personDOBPicker) {
		personDOBPicker.setConverter(createStringConverter());
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
}
