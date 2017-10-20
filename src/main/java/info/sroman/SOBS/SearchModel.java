package info.sroman.SOBS;

import javafx.collections.ObservableList;

public abstract class SearchModel {
	
	protected ObservableList resultsList;

	public ObservableList getResultsList() {
		return resultsList;
	}

	public void setResultsList(ObservableList resultsList) {
		this.resultsList = resultsList;
	}
	
	
	
}
