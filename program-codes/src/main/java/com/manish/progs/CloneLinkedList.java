package com.manish.progs;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 * Program to clone a single linked list and test it  
 * 
 */
class CloneLinkedList {

	/*
	 * Insert the new nodes in between the existing nodes of linked list
	 */
	private void insertNodesInBetween(Node head) {
		while (head != null) {
			Node node = new Node(head.data);
			node.next = head.next;
			head.next = node;
			head = node.next;
		}
	}

	/*
	 * Initialize the random of new nodes to the proper nodes
	 * 
	 */
	private void initializeRandom(Node head) {
		while (head != null) {
			Node node = head.next;
			if (head.random != null)
				node.random = head.random.next;
			head = node.next;
		}
	}

	/*
	 * Split old linked list and new linked list
	 * 
	 */
	private Node splitNewLL(Node head) {
		if (head == null)
			return null;
		Node newHead = head.next;

		while (head != null) {
			Node node = head.next;
			head.next = node.next;
			if (head.next != null)
				node.next = head.next.next;
			else
				node.next = null;

			head = head.next;
		}
		return newHead;
	}

	/*
	 * Clone a given linked list using the function defined above
	 */
	private Node cloneLL(Node head) {
		//Node headCopy = head;
		insertNodesInBetween(head);
		initializeRandom(head);
		Node newHead = splitNewLL(head);
		return newHead;
	}

	/*
	 * main function : to test
	 * First create a linked list with random pointer set to arbitary pointer
	 * then clone the linked list and print both the list to see if both are same
	 */
	public static void main(String[] args) {
		CloneLinkedList cll = new CloneLinkedList();
		Random ranGen = new Random();
		List<Node> nodeList = new ArrayList<Node>();
		// create head first
		Node h1 = cll.insertLL(null, 1);
		nodeList.add(h1);
		// Add to the linked list
		for (int i = 0; i < 8; i++) {
			int num = ranGen.nextInt(10);
			Node n2 = cll.insertLL(h1, num);
			nodeList.add(n2);
		}
		// set random
		for (int i = 0; i < 8; i++) {
			int num = ranGen.nextInt(8);
			nodeList.get(i).random = nodeList.get(num);
		}
		cll.printLL(h1);
		Node nHead = cll.cloneLL(h1);
		cll.printLL(h1);
		cll.printLL(nHead);
	}

	/*
	 * Insert a new node into a linked list, if head passed is null then create the 
	 * first node
	 */
	private Node insertLL(Node hd, int num) {
		Node nn = new Node(num);
		if (hd == null)
			return nn;
		
		while(hd.next != null)
			hd = hd.next;
		
		hd.next = nn;		
		return nn;		
	}
	
	/*
	 * Print the given list along with the random node data
	 * 
	 */
	private void printLL(Node head) {
		while (head != null) {
			System.out.print("data: " + head.data);
			if (head.random != null)
				System.out.print(", random: " + head.random.data);
			else
				System.out.print(", random: null");
			System.out.print(" -->  ");
			head = head.next;
		}
		System.out.println("");
	}

	// Node of the single linked list with reference to a random node
	private class Node {
		int data;
		Node next;
		Node random;

		// constructor
		Node(int d) {
			this.data = d;
			next = null;
			random = null;
		}
	}

}
