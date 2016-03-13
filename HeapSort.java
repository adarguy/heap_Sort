/* HeapSort.java
   CSC 225 - Fall 2015
   Assignment 3 - Template for Heap Sort
   
   This template includes some testing code to help verify the implementation.
   To interactively provide test inputs, run the program with
	java HeapSort

   To conveniently test the algorithm with a large input, create a 
   text file containing space-separated integer values and run the program with
	java HeapSort file.txt
   where file.txt is replaced by the name of the text file.

   M. Simpson & B. Bird - 11/16/2015
*/

import java.util.Scanner;
import java.util.Vector;
import java.util.Arrays;
import java.io.File;
import java.math.*;

//Do not change the name of the HeapSort class
public class HeapSort{
	/* HeapSort(A)
		Sort the array A using heap sort.
		You may add additional functions (or classes) if needed, but the entire program must be
		contained in this file. 

		Do not change the function signature (name/parameters).
	*/
	public static void HeapSort(int[] A){

		int[] H = new int[A.length+1];
		int root = 1;
		H[root] = A[0];

		for (int i=2;i<A.length+1; i++){
			H[i] = A[i-1];
			System.out.println("Insert: "+Arrays.toString(H));
			bubbleUp(H, i);
		}

		for (int i=1; i<A.length+1; i++){
			A[i-1] = H[root];
			H[root] = H[H.length-i];
			H[H.length-i] = 0;
			System.out.println("ROOT: "+H[root]);
			bubbleDown(H, root);		
		}
	}

	public static void bubbleUp(int[] A, int a) {
		while (A[a] < A[parent(a)]) {
			int k = A[a];
			A[a] = A[parent(a)];
			A[parent(a)] = k;
			a = parent(a);
		}	
	}

	public static void bubbleDown(int[] A, int a) {
		int leaf = 0;

		while (hasNext(A, leftChild(a)) && (A[a] > A[leftChild(a)] || A[a] > A[rightChild(a)])) {
			if (hasNext(A, rightChild(a))){
				if (A[rightChild(a)] < A[leftChild(a)]) {
					int k = A[rightChild(a)];
					A[rightChild(a)] = A[a];
					A[a] = k;
					a = rightChild(a);
				}
				else {
					int k = A[leftChild(a)];
					A[leftChild(a)] = A[a];
					A[a] = k;
					a = leftChild(a);
				}
			}
			else {
				if (A[a] > A[leftChild(a)]){
					int k = A[leftChild(a)];
					A[leftChild(a)] = A[a];
					A[a] = k;
				}
				break;
			}		
		}
	}
	
	public static int parent(int a){
		return a/2;
	}

	public static int leftChild(int a){
		return a*2;
	}

	public static int rightChild(int a){
		return (a*2)+1;
	}

	public static boolean hasNext(int[] A, int a) {
		int leaf = 0;
		if (a < A.length && A[a] != leaf)
			return true;
		return false;
	}
	/* main()
	   Contains code to test the HeapSort function. Nothing in this function 
	   will be marked. You are free to change the provided code to test your 
	   implementation, but only the contents of the HeapSort() function above 
	   will be considered during marking.
	*/
	public static void main(String[] args){
		Scanner s;
		if (args.length > 0){
			try{
				s = new Scanner(new File(args[0]));
			} catch(java.io.FileNotFoundException e){
				System.out.printf("Unable to open %s\n",args[0]);
				return;
			}
			System.out.printf("Reading input values from %s.\n",args[0]);
		}else{
			s = new Scanner(System.in);
			System.out.printf("Enter a list of non-negative integers. Enter a negative value to end the list.\n");
		}
		Vector<Integer> inputVector = new Vector<Integer>();

		int v;
		while(s.hasNextInt() && (v = s.nextInt()) >= 0)
			inputVector.add(v);

		int[] array = new int[inputVector.size()];

		for (int i = 0; i < array.length; i++)
			array[i] = inputVector.get(i);

		System.out.printf("Read %d values.\n",array.length);


		long startTime = System.currentTimeMillis();

		HeapSort(array);

		long endTime = System.currentTimeMillis();

		double totalTimeSeconds = (endTime-startTime)/1000.0;

		//Don't print out the values if there are more than 100 of them
		if (array.length <= 100){
			System.out.println("Sorted values:");
			for (int i = 0; i < array.length; i++)
				System.out.printf("%d ",array[i]);
			System.out.println();
		}

		//Check whether the sort was successful
		boolean isSorted = true;
		for (int i = 0; i < array.length-1; i++)
			if (array[i] > array[i+1])
				isSorted = false;

		System.out.printf("Array %s sorted.\n",isSorted? "is":"is not");
		System.out.printf("Total Time (seconds): %.2f\n",totalTimeSeconds);
	}
}
