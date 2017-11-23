package Views;

import Models.CourtDateSearchModel;
import info.sroman.SOBS.Controller;
import javafx.scene.layout.VBox;
import info.sroman.SOBS.IComponent;
import info.sroman.SOBS.Entities.CourtDate;
import info.sroman.SOBS.SearchView;
import java.sql.SQLException;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

public class CourtDateSearchView extends SearchView implements IComponent {
	
	HBox courtDateIdBox;
	Label courtDateIdLabel;
	TextField courtDateIdField;
	
	HBox dateBox;
	Label dateLabel;
	DatePicker datePicker;
	
	HBox verdictBox;
	Label verdictLabel;
	ComboBox verdictCombo;
			
	HBox prisonerIdBox;
	Label prisonerIdLabel;
	TextField prisonerIdField;
	
	HBox submitResetBtnBox;
	Button submitBtn;
	Button resetBtn;
	
	
	public CourtDateSearchView (Controller controller) {
		super(controller);
		
		courtDateIdBox = new HBox();
		courtDateIdLabel = new Label("Court Date ID");
		courtDateIdField = new TextField();
		
		dateBox = new HBox();
		dateLabel = new Label("Date");
		datePicker = new DatePicker();
		
		verdictBox = new HBox();
		verdictLabel = new Label("Verdict");
		verdictCombo = new ComboBox();
		
		prisonerIdBox = new HBox();
		prisonerIdLabel = new Label("Prisoner ID");
		prisonerIdField = new TextField();
		
		submitResetBtnBox = new HBox();
		submitBtn = new Button("Submit");
		resetBtn = new Button("Reset");
		
		addControlsToContainers();
		styleControls();
		configureControls();
		setSearchResultsCols();
	}
	
	@Override
	public void addControlsToContainers() {
		
		courtDateIdBox.getChildren().addAll(courtDateIdLabel, courtDateIdField);
		dateBox.getChildren().addAll(dateLabel, datePicker);
		verdictBox.getChildren().addAll(verdictLabel, verdictCombo);
		prisonerIdBox.getChildren().addAll(prisonerIdLabel, prisonerIdField);
		submitResetBtnBox.getChildren().addAll(submitBtn, resetBtn);
		
		this.searchInputsContainer.getChildren().addAll(courtDateIdBox, dateBox, verdictBox, prisonerIdBox, submitResetBtnBox);	
	}
			
	@Override
	public void configureControls() {
		
		submitBtn.setOnAction(e -> {
			this.searchResults.getItems().clear();					
			
			this.model = new CourtDateSearchModel(
					courtDateIdField.getText(),
					getPickerValueString(datePicker),
					getComboValueString(verdictCombo),
					prisonerIdField.getText()
			);
			
			try {
				CourtDateSearchModel receivedModel = (CourtDateSearchModel) this.controller.search(this.model);
				this.searchResults.getItems().addAll(receivedModel.getResultsList());
			} catch (SQLException ex) {
				System.err.print(ex);
			}
		});
		
		resetBtn.setOnAction( e-> {
			courtDateIdField.setText("");
			datePicker.setValue(null);
			verdictCombo.setValue("");
			prisonerIdField.setText("");
		});
		
		datePicker.setMinHeight(12);
		datePicker.setMinWidth(170);
		configPickerDateFormat(datePicker, createStringConverter("yyyy-MM-dd"));

		addComboBoxOptions(verdictCombo, "Pending", "Guilty", "Not Guilty");
		
		verdictCombo.setMinSize(10, verdictBox.getWidth());
		
	}
	
	
	@Override
	public void styleControls() {
		
		courtDateIdBox.getStyleClass().add("search-control-group");
		dateBox.getStyleClass().add("search-control-group");
		verdictBox.getStyleClass().add("search-control-group");
		prisonerIdBox.getStyleClass().add("search-control-group");
		submitResetBtnBox.getStyleClass().add("search-control-group");
		
	}
	
	@Override
	public void setSearchResultsCols() {
		
		TableColumn<CourtDate, String> courtDateIdCol = new TableColumn<>("Court Date ID");
		courtDateIdCol.setCellValueFactory(new PropertyValueFactory("COURT_DATE_ID"));
		courtDateIdCol.prefWidthProperty().bind(this.searchResults.widthProperty().multiply(.2));

		TableColumn<CourtDate, String> dateCol = new TableColumn<>("Date");
		dateCol.setCellValueFactory(new PropertyValueFactory("date"));
		dateCol.prefWidthProperty().bind(this.searchResults.widthProperty().multiply(.2));

		TableColumn<CourtDate, String> verdictCol = new TableColumn<>("Verdict");
		verdictCol.setCellValueFactory(new PropertyValueFactory("verdict"));
		verdictCol.prefWidthProperty().bind(this.searchResults.widthProperty().multiply(.2));
		
		TableColumn<CourtDate, Integer> prisonerIdCol = new TableColumn<>("Prisoner ID");
		prisonerIdCol.setCellValueFactory(new PropertyValueFactory("PRISONER_ID"));
		prisonerIdCol.prefWidthProperty().bind(this.searchResults.widthProperty().multiply(.2));

		this.searchResults.getColumns().setAll(courtDateIdCol, dateCol,
				verdictCol, prisonerIdCol);
	}
	
	@Override
	public VBox getPane() {
		return container;
	}
}
