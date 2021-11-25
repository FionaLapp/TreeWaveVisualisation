package uk.ac.ncl.logic;

import java.util.Objects;

public class ImplementedMessage implements Message {
	
	private final Node sender;
	private final Node receiver;
	private int timeLeft;
	
	public ImplementedMessage(Node sender,Node receiver, int timeLeft) {
		this.sender=sender;
		this.receiver= receiver;
		this.timeLeft=timeLeft;
	}

	@Override
	public Node getSender() {
		return sender;
	}



	@Override
	public Node getRecipient() {
		return receiver;
	}

	@Override
	public int getTimeLeft() {
		return timeLeft;
	}

	
	@Override
	public void decreaseTimeLeft() { //by one
		if (timeLeft>0)
		{timeLeft--;}
		// if the message has already arrived in the buffer, nothing happens
	
	}
	@Override
	public String toString() {
		return "ImplementedMessage from " + sender + " to " + receiver + " has " + timeLeft + " time units left until arrival";
	}

	@Override
	public int hashCode() {
		return Objects.hash(receiver, sender, timeLeft);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof ImplementedMessage))
			return false;
		ImplementedMessage other = (ImplementedMessage) obj;
		return Objects.equals(receiver, other.receiver) && Objects.equals(sender, other.sender)
				&& timeLeft == other.timeLeft;
	}

}
