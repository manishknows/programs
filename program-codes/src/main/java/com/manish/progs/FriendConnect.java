package com.manish.progs;

import java.util.Arrays;
import java.util.Random;


/*
 * Application of quick union
 * 
 * This class is to know if all the people in a social network are connected?
 *  Queston Source: https://class.coursera.org/algs4partI-007/quiz/attempt?quiz_id=89 Question 1
 *  
 *  
 *  Input given an integer N .. 
 *  
 *  APIs
 *  ----
 *  find
 *  connect/union
 *  isConnected
 *  
 *  To tell as soon as all the elements are connected 
 *  
 *  For Other questions in the same exercise see at the bottom
 *  
 */
public class FriendConnect {
	final static int totFriends = 10;
	int [] arr;
	int [] sz;
	
	//constructor
	FriendConnect(int n){
		arr = new int[n];
		sz = new int[n];
		for (int i=0; i<n; i++) {
			arr[i] = i;
			// initialize size array with 1
			sz[i] = 1;
		}
	}
	
	// find the root of an element
	int findRoot(int n) {
		while (arr[n] != n)
			n = arr[n];
		return n;
	}
	
	void union(int p, int q){
		int rootP = findRoot(p);
		int rootQ = findRoot(q);
		// make sure that P and Q are already not connected
		if (rootP != rootQ) {
			// Make smaller tree subtree of larger one
			if (sz[rootP] < sz[rootQ]) {
				//q subtree is larger than p subtree
				// thus making smaller subtree child of larger tree
				arr[rootP] = rootQ;
				// Size of p is increased by the number of child in q
				sz[rootQ] += sz[rootP];
				if (sz[rootQ] == totFriends)
					System.out.println("All people are connected now");
			}
			else {
				arr[rootQ] = rootP;
				sz[rootP] += sz[rootQ];
				if (sz[rootP] == totFriends)
					System.out.println("All people are connected now");
			}
		}
		else
			System.out.println(p + " and " + q + " is already connected !" );
	}
	
	boolean isConnected(int p, int q) {
		return findRoot(p) == findRoot(q);
	}
	
	public static void main(String [] args) {
		Random randGenerator = new Random();
		FriendConnect friends = new FriendConnect(totFriends);
		for (int i = 0; i< 20; i++){
			int f1 = randGenerator.nextInt(totFriends);
			int f2 = randGenerator.nextInt(totFriends);
			if (friends.isConnected(f1, f2))
				System.out.println(f1+" and " + f2 + " are already connected");
			else {
				System.out.println( "Conecting: " + f1 +" and " + f2);
				friends.union(f1, f2);
			}
		}
		System.out.println("printing arrays: ");
		System.out.println("Friend Array: " + Arrays.toString(friends.arr));
		System.out.println("Size Array: " + Arrays.toString(friends.sz));
	}

}

/**
 * Q2: Implement a function to get the greatest number of a connected component.
 * Ans: Maintain an array which gives greatest for each root.
 * 
 * Q3: delete a number and get the next successor of an element
 * Similiar to q2
 * Maintian an array of largest element of connected components root.
 * In delete connect it with the next right element (maintaining longest)
 * In get successor, return the largest element of the root of the next right element.
 * 
 * 
 */
