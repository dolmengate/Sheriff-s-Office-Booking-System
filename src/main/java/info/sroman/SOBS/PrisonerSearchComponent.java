package info.sroman.SOBS;

import info.sroman.SOBS.Model.Prisoner;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

public class PrisonerSearchComponent {
	
	VBox container;
	
	TilePane prisonersSearchContainer;
	
	HBox prisonersPersonIdBox;
	Label prisonersPersonIdLabel;
	TextField prisonersPersonIdField;
	
	HBox prisonersFirstNameBox;
	Label prisonersFirstNameLabel;
	TextField prisonersFirstNameField;
	
	HBox prisonersLastNameBox;
	Label prisonersLastNameLabel;
	TextField prisonersLastNameField;
	
	HBox prisonersHeightBox;
	Label prisonersHeightLabel;
	TextField prisonersHeightField;
	
	HBox prisonersWeightBox;
	Label prisonersWeightLabel;
	TextField prisonersWeightField;
	
	HBox prisonersDOBBox;
	Label prisonersDOBLabel;
	TextField prisonersDOBField;
	
	HBox prisonersRaceBox;
	Label prisonersRaceLabel;
	TextField prisonersRaceField;
	
	HBox prisonersPrisonerIdBox;
	Label prisonersPrisonerIdLabel;
	TextField prisonersPrisonerIdField;
	
	HBox prisonersArrestDateBox;
	Label prisonersArrestDateLabel;
	TextField prisonersArrestDateField;
	
	HBox prisonersReleaseDateBox;
	Label prisonersReleaseDateLabel;
	TextField prisonersReleaseDateField;
	
	HBox prisonersBunkIdBox;
	Label prisonersBunkIdLabel;
	TextField prisonersBunkIdField;
	
	HBox prisonersSubmitResetBox;
	Button prisonersSubmitBtn;
	Button prisonersResetBtn;
	
	VBox prisonersContentContainer;
	TableView prisonersSearchResults;
	
	PrisonerSearchModel model;
	PrisonerSearchController controller;
	
	public PrisonerSearchComponent(PrisonerSearchController controller) {
		
		this.controller = controller;
		
		prisonersSearchResults = new TableView();
		setSearchResultsCols();

		prisonersContentContainer = new VBox(10);
		
		prisonersSearchContainer = new TilePane();
		prisonersSearchContainer.setPrefColumns(4);

		prisonersPersonIdBox = new HBox();
		prisonersPersonIdLabel = new Label("Person ID");
		prisonersPersonIdField = new TextField();

		prisonersFirstNameBox = new HBox();
		prisonersFirstNameLabel = new Label("First Name");
		prisonersFirstNameField = new TextField();

		prisonersLastNameBox = new HBox();
		prisonersLastNameLabel = new Label("Last Name");
		prisonersLastNameField = new TextField();

		prisonersHeightBox = new HBox();
		prisonersHeightLabel = new Label("Height");
		prisonersHeightField = new TextField();

		prisonersWeightBox = new HBox();
		prisonersWeightLabel = new Label("Weight");
		prisonersWeightField = new TextField();

		prisonersDOBBox = new HBox();
		prisonersDOBLabel = new Label("Date of Birth");
		prisonersDOBField = new TextField();

		prisonersRaceBox = new HBox();
		prisonersRaceLabel = new Label("Race");
		prisonersRaceField = new TextField();

		prisonersPrisonerIdBox = new HBox();
		prisonersPrisonerIdLabel = new Label("Prisoner ID");
		prisonersPrisonerIdField = new TextField();

		prisonersArrestDateBox = new HBox();
		prisonersArrestDateLabel = new Label("Arrest Date");
		prisonersArrestDateField = new TextField();

		prisonersReleaseDateBox = new HBox();
		prisonersReleaseDateLabel = new Label("Release Date");
		prisonersReleaseDateField = new TextField();

		prisonersBunkIdBox = new HBox();
		prisonersBunkIdLabel = new Label("Bunk ID");
		prisonersBunkIdField = new TextField();

		prisonersSubmitResetBox = new HBox();
		prisonersSubmitBtn = new Button("Submit");
		prisonersResetBtn = new Button("Reset");
		
		prisonersSubmitBtn.setOnAction(e-> {
			this.prisonersSearchResults.getItems().clear();
			
			model = new PrisonerSearchModel(
					prisonersPersonIdField.getText(), 
					prisonersFirstNameField.getText(), 
					prisonersLastNameField.getText(), 
					prisonersHeightField.getText(),
					prisonersWeightField.getText(),
					prisonersDOBField.getText(),
					prisonersRaceField.getText(),
					prisonersPrisonerIdField.getText(),
					prisonersArrestDateField.getText(),
					prisonersReleaseDateField.getText(),
					prisonersBunkIdField.getText()
			);
			PrisonerSearchModel send = controller.submitBtn(model, e);
			this.prisonersSearchResults.getItems().addAll(send.getResultsList());
		});
		
		// Add controls to container boxes and set style classes
		prisonersPersonIdBox.getStyleClass().add("search-control-pair");
		prisonersPersonIdBox.getChildren().addAll(prisonersPersonIdLabel, prisonersPersonIdField);
		
		prisonersFirstNameBox.getStyleClass().add("search-control-pair");
		prisonersFirstNameBox.getChildren().addAll(prisonersFirstNameLabel, prisonersFirstNameField);
		
		prisonersLastNameBox.getStyleClass().add("search-control-pair");
		prisonersLastNameBox.getChildren().addAll(prisonersLastNameLabel, prisonersLastNameField);
		
		prisonersHeightBox.getStyleClass().add("search-control-pair");
		prisonersHeightBox.getChildren().addAll(prisonersHeightLabel, prisonersHeightField);
		
		prisonersWeightBox.getStyleClass().add("search-control-pair");
		prisonersWeightBox.getChildren().addAll(prisonersWeightLabel, prisonersWeightField);
		
		prisonersDOBBox.getStyleClass().add("search-control-pair");
		prisonersDOBBox.getChildren().addAll(prisonersDOBLabel, prisonersDOBField);
		
		prisonersRaceBox.getStyleClass().add("search-control-pair");
		prisonersRaceBox.getChildren().addAll(prisonersRaceLabel, prisonersRaceField);
		
		prisonersPrisonerIdBox.getStyleClass().add("search-control-pair");
		prisonersPrisonerIdBox.getChildren().addAll(prisonersPrisonerIdLabel, prisonersPrisonerIdField);
		
		prisonersArrestDateBox.getStyleClass().add("search-control-pair");
		prisonersArrestDateBox.getChildren().addAll(prisonersArrestDateLabel, prisonersArrestDateField);
		
		prisonersReleaseDateBox.getStyleClass().add("search-control-pair");
		prisonersReleaseDateBox.getChildren().addAll(prisonersReleaseDateLabel, prisonersReleaseDateField);
		
		prisonersBunkIdBox.getStyleClass().add("search-control-pair");
		prisonersBunkIdBox.getChildren().addAll(prisonersBunkIdLabel, prisonersBunkIdField);
		
		prisonersSubmitResetBox.getStyleClass().add("search-control-pair");
		prisonersSubmitResetBox.getChildren().addAll(prisonersSubmitBtn, prisonersResetBtn);
		
		prisonersSearchContainer.getStyleClass().add("search-container");
		prisonersSearchContainer.getChildren().addAll(prisonersPersonIdBox, prisonersFirstNameBox, 
				prisonersLastNameBox, prisonersHeightBox, prisonersWeightBox, prisonersDOBBox,
				prisonersRaceBox, prisonersPrisonerIdBox, prisonersArrestDateBox, 
				prisonersReleaseDateBox, prisonersBunkIdBox, prisonersSubmitResetBox);
		
		
		container = new VBox(10);
		container.getChildren().addAll(prisonersSearchContainer, prisonersSearchResults);
	}
	
	public Node getNode() {
		return container;
	}
	
	private void setSearchResultsCols() {
		TableColumn<Prisoner,String> personIDCol = new TableColumn<>("Person ID");
		personIDCol.setCellValueFactory(new PropertyValueFactory("PERSON_ID"));
		personIDCol.prefWidthProperty().bind(prisonersSearchResults.widthProperty().multiply(.09));
		
		TableColumn<Prisoner,String> firstNameCol = new TableColumn<>("First Name");
		firstNameCol.setCellValueFactory(new PropertyValueFactory("firstName"));
		firstNameCol.prefWidthProperty().bind(prisonersSearchResults.widthProperty().multiply(.09));
		
		TableColumn<Prisoner,String> lastNameCol = new TableColumn<>("Last Name");
		lastNameCol.setCellValueFactory(new PropertyValueFactory("lastName"));
		lastNameCol.prefWidthProperty().bind(prisonersSearchResults.widthProperty().multiply(.09));
		
		TableColumn<Prisoner,Integer> heightCol = new TableColumn<>("Height");
		heightCol.setCellValueFactory(new PropertyValueFactory("HEIGHT"));
		heightCol.prefWidthProperty().bind(prisonersSearchResults.widthProperty().multiply(.09));
		
		TableColumn<Prisoner,Integer> weightCol = new TableColumn<>("Weight");
		weightCol.setCellValueFactory(new PropertyValueFactory("weight"));
		weightCol.prefWidthProperty().bind(prisonersSearchResults.widthProperty().multiply(.09));
		
		TableColumn<Prisoner,String> DOBCol = new TableColumn<>("DOB");
		DOBCol.setCellValueFactory(new PropertyValueFactory("DOB"));
		DOBCol.prefWidthProperty().bind(prisonersSearchResults.widthProperty().multiply(.09));
		
		TableColumn<Prisoner,String> raceCol = new TableColumn<>("Race");
		raceCol.setCellValueFactory(new PropertyValueFactory("RACE"));
		raceCol.prefWidthProperty().bind(prisonersSearchResults.widthProperty().multiply(.09));
		
		TableColumn<Prisoner,String> prisonerIDCol = new TableColumn<>("Prisoner ID");
		prisonerIDCol.setCellValueFactory(new PropertyValueFactory("PRISONER_ID"));
		prisonerIDCol.prefWidthProperty().bind(prisonersSearchResults.widthProperty().multiply(.09));
		
		TableColumn<Prisoner,String> bunkIDCol = new TableColumn<>("Bunk ID");
		bunkIDCol.setCellValueFactory(new PropertyValueFactory("bunkID"));
		bunkIDCol.prefWidthProperty().bind(prisonersSearchResults.widthProperty().multiply(.09));
		
		TableColumn<Prisoner,String> arrestDateCol = new TableColumn<>("Arrest Date");
		arrestDateCol.setCellValueFactory(new PropertyValueFactory("ARREST_DATE"));
		arrestDateCol.prefWidthProperty().bind(prisonersSearchResults.widthProperty().multiply(.09));
		
		TableColumn<Prisoner,String> releaseDateCol = new TableColumn<>("Release Date");
		releaseDateCol.setCellValueFactory(new PropertyValueFactory("releaseDate"));
		releaseDateCol.prefWidthProperty().bind(prisonersSearchResults.widthProperty().multiply(.09));
		
		prisonersSearchResults.getColumns().setAll(personIDCol, firstNameCol, 
				lastNameCol, heightCol, weightCol, DOBCol, raceCol, prisonerIDCol, 
				bunkIDCol, arrestDateCol, releaseDateCol);
		
//		prisonersSearchResults.
	}
	
}
