import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class lcatest {

	@Test
	/* Code to test if binary tree is empty */
	public void testisEmpty() {
		lca binaryTree = new lca();
		assertEquals(true, binaryTree.isEmpty()); // Need to create method to check if tree is empty or maybe create method to calculate length?
	}
	
	@Test
	/* Code to test if binary tree can add new elements */
	public void testGet() {
		lca binaryTree = new lca();
		binaryTree.put(2); // Need to create method that takes a value and creates a new node
		assertEquals(binaryTree.get(2).intValue(),2); // Need to create method to get value of node from given index
	}
	
	@Test
	/* Code to test if binary tree can add new elements */
	public void testDelete() {
		lca binaryTree = new lca();
		binaryTree.put(7);				//		_7_
		binaryTree.put(5);				//     /   \
		binaryTree.put(6);				//	 5		 6
		
		binaryTree.delete(6);	//Should delete leaf with value = 6
		assertEquals(binaryTree.get(6)==false); // Need to adjust get method to return false if value is not in tree
	}
	
	@Test
	/* Code to test if binary tree can calculate lca */
	public void testDelete() {
		lca binaryTree = new lca();
		binaryTree.put(7);				//		_7_
		binaryTree.put(5);				//     /   \
		binaryTree.put(6);				//	 5		 6
		
		int lcaIndex = binaryTree.lca(Node a, Node b);	//Need method called lca that will return the index value of the lca of two given nodes
		assertEquals(lcaIndex = 0); 
	}

}