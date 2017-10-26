package info.sroman.SOBS.Prisoner;

import info.sroman.SOBS.Controller;
import info.sroman.SOBS.IComponent;
import info.sroman.SOBS.InputView;
import java.sql.SQLException;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class PrisonerEditModal extends InputView implements IComponent {

	VBox container;
	PrisonerFieldsComponent prisonerFields = new PrisonerFieldsComponent();
	PrisonerSearchModel model;
	PrisonerEditController controller;
	Text messageText;

	public PrisonerEditModal(Controller controller) {
		this.controller = (PrisonerEditController) controller;

		Label editLabel = new Label("Edit Record");
		editLabel.setStyle("-fx-font-weight: bold;");
		
		messageText = new Text("");
		messageText.setStyle("-fx-font-weight: bold;"
				+ "-fx-color: darkred;");

		prisonerFields.getPane().setPadding(new Insets(20));
		
		this.container = new VBox();
		this.container.setPadding(new Insets(20));
		this.container.getChildren().addAll(editLabel, prisonerFields.getPane(), messageText);

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
				messageText.setText(ex.getMessage());
				return;
			}
			messageText.setText("Update Successful");
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
					getPickerValueString(prisonerFields.getDOBPicker()),
					getComboValueString(prisonerFields.getRaceCombo()),
					prisonerFields.getPrisonerIdField().getText(),
					getPickerValueString(prisonerFields.getArrestDatePicker()),
					getPickerValueString(prisonerFields.getReleaseDatePicker()),
					prisonerFields.getBunkIdField().getText(),
					false
			);
	}
	
	public Text getMessageText() {
		return messageText;
	}

	public PrisonerFieldsComponent getPrisonerFields() {
		return prisonerFields;
	}

	@Override
	public VBox getPane() {
		return container;
	}
}
