package info.sroman.SOBS;

import info.sroman.SOBS.CourtDate.CourtDateController;
import info.sroman.SOBS.CourtDate.CourtDateSearchModel;
import info.sroman.SOBS.CourtDate.CourtDateSearchView;
import info.sroman.SOBS.Entities.Cell;
import info.sroman.SOBS.Prisoner.PrisonerController;
import info.sroman.SOBS.Prisoner.PrisonerSearchModel;
import info.sroman.SOBS.Prisoner.PrisonerSearchView;
import info.sroman.SOBS.Visitor.VisitorSearchModel;
import info.sroman.SOBS.Visitor.VisitorController;
import info.sroman.SOBS.Visitor.VisitorSearchView;
import info.sroman.SOBS.Visit.VisitController;
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
	PrisonerController prisonerController;
	
	PrisonerDAO prisonerDao;
	VisitDAO visitDao;
	VisitorDAO visitorDao;
	CourtDateDAO courtDateDao;

	VisitorSearchView visitorSearchView;
	VisitorSearchModel visitorSearchModel;
	VisitorController visitorSearchController;

	VisitSearchView visitSearchView;
	VisitSearchModel visitSearchModel;
	VisitController visitSearchController;
	
	CourtDateSearchView courtDateSearchView;
	CourtDateSearchModel courtDateSearchModel;
	CourtDateController courtDateSearchController;

	@Override
	public void start(Stage stage) throws Exception {
		
		prisonerDao = new PrisonerDAO();
		visitDao = new VisitDAO();
		visitorDao = new VisitorDAO();
		courtDateDao = new CourtDateDAO();
		
		prisonerSearchModel = new PrisonerSearchModel();
		prisonerController = new PrisonerController(prisonerDao);
		prisonerSearchView = new PrisonerSearchView(prisonerController);

		visitorSearchModel = new VisitorSearchModel();
		visitorSearchController = new VisitorController(visitorDao);
		visitorSearchView = new VisitorSearchView(visitorSearchController);

		visitSearchModel = new VisitSearchModel();
		visitSearchController = new VisitController(visitDao);
		visitSearchView = new VisitSearchView(visitSearchController);
	
		courtDateSearchModel = new CourtDateSearchModel();
		courtDateSearchController = new CourtDateController(courtDateDao);
		courtDateSearchView = new CourtDateSearchView(courtDateSearchController);

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
		courtDatesTab.setContent(courtDateSearchView.getPane());

		tabs.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
		tabs.getTabs().addAll(prisonersTab, visitorsTab, visitsTab, courtDatesTab);

		container = new BorderPane();
		container.setCenter(tabs);

		root.getChildren().addAll(container);

		Scene scene = new Scene(root);
		scene.getStylesheets().add("/styles/Styles.css");

		stage.setTitle("Sheriff's Office Booking System");
		stage.setScene(scene);
		
		Database.genDB();
		
		for (int i = 0; i < 50; i++) {
			Database.createCell("Hospital");
		}
		
		for (int i = 0; i < 10; i++) {
			Database.createCell("Isolation");
		}
		
		for (int i = 0; i < 200; i++) {
			Database.createCell("MinSec");
		}
		
		
		for (int i = 0; i < 50; i++) {
			Cell c = Database.createCell("Isolation");
			Database.createPrisoner(i);
			Database.createBunk(i, c.getCELL_ID());
		}

		for (int i = 0; i < 25; i++) {
			Database.createVisitor();
		}
		
		for (int i = 0; i < 20; i++) {
			Database.createVisit();
		}
		
		for (int i = 0; i < 20; i++) {
			Database.createCourtDate();
		}
		
		stage.show();

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
