package uk.ac.ncl.logic;

public class ImplementedGraph {
	private int [][] connectionMatrix;
	private int[][] adjacencyMatrix;
	private int size;
	private Node[] nodes;
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
	

	private void createAdjacencyMatrix(){
		adjacencyMatrix=new int[size][size];
		for (int row=0; row<size; row++) {
			for (int col=0; col<size; col++) {
				int connectionLength=connectionMatrix[row][col];
			adjacencyMatrix[row][col]=(connectionLength==Integer.MAX_VALUE)? 0:1; //set connection to 0(false) if connection length is infinity, else seit it to true (1)
			
			}
			}
		}
	
	
	/**
	 * @return the nodes
	 */
	public Node[] getNodes() {
		return nodes;
	}


	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}


	/**
	 * @return the connectionMatrix
	 */
	public int[][] getConnectionMatrix() {
		return connectionMatrix;
	}

	/**
	 * @return the adjacencyMatrix
	 */
	public int[][] getAdjacencyMatrix() {
		return adjacencyMatrix;
	}
	
	

}
