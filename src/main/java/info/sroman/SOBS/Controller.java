package info.sroman.SOBS;

public abstract class Controller {
	
	public abstract SearchModel makeSelect(SearchModel model);
	public abstract String constructStatement();
	
	/*
	*	Return AND statement if TextField has input, otherwise returns an empty string
	*/
	public String checkForAnd(String fieldText, String colName) {
		StringBuilder fieldWhere = new StringBuilder();
		if (!fieldText.equals(""))
			return new String(fieldWhere.append(colName).append(" = '").append(fieldText).append("'"));
		return "";
	}
}
