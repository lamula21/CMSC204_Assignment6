import java.util.List;


/**
 * Town class holds the name of the town
 * Implements Comparable interface
 * @author pjose
 *
 */
public class Town implements Comparable<Town> {

	private String name;
	
	/**
	 * Constructor
	 * @param name
	 */
	Town (String name){
		this.name = name;
	}
	
	/**
	 * Copy Constructor
	 * @param templateTown
	 */
	Town (Town templateTown){
		this.name = templateTown.getName();
	}
	
	/**
	 * Compares two towns according on name
	 * @return 0 if names are equal, positive/negative if unequal
	 */
	@Override
	public int compareTo(Town o) {
		return o.name.compareTo(this.name);
	}


	/**
	 * Returns the name of the town
	 * @return a String towns' name
	 */
	public String getName() {
		return name;
	}

	/**
	 * toString that prints name of this town
	 * @return the name of town
	 */
	public String toString() {
		String toString = this.name;
		return toString;
	}
	/**
	 * HashCode method for Town object
	 * @return the hashCode of the town
	 */
	public int hashCode() {
		return name.hashCode();
		}
	
	
	/**
	 * Equals method for town object
	 * @param obj town to be compared with
	 * @return true if names are equal, false if not
	 */
	@Override
	public boolean equals(Object o) {
		Town obj = (Town) o;
		return this.name.equals(obj.name);
		}
}
