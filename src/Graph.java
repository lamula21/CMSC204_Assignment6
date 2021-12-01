import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/**
 * Data Structure.
 * Graph class that implements the GraphInterface given.  
 * For Graph<V,E>,  V is the vertex type (a Town), E is the edge type (a Road).  
 * @author pjose
 *
 */
public class Graph implements GraphInterface<Town,Road>{

	private Set<Town> towns; // Vertex
	private Set<Road> roads; // Edge
	private Map <String, Town> previousVertex;
	
	Graph(){
		towns = new HashSet<>();
		roads = new HashSet<>();
		previousVertex = new HashMap<>();
	}
	
	
	
	/**
     * Returns an edge connecting source vertex to target vertex if such
     * vertices and such edge exist in this graph. Otherwise returns
     * null. If any of the specified vertices is null
     * returns null
	 * @param name the name of the town
	 * @return the town if found, otherwise null
	 */
	@Override
	public Road getEdge(Town sourceVertex, Town destinationVertex) {

		// If any of the specified vertices is null, returns null
		if (sourceVertex == null || destinationVertex == null)
			return null;
		
		// Use for loop to find if has the sourceVertex and destinationVertex within this Set of roads
		for ( Road thisRoad: roads)
		{
			if ( thisRoad.getSource().equals(sourceVertex) || thisRoad.getSource().equals(destinationVertex) ) 
				if (thisRoad.getDestination().equals(destinationVertex) || thisRoad.getDestination().equals(sourceVertex))
					return thisRoad;
		}
		return null;
	}

	
	
    /**
     * Creates a new edge in this graph, going from the source vertex to the
     * target vertex, and returns the created edge. 
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @param weight weight of the edge
     * @param description description for edge
     *
     * @return The newly created edge if added to the graph, otherwise null.
     *
     * @throws IllegalArgumentException if source or target vertices are not
     * found in the graph.
     * @throws NullPointerException if any of the specified vertices is null.
     */
	@Override
	public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		
		// IllegalArgumentException if source or target vertices are not found in the graph.
		if (!containsVertex(sourceVertex) || !containsVertex(destinationVertex) )
			throw new IllegalArgumentException();
		
		// NullPointerException if any of the specified vertices is null.
		if (sourceVertex == null || destinationVertex == null)
			throw new NullPointerException();
		
		// Creates a new edge in this graph and add it
		Road newRoad = new Road(sourceVertex, destinationVertex, weight, description);
		roads.add(newRoad);
		
		// Return the newly created edge if added to the graph, otherwise null.
		if (containsEdge(sourceVertex, destinationVertex))
			return newRoad;
		else
			return null;
	}

	
    /**
     * Adds the specified vertex to this graph if not already present. More
     * formally, adds the specified vertex, v, to this graph if
     * this graph contains no vertex u such that
     * u.equals(v). If this graph already contains such vertex, the call
     * leaves this graph unchanged and returns false. In combination
     * with the restriction on constructors, this ensures that graphs never
     * contain duplicate vertices.
     *
     * @param v vertex to be added to this graph.
     *
     * @return true if this graph did not already contain the specified
     * vertex.
     *
     * @throws NullPointerException if the specified vertex is null.
     */
	@Override
	public boolean addVertex(Town v) {
		
		// NullPointerException if the specified vertex is null
		if (v == null)
			throw new NullPointerException();
		
		// Adds the specified vertex to this graph if not already present
		// true if this graph contains the specified vertex
		if (!containsVertex(v)) {
			towns.add(v);
			return true;
			}
		else 
			return false;
	}

	
    /**
     * Returns true if and only if this graph contains an edge going
     * from the source vertex to the target vertex. In undirected graphs the
     * same result is obtained when source and target are inverted. If any of
     * the specified vertices does not exist in the graph, or if is
     * null, returns false.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     *
     * @return true if this graph contains the specified edge.
     */
	@Override
	public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
		
		// Use for loop to find if there's a road in this Set of roads(Edges) that goes from "source" to "destination" 
		// or "destination" to "source"
		for ( Road thisRoad: roads)
		{
			if (  (thisRoad.getSource().equals(sourceVertex) || thisRoad.getSource().equals(destinationVertex)) 
				&& (thisRoad.getDestination().equals(destinationVertex) || thisRoad.getDestination().equals(sourceVertex)) )
					return true;
		}
		return false;
	}

	
    /**
     * Returns true if this graph contains the specified vertex. More
     * formally, returns true if and only if this graph contains a
     * vertex u such that u.equals(v). If the
     * specified vertex is null returns false.
     *
     * @param v vertex whose presence in this graph is to be tested.
     *
     * @return true if this graph contains the specified vertex.
     */
	@Override
	public boolean containsVertex(Town v) {
		
		// For loop to find if there is a town v in this set of Towns
		for (Town thisVertex : towns) {
			if (thisVertex.equals(v))
				return true;
		}
		return false;
	}

	
    /**
     * Returns a set of the edges contained in this graph. 
     * @return a set of the edges contained in this graph.
     */
	@Override
	public Set<Road> edgeSet() {
		return roads;
	}

	
	
    /**
     * Returns a set of all edges touching the specified vertex (also
     * referred to as adjacent vertices). If no edges are
     * touching the specified vertex returns an empty set.
     *
     * @param vertex the vertex for which a set of touching edges is to be
     * returned.
     *
     * @return a set of all edges touching the specified vertex.
     *
     * @throws IllegalArgumentException if vertex is not found in the graph.
     * @throws NullPointerException if vertex is null.
     */
	@Override
	public Set<Road> edgesOf(Town vertex) {
		
		// A set of all edges touching the specified vertex
		Set<Road> setOfEdges = new HashSet<>();
		
		// IllegalArgumentException if vertex is not found in the graph
		if (!containsVertex(vertex))
			throw new IllegalArgumentException();
		
		// NullPointerException if vertex is null
		if ( vertex == null)
			throw new NullPointerException();
		
		// Find each road from this Set of Roads which has the same vertex either as "source" or "destination"
		for (Road thisEdge : roads) {
			if (thisEdge.getSource().equals(vertex) || thisEdge.getDestination().equals(vertex) ) {
				setOfEdges.add(thisEdge);
			}
		}

		return setOfEdges;
	}

	
	
    /**
     * Removes an edge going from source vertex to target vertex, if such
     * vertices and such edge exist in this graph. 
     * 
     * If weight >- 1 it must be checked
     * If description != null, it must be checked 
     * 
     * Returns the edge if removed
     * or null otherwise.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @param weight weight of the edge
     * @param description description of the edge
     *
     * @return The removed edge, or null if no edge removed.
     */
	@Override
	public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {

		// Create an Road to be removed
		Road roadRemoved = new Road(sourceVertex,destinationVertex,weight,description);
		
		// Remove if such vertices and such edge exist in this graph
		if (containsVertex(sourceVertex) && containsVertex(destinationVertex))
				for (Road thisRoad : roads) {
					if (thisRoad.equals(roadRemoved)) {
						roads.remove(thisRoad);
						return roadRemoved;
					}
					
				}
		return null;
	}

	
	
    /**
     * Removes the specified vertex from this graph including all its touching
     * edges if present. More formally, if the graph contains a vertex 
     * u such that u.equals(v), the call removes all edges
     * that touch u and then removes u itself. If no
     * such u is found, the call leaves the graph unchanged.
     * Returns true if the graph contained the specified vertex. (The
     * graph will not contain the specified vertex once the call returns).
     *
     * If the specified vertex is null returns false.
     *
     * @param v vertex to be removed from this graph, if present.
     *
     * @return true if the graph contained the specified vertex;
     * false otherwise.
     */
	@Override
	public boolean removeVertex(Town v) {
		
		// If the graph contains a vertex, removes the specified vertex from this graph
		// including all its touching edges if present
		if (containsVertex(v)) 
		{

			// Find the specific town in this set of Towns
			for (Town thisTown: towns) 
			{
				// Once found it
				if (thisTown.equals(v)) 
				{
					// Remove each Road of the Town
					for (Road thisRoad: edgesOf(thisTown))
						roads.remove(thisRoad);

					// Then remove the Town from the set of Towns
					towns.remove(thisTown);
					return true;
				}
			}
		}
		return false;
	}

	
	
	/**
     * Returns a set of the vertices contained in this graph.
     * @return a set view of the vertices contained in this graph.
     */
	@Override
	public Set<Town> vertexSet() {
		return towns;
	}

	
	
    /**
     * Find the shortest path from the sourceVertex to the destinationVertex
     * call the dijkstraShortestPath with the sourceVertex
     * @param sourceVertex starting vertex
     * @param destinationVertex ending vertex
     * @return An arraylist of Strings that describe the path from sourceVertex
     * to destinationVertex
     * They will be in the format: startVertex "via" Edge "to" endVertex weight
	 * As an example: if finding path from Vertex_1 to Vertex_10, the ArrayList<String>
	 * would be in the following format(this is a hypothetical solution):
	 * Vertex_1 via Edge_2 to Vertex_3 4 (first string in ArrayList)
	 * Vertex_3 via Edge_5 to Vertex_8 2 (second string in ArrayList)
	 * Vertex_8 via Edge_9 to Vertex_10 2 (third string in ArrayList)
     */ 
	@Override
	public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
		
		ArrayList<String> output = new ArrayList<String>();
		
		// Call method to populate the Map_previousVertex of each Town
		dijkstraShortestPath(sourceVertex);
		
		// Store the destinationTown in a new variable
		Town nextTown = destinationVertex;
		
		// If the map previousVertex does not have the destinationTown
		// clear the arrayList 
		while (!nextTown.equals(sourceVertex)) {
			if (!previousVertex.containsKey(nextTown.getName())) {
				output.clear();
				break;
			}
			// Get the previous Town of the destinationTown
			Town previousTown = previousVertex.get(nextTown.getName());
			
			// If the town is not connected to any roads, return output
			if (previousTown == null) {
				return output;}
			// Get the edges between the destinationTown and its previousTown
			Road edge = getEdge(previousTown, nextTown);
			// Add the information of the destinationTown and its previousTown with their road
			output.add(0, previousTown.getName() + " via " + edge.getName() + " to " + nextTown.getName() + " " + edge.getWeight() + " mi");
			// The previousTown will become the new destinationTown
			nextTown = previousTown;
		}
		return output;
	}

    /**
     * Dijkstra's Shortest Path Method.  Internal structures are built which
     * hold the ability to retrieve the path, shortest distance from the
     * sourceVertex to all the other vertices in the graph, etc.
     * @param sourceVertex the vertex to find shortest path from
     * 
     */
	@Override
	public void dijkstraShortestPath(Town sourceVertex) {
		
		Set<Town> visisted = new HashSet<Town>(); // Visited vertices
		ArrayList<Town> unvisited = new ArrayList<Town>(); // Unvisited vertices
		HashMap<String, Integer> distance = new HashMap<String, Integer>(); // Keep track to every town distance
		previousVertex.clear(); // Previous Vertex - empty Map
		
		// For loop: 
		// 1. to add all towns vertices to the Unvisited Vertices ArrayList
		// 2. Distances from sourceVertex to all other vertices = INFINITY
		// 3. Previous Vertex from each Town is set to null
		for (Town thisTown : towns) {
			unvisited.add(thisTown);
			distance.put(thisTown.getName(), Integer.MAX_VALUE);
			previousVertex.put(thisTown.getName(), null);
		}
		
		// 4. Let distance from start vertex(sourceVertex) to sourceVertex = 0
		distance.put(sourceVertex.getName(), 0);
		
		
		// Algorithm to find the smallest vertex
		// While there're vertices unvisited, keep doing this
		while (!unvisited.isEmpty()) {
			
			int shortest = 0; // keep track of the shortest vertex in the unvisited arrayList
			
			// For loop to visit the unvisited vertex with the smallest known distance 
			for (int i = 0; i < unvisited.size(); i++) {
				
				// Store each element of the unvisited vertices in each loop
				Town unvisitedVertex = unvisited.get(i);
				
				// Find the index of the smallest vertex and store in "shortest"
				// If first vertex distance (which is infinity) is > each element's distance of the unvisited vertices
				// Store the index of the smallest vertex distance in "shortest"
				if ( distance.get(unvisited.get(shortest).getName()) > distance.get(unvisitedVertex.getName()) )
					shortest = i;
			}
			
			
			// Remove the smallest vertex by its index in the unvisited List, then add it to the Visisted vertices
			Town closestTown = unvisited.remove(shortest);
			visisted.add(closestTown);
			
			// If the current vertex = infinity, end the method.
			if (distance.get(closestTown.getName())==Integer.MAX_VALUE) {
				return;
			}
			
			// Algorithm to evaluate a visited vertex
			//for each edge of the current visited vertex
			for (Road eachEdge : edgesOf(closestTown)) {
				
				// Get the adjacent vertex by using the "road source" 
				Town adjacent = eachEdge.getDestination();
				// or "road destination"
				if (adjacent.equals(closestTown)) {
					adjacent = eachEdge.getSource();
				}
				
				// if the adjacent is already in the visited vertices, continue
				if (visisted.contains(adjacent)) {
					continue;
				}
				
				// Calculate the distance of the current vertex plus the edge weight
				int adjDistance = distance.get(closestTown.getName()) + eachEdge.getWeight();
				
				// If the calculated distance is less than the known distance
				// 1. add the new distance to the adjacent town 
				// 2. Link the adjacent town with the previous town
				if (adjDistance < distance.get(adjacent.getName())) {
					distance.put(adjacent.getName(), adjDistance);
					previousVertex.put(adjacent.getName(), closestTown);
				}
			}
		}
		
		
		
	}	

	
	
	
	
	
	
}


