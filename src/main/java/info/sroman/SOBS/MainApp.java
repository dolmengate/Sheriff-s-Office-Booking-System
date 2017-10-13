package info.sroman.SOBS;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApp extends Application {
	
	BorderPane container;
	
	HBox sidebarNavigation;
	
	TabPane tabs;
	Tab prisonersTab;
	Tab visitorsTab;
	Tab visitsTab;
	Tab courtDatesTab;
	
	PrisonerSearchComponent prisonerSearchComponent;
	PrisonerSearchModel prisonerSearchModel;
	PrisonerSearchController prisonerSearchController;

    @Override
    public void start(Stage stage) throws Exception {

		prisonerSearchModel = new PrisonerSearchModel();
		prisonerSearchController = new PrisonerSearchController();
		prisonerSearchComponent = new PrisonerSearchComponent(prisonerSearchController);
		
		AnchorPane root = new AnchorPane();
		tabs = new TabPane();
		tabs.setMinSize(1280, 720);
		
		prisonersTab = new Tab("Prisoners");
		
		prisonersTab.setContent(prisonerSearchComponent.getNode());
		
		visitorsTab = new Tab("Visitors");
		visitsTab = new Tab("Visits");
		courtDatesTab = new Tab("Court Dates");
		
		tabs.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
		tabs.getTabs().addAll(prisonersTab, visitorsTab, visitsTab, courtDatesTab);
		
		sidebarNavigation = new HBox();
		sidebarNavigation.setPrefWidth(200);
		
		container = new BorderPane();
		container.setLeft(sidebarNavigation);
		container.setRight(tabs);
		
		root.getChildren().add(container);
		
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        
        stage.setTitle("Sheriff's Office Booking System");
        stage.setScene(scene);
        stage.show();	
		
		Database.genDB();
		
		for (int i = 0; i < 50; i++) {
			Database.createPrisoner();
		}
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
