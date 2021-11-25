package uk.ac.ncl.logic;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;

public class ImplementedNode implements Node {
	private int id;
	private int[] neighbours;
	private boolean[] received;
	private boolean hasSilentNeighbour;
	private int numberOfNeighbours;
	private Integer silentNeighbour;
	private LinkedList<Message> buffer;
	private boolean[] sent;
	
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
	@Override
	public int getId() {
		return this.id;
	}
	
	@Override
	public Integer getSilentNeighbour() {
		
		return new Integer(silentNeighbour);
	}
	
	@Override
	public int[] getNeighbours() {
		return neighbours;
	}

	@Override
	public boolean[] getReceived() {
		return received;
	}

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
	
	@Override 
	public void setSent(Node receiver) {
		int receiverIndex= this.getIndexForReceived(receiver.getId());
		sent[receiverIndex]=true;
	}
	

		
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

	

	private void checkForSilentNeighbour() {
		
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
		if (possibleSilentNeighbour!=-1) {
		silentNeighbour=possibleSilentNeighbour;
		hasSilentNeighbour=true;}
	}
	
	@Override
	public boolean hasSilentNeighbour() {
		checkForSilentNeighbour();
		return hasSilentNeighbour;
	}

	@Override
	public LinkedList<Message> getBuffer(){
		return buffer;
	}
	
	@Override
	public void addToBuffer(Message message) {
		buffer.add(message);
	}
	
	@Override
	public Message popFromBuffer() {
		return buffer.pop();
		
	}
	
	
	@Override 
	public boolean hasSent(Node node) {
		int nodeIndex= this.getIndexForReceived(node.getId());
		return sent[nodeIndex];
	}
	
	@Override 
	public boolean hasSent(int nodeId) {
		int nodeIndex= this.getIndexForReceived(nodeId);
		return sent[nodeIndex];
	}
	
	@Override
	public String toString() {
		return "Node  #" + id;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
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
