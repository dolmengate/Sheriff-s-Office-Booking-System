package Controllers;

import info.sroman.SOBS.Controller;
import info.sroman.SOBS.Dao;
import Models.PrisonerSearchModel;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PrisonerController extends Controller<PrisonerSearchModel> {
		
	public <D extends Dao> PrisonerController(D dao) {
		super(dao);
	}

	@Override
	public PrisonerSearchModel search(PrisonerSearchModel model) throws SQLException {
		ObservableList rl = FXCollections.observableArrayList();
		
		rl.setAll(
			this.dao.findAll(model)
		);
		
		model.setResultsList(rl);
		return model;
	}

	@Override
	public boolean edit(PrisonerSearchModel model) throws SQLException {		
		return this.dao.update(model);
	}

	@Override
	public boolean add(PrisonerSearchModel model) throws SQLException {
		return this.dao.create(model);
	}
	
	@Override
	public boolean remove(PrisonerSearchModel model) throws SQLException {
		return this.dao.delete(model);
	}
}
