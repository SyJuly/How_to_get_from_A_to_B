import java.util.*;

public class WeightedGraph {
	
	private ArrayList<Vertex> vertices;
	private ArrayList<EdgeList> edges;
	
	public WeightedGraph(){
		vertices = new ArrayList<Vertex>();
		edges = new ArrayList<EdgeList>();
	}
	
	public boolean isEmpty(){
		return vertices.isEmpty();
	}
	
	public boolean hasEdge(Vertex v1, Vertex v2){
		return (getWeight(v1,v2)!=-1);
	}
	
	public void addVertex(String name){
		if(name != null && name != ""){
			Vertex v = new Vertex(name);
			vertices.add(v);
			edges.add(new EdgeList(v));
		}
	}
	
	public void removeVertex(Vertex v){
		edges.remove(edges.indexOf(getEdgeList(v)));
		for(EdgeList el: edges){
			for(int i = 0; i<el.getEdges().size(); i++){
				EdgeNode edge = el.getEdges().get(i);
				if(edge.v2.equals(v)){
					removeEdge(el.v1,v);
				}
			}
		}
		vertices.remove(v);
	}
	
	public void addEgde(Vertex v1, Vertex v2, int weight){
		getEdgeList(v1).addEdge(v2, weight);
	}
	
	public void removeEdge(Vertex v1, Vertex v2){
		getEdgeList(v1).removeEdge(v2);
	}
	
	public int getWeight(Vertex v1, Vertex v2){
		if (v1.equals(v2))
			return 0;
		if (getEdgeList(v1).getEdge(v2)!=null)
			return getEdgeList(v1).getEdge(v2).weight;
		return (int)Integer.MAX_VALUE;
	}
	
	public ArrayList<Vertex> getVertices(){
		return vertices;
	}
	
	public Vertex getVertex(String withName){
		if(withName != null && withName != ""){
			for (Vertex vertex: vertices){
				if(vertex.name.equals(withName))
					return vertex;
			}
		}
		return null;
	}
	
	public boolean hasVertex(String name){
		for (Vertex v: vertices){
			if(v.equals(new Vertex(name))){
				return true;
			}
		}
		return false;
	}
	
	public EdgeList getEdgeList(Vertex v){
		int size = edges.size();
		for (int i=0; i<size; i++){
			if(edges.get(i).v1.equals(v)){
				return edges.get(i);
			}
		}
		return null;
	}
	

	
	public String pathToString(List<Vertex> path){
		// remove all elements from the list that are null to avoid an exception during iteration
		for(int i = 0;i<path.size();i++){
			if(path.get(i)==null){
				path.remove(path.indexOf(path.get(i)));
			}
		}
		String result = "Path: "+ path.get(0).name+ " to "+ path.get(path.size()-1).name + "\n";
		for(int i = 0;i<path.size();i++){
			Vertex v = path.get(i);
			if(v!=null){
				result += v.name + " ";
			}
		}
		return result;
	}
	
	public String pathToString(Vertex[] prev, String source, String target){
		String result = "";
		Vertex v1 = getVertex(source);
		Vertex v2 = getVertex(target);
		if (vertices.indexOf(prev[vertices.indexOf(v2)])==-1){
			return "You can't get from "+v1.name+" to "+v2.name+".";
		}
		result = v2.name;
		int i = vertices.indexOf(v2);
		while(i != vertices.indexOf(v1)){
			result = prev[i].name+ " "+ result;
			i = vertices.indexOf(prev[i]);
		}
		result = "Path: "+source+" to "+target+"\n"+result;
		return result;
	}
	
	public String[] getRandomSourceAndTarget(){
		String[] st = new String[2];
		String s1 = "*";
		String s2 = "*";
		while((s1.equals(s2))||(!hasVertex(s1))||(!hasVertex(s2))){
			s1 = RandomGraph.randomLetter();
			s2 = RandomGraph.randomLetter();
		}
		st[0] = s1;
		st[1] = s2;
		return st;
	}
	
	
	
	
	// Internal classes to create a graph: Vertex, EdgeList, EdgeNode
	
	
	public class Vertex {
		public String name;
		
		public Vertex(String name){
			this.name = name;
		}
		
		@Override
		public boolean equals(Object o){
			if(o instanceof Vertex){
				Vertex v = (Vertex) o;
				if(v.name.equals(this.name)){
					return true;
				}
			}
			return false;
		}
		
		@Override
		public String toString(){
			return name;
		}
	}
	
	public class EdgeNode {
		public Vertex v2;
		public int weight;
		
		public EdgeNode(Vertex v2, int weight){
			this.v2 = v2;
			this.weight = weight;
		}
		
		@Override
		public boolean equals(Object o){
			if(o instanceof EdgeNode){
				EdgeNode e = (EdgeNode) o;
				if(e.v2==v2 && e.weight==weight){
					return true;
				}
			}
			return false;
		}
	}
	
	public class EdgeList{
		public Vertex v1;
		public LinkedList<EdgeNode> edges;
		
		public EdgeList(Vertex v1){
			this.v1 = v1;
			edges = new LinkedList<EdgeNode>();
		}
		
		public void addEdge(Vertex v2, int weight){
			boolean present = false;
			int length = edges.size();
			for(int i = 0; i<length; i++){
				EdgeNode curr = edges.get(i);
				if(curr.v2.equals(v2)){
					present = true;
				}
			}
			if(present==false){
				edges.add(new EdgeNode(v2, weight));	
			}
		}
		
		public void removeEdge(Vertex v2){
			for(int i = 0; i < edges.size(); i++){
				EdgeNode curr = edges.get(i);
				if(curr.v2.equals(v2)){
					edges.remove(edges.indexOf(curr));
				}
				else{
					System.out.println("This edge is not in here.");
				}
			}
		}
		
		public EdgeNode getEdge(Vertex v2){
			for(EdgeNode e: edges){
				if(e.v2.equals(v2)){
					return e;
				}
			}
			return null;
		}
		
		public LinkedList<EdgeNode> getEdges(){
			return edges;
		}
		
		public boolean isEmpty(){
			if(edges.size()==0){
				return true;
			} else{
				return false;
			}
		}
		
		@Override
		public String toString(){
			String result = "";
			result += v1.name + ": ";
			int length = edges.size();
			for(int i = 0; i<length; i++){
				result += " --"+edges.get(i).weight+"--> " + edges.get(i).v2.name + " | ";
			}
			return result;
		}
	}
}
