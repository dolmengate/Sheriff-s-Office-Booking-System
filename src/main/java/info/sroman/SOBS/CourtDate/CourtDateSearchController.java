package info.sroman.SOBS.CourtDate;

import info.sroman.SOBS.Controller;
import info.sroman.SOBS.SearchModel;

public class CourtDateSearchController extends Controller {
	
	
	@Override
	public String constructStatement() {
		return "";
	}
	
	@Override
	public SearchModel makeSelect(SearchModel model) {
		return new CourtDateSearchModel();
	}

}
