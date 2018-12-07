package uk.co.elimurray.ci284.eightqueens;

import java.util.Scanner;

public class EightQueens {
	
	/** @author Eli Murray - ejdm11
	 *  @category University Assignment
	 */ 
	
	static int firstX;
	static int firstY;
	
	static char[] letters = "ABCDEFGH".toCharArray();
	
	/***************************************************************************
	 * Return true if queen placement q[n] does not conflict with
	 * other queens q[0] through q[n-1]
	 ***************************************************************************/
	
	public static boolean isConsistent(int[] q, int n) {
		for (int i = 0; i < n; i++) {
			if (q[i] == q[n])             return false;							// same column
			if ((q[i] - q[n]) == (n - i)) return false;							// same major diagonal
			if ((q[n] - q[i]) == (n - i)) return false;							// same minor diagonal
		}
		return true;
	}

	/***************************************************************************
	 * Prints n-by-n placement of queens from permutation q in ASCII.
	 ***************************************************************************/
	public static void printQueens(int[] q) {

		if (q[firstY] == firstX) {												// Checks if the permutation contains the Queen position given by user.
			System.out.println(" |1 2 3 4 5 6 7 8 ");
			System.out.println("-+----------------");
			for (int i = 0; i < 8; i++) {
				System.out.print(letters[i] + "|");
				for (int j = 0; j < 8; j++) {
					if (q[i] == j) System.out.print("Q ");
					else           System.out.print("* ");
				}
				System.out.println();
			}  
			System.out.println();
		}
	}


	/***************************************************************************
	 *  Try all permutations using backtracking
	 ***************************************************************************/
	public static void enumerate(int n) {
		int[] a = new int[n];
		enumerate(a, 0);
	}

	public static void enumerate(int[] q, int k) {
		if (k == 8) printQueens(q);
		else {
			for (int i = 0; i < 8; i++) {
				q[k] = i;
				if (isConsistent(q, k)) enumerate(q, k+1);
			}
		}
	}  

	public static void main(String[] args) {
		System.out.print("Please input x and y coordinates (1-8) for the first Queen:\n> ");
		Scanner in = new Scanner(System.in);
		firstX = in.nextInt() - 1;
		firstY = in.nextInt() - 1;
		while (firstX < 0 || firstX > 7 || firstY < 0 || firstY > 7) {
			System.out.printf("Invalid coordinates %d, %d. Please input valid coordinates.\n> ", firstX + 1, firstY + 1);
			firstX = in.nextInt() - 1;
			firstY = in.nextInt() - 1;
		}
		System.out.print("\n\n");
		in.close();
		
		enumerate(8);
	}

}