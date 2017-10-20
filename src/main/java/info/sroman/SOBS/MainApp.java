package info.sroman.SOBS;

import info.sroman.SOBS.Prisoner.PrisonerSearchModel;
import info.sroman.SOBS.Prisoner.PrisonerSearchView;
import info.sroman.SOBS.Prisoner.PrisonerSearchController;
import info.sroman.SOBS.Visitor.VisitorSearchModel;
import info.sroman.SOBS.Visitor.VisitorSearchController;
import info.sroman.SOBS.Visitor.VisitorSearchView;
import info.sroman.SOBS.Visit.VisitSearchController;
import info.sroman.SOBS.Visit.VisitSearchModel;
import info.sroman.SOBS.Visit.VisitSearchView;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

	BorderPane container;

	TabPane tabs;
	Tab prisonersTab;
	Tab visitorsTab;
	Tab visitsTab;
	Tab courtDatesTab;

	PrisonerSearchView prisonerSearchView;
	PrisonerSearchModel prisonerSearchModel;
	PrisonerSearchController prisonerSearchController;

	VisitorSearchView visitorSearchView;
	VisitorSearchModel visitorSearchModel;
	VisitorSearchController visitorSearchController;

	VisitSearchView visitSearchView;
	VisitSearchModel visitSearchModel;
	VisitSearchController visitSearchController;

	@Override
	public void start(Stage stage) throws Exception {

		prisonerSearchModel = new PrisonerSearchModel();
		prisonerSearchController = new PrisonerSearchController();
		prisonerSearchView = new PrisonerSearchView(prisonerSearchController);

		visitorSearchModel = new VisitorSearchModel();
		visitorSearchController = new VisitorSearchController();
		visitorSearchView = new VisitorSearchView(visitorSearchController);

		visitSearchModel = new VisitSearchModel();
		visitSearchController = new VisitSearchController();
		visitSearchView = new VisitSearchView(visitSearchController);

		AnchorPane root = new AnchorPane();
		tabs = new TabPane();
		tabs.setMinSize(1280, 720);

		prisonersTab = new Tab("Prisoners");
		prisonersTab.setContent(prisonerSearchView.getPane());

		visitorsTab = new Tab("Visitors");
		visitorsTab.setContent(visitorSearchView.getPane());

		visitsTab = new Tab("Visits");
		visitsTab.setContent(visitSearchView.getPane());

		courtDatesTab = new Tab("Court Dates");

		tabs.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
		tabs.getTabs().addAll(prisonersTab, visitorsTab, visitsTab, courtDatesTab);

		container = new BorderPane();
		container.setCenter(tabs);

		root.getChildren().addAll(container);

		Scene scene = new Scene(root);
		scene.getStylesheets().add("/styles/Styles.css");

		stage.setTitle("Sheriff's Office Booking System");
		stage.setScene(scene);
		stage.show();

		Database.genDB();
		
		for (int i = 0; i < 50; i++) {
			Database.createPrisoner();
		}
		
		for (int i = 0; i < 50; i++) {
			Database.createVisitor();
		}
		
		for (int i = 0; i < 50; i++) {
			Database.createVisit();
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
