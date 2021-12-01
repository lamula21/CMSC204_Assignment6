import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Set;

/**
 * This class is the Data Manager and will hold an object of Graph. 
 * Implement the TownGraphManagerInterface. 
 * There are methods to populate the graph (reading from a text file), add a town (vertices), add a road (edge), list all towns and all roads, and list towns adjacent to a given town.
 * @author pjose
 *
 */
public class TownGraphManager implements TownGraphManagerInterface{

	private Graph townGraph;
	
	
	TownGraphManager(){
		townGraph = new Graph();
	}
	
	
	/**
	 * Adds a road with 2 towns and a road name
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @param roadName name of road
	 * @return true if the road was added successfully
	 */
	@Override
	public boolean addRoad(String town1, String town2, int weight, String roadName) {
		
		Town source = new Town(town1);
		Town destination = new Town(town2);
		
		Road addedRoad = townGraph.addEdge(source, destination, weight, roadName);
		
		if (townGraph.getEdge(source, destination) == addedRoad) {
			return true;
		}
		return false;
	}

	
	/**
	 * Returns the name of the road that both towns are connected through
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return name of road if town 1 and town2 are in the same road, returns null if not
	 */
	@Override
	public String getRoad(String town1, String town2) {
		
		Town source = new Town(town1);
		Town destination = new Town(town2);
		
		Road road = townGraph.getEdge(source, destination);
		return road.getName();
	}

	
	/**
	 * Adds a town to the graph
	 * @param v the town's name  (lastname, firstname)
	 * @return true if the town was successfully added, false if not
	 */
	@Override
	public boolean addTown(String v) {
		
		Town newTown = new Town(v);
		return townGraph.addVertex(newTown);
	
	}

	
	/**
	 * Gets a town with a given name
	 * @param name the town's name 
	 * @return the Town specified by the name, or null if town does not exist
	 */
	@Override
	public Town getTown(String name) {

		Set<Town> towns_Of_theGraph = townGraph.vertexSet();
		
		for ( Town thisTown : towns_Of_theGraph ) {
			if (thisTown.getName().equals(name))
				return thisTown;
		}
		return null;
		
	}

	
	/**
	 * Determines if a town is already in the graph
	 * @param v the town's name 
	 * @return true if the town is in the graph, false if not
	 */
	@Override
	public boolean containsTown(String v) {
		return townGraph.containsVertex(new Town(v));
	}

	
	/**
	 * Determines if a road is in the graph
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return true if the road is in the graph, false if not
	 */
	@Override
	public boolean containsRoadConnection(String town1, String town2) {

		return townGraph.containsEdge( new Town(town1), new Town(town2) );
	}

	
	/**
	 * Creates an arraylist of all road titles in sorted order by road name
	 * @return an arraylist of all road titles in sorted order by road name
	 */
	@Override
	public ArrayList<String> allRoads() {
		
		ArrayList<String> allRoads = new ArrayList<String>();
		
		for (Road thisRoad : townGraph.edgeSet()) {
			allRoads.add(thisRoad.getName());
		}
		
		// Sort all Roads by name
		Collections.sort(allRoads);
		return allRoads;
	}

	
	/**
	 * Deletes a road from the graph
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @param roadName the road name
	 * @return true if the road was successfully deleted, false if not
	 */
	@Override
	public boolean deleteRoadConnection(String town1, String town2, String roadName) {

		Town source = new Town(town1);
		Town destination = new Town(town2);
		
		Road removeRoad = townGraph.getEdge(source, destination);
		
		if (removeRoad.getName().equals(roadName)) 
			townGraph.removeEdge(source, destination, removeRoad.getWeight(), roadName);

		if (!townGraph.containsEdge(source, destination))
			return true;

		return false;

		
	}

	
	/**
	 * Deletes a town from the graph
	 * @param v name of town (lastname, firstname)
	 * @return true if the town was successfully deleted, false if not
	 */
	@Override
	public boolean deleteTown(String v) {
		
		return townGraph.removeVertex(new Town(v));
	}

	
	/**
	 * Creates an arraylist of all towns in alphabetical order (last name, first name)
	 * @return an arraylist of all towns in alphabetical order (last name, first name)
	 */
	@Override
	public ArrayList<String> allTowns() {
		
		ArrayList<String> allTowns = new ArrayList<String>();
		
		for (Town thisTown : townGraph.vertexSet()) {
			allTowns.add(thisTown.getName());
		}
		
		// Sort all Roads by name
		Collections.sort(allTowns);
		return allTowns;
	}

	
	/**
	 * Returns the shortest path from town 1 to town 2
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return an Arraylist of roads connecting the two towns together, null if the
	 * towns have no path to connect them.
	 */
	@Override
	public ArrayList<String> getPath(String town1, String town2) {
		Town source = new Town(town1);
		Town destination = new Town(town2);
		return townGraph.shortestPath(source, destination);
	}


	/**
	 * Populate the Graph according a file
	 * @param selectedFile
	 * @throws FileNotFoundException
	 */
	public void populateTownGraph(File selectedFile) throws FileNotFoundException {
		
		Scanner scanner = new Scanner(selectedFile);
		String[] line;
		String[] road_miles;
		String road = "";
		String miles = "";
		String town1 = "";
		String town2 = "";
		
		while(scanner.hasNextLine()) {
			line = scanner.nextLine().split(";");
			
			road_miles = line[0].split(",");
			road = road_miles[0];
			miles = road_miles[1];
			
			town1 = line[1];
			town2 = line[2];
			
			addTown(town1);
			addTown(town2);
			addRoad(town1, town2, Integer.parseInt(miles), road);
			
		}
		
		
		
		
		
		
	}

}
