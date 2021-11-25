package uk.ac.ncl.logic;

/**
 * An interface for messages. They have a sender id, receiver id, and a field for the remaining time of transit.
 * @author Fiona Lapp
 *
 */
public interface Message {
	/**
	 * id of the node that sent the message
	 * @return the sender id
	 */
	public Node getSender();
	/**
	 * id of the node that will receive the message
	 * @return the receiver id
	 */
	public Node getRecipient();
	/**
	 * The transit time that is left for this message
	 * @return how long the message will still be in transit
	 */
	public int getTimeLeft();
	
	/**
	 * decrease the remaining transit time by 1. 
	 * <p>This method should be called on every message at every time step of the algorithm.</p>
	 * <p>If the time left is already 0, nothing will happen</p>
	 */
	public void decreaseTimeLeft();// by one
	
}
