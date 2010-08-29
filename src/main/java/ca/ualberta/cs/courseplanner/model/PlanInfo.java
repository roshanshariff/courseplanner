package ca.ualberta.cs.courseplanner.model;

public class PlanInfo implements java.io.Serializable, Comparable<PlanInfo> {
	
	private static final long serialVersionUID = 9056869107744111310L;

	
	private long id;
	
	private String name;

	
	public PlanInfo () { }
	
	public PlanInfo (long id, String name) {
		this.id = id;
		this.name = name;
	}

	
	public void setId(long id) { this.id = id; }

	public long getId() { return id; }

	public void setName(String name) { this.name = name; }

	public String getName() { return name; }

	
	@Override
	public int hashCode () {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals (Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		PlanInfo other = (PlanInfo) obj;
		if (id != other.id) return false;
		if (name == null) {
			if (other.name != null) return false;
		}
		else if (!name.equals(other.name)) return false;
		return true;
	}

	@Override
	public int compareTo (PlanInfo o) {
		int result = 0;
		if (result == 0) result = name.compareToIgnoreCase(o.name);
		if (result == 0) result = new Long(id).compareTo(o.id);
		return result;
	}

}
