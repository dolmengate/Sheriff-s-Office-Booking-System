package info.sroman.SOBS.Visitor;

import info.sroman.SOBS.Model.Visitor;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;
import info.sroman.SOBS.IComponent;

public class VisitorSearchView implements IComponent {

	VBox container;

	TilePane visitorSearchContainer;

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
	Button visitorrResetBtn;

	TableView visitorSearchResults;

	VisitorSearchModel model;
	VisitorSearchController controller;

	public VisitorSearchView(VisitorSearchController controller) {

		this.controller = controller;

		visitorSearchResults = new TableView();

		visitorSearchContainer = new TilePane();
		visitorSearchContainer.setPrefColumns(4);

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
		visitorrResetBtn = new Button("Reset");

		// Style controls
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
		visitorSearchContainer.getStyleClass().add("search-container");

		// Configure controls
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
		configDOBPickerDateFormat();
		
		visitorSubmitBtn.setOnAction(e -> {
			this.visitorSearchResults.getItems().clear();

			model = new VisitorSearchModel(
					visitorPersonIdField.getText(),
					visitorFirstNameField.getText(),
					visitorLastNameField.getText(),
					stringifyHeightFields(),
					visitorWeightField.getText(),
					getDOBPickerValue(),
					getRaceComboValue(), 
					visitorVisitorIdField.getText(),
					visitorSSNField.getText()
			);
			VisitorSearchModel receivedModel = (VisitorSearchModel) controller.makeQuery(model, e);
			this.visitorSearchResults.getItems().addAll(receivedModel.getResultsList());
		});
		
		visitorrResetBtn.setOnAction(e -> {
			visitorPersonIdField.setText("");
			visitorFirstNameField.setText("");
			visitorLastNameField.setText("");
			visitorHeightFeetField.setText("");
			visitorHeightInchesCombo.setValue("");
			visitorWeightField.setText("");
			visitorDOBPicker.setValue(null);
			visitorRaceCombo.setValue("");
		});
		

		// Add controls to container boxes
		visitorPersonIdBox.getChildren().addAll(visitorPersonIdLabel, visitorPersonIdField);
		visitorFirstNameBox.getChildren().addAll(visitorFirstNameLabel, visitorFirstNameField);
		visitorLastNameBox.getChildren().addAll(visitorLastNameLabel, visitorLastNameField);
		visitorrHeightBox.getChildren().addAll(visitorHeightLabel, visitorHeightFeetField, visitorHeightInchesCombo);
		visitorWeightBox.getChildren().addAll(visitorWeightLabel, visitorWeightField);
		visitorDOBBox.getChildren().addAll(visitorDOBLabel, visitorDOBPicker);
		visitorRaceBox.getChildren().addAll(visitorRaceLabel, visitorRaceCombo);
		visitorSubmitResetBox.getChildren().addAll(visitorSubmitBtn, visitorrResetBtn);
		visitorSSNBox.getChildren().addAll(visitorSSNLabel, visitorSSNField);
		visitorVisitorIdBox.getChildren().addAll(visitorVisitorIdLabel, visitorVisitorIdField);

		visitorSearchContainer.getChildren().addAll(visitorPersonIdBox, visitorFirstNameBox,
				visitorLastNameBox, visitorrHeightBox, visitorWeightBox, visitorDOBBox,
				visitorRaceBox, visitorSSNBox, visitorVisitorIdBox, visitorSubmitResetBox);

		container = new VBox(10);
		container.getChildren().addAll(visitorSearchContainer, visitorSearchResults);
	}

	private String stringifyHeightFields() {
		String feet = visitorHeightFeetField.getText();
		String inches;
		if (visitorHeightInchesCombo.getValue() == null) {
			inches = "";
		} else {
			inches = visitorHeightInchesCombo.getValue().toString();
		}
		return feet.concat(inches);
	}

	private String getRaceComboValue() {
		if (visitorRaceCombo.getValue() == null) {
			return "";
		}
		return visitorRaceCombo.getValue().toString();
	}

	private String getDOBPickerValue() {
		if (visitorDOBPicker.getValue() == null) {
			return "";
		}
		return visitorDOBPicker.getValue().toString();
	}
	
	private void configDOBPickerDateFormat() {
		visitorDOBPicker.setConverter(createStringConverter());
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

	private void setSearchResultsCols() {
		TableColumn<Visitor, String> personIDCol = new TableColumn<>("Person ID");
		personIDCol.setCellValueFactory(new PropertyValueFactory("PERSON_ID"));
		personIDCol.prefWidthProperty().bind(visitorSearchResults.widthProperty().multiply(.11));

		TableColumn<Visitor, String> firstNameCol = new TableColumn<>("First Name");
		firstNameCol.setCellValueFactory(new PropertyValueFactory("firstName"));
		firstNameCol.prefWidthProperty().bind(visitorSearchResults.widthProperty().multiply(.11));

		TableColumn<Visitor, String> lastNameCol = new TableColumn<>("Last Name");
		lastNameCol.setCellValueFactory(new PropertyValueFactory("lastName"));
		lastNameCol.prefWidthProperty().bind(visitorSearchResults.widthProperty().multiply(.11));

		TableColumn<Visitor, Integer> heightCol = new TableColumn<>("Height");
		heightCol.setCellValueFactory(new PropertyValueFactory("HEIGHT"));
		heightCol.prefWidthProperty().bind(visitorSearchResults.widthProperty().multiply(.10));

		TableColumn<Visitor, Integer> weightCol = new TableColumn<>("Weight");
		weightCol.setCellValueFactory(new PropertyValueFactory("weight"));
		weightCol.prefWidthProperty().bind(visitorSearchResults.widthProperty().multiply(.10));

		TableColumn<Visitor, String> DOBCol = new TableColumn<>("DOB");
		DOBCol.setCellValueFactory(new PropertyValueFactory("DOB"));
		DOBCol.prefWidthProperty().bind(visitorSearchResults.widthProperty().multiply(.11));

		TableColumn<Visitor, String> raceCol = new TableColumn<>("Race");
		raceCol.setCellValueFactory(new PropertyValueFactory("RACE"));
		raceCol.prefWidthProperty().bind(visitorSearchResults.widthProperty().multiply(.11));
		
		TableColumn<Visitor, String> visitorCol = new TableColumn<>("Visitor ID");
		visitorCol.setCellValueFactory(new PropertyValueFactory("VISITOR_ID"));
		visitorCol.prefWidthProperty().bind(visitorSearchResults.widthProperty().multiply(.11));
		
		TableColumn<Visitor, String> ssnCol = new TableColumn<>("SSN");
		ssnCol.setCellValueFactory(new PropertyValueFactory("SSN"));
		ssnCol.prefWidthProperty().bind(visitorSearchResults.widthProperty().multiply(.11));	

		visitorSearchResults.getColumns().setAll(personIDCol, firstNameCol,
				lastNameCol, heightCol, weightCol, DOBCol, raceCol, visitorCol, ssnCol);
	}
	
	@Override
	public VBox getPane() {
		return container;
	}
}
