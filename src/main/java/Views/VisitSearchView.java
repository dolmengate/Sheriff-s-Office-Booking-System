package Views;

import Models.VisitSearchModel;
import Controllers.VisitController;
import info.sroman.SOBS.Entities.Visit;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import info.sroman.SOBS.IComponent;
import info.sroman.SOBS.SearchView;
import java.sql.SQLException;
import javafx.scene.control.DatePicker;

public class VisitSearchView extends SearchView implements IComponent {		// separate controls for Date and Time?
	// separate search date range controls?

	HBox visitVisitIdBox;
	Label visitVisitIdLabel;
	TextField visitVisitIdField;

	HBox visitStartTimeBox;
	Label visitStartTimeLabel;
	DatePicker visitStartTimePicker;

	HBox visitEndTimeBox;
	Label visitEndTimeLabel;
	DatePicker visitEndTimePicker;

	HBox visitNotesBox;
	Label visitNotesLabel;
	TextField visitNotesField;

	HBox visitVisitorIdBox;
	Label visitVisitorIdLabel;
	TextField visitVisitorIdField;

	HBox visitPrisonerIdBox;
	Label visitPrisonerIdLabel;
	TextField visitPrisonerIdField;

	HBox visitSubmitResetBox;
	Button visitSubmitBtn;
	Button visitResetBtn;

	public VisitSearchView(VisitController controller) {
		super(controller);

		visitVisitIdBox = new HBox();
		visitVisitIdLabel = new Label("Visit ID");
		visitVisitIdField = new TextField();

		visitStartTimeBox = new HBox();
		visitStartTimeLabel = new Label("Start Time");
		visitStartTimePicker = new DatePicker();

		visitEndTimeBox = new HBox();
		visitEndTimeLabel = new Label("End Time");
		visitEndTimePicker = new DatePicker();

		visitNotesBox = new HBox();
		visitNotesLabel = new Label("Notes");
		visitNotesField = new TextField();

		visitVisitorIdBox = new HBox();
		visitVisitorIdLabel = new Label("Visitor ID");
		visitVisitorIdField = new TextField();

		visitPrisonerIdBox = new HBox();
		visitPrisonerIdLabel = new Label("Prisoner ID");
		visitPrisonerIdField = new TextField();

		visitSubmitResetBox = new HBox();
		visitSubmitBtn = new Button("Submit");
		visitResetBtn = new Button("Reset");

		styleControls();
		configureControls();
		addControlsToContainers();
		setSearchResultsCols();

	}

	@Override
	public void styleControls() {
		visitVisitIdBox.getStyleClass().add("search-control-group");
		visitStartTimeBox.getStyleClass().add("search-control-group");
		visitEndTimeBox.getStyleClass().add("search-control-group");
		visitNotesBox.getStyleClass().add("search-control-group");
		visitVisitorIdBox.getStyleClass().add("search-control-group");
		visitPrisonerIdBox.getStyleClass().add("search-control-group");

		visitSubmitResetBox.getStyleClass().add("search-control-group");
		this.searchInputsContainer.getStyleClass().add("search-container");
	}

	@Override
	public void configureControls() {
		visitSubmitBtn.setOnAction(e -> {
			this.searchResults.getItems().clear();

			this.model = new VisitSearchModel(
					visitVisitIdField.getText(),
					getPickerValueString(visitStartTimePicker),
					getPickerValueString(visitEndTimePicker),
					visitNotesField.getText(),
					visitVisitorIdField.getText(),
					visitPrisonerIdField.getText()
			);

			try {
				VisitSearchModel receivedModel = (VisitSearchModel) this.controller.search(this.model);
				this.searchResults.getItems().addAll(receivedModel.getResultsList());
			} catch (SQLException ex) {
				System.err.println(ex);
			}
			
		});

		visitResetBtn.setOnAction(e -> {
			visitVisitIdField.setText("");
			visitStartTimePicker.setValue(null);
			visitEndTimePicker.setValue(null);
			visitNotesField.setText("");
			visitVisitorIdField.setText("");
			visitPrisonerIdField.setText("");
		});

		configPickerDateFormat(visitStartTimePicker, createStringConverter("yyyy-MM-dd"));
		configPickerDateFormat(visitEndTimePicker, createStringConverter("yyyy-MM-dd"));
		visitStartTimePicker.setMinHeight(12);
		visitStartTimePicker.setMaxWidth(170);
		visitEndTimePicker.setMinHeight(12);
		visitEndTimePicker.setMaxWidth(170);

	}

	@Override
	public void addControlsToContainers() {
		visitVisitIdBox.getChildren().addAll(visitVisitIdLabel, visitVisitIdField);
		visitStartTimeBox.getChildren().addAll(visitStartTimeLabel, visitStartTimePicker);
		visitEndTimeBox.getChildren().addAll(visitEndTimeLabel, visitEndTimePicker);
		visitNotesBox.getChildren().addAll(visitNotesLabel, visitNotesField);
		visitVisitorIdBox.getChildren().addAll(visitVisitorIdLabel, visitVisitorIdField);
		visitPrisonerIdBox.getChildren().addAll(visitPrisonerIdLabel, visitPrisonerIdField);
		visitSubmitResetBox.getChildren().addAll(visitSubmitBtn, visitResetBtn);

		this.searchInputsContainer.getChildren().addAll(visitVisitIdBox, visitStartTimeBox,
				visitEndTimeBox, visitNotesBox, visitVisitorIdBox, visitPrisonerIdBox,
				visitSubmitResetBox);
	}

	@Override
	public void setSearchResultsCols() {
		TableColumn<Visit, String> visitIdCol = new TableColumn<>("Visit ID");
		visitIdCol.setCellValueFactory(new PropertyValueFactory("VISIT_ID"));
		visitIdCol.prefWidthProperty().bind(this.searchResults.widthProperty().multiply(0.1));

		TableColumn<Visit, String> startTimeCol = new TableColumn<>("Start Time");
		startTimeCol.setCellValueFactory(new PropertyValueFactory("START_TIME"));
		startTimeCol.prefWidthProperty().bind(this.searchResults.widthProperty().multiply(0.1));

		TableColumn<Visit, String> endTimeCol = new TableColumn<>("End Time");
		endTimeCol.setCellValueFactory(new PropertyValueFactory("END_TIME"));
		endTimeCol.prefWidthProperty().bind(this.searchResults.widthProperty().multiply(0.1));

		TableColumn<Visit, Integer> notesCol = new TableColumn<>("Notes");
		notesCol.setCellValueFactory(new PropertyValueFactory("notes"));
		notesCol.prefWidthProperty().bind(this.searchResults.widthProperty().multiply(0.1));

		TableColumn<Visit, Integer> visitorIdCol = new TableColumn<>("Visitor ID");
		visitorIdCol.setCellValueFactory(new PropertyValueFactory("VISITOR_ID"));
		visitorIdCol.prefWidthProperty().bind(this.searchResults.widthProperty().multiply(0.1));

		TableColumn<Visit, String> prisonerIdCol = new TableColumn<>("Prisoner ID");
		prisonerIdCol.setCellValueFactory(new PropertyValueFactory("PRISONER_ID"));
		prisonerIdCol.prefWidthProperty().bind(this.searchResults.widthProperty().multiply(0.1));

		this.searchResults.getColumns().setAll(visitIdCol, startTimeCol, endTimeCol,
				notesCol, visitorIdCol, prisonerIdCol);

	}

	@Override
	public VBox getPane() {
		return container;
	}

}
