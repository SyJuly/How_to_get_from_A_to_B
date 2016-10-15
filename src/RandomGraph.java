import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class RandomGraph {
	private int vertices;
	private int edges;
	public WeightedGraph graph;

	public RandomGraph(int vertices, int edges){
		this.vertices = vertices;
		this.edges = edges;
		generateGraph();
		printGraph();
	}
	
	public RandomGraph(){
		vertices = 6;
		edges = 6;
		WeightedGraph graph = new WeightedGraph();
		graph.addVertex("A");
		graph.addVertex("B");
		graph.addVertex("C");
		graph.addVertex("D");
		graph.addVertex("E");
		graph.addVertex("F");
		
		graph.addEgde(graph.getVertex("A"), graph.getVertex("B"), 1);
		graph.addEgde(graph.getVertex("A"), graph.getVertex("C"), 3);
		graph.addEgde(graph.getVertex("B"), graph.getVertex("F"), 6);
		graph.addEgde(graph.getVertex("B"), graph.getVertex("D"), 2);
		graph.addEgde(graph.getVertex("C"), graph.getVertex("F"), 8);
		graph.addEgde(graph.getVertex("D"), graph.getVertex("F"), 1);
		this.graph = graph;
		printGraph();
	}
	
	public WeightedGraph generateGraph(){
		graph = new WeightedGraph();
		// add all the vertices with a random letter as name
		for(int i = 0; i<vertices; i++){
			String name = "";
			if((i)/26==0){
				Character letter = (char)(i+65);
				name = letter.toString();
			}
			else{
				Character letter = (char)((i-26*(i/26))+65);
				int number = i/26 + 1;
				name = letter.toString()+number;
			}
			graph.addVertex(name);
		}
		//add all the edges with random vertices
		for(int i = 0; i<edges; i++){
			Random rand = new Random();
			int weight = rand.nextInt(10)+1;
			int n1 = rand.nextInt(vertices);
			int n2 = rand.nextInt(vertices);
			while(n1==n2){
				n2 = rand.nextInt(vertices);
			}
			graph.addEgde(graph.getVertices().get(n1), graph.getVertices().get(n2), weight);
		}
		
		return graph;
	}
	
	public static String randomLetter(){
		Random rand = new Random();
		int n = rand.nextInt(26)+65;
		Character c = (char) n;
		return c.toString();
	}

	
	private void printGraph(){
		System.out.println("Vertices");
		for(int i = 0; i<vertices; i++){
			System.out.println(graph.getEdgeList(graph.getVertices().get(i)).toString());
		}
	}
	
	public static void main(String[] args){
		RandomGraph graph = new RandomGraph(40,40);
		WeightedGraph g = graph.graph;
		String[] st = g.getRandomSourceAndTarget();
//		System.out.println("Minimum: ");
//		System.out.println(g.pathToString(g.findMinimumPath(g.getVertex(st[0]), g.getVertex(st[1]))));
//		System.out.println();
		System.out.println("Shortest: ");
		System.out.println(g.pathToString(g.findShortestPath(g.getVertex(st[0]), g.getVertex(st[1])),st[0], st[1]));
	}
	
}
