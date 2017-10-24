package info.sroman.SOBS.Prisoner;

import info.sroman.SOBS.Controller;
import info.sroman.SOBS.IComponent;
import java.sql.SQLException;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class PrisonerEditModal implements IComponent {

	VBox container;
	PrisonerFieldsComponent prisonerFields = new PrisonerFieldsComponent();
	PrisonerSearchModel model;
	PrisonerEditController controller;
	Label messageLabel;

	public PrisonerEditModal(Controller controller) {
		this.controller = (PrisonerEditController) controller;

		Label editLabel = new Label("Edit Record");
		editLabel.setStyle("-fx-font-weight: bold;");
		
		messageLabel = new Label("");
		messageLabel.setStyle("-fx-font-weight: bold;"
				+ "-fx-color: darkred;");

		prisonerFields.getPane().setPadding(new Insets(20));
		
		this.container = new VBox();
		this.container.setPadding(new Insets(20));
		this.container.getChildren().addAll(editLabel, prisonerFields.getPane(), messageLabel);

		prisonerFields.getPersonIdField().setDisable(true);
		prisonerFields.getPrisonerIdField().setDisable(true);
		prisonerFields.getDOBPicker().setDisable(true);
		prisonerFields.getHeightFeetField().setDisable(true);
		prisonerFields.getHeightInchesCombo().setDisable(true);
		prisonerFields.getRaceCombo().setDisable(true);
		prisonerFields.getArrestDatePicker().setDisable(true);

		prisonerFields.getSubmitBtn().setOnAction(e -> {
			createModel();
			
			try {
				this.controller.makeUpdate(this.model);
			} catch (SQLException ex) {
				messageLabel.setText(ex.getMessage());
				return;
			}
			messageLabel.setText("Update Successful");
		});
	}
	
	public void createModel() {
		
		this.model = new PrisonerSearchModel(
					prisonerFields.getPersonIdField().getText(),
					prisonerFields.getFirstNameField().getText(),
					prisonerFields.getLastNameField().getText(),
					stringifyHeightFields(
							prisonerFields.getHeightFeetField(),
							prisonerFields.getHeightInchesCombo()
					),
					prisonerFields.getWeightField().getText(),
					getDOBPickerValue(prisonerFields.getDOBPicker()),
					getRaceComboValue(prisonerFields.getRaceCombo()),
					prisonerFields.getPrisonerIdField().getText(),
					getArrestDatePickerValue(),
					getReleaseDatePickerValue(),
					prisonerFields.getBunkIdField().getText()
			);
	}
	
	public Label getMessageLabel() {
		return messageLabel;
	}

	public PrisonerFieldsComponent getPrisonerFields() {
		return prisonerFields;
	}

	private String getArrestDatePickerValue() {
		if (prisonerFields.getArrestDatePicker().getValue() == null) {
			return "";
		}
		return prisonerFields.getArrestDatePicker().getValue().toString();
	}

	private String getReleaseDatePickerValue() {
		if (prisonerFields.getReleaseDatePicker().getValue() == null) {
			return "";
		}
		return prisonerFields.getReleaseDatePicker().getValue().toString();
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

	@Override
	public VBox getPane() {
		return container;
	}
}
