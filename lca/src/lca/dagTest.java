package lca;

//Test class for the whole program

import static org.junit.Assert.*;

import org.junit.Test;

public class dagTest {
	
	dag acyclic =new dag(8);//create an acyclic graph
	dag cycle = new dag(9);//create a cycle graph
	dag directAcyclic = new dag(9);//creates an acyclic graph
	
	//testing the set up of the constructor
	@Test(expected = IllegalArgumentException.class)//must be non-negative
	public void testdag() {
		dag negativeTest = new dag(-2);//will throw IllegalException if out of bounds
	}
	
	//Test the indegree of a vertex in the graph
	@Test(expected = IllegalArgumentException.class)
	public void testIndegree(){
		acyclicGraph();//has no cycle
		cycleGraph();//has cycle
		assertEquals("fails due the exception thrown", null, acyclic.indegree(-3));
		assertEquals("A straight line of vertices so each will only have one", 1, acyclic.indegree(5));
		assertEquals("2 edges leading into vertex eight", 2, cycle.indegree(8));
	}
	
	//Test the outdegree of a vertex in the graph
	@Test(expected = IllegalArgumentException.class)
	public void testOutdegree(){
		acyclicGraph();//has no cycle
		cycleGraph();//has cycle
		assertEquals("fails due to the exception thrown for out of bounds number", null, acyclic.outdegree(9));
		assertEquals("0 only has one outdegree", 1, acyclic.outdegree(0));	
		assertEquals("0 for the cycle graph has two outdegree edges", 2, cycle.outdegree(0));
	}
	
	//test the amount of edges with in a graph
	@Test
	public void testEdges(){
		acyclicGraph();
		cycleGraph();
		directAcyclicGraph();
		assertEquals("Number of edges within the graph should be", 7, acyclic.E());
		assertEquals("Number of edges within the graph should be", 9, cycle.E());
		assertEquals("Number of verices within the graph should be", 9, directAcyclic.E());
	}
	
	//test the number of vertices within a graph
	@Test
	public void testVerticies(){
		acyclicGraph();
		cycleGraph();
		directAcyclicGraph();
		assertEquals("Number of vertices within the graph should be", 8, acyclic.V());
		assertEquals("Number of vertices within the graph should be", 9, cycle.V());
		assertEquals("Number of verices within the graph should be", 9, directAcyclic.V());

	
	//test that adding an edge between two vertices crates a connection
	@Test(expected = IllegalArgumentException.class)
	public void testAddEdge(){
		acyclicGraph();//has no cycle
		cycleGraph();//has cycle
		assertEquals("Number of edges for dag is", 7, acyclic.E());
		acyclic.addEdge(5, 8);
		assertEquals("Number of edges for dag should increase to", 8, acyclic.E());
		acyclic.addEdge(-1, -1);
		assertEquals("This should do nothing as an exception is thrown, once the values are validated", 0, acyclic.E());
	}
	
	//test that the graph is acyclic, if there is a cycle the method will throw true that there is a cycle
	//with in the graph
	@Test
	public void testCycle(){
		acyclicGraph();//has no cycle
		cycleGraph();//has cycle
		directAcyclicGraph();//acyclic
		assertFalse(acyclic.hasCycle());//it is acyclic
		assertTrue(cycle.hasCycle());//there exists a cycle within the graph
		assertFalse(directAcyclic.hasCycle());//there exists a cycle within the graph
		dag emptyGraph = new dag(0);
		assertFalse(emptyGraph.hasCycle());//No graph therefore no cycle
	}
	
 	//Testing the LCA method, will test for various problems that may arise
	@Test(expected = IllegalArgumentException.class) 
	public void testLCA(){
		acyclicGraph();
		cycleGraph();
		directAcyclicGraph();
		//test the lca for both acyclic and cycled graph using two vertices
		assertEquals("Can be its own ancestor", 3, acyclic.findLCA(2, 3));
		assertEquals("Because it is cyclic it will throw an exception", 1, cycle.findLCA(1, 4));
		//showing different levels within the graph
		assertEquals("", 7, directAcyclic.findLCA(3, 4));
		assertEquals("", 7, directAcyclic.findLCA(1, 4));
		assertEquals("", 7, directAcyclic.findLCA(5, 2));
		//swapping around the vertices v and w
		assertEquals("", 5, directAcyclic.findLCA(1, 5));
		assertEquals("", 5, directAcyclic.findLCA(5, 1));
		//same v as w
		assertEquals("Can be its own ancestor", 3, acyclic.findLCA(3, 3));
		//empty graph
		dag emptyG = new dag(0);
		assertEquals("Should throw exception", null, acyclic.findLCA(3, 3));
	}
	
	//function to create an acyclic graph that I will use in the tests
	public void acylcicGraph(){
		//1->2->3->4->5->6->7
		acyclic.addEdge(0, 1);
		acyclic.addEdge(1, 2);
		acyclic.addEdge(2, 3);
		acyclic.addEdge(3, 4);
		acyclic.addEdge(4, 5);
		acyclic.addEdge(5, 6);
		acyclic.addEdge(6, 7);
	}
	
	public void directAcyclicGraph(){
		//  -> 1 -> 3 -> 5 ->
		//0    ^              7 -> 8             
		//  -> 2 -> 4 -> 6 ->      
		directAcyclic.addEdge(0, 1);
		directAcyclic.addEdge(0, 2);
		directAcyclic.addEdge(1, 3);
		directAcyclic.addEdge(2, 4);
		directAcyclic.addEdge(3, 5);
		directAcyclic.addEdge(4, 6);
		directAcyclic.addEdge(5, 7);
		directAcyclic.addEdge(6, 7);
		directAcyclic.addEdge(7, 8);
	}
	
	//function to create an graph(that cycles) that I will use in the tests
	public void cycleGraph(){
		//  -> 1    <- 3 -> 6 -> 8 
		//0    ->      ^         ^
		//  ->    2 -> 4         7
		cycle.addEdge(0, 1);
		cycle.addEdge(0, 2);
		cycle.addEdge(1, 2);
		cycle.addEdge(2, 4);
		cycle.addEdge(4, 3);
		cycle.addEdge(3, 1);
		cycle.addEdge(3, 6);
		cycle.addEdge(6, 8);
		cycle.addEdge(7, 8);
	}	
}
