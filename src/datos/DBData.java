package datos;

import java.util.ArrayList;

public class DBData {
	private String query;
	private ArrayList<Object> parameters;
	
	public DBData() {
		
	}
	
	public DBData(String query) {
		this.query = query;
	}

	public String getQuery() {
		return query;
	}
	
	public void setQuery(String query) {
		this.query = query;
	}
	
	public ArrayList<Object> getParameters() {
		return parameters;
	}
	
	public void setParameters(ArrayList<Object> parameters) {
		this.parameters = parameters;
	}
	
	public void addParameter(Object parameter) {
		if (this.parameters == null)
			this.parameters = new ArrayList<Object>();
		
		this.parameters.add(parameter);
	}
}
