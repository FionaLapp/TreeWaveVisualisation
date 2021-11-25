package uk.ac.ncl.logic;

import java.util.LinkedList;
import java.util.Objects;


/**
 * An interface for node objects. These have neighbours with whom they interchange messages, a buffer to store messages functionality associated with this.
 * @author Fiona Lapp
 *
 */

public interface Node {
	/**
	 * @return neighbours - an array of the id numbers of the neighbours of this node
	 */
	int[] getNeighbours();
	/**
	 * @return received  - an array of booleans for each neighbour (in the order of the neighbours array) with "true" if a message has been received from this neighbour, "false" otherwise.
	 */
	boolean[] getReceived();
	/**
	 * receive a message.
	 * <p>adds received message to the received array and removes it from buffer. Checks if the node has a silent neighbour (setting "hasSilentNeighbour" field)</p>
	 * @param message the message to be received
	 */
	void receiveMessage(Message message);
	/**
	 * value of "hasSilentNeighbour" field.
	 * <p>gets updated every time a message is received.</p>
	 * @return true if the node has a silent neighbour, false if it has not found its silent neighbour yet
	 */
	boolean hasSilentNeighbour();
	/**
	 * Id of the node in the graph. This should be set to a uniquely identifying integer number which will also be used for equals and hashcode.
	 * @return the id number of the node
	 */
	int getId();
	/**
	 * The silent neighbour id
	 * @return the id number of the silent neighbour. If the silent neighbour has not been found yet, this is null.
	 */
	Integer getSilentNeighbour();//returns null if there is none yet
	/**
	 * The buffer array for messages. 
	 * All incoming messages should be stored here, to be consumed by the node.
	 * @return the buffer array
	 */
	LinkedList<Message> getBuffer();
	/**
	 * Add a message to the buffer
	 * @param message the message to be added to the buffer. This function should be called whenever an incoming message arrives at the node.
	 */
	void addToBuffer(Message message);
	/**
	 * pop a message from the buffer.
	 * This function should be called when the node is ready to consume messages.
	 * @return the first message in the buffer
	 */
	Message popFromBuffer();
	/**
	 * Helper function to convert a neighbour's id to their index in the "received" and "sent" array of this specific node
	 * @param neighbourId the neighbour's id
	 * @return the index of that neighbour's value in this node's "received" and "sent" array
	 */
	int getIndexForReceived(int neighbourId);
	/**
	 * Check if this node has sent a message to the input node yet
	 * @param node the input node
	 * @return true if a message has already been sent, false otherwise
	 * @see hasSent(int)
	 */
	boolean hasSent(Node node);
	/**
	 * Set the receiver's boolean value in this node's "sent" array to true. Call this function when sending a message from this node to the receiving node.
	 * @param receiver the node receiving a message
	 */
	void setSent(Node receiver);
	/**
	 * Check if this node has sent a message to the input node yet. Overloaded function with receiver's id instead of node object.
	 * @param nodeId the receiver's id
	 * @return true if a message has already been sent, false otherwise
	 * @see hasSent(Node)
	 */
	boolean hasSent(int nodeId);
	/**
	 * The string representation of a node. This is of the form: "node # [node id]"
	 * @return String representation of the node
	 */
	@Override
	public String toString();
	/**
	 * hashcode corresponding to equals method
	 * @see equals
	 */
	@Override
	public int hashCode();
	/**
	 * a node is equal to another node if they have the same id
	 */
	@Override
	public boolean equals(Object obj);
}
