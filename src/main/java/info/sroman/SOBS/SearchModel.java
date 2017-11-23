package info.sroman.SOBS;

import javafx.collections.ObservableList;

public abstract class SearchModel {
	
	// list of the entities that resulted from a search
	protected ObservableList<Entity> resultsList;

	public ObservableList<Entity> getResultsList() {
		return resultsList;
	}

	public void setResultsList(ObservableList<Entity> resultsList) {
		this.resultsList = resultsList;
	}
}
