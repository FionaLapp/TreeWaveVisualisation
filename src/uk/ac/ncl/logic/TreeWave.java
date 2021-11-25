package uk.ac.ncl.logic;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class TreeWave {
	private LinkedList<Message> messagesInTransit;
	private int timeCounter;
	private Node decisionMaker;
	private int timeOut;
	public TreeWave(ImplementedGraph graph) throws Exception {
		timeCounter=0;
		messagesInTransit= new LinkedList<Message>();
		timeOut=10;
		while (timeCounter<timeOut) {
			System.out.println(messagesInTransit);
			//push messages forward by one time step. If they arrive, put them into buffer
			Iterator iterator= messagesInTransit.iterator();
			while(iterator.hasNext()) {
				Message message= (Message)iterator.next();
				message.decreaseTimeLeft();
				if (message.getTimeLeft()==0) {
					message.getRecipient().addToBuffer(message);
					System.out.println("removing "+ message);
					iterator.remove();
					System.out.println("Message list after removal:"+ messagesInTransit);
				}
				else if (message.getTimeLeft()<0) {
					throw new Exception("Something went wrong, shouldn't get negative time");
				}
				
			}
			
			//handle nodes receiving and sendingmessages
			for (Node node:graph.getNodes()) {
				// check if it has silent neighbour
				boolean hasNotReceivedAllYet= !areAllTrue(node.getReceived());
				//System.out.println(node + " has not received all yet: "+ hasNotReceivedAllYet);
				while (hasNotReceivedAllYet&&!node.getBuffer().isEmpty()) {
					Message message=node.getBuffer().peek();
					node.receiveMessage(message);
					hasNotReceivedAllYet= !areAllTrue(node.getReceived());
					//System.out.println(node.getReceived());
				}
			if (node.hasSilentNeighbour()) {
				System.out.println("node "+node.getPosition()+ " has silent neighbour:" + node.getSilentNeighbour());
				
				
				if (node.getReceived()[node.getIndexForReceived(node.getSilentNeighbour())]==true) {
					decisionMaker= node;
					System.out.println("Decision reached in "+ timeCounter + "s");
					return;
				}
				if (!node.hasSent(node.getSilentNeighbour())) {
				Node sender= node;
				Node receiver= graph.getNodes()[node.getSilentNeighbour()];
					int timeForMessage= graph.getConnectionMatrix()[sender.getPosition()][receiver.getPosition()];
					sendMessage(sender,receiver, timeForMessage);
				}
				
				}
			}
			timeCounter++;
		}
		if (timeCounter==timeOut) {
			throw new Exception("Tree wave took too long, stopped executing at time "+ timeCounter);
		}
	}
	public static void main(String[] args) {
		
	}
	
	private static boolean areAllTrue(boolean[] array)
	{
	    for(boolean b : array) if(!b) return false;
	    return true;
	}
	
	public Node getDecisionMaker() {
		return decisionMaker;
	}
	
	private void sendMessage(Node sender, Node receiver, int time) {
		Message message = new ImplementedMessage(sender, receiver, time);
		sender.setSent(receiver);
		messagesInTransit.add(message);
		
	}
	
}
