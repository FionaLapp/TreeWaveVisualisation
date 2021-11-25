package uk.ac.ncl.logic;

/**
 * Interface for graphs
 * @author Fiona Lapp
 *
 */
public interface Graph {
	
		

	
		
		/**
		 * nodes in the graph
		 * @return the nodes an array containing all node objects of the graph
		 */
		public Node[] getNodes();


		/**
		 * size of the graph
		 * @return the size the size of the graph
		 */
		public int getSize();


		/**
		 * connection matrix 
		 * <p>a matrix containing the length of each connection (rows represent senders, columns recipients)</p>
		 * @return the connectionMatrix
		 */
		public int[][] getConnectionMatrix();

		/**
		 * adjacency matrix
		 * <p>each cell contains a 0 if there is no connection, a 1 if there is a connection (by default symmetrical). Rows represent senders, columns recipients.</p>
		 * @return the adjacencyMatrix
		 */
		public int[][] getAdjacencyMatrix() ;
		
		

	}
