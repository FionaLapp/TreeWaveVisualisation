package uk.ac.ncl.logic;

public interface Message extends Comparable<Message> {
	public Node getSender();
	public Node getRecipient();
	public int getTimeLeft();
	public int compareTo(Message message);
	public void decreaseTimeLeft();// by one
	
}
