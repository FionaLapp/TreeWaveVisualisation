package uk.ac.ncl.logic;

import java.util.LinkedList;

public interface Node {
	int[] getNeighbours();
	boolean[] getReceived();
	void receiveMessage(Message message);
	boolean hasSilentNeighbour();
	int getPosition();
	Integer getSilentNeighbour();//returns null if there is none yet
	LinkedList<Message> getBuffer();
	void addToBuffer(Message m);
	Message removeFromBuffer();
	int getIndexForReceived(int neighbourPosition);
	boolean hasSent(Node node);
	void setSent(Node receiver);
	boolean hasSent(int nodePosition);
}
