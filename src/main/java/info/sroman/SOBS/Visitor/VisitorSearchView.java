package info.sroman.SOBS.Visitor;

import info.sroman.SOBS.Entity.Visitor;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import info.sroman.SOBS.IComponent;
import info.sroman.SOBS.SearchView;

public class VisitorSearchView extends SearchView implements IComponent {

	HBox visitorPersonIdBox;
	Label visitorPersonIdLabel;
	TextField visitorPersonIdField;

	HBox visitorFirstNameBox;
	Label visitorFirstNameLabel;
	TextField visitorFirstNameField;

	HBox visitorLastNameBox;
	Label visitorLastNameLabel;
	TextField visitorLastNameField;

	HBox visitorrHeightBox;
	Label visitorHeightLabel;
	TextField visitorHeightFeetField;
	ComboBox visitorHeightInchesCombo;

	HBox visitorWeightBox;
	Label visitorWeightLabel;
	TextField visitorWeightField;

	HBox visitorDOBBox;
	Label visitorDOBLabel;
	DatePicker visitorDOBPicker;

	HBox visitorRaceBox;
	Label visitorRaceLabel;
	ComboBox visitorRaceCombo;

	HBox visitorSSNBox;
	Label visitorSSNLabel;
	TextField visitorSSNField;
	
	HBox visitorVisitorIdBox;
	Label visitorVisitorIdLabel;
	TextField visitorVisitorIdField;

	HBox visitorSubmitResetBox;
	Button visitorSubmitBtn;
	Button visitorResetBtn;

	public VisitorSearchView(VisitorSearchController controller) {
		super(controller);

		visitorPersonIdBox = new HBox();
		visitorPersonIdLabel = new Label("Person ID");
		visitorPersonIdField = new TextField();

		visitorFirstNameBox = new HBox();
		visitorFirstNameLabel = new Label("First Name");
		visitorFirstNameField = new TextField();

		visitorLastNameBox = new HBox();
		visitorLastNameLabel = new Label("Last Name");
		visitorLastNameField = new TextField();

		visitorrHeightBox = new HBox();
		visitorHeightLabel = new Label("Height");
		visitorHeightFeetField = new TextField();
		visitorHeightInchesCombo = new ComboBox();

		visitorWeightBox = new HBox();
		visitorWeightLabel = new Label("Weight");
		visitorWeightField = new TextField();

		visitorDOBBox = new HBox();
		visitorDOBLabel = new Label("Date of Birth");
		visitorDOBPicker = new DatePicker();

		visitorRaceBox = new HBox();
		visitorRaceLabel = new Label("Race");
		visitorRaceCombo = new ComboBox();
		
		visitorSSNBox = new HBox();
		visitorSSNLabel = new Label("SSN");
		visitorSSNField = new TextField();

		visitorVisitorIdBox = new HBox();
		visitorVisitorIdLabel = new Label("Visitor ID");
		visitorVisitorIdField = new TextField();

		visitorSubmitResetBox = new HBox(20);
		visitorSubmitBtn = new Button("Submit");
		visitorResetBtn = new Button("Reset");
		
		styleControls();
		configureControls();
		addControlsToContainers();
		setSearchResultsCols();
	}
	
	@Override
	public void styleControls() {
		visitorPersonIdBox.getStyleClass().add("search-control-group");
		visitorFirstNameBox.getStyleClass().add("search-control-group");
		visitorLastNameBox.getStyleClass().add("search-control-group");
		visitorrHeightBox.getStyleClass().add("search-control-group");
		visitorWeightBox.getStyleClass().add("search-control-group");
		visitorDOBBox.getStyleClass().add("search-control-group");
		visitorRaceBox.getStyleClass().add("search-control-group");
		visitorSSNBox.getStyleClass().add("search-control-group");
		visitorVisitorIdBox.getStyleClass().add("search-control-group");

		visitorSubmitResetBox.getStyleClass().add("search-control-group");
		this.searchInputsContainer.getStyleClass().add("search-container");
	}
	
	@Override
	public void configureControls() {
		setSearchResultsCols();

		visitorHeightFeetField.setPrefWidth(50);
		visitorHeightInchesCombo.setItems(
				FXCollections.observableArrayList(
						"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"
				)
		);
		visitorHeightInchesCombo.setMinSize(10, visitorrHeightBox.getWidth() * 0.25);
		visitorHeightFeetField.setMinWidth(visitorrHeightBox.getWidth() * 0.75);

		visitorRaceCombo.setItems(
				FXCollections.observableArrayList(
						"Black", "Hispanic", "White"
				)
		);
		visitorRaceCombo.setMinSize(10, visitorRaceBox.getWidth());

		
		visitorDOBPicker.setMinHeight(12);
		visitorDOBPicker.setMaxWidth(170);
		visitorDOBPicker.setShowWeekNumbers(true);
		configPickerDateFormat(visitorDOBPicker);
		
		visitorSubmitBtn.setOnAction(e -> {
			this.searchResults.getItems().clear();

			model = new VisitorSearchModel(
					visitorPersonIdField.getText(),
					visitorFirstNameField.getText(),
					visitorLastNameField.getText(),
					stringifyHeightFields(visitorHeightFeetField, visitorHeightInchesCombo),
					visitorWeightField.getText(),
					getPickerValueString(visitorDOBPicker),
					getComboValueString(visitorRaceCombo), 
					visitorVisitorIdField.getText(),
					visitorSSNField.getText()
			);
			VisitorSearchModel receivedModel = (VisitorSearchModel) this.controller.makeSelect(model);
			this.searchResults.getItems().addAll(receivedModel.getResultsList());
		});
		
		visitorResetBtn.setOnAction(e -> {
			visitorPersonIdField.setText("");
			visitorFirstNameField.setText("");
			visitorLastNameField.setText("");
			visitorHeightFeetField.setText("");
			visitorHeightInchesCombo.setValue("");
			visitorWeightField.setText("");
			visitorDOBPicker.setValue(null);
			visitorRaceCombo.setValue("");
		});
	}
	
	@Override
	public void addControlsToContainers() {
		visitorPersonIdBox.getChildren().addAll(visitorPersonIdLabel, visitorPersonIdField);
		visitorFirstNameBox.getChildren().addAll(visitorFirstNameLabel, visitorFirstNameField);
		visitorLastNameBox.getChildren().addAll(visitorLastNameLabel, visitorLastNameField);
		visitorrHeightBox.getChildren().addAll(visitorHeightLabel, visitorHeightFeetField, visitorHeightInchesCombo);
		visitorWeightBox.getChildren().addAll(visitorWeightLabel, visitorWeightField);
		visitorDOBBox.getChildren().addAll(visitorDOBLabel, visitorDOBPicker);
		visitorRaceBox.getChildren().addAll(visitorRaceLabel, visitorRaceCombo);
		visitorSubmitResetBox.getChildren().addAll(visitorSubmitBtn, visitorResetBtn);
		visitorSSNBox.getChildren().addAll(visitorSSNLabel, visitorSSNField);
		visitorVisitorIdBox.getChildren().addAll(visitorVisitorIdLabel, visitorVisitorIdField);

		this.searchInputsContainer.getChildren().addAll(visitorPersonIdBox, visitorFirstNameBox,
				visitorLastNameBox, visitorrHeightBox, visitorWeightBox, visitorDOBBox,
				visitorRaceBox, visitorSSNBox, visitorVisitorIdBox, visitorSubmitResetBox);
	}
	
	@Override
	public void setSearchResultsCols() {
		TableColumn<Visitor, String> personIDCol = new TableColumn<>("Person ID");
		personIDCol.setCellValueFactory(new PropertyValueFactory("PERSON_ID"));
		personIDCol.prefWidthProperty().bind(this.searchResults.widthProperty().multiply(.11));

		TableColumn<Visitor, String> firstNameCol = new TableColumn<>("First Name");
		firstNameCol.setCellValueFactory(new PropertyValueFactory("firstName"));
		firstNameCol.prefWidthProperty().bind(this.searchResults.widthProperty().multiply(.11));

		TableColumn<Visitor, String> lastNameCol = new TableColumn<>("Last Name");
		lastNameCol.setCellValueFactory(new PropertyValueFactory("lastName"));
		lastNameCol.prefWidthProperty().bind(this.searchResults.widthProperty().multiply(.11));

		TableColumn<Visitor, Integer> heightCol = new TableColumn<>("Height");
		heightCol.setCellValueFactory(new PropertyValueFactory("HEIGHT"));
		heightCol.prefWidthProperty().bind(this.searchResults.widthProperty().multiply(.10));

		TableColumn<Visitor, Integer> weightCol = new TableColumn<>("Weight");
		weightCol.setCellValueFactory(new PropertyValueFactory("weight"));
		weightCol.prefWidthProperty().bind(this.searchResults.widthProperty().multiply(.10));

		TableColumn<Visitor, String> DOBCol = new TableColumn<>("DOB");
		DOBCol.setCellValueFactory(new PropertyValueFactory("DOB"));
		DOBCol.prefWidthProperty().bind(this.searchResults.widthProperty().multiply(.11));

		TableColumn<Visitor, String> raceCol = new TableColumn<>("Race");
		raceCol.setCellValueFactory(new PropertyValueFactory("RACE"));
		raceCol.prefWidthProperty().bind(this.searchResults.widthProperty().multiply(.11));
		
		TableColumn<Visitor, String> visitorCol = new TableColumn<>("Visitor ID");
		visitorCol.setCellValueFactory(new PropertyValueFactory("VISITOR_ID"));
		visitorCol.prefWidthProperty().bind(this.searchResults.widthProperty().multiply(.11));
		
		TableColumn<Visitor, String> ssnCol = new TableColumn<>("SSN");
		ssnCol.setCellValueFactory(new PropertyValueFactory("SSN"));
		ssnCol.prefWidthProperty().bind(this.searchResults.widthProperty().multiply(.11));	

		this.searchResults.getColumns().setAll(personIDCol, firstNameCol,
				lastNameCol, heightCol, weightCol, DOBCol, raceCol, visitorCol, ssnCol);
	}
	
	@Override
	public VBox getPane() {
		return this.container;
	}
}
