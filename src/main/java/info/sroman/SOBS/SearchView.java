package info.sroman.SOBS;

import javafx.scene.control.TableView;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

public abstract class SearchView extends InputView implements IComponent {
	
	// the root node of the view
	protected VBox container;
	
	// input boxes for search parameters
	protected TilePane searchInputsContainer;
	
	// results of a given search in TableView format
	protected final TableView searchResults;
	
	// model containing parameters to search by
	protected SearchModel model;
	
	public <C extends Controller> SearchView(C controller) {
		super(controller);
		this.searchResults = new TableView();
		
		this.container = new VBox(10);
		
		searchInputsContainer = new TilePane();
		searchInputsContainer.setPrefColumns(4);
		searchInputsContainer.setPrefHeight(160);
		
		container.getChildren().addAll(searchInputsContainer, searchResults);
	}
	
	/**
	 * Set controls styling.
	 */
	public abstract void styleControls();
	
	/**
	 * Configure controls.
	 */
	public abstract void configureControls();
	
	/**
	 * Organize controls into their proper containers.
	 */
	public abstract void addControlsToContainers();
	
	/**
	 * Set the proper TableView Columns for searchResults.
	 */
	public abstract void setSearchResultsCols();
}
