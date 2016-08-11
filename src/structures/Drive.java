package structures;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import apps.MST;
import apps.PartialTree;


public class Drive {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		Scanner sysin = new Scanner(System.in);
		System.out.print("Enter input file name: ");
		String inFile = sysin.nextLine();
		
		Graph g = new Graph(inFile);
		
		MST.initialize(g);
		
		ArrayList<PartialTree.Arc> n = MST.execute(MST.initialize(g));
	}

}
