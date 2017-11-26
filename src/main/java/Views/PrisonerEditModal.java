package Views;

import info.sroman.SOBS.Controller;
import info.sroman.SOBS.IComponent;
import info.sroman.SOBS.InputView;
import Models.PrisonerSearchModel;
import java.sql.SQLException;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class PrisonerEditModal extends InputView implements IComponent {

	VBox container;
	
	// user input fields component 
	PrisonerFieldsComponent prisonerFields;
	
	// values of input fields, to be passed between the layers
	PrisonerSearchModel model;
	
	// error or success text
	Text messageText;

	public PrisonerEditModal(Controller controller) {
		super(controller);
		
		// create and style fields within modal for display
		prisonerFields = new PrisonerFieldsComponent(controller);

		Label editLabel = new Label("Edit Record");
		editLabel.setStyle("-fx-font-weight: bold;");
		
		messageText = new Text("");
		messageText.setStyle("-fx-font-weight: bold;"
				+ "-fx-color: darkred;");

		prisonerFields.getPane().setPadding(new Insets(20));
		
		this.container = new VBox();
		this.container.setPadding(new Insets(20));
		this.container.getChildren().addAll(editLabel, prisonerFields.getPane(), messageText);

		// disable unchangeable characteristics' fields
		prisonerFields.getPersonIdField().setDisable(true);
		prisonerFields.getPrisonerIdField().setDisable(true);
		prisonerFields.getDOBPicker().setDisable(true);
		prisonerFields.getHeightFeetField().setDisable(true);
		prisonerFields.getHeightInchesCombo().setDisable(true);
		prisonerFields.getRaceCombo().setDisable(true);
		prisonerFields.getArrestDatePicker().setDisable(true);
		prisonerFields.getCellBlockCombo().setDisable(true);

		prisonerFields.getSubmitBtn().setOnAction(e -> {
			// create a model containing the information in the input fields
			// to be passed to the controller so the DAO knows what to change
			createModel();
			
			try {
				this.controller.edit(this.model);
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
					"", 
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
