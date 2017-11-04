package Controllers;

import info.sroman.SOBS.Controller;
import Models.CourtDateSearchModel;
import info.sroman.SOBS.Dao;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CourtDateController extends Controller<CourtDateSearchModel> {

	public <D extends Dao> CourtDateController(D dao) {
		super(dao);
	}

	@Override
	public CourtDateSearchModel search(CourtDateSearchModel model) throws SQLException {
		ObservableList rl = FXCollections.observableArrayList();

		rl.setAll(
				dao.findAll(model)
		);
		
		model.setResultsList(rl);
		return model;
	}

	@Override
	public boolean edit(CourtDateSearchModel model) throws SQLException {
		return false;
	}

	@Override
	public boolean add(CourtDateSearchModel model) throws SQLException {
		return false;
	}

	@Override
	public boolean remove(CourtDateSearchModel model) throws SQLException {
		return false;
	}

}
