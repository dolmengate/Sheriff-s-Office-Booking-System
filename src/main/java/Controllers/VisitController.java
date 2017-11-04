package info.sroman.SOBS.Visit;

import info.sroman.SOBS.Controller;
import info.sroman.SOBS.Dao;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class VisitController extends Controller<VisitSearchModel> {
		
	public <D extends Dao> VisitController(D dao) {
		super(dao);
	}
	
	@Override
	public VisitSearchModel search(VisitSearchModel model) throws SQLException {
		ObservableList rl = FXCollections.observableArrayList();
		rl.setAll(
			dao.findAll(model)
		);
		
		model.setResultsList(rl);
		return model;
	}
	
	@Override
	public boolean edit(VisitSearchModel model) throws SQLException {
		return false;
	}
	
	@Override
	public boolean add(VisitSearchModel model) throws SQLException {
		return false;
	}
	
	@Override
	public boolean remove(VisitSearchModel model) throws SQLException {
		return false;
	}
}
