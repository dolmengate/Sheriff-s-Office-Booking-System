package info.sroman.SOBS.Visit;

import info.sroman.SOBS.Model.Visit;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import info.sroman.SOBS.IComponent;

public class VisitSearchView implements IComponent{		// separate controls for Date and Time?
	// separate search date range controls?

	VBox container;

	TilePane visitSearchContainer;

	HBox visitVisitIdBox;
	Label visitVisitIdLabel;
	TextField visitVisitIdField;

	HBox visitStartTimeBox;				// delete?
	Label visitStartTimeLabel;
	TextField visitStartTimeField;

	HBox visitEndTimeBox;				// delete?
	Label visitEndTimeLabel;
	TextField visitEndTimeField;

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

	TableView visitSearchResults;

	VisitSearchModel model;
	VisitSearchController controller;

	public VisitSearchView(VisitSearchController controller) {
		this.controller = controller;

		visitSearchResults = new TableView();

		visitSearchContainer = new TilePane();
		visitSearchContainer.setPrefColumns(4);

		visitVisitIdBox = new HBox();
		visitVisitIdLabel = new Label("Visit ID");
		visitVisitIdField = new TextField();

		visitStartTimeBox = new HBox();
		visitStartTimeLabel = new Label("Start Time");
		visitStartTimeField = new TextField();		// datepicker?

		visitEndTimeBox = new HBox();
		visitEndTimeLabel = new Label("End Time");
		visitEndTimeField = new TextField();		// datepicker?

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

		// Style controls
		visitVisitIdBox.getStyleClass().add("search-control-group");
		visitStartTimeBox.getStyleClass().add("search-control-group");
		visitEndTimeBox.getStyleClass().add("search-control-group");
		visitNotesBox.getStyleClass().add("search-control-group");
		visitVisitorIdBox.getStyleClass().add("search-control-group");
		visitPrisonerIdBox.getStyleClass().add("search-control-group");

		visitSubmitResetBox.getStyleClass().add("search-control-group");
		visitSearchContainer.getStyleClass().add("search-container");

		// Configure controls
		setSearchResultsCols();
		
		visitSubmitBtn.setOnAction(e -> {
			this.visitSearchResults.getItems().clear();
			
			model = new VisitSearchModel(
					visitVisitIdField.getText(),
					visitStartTimeField.getText(),
					visitEndTimeField.getText(),
					visitNotesField.getText(),
					visitVisitorIdField.getText(),
					visitPrisonerIdField.getText()
			);
			VisitSearchModel receivedModel = (VisitSearchModel) controller.makeQuery(model, e);
			this.visitSearchResults.getItems().addAll(receivedModel.getResultsList());
		});
		
		visitResetBtn.setOnAction(e -> {
			visitVisitIdField.setText("");
			visitStartTimeField.setText("");
			visitEndTimeField.setText("");
			visitNotesField.setText("");
			visitVisitorIdField.setText("");
			visitPrisonerIdField.setText("");
		});

		// Add controls to container boxes
		visitVisitIdBox.getChildren().addAll(visitVisitIdLabel, visitVisitIdField);
		visitStartTimeBox.getChildren().addAll(visitStartTimeLabel, visitStartTimeField);
		visitEndTimeBox.getChildren().addAll(visitEndTimeLabel, visitEndTimeField);
		visitNotesBox.getChildren().addAll(visitNotesLabel, visitNotesField);
		visitVisitorIdBox.getChildren().addAll(visitVisitorIdLabel, visitVisitorIdField);
		visitPrisonerIdBox.getChildren().addAll(visitPrisonerIdLabel, visitPrisonerIdField);
		visitSubmitResetBox.getChildren().addAll(visitSubmitBtn, visitResetBtn);

		visitSearchContainer.getChildren().addAll(visitVisitIdBox, visitStartTimeBox,
				visitEndTimeBox, visitNotesBox, visitVisitorIdBox, visitPrisonerIdBox, 
				visitSubmitResetBox);
		
		container = new VBox(10);
		container.getChildren().addAll(visitSearchContainer, visitSearchResults);

	}

	private void setSearchResultsCols() {
		TableColumn<Visit, String> visitIdCol = new TableColumn<>("Visit ID");
		visitIdCol.setCellValueFactory(new PropertyValueFactory("VISIT_ID"));
		visitIdCol.prefWidthProperty().bind(visitSearchResults.widthProperty().multiply(0.1));

		TableColumn<Visit, String> startTimeCol = new TableColumn<>("Start Time");
		startTimeCol.setCellValueFactory(new PropertyValueFactory("START_TIME"));
		startTimeCol.prefWidthProperty().bind(visitSearchResults.widthProperty().multiply(0.1));

		TableColumn<Visit, String> endTimeCol = new TableColumn<>("End Time");
		endTimeCol.setCellValueFactory(new PropertyValueFactory("END_TIME"));
		endTimeCol.prefWidthProperty().bind(visitSearchResults.widthProperty().multiply(0.1));

		TableColumn<Visit, Integer> notesCol = new TableColumn<>("Notes");
		notesCol.setCellValueFactory(new PropertyValueFactory("notes"));
		notesCol.prefWidthProperty().bind(visitSearchResults.widthProperty().multiply(0.1));

		TableColumn<Visit, Integer> visitorIdCol = new TableColumn<>("Visitor ID");
		visitorIdCol.setCellValueFactory(new PropertyValueFactory("VISITOR_ID"));
		visitorIdCol.prefWidthProperty().bind(visitSearchResults.widthProperty().multiply(0.1));

		TableColumn<Visit, String> prisonerIdCol = new TableColumn<>("Prisoner ID");
		prisonerIdCol.setCellValueFactory(new PropertyValueFactory("PRISONER_ID"));
		prisonerIdCol.prefWidthProperty().bind(visitSearchResults.widthProperty().multiply(0.1));

		visitSearchResults.getColumns().setAll(visitIdCol, startTimeCol, endTimeCol,
				notesCol, visitorIdCol, prisonerIdCol);

	}
	
	@Override
	public VBox getPane() {
		return container;
	}

}
