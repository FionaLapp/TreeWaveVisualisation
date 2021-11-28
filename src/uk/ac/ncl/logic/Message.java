package uk.ac.ncl.logic;

import java.util.Objects;

/**
 * An interface for messages. They have a sender id, receiver id, and a field for the remaining time of transit.
 * @author Fiona Lapp
 *
 */
public interface Message {
	/**
	 * the node that sent the message
	 * @return the sender id
	 */
	public Node getSender();
	/**
	 * the node that will receive the message
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
	
	/**
	 * The string representation of a message. This is of the form: "message from [sender] to [recipient] has [timeLeft] time units left until arrival"
	 * @return String representation of the message
	 */
	@Override
	public String toString() ;

	/**
	 * hashcode corresponding to equals method
	 * @see equals
	 */
	@Override
	public int hashCode() ;
	/**
	 * a message is equal to another message if they have the same sender, recipient and time left
	 */
	@Override
	public boolean equals(Object obj);
}
