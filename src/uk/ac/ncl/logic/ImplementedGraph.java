package uk.ac.ncl.logic;

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
	 */
	private void createAdjacencyMatrix(){
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
