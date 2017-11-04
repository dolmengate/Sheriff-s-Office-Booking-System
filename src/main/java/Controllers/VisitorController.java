package info.sroman.SOBS.Visitor;

import info.sroman.SOBS.Controller;
import info.sroman.SOBS.Dao;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class VisitorController extends Controller<VisitorSearchModel> {
		
	public <D extends Dao> VisitorController(D dao) {
		super(dao);
	}
	
	@Override
	public VisitorSearchModel search(VisitorSearchModel model) throws SQLException {
		ObservableList rl = FXCollections.observableArrayList();
		
		rl.setAll(
			dao.findAll(model)
		);
		
		model.setResultsList(rl);
		return model;
	}
	
	@Override
	public boolean edit(VisitorSearchModel model) throws SQLException {
		return false;
	}
	
	@Override
	public boolean add(VisitorSearchModel model) throws SQLException {
		return false;
	}
	
	@Override
	public boolean remove(VisitorSearchModel model) throws SQLException {
		return false;
	}
}
