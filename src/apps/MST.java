package apps;

import structures.*;
import java.util.ArrayList;

public class MST {
	
	/**
	 * Initializes the algorithm by building single-vertex partial trees
	 * 
	 * @param graph Graph for which the MST is to be found
	 * @return The initial partial tree list
	 */
	public static PartialTreeList initialize(Graph graph) {
		/* COMPLETE THIS METHOD */
		PartialTreeList L = new PartialTreeList();
		Vertex[] v = graph.vertices;
		PartialTree T;
		PartialTree.Arc a = null;
		
		for(int i = 0; i < v.length; i++){
			Vertex.Neighbor nbr = v[i].neighbors;
			T = new PartialTree(v[i]);
	   		MinHeap<PartialTree.Arc> P = T.getArcs();
	   		
	   		createHeap(v, nbr, a, P, L, i);
	   		
	   		L.append(T);
		}
		return L;
	}
	private static void createHeap(Vertex[] v,Vertex.Neighbor x, PartialTree.Arc a, MinHeap<PartialTree.Arc> P, PartialTreeList L, int i){
		while( x!= null){
			a = new PartialTree.Arc(v[i], x.vertex, x.weight);
			P.insert(a);
			System.out.println(P);
			x = x.next;
		}	
	}
	
	
	/**
	 * Executes the algorithm on a graph, starting with the initial partial tree list
	 * 
	 * @param ptlist Initial partial tree list
	 * @return Array list of all arcs that are in the MST - sequence of arcs is irrelevant
	 */
	public static ArrayList<PartialTree.Arc> execute(PartialTreeList ptlist) {
		ArrayList<PartialTree.Arc> L = new ArrayList<PartialTree.Arc>();
		
		System.out.println(executeHelper(ptlist, L));
		return executeHelper(ptlist, L);
	}
	
	private static ArrayList<PartialTree.Arc> executeHelper(PartialTreeList ptlist, ArrayList<PartialTree.Arc> L){
		while(ptlist.size() > 1){
			PartialTree ptx = ptlist.remove();
			PartialTree.Arc Pr = ptx.getArcs().getMin();
			
			Vertex v1 = Pr.v1;
			Vertex v2 = Pr.v2;

			PartialTree.Arc pqx = null;
			
			while(ifTrue(v2, ptx) == true){
				pqx = ptx.getArcs().deleteMin();
				Pr = ptx.getArcs().getMin();
				v1 = Pr.v1;
				v2 = Pr.v2;
				
			}
			pqx = ptx.getArcs().deleteMin();
			PartialTree pty = ptlist.removeTreeContaining(pqx.v2);
			ifPTY(pty,ptx, L, ptlist, pqx);
		}
		return L;

	}
	private static boolean ifTrue(Vertex v, PartialTree Tree){
    	while(v != null){
    		if(v == Tree.getRoot()){
    			return true;
    		}
    		if(v.parent == v){
    			return false;
    		}
    		v = v.parent;
    	}
    	return false;
    }
	private static void ifPTY(PartialTree pty, PartialTree ptx, ArrayList<PartialTree.Arc> L, PartialTreeList ptlist, PartialTree.Arc pqx){
		if(pty != null){
			L.add(pqx);
		
			ptx.merge(pty);
			ptlist.append(ptx);
		}

	}

}
