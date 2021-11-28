package uk.ac.ncl.logic;

import java.util.Objects;

/**
 * Implements the {@link Message} interface.
 * @author Fiona Lapp
 *
 */
public class ImplementedMessage implements Message {
	
	private final Node sender;
	private final Node recipient;
	private int timeLeft;
	
	/**
	 * @param sender the node sending the message
	 * @param recipient the node receiving the message
	 * @param timeLeft the time that the node will still remain in transit
	 */
	public ImplementedMessage(Node sender,Node recipient, int timeLeft) {
		this.sender=sender;
		this.recipient= recipient;
		this.timeLeft=timeLeft;
	}

	/**
	 * the node that sent the message
	 * @return the sender id
	 */
	@Override
	public Node getSender() {
		return sender;
	}


	/**
	 * the node that will receive the message
	 * @return the recipient id
	 */
	@Override
	public Node getRecipient() {
		return recipient;
	}
	
	/**
	 * The transit time that is left for this message
	 * @return how long the message will still be in transit
	 */
	@Override
	public int getTimeLeft() {
		return timeLeft;
	}

	/**
	 * decrease the remaining transit time by 1. 
	 * <p>This method should be called on every message at every time step of the algorithm.</p>
	 * <p>If the time left is already 0, nothing will happen</p>
	 */
	@Override
	public void decreaseTimeLeft() { //by one
		if (timeLeft>0)
		{timeLeft--;}
		// if the message has already arrived in the buffer, nothing happens
	
	}
	
	/**
	 * The string representation of a message. This is of the form: "message from [sender] to [recipient] has [timeLeft] time units left until arrival"
	 * @return String representation of the message
	 */
	@Override
	public String toString() {
		return "message from " + sender + " to " + recipient + " has " + timeLeft + " time units left until arrival";
	}

	/**
	 * hashcode corresponding to equals method
	 * @see equals
	 */
	@Override
	public int hashCode() {
		return Objects.hash(recipient, sender, timeLeft);
	}
	/**
	 * a message is equal to another message if they have the same sender, recipient and time left
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof ImplementedMessage))
			return false;
		ImplementedMessage other = (ImplementedMessage) obj;
		return Objects.equals(recipient, other.recipient) && Objects.equals(sender, other.sender)
				&& timeLeft == other.timeLeft;
	}

}
