package Models;

import info.sroman.SOBS.SearchModel;

public class CourtDateSearchModel extends SearchModel {
	
	private String courtDateId;
	private String date;
	private String verdict;
	private String prisonerId;
	
	public CourtDateSearchModel(){}

	public CourtDateSearchModel(String courtDateId, String date, String verdict, String prisonerId) {
		this.courtDateId = courtDateId;
		this.date = date;
		this.verdict = verdict;
		this.prisonerId = prisonerId;
	}

	public String getCourtDateId() {
		return courtDateId;
	}

	public void setCourtDateId(String courtDateId) {
		this.courtDateId = courtDateId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getVerdict() {
		return verdict;
	}

	public void setVerdict(String verdict) {
		this.verdict = verdict;
	}

	public String getPrisonerId() {
		return prisonerId;
	}

	public void setPrisonerId(String prisonerId) {
		this.prisonerId = prisonerId;
	}
	
	
	
}
