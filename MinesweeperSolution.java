/*
 * Andrew McCall
 * Software Engineering Assignment 2
 * Minesweeper solution
 * 04/08/2014
 */
import java.io.PrintStream;
import java.util.Scanner;


public class MinesweeperSolution {
	private static char[][] inputArray;
	private static String[][] outputArray;
	private static Scanner fin;
	private static PrintStream fout;
	
	//Main insertion point
	public static void main(String[] args) {
		hookup();
		parseInput();
	}
	
	//Attach the input and output streams to scanner and printstream
	private static void hookup() {
		fin = new Scanner(System.in);
		fout = new PrintStream(System.out);
	}
	
	//Read input in format of rows, cols, minesweeper grid. Repeat until rows == cols == 0.
	private static void parseInput() {
		int fieldNum = 1;
		while (true) {
			int n = fin.nextInt(), m = fin.nextInt();
			if (n == 0 || m == 0) 
				break;
			fin.nextLine();
			inputArray = new char[n][m];
			for (int i = 0; i < n; i++) {
				char[] tempParser = fin.nextLine().toCharArray();
				int mtemp = 0;
				for (char c : tempParser) {
					inputArray[i][mtemp] = c;
					mtemp++;
				}
			}
			traverseGrid(n,m);
			sendOutput(fieldNum);
			fieldNum++;
		}
	}
	
	//Print out the solution
	private static void sendOutput(int fieldNum) {
		if (inputArray.length > 0) {
			fout.println("Field #" + fieldNum);
			for (String[] ara : outputArray) {
				for (String str : ara) {
					fout.print(str);
				}
				fout.println();
			}
			fout.println();
		}
	}
	//Change in repo
	//Traverse 2d array, and build output array
	private static void traverseGrid(int n, int m) {
		if (inputArray.length > 0) {
			outputArray = new String[n][m];
			for (int i = 0;i < n;i++) {
				for (int j = 0;j < m;j++) {
					char curr;
					if ((curr = inputArray[i][j]) == '*') {
						outputArray[i][j] = ((Character)curr).toString();
					}
					else {
						outputArray[i][j] = findNeighbors(i,j);
					}
				}
			}
		}
	}
	
	//Find neighbors of an element in the array and return the number of mines around it
	private static String findNeighbors(int n, int m) {
		int topBound = n>0?n-1:0;
		int bottomBound = n<inputArray.length-1?n+1:inputArray.length-1;
		int leftBound = m>0?m-1:0;
		int rightBound = m<inputArray[n].length-1?m+1:inputArray[n].length-1;
		int numMines = 0;	
		for (int i = topBound; i <= bottomBound; i++) {
			for (int j = leftBound; j <= rightBound; j++) {
				if (inputArray[i][j] == '*') {
					numMines++;
				}
			}
		}
		return ((Integer)numMines).toString();
	}
}
