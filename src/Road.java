
public class Road implements Comparable<Road> {

	private Town source, destination;
	private int distance;
	private String name;
	
	/**
	 * Constructor
	 * @param source
	 * @param destination
	 * @param degrees
	 * @param name
	 */
	Road(Town source, Town destination, int degrees, String name){
		this.source = source;
		this.destination = destination;
		this.distance = degrees;
		this.name = name;
	}
	
	/**
	 * Constructor
	 * @param source
	 * @param destination
	 * @param name
	 */
	Road(Town source, Town destination, String name){
		this.source = source;
		this.destination = destination;
		this.distance = 1;
		this.name = name;
	}
	
	/**
	 * Compares two road objects according on name
	 */
	@Override
	public int compareTo(Road o) {
		return o.name.compareTo(this.name);

	}

	/**
	 * Checks if the road contains a given town
	 * @param town the town to be checked
	 * @return true if road has town, false if not
	 */
	public boolean contains(Town town) {
		
		return (this.source.equals(town) || this.destination.equals(town)) ;
		
	}
	
	/**
	 * Equals method for road object
	 * @param r the road to be compared with
	 * @return true if ends of road are same (irregardless of order)
	 */
	@Override
	public boolean equals(Object road) {
		Road r = (Road) road;
		if ( this.source.equals(r.source) || this.source.equals(r.destination) )
			if (this.destination.equals(r.destination) || this.destination.equals(r.source))
				if (this.name.equals(r.name))
					if (this.distance == r.distance)
						return true;

		return false;
	}
	
	/**
	 * Returns the destination town of the road
	 * @return a town on the road
	 */
	public Town getDestination() {
		return this.destination;
	}
	
	/**
	 * Returns the name of the road
	 * @return the road's name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Returns the source town of the road
	 * @return a town on the road
	 */
	public Town getSource() {
		return this.source;
	}
	
	/**
	 * Returns the distance of the road
	 * @return the distance of the road
	 */
	public int getWeight() {
		return this.distance;
	}
	
	/**
	 * toString method for road object
	 */
	public String toString() {
		return name + ", " + distance + "; " + source + "; " + destination;
	}
	
}
