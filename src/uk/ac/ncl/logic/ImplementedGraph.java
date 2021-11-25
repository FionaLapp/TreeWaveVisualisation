package uk.ac.ncl.logic;

import java.util.Arrays;

public class ImplementedGraph {
	private int [][] connectionMatrix;
	private int[][] adjacencyMatrix;
	private int size;
	private Node[] nodes;
	/**
	 * Create a graph 
	 * <p>a matrix specifying how long it takes messages to be sent between nodes. Rows represent the sender, columns the receiver.
	 * If a value is set to Integer.MAX_VALUE, this is interprested as no connection being present.
	 * 
	 * </p>
	 * @param connectionMatrix a matrix specifying how long it takes messages to be sent between nodes. Rows represent the sender, columns the receiver.
	 */
	public ImplementedGraph(int[][] connectionMatrix) {
		if (connectionMatrix[0].length!=connectionMatrix.length) {
			throw new IllegalArgumentException("Needs to be a square matrix");
		}
		this.connectionMatrix=connectionMatrix;
		this.size= connectionMatrix.length;
		createAdjacencyMatrix();
		nodes= new Node[size];
		for (int i=0; i<size;i++) {
			nodes[i]=new ImplementedNode(i, adjacencyMatrix);
		}
		
	}
	

	/**
	 * creates an adjacency matrix using the connection matrix field. The adjacency matrix is a symmetrical matrix with each cell being 1 if there is a connection, 0 if there isn't.
	 * @throws IllegalArgumentException if the connection matrix is not symmetrical or the graph is not a tree
	 * 
	 */
	private void createAdjacencyMatrix() throws IllegalArgumentException{
		adjacencyMatrix=new int[size][size];
		for (int row=0; row<size; row++) {
			for (int col=0; col<=row; col++) {
				int connectionLength=connectionMatrix[row][col];
				int oppositeLength= connectionMatrix[col][row];
				int m= Integer.MAX_VALUE;
				if (connectionLength==m) {
					if (oppositeLength==m){
			adjacencyMatrix[row][col]=0; //set connection to 0(false) if connection length is infinity
			adjacencyMatrix[col][row]=0;
					}
					else{
						throw new IllegalArgumentException("graph needs to have symmetrical connections");
					}}
				else {
					if (!( oppositeLength==m)){
						adjacencyMatrix[row][col]=1; //set connection to 1(true) if connection length is less than infinity
						adjacencyMatrix[col][row]=1;
					}
					else{
						throw new IllegalArgumentException("graph needs to have symmetrical connections");
					}
				}
			}
			}
		int connectionSum=0;
		for (int[] row : adjacencyMatrix) {
			int rowSum=Arrays.stream(row).sum();
			if (rowSum==0) {
				throw new IllegalArgumentException("Not a tree, there are disconnected nodes");
			}
			connectionSum=connectionSum+rowSum;
		}
		if (connectionSum!=2*(size-1)) {
			throw new IllegalArgumentException("Not a tree, too many edges in the graph");
		}
		
		}
	
	
	/**
	 * nodes in the graph
	 * @return the nodes an array containing all node objects of the graph
	 */
	public Node[] getNodes() {
		return nodes;
	}


	/**
	 * size of the graph
	 * @return the size the size of the graph
	 */
	public int getSize() {
		return size;
	}


	/**
	 * connection matrix 
	 * <p>a matrix containing the length of each connection (rows represent senders, columns recipients)</p>
	 * @return the connectionMatrix
	 */
	public int[][] getConnectionMatrix() {
		return connectionMatrix;
	}

	/**
	 * adjacency matrix
	 * <p>each cell contains a 0 if there is no connection, a 1 if there is a connection (by default symmetrical). Rows represent senders, columns recipients.</p>
	 * @return the adjacencyMatrix
	 */
	public int[][] getAdjacencyMatrix() {
		return adjacencyMatrix;
	}
	
	

}
