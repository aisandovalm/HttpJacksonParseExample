package cl.telematica.httpjacksonparseexample.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ResultsModel {
	
	@JsonProperty("event")
	public List<EventModel> eventList;
	

}
