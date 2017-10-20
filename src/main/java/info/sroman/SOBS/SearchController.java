package info.sroman.SOBS;

import javafx.event.ActionEvent;

public abstract class SearchController {
	
	public abstract SearchModel makeQuery(SearchModel model, ActionEvent event);
	public abstract String constructStatement();
	
	/*
	*	Return AND statement if TextField has input, otherwise returns an empty string
	*/
	public String checkFieldForAndStatement(String fieldText, String colName) {
		StringBuilder fieldWhere = new StringBuilder();
		if (!fieldText.equals(""))
			return new String(fieldWhere.append(colName).append(" = '").append(fieldText).append("'"));
		return "";
	}
}
