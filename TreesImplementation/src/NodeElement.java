import java.util.LinkedList;

public class NodeElement {
	private LinkedList<CrimeIncidents> listCrimeIncidents;
	private int zipCode;
	public NodeElement(LinkedList<CrimeIncidents> listCrimeIncidents, int zipCode) {
		super();
		this.listCrimeIncidents = listCrimeIncidents;
		this.zipCode = zipCode;
	}
	public LinkedList<CrimeIncidents> getListCrimeIncidents() {
		return listCrimeIncidents;
	}
	public void setListCrimeIncidents(LinkedList<CrimeIncidents> listCrimeIncidents) {
		this.listCrimeIncidents = listCrimeIncidents;
	}
	public int getZipCode() {
		return zipCode;
	}
	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}	
	
}
