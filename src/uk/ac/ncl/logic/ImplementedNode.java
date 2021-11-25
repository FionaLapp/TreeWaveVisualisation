package uk.ac.ncl.logic;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;

/**
 * Implements {@link Node} interface.
 * @author Fiona Lapp
 *
 */
public class ImplementedNode implements Node {
	private int id;
	private int[] neighbours;
	private boolean[] received;
	private boolean hasSilentNeighbour;
	private int numberOfNeighbours;
	private Integer silentNeighbour;
	private LinkedList<Message> buffer;
	private boolean[] sent;
	
	/**
	 * Creates a node object with empty buffer, "sent" and "received" arrays of correct size set to false
	 * @param id the unique id for the node (corresponding to the row/column of the adjacency matrix)
	 * @param adjacencyMatrix the adjacency matrix of the graph
	 */
	public ImplementedNode(int id, int[][] adjacencyMatrix) {
		this.id= id;
		this.silentNeighbour=null;
		this.buffer=new LinkedList<Message>();
		numberOfNeighbours= Arrays.stream(adjacencyMatrix[id]).sum();
		int graphSize=adjacencyMatrix[id].length;
		this.neighbours=new int[numberOfNeighbours]; // an empty array of the correct size
		
		//setting the neighbour array
		int neighbourCounter=0;
		for(int allNodesCounter=0; allNodesCounter<graphSize; allNodesCounter++) {
			if (adjacencyMatrix[id][allNodesCounter]==1) {
				neighbours[neighbourCounter]=allNodesCounter;
				neighbourCounter++;
			}
		}
		received= new boolean[graphSize];
		Arrays.fill(received, false);
		sent= new boolean[graphSize];
		Arrays.fill(sent, false);
		
		// TODO Auto-generated constructor stub
	}
	/**
	 * Id of the node in the graph. This should be set to a uniquely identifying integer number which will also be used for equals and hashcode.
	 * @return the id number of the node
	 */
	@Override
	public int getId() {
		return this.id;
	}
	
	/**
	 * The silent neighbour id
	 * @return the id number of the silent neighbour. If the silent neighbour has not been found yet, this is null.
	 */
	@Override
	public Integer getSilentNeighbour() {
		
		return new Integer(silentNeighbour);
	}
	
	/**
	 * @return neighbours - an array of the id numbers of the neighbours of this node
	 */
	@Override
	public int[] getNeighbours() {
		return neighbours;
	}
	/**
	 * @return received  - an array of booleans for each neighbour (in the order of the neighbours array) with "true" if a message has been received from this neighbour, "false" otherwise.
	 */
	@Override
	public boolean[] getReceived() {
		return received;
	}

	/**
	 * receive a message.
	 * <p>adds received message to the received array and removes it from buffer. Checks if the node has a silent neighbour (setting "hasSilentNeighbour" field)</p>
	 * @param message the message to be received
	 */
	@Override
	public void receiveMessage(Message message) {
		Node neighbour= message.getSender();
		int neighbourIndex=getIndexForReceived(neighbour.getId());
		received[neighbourIndex]=true;
		
		buffer.remove(message);
		
		if (!hasSilentNeighbour) {
			//iterate through to find out if there is a silent neighbour:
			checkForSilentNeighbour();
			
		}
	}
	
	/**
	 * Set the receiver's boolean value in this node's "sent" array to true. Call this function when sending a message from this node to the receiving node.
	 * @param receiver the node receiving a message
	 */
	@Override 
	public void setSent(Node receiver) {
		int receiverIndex= this.getIndexForReceived(receiver.getId());
		sent[receiverIndex]=true;
	}
	

	/**
	 * Helper function to convert a neighbour's id to their index in the "received" and "sent" array of this specific node
	 * @param neighbourId the neighbour's id
	 * @return the index of that neighbour's value in this node's "received" and "sent" array
	 */	
	@Override
	public int getIndexForReceived(int neighbourId) {
		int neighbourIndex= Arrays.binarySearch(neighbours, neighbourId);
		if (neighbours[neighbourIndex]==neighbourId) {
			return neighbourIndex;
		}
		else {
			throw new IndexOutOfBoundsException("This node is not a neighbour");
		}
	}

	

	/**
	 * check if the node has a silent neighbour, if so, set the "hasSilentNeighbour" field and the "silentNeighbour" field
	 */
	private void checkForSilentNeighbour() {
		if (!hasSilentNeighbour) {
		int possibleSilentNeighbour=-1;//none
		for (int neighbour=0; neighbour<numberOfNeighbours; neighbour++) {
			
			if (received[neighbour]==false) {
				if(possibleSilentNeighbour==-1) {
				possibleSilentNeighbour=neighbours[neighbour];
				}
				else {
					return;//no silent neighbour found since there are more than two not received messages
				}
			}
		}
		
		silentNeighbour=possibleSilentNeighbour;
		hasSilentNeighbour=true;}
	}
	
	/**
	 * value of "hasSilentNeighbour" field.
	 * <p>gets updated every time a message is received.</p>
	 * @return true if the node has a silent neighbour, false if it has not found its silent neighbour yet
	 */
	@Override
	public boolean hasSilentNeighbour() {
		checkForSilentNeighbour();
		return hasSilentNeighbour;
	}
	
	/**
	 * The buffer array for messages. 
	 * All incoming messages should be stored here, to be consumed by the node.
	 * @return the buffer array
	 */
	@Override
	public LinkedList<Message> getBuffer(){
		return buffer;
	}
	/**
	 * Add a message to the buffer
	 * @param message the message to be added to the buffer. This function should be called whenever an incoming message arrives at the node.
	 */
	@Override
	public void addToBuffer(Message message) {
		buffer.add(message);
	}
	/**
	 * pop a message from the buffer.
	 * This function should be called when the node is ready to consume messages.
	 * @return the first message in the buffer
	 */
	@Override
	public Message popFromBuffer() {
		return buffer.pop();
		
	}
	
	/**
	 * Check if this node has sent a message to the input node yet
	 * @param node the input node
	 * @return true if a message has already been sent, false otherwise
	 * @see hasSent(int)
	 */
	@Override 
	public boolean hasSent(Node node) {
		int nodeIndex= this.getIndexForReceived(node.getId());
		return sent[nodeIndex];
	}
	/**
	 * Check if this node has sent a message to the input node yet. Overloaded function with receiver's id instead of node object.
	 * @param nodeId the receiver's id
	 * @return true if a message has already been sent, false otherwise
	 * @see hasSent(Node)
	 */
	@Override 
	public boolean hasSent(int nodeId) {
		int nodeIndex= this.getIndexForReceived(nodeId);
		return sent[nodeIndex];
	}
	
	
	/**
	 * The string representation of a node. This is of the form: "node # [node id]"
	 * @return String representation of the node
	 */
	@Override
	public String toString() {
		return "Node #" + id;
	}
	/**
	 * hashcode corresponding to equals method
	 * @see equals
	 */
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	/**
	 * a node is equal to another node if they have the same id
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof ImplementedNode))
			return false;
		ImplementedNode other = (ImplementedNode) obj;
		return id == other.id;
	}
		
}
