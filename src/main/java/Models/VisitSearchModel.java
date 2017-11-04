package Models;

import info.sroman.SOBS.SearchModel;
import javafx.collections.ObservableList;

public class VisitSearchModel extends SearchModel {
	
	private String visitId;
	private String startTime;
	private String endTime;
	private String notes;
	private String visitorId;
	private String prisonerId;
		
	public VisitSearchModel() {}

	public VisitSearchModel(String visitId, String startTime, String endTime, 
			String notes, String visitorId, String prisonerId) {
		this.visitId = visitId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.notes = notes;
		this.visitorId = visitorId;
		this.prisonerId = prisonerId;
	}

	public String getVisitId() {
		return visitId;
	}

	public void setVisitId(String visitId) {
		this.visitId = visitId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getVisitorId() {
		return visitorId;
	}

	public void setVisitorId(String visitorId) {
		this.visitorId = visitorId;
	}

	public String getPrisonerId() {
		return prisonerId;
	}

	public void setPrisonerId(String prisonerId) {
		this.prisonerId = prisonerId;
	}
}
