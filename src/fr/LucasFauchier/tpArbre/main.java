package fr.LucasFauchier.tpArbre;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.swing.JFrame;

import org.jgrapht.Graph;
import org.jgrapht.ListenableGraph;
import org.jgrapht.alg.connectivity.KosarajuStrongConnectivityInspector;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm.SingleSourcePaths;
import org.jgrapht.alg.interfaces.StrongConnectivityAlgorithm;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultListenableGraph;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;

public class main {
	
	public static void main(String[] args) {
		    
		ListenableGraph<String, DefaultEdge> graph = new DefaultListenableGraph<>(new DefaultDirectedGraph<>(DefaultEdge.class));
	
		for (int i = 1; i <= 10; ++i) {
			graph.addVertex(String.valueOf(i));
		}
		graph.addEdge("1", "10");
		graph.addEdge("2", "8");
		graph.addEdge("3", "8");
		graph.addEdge("4", "1");
		graph.addEdge("4", "2");
		graph.addEdge("5", "10");
		graph.addEdge("5", "3");
		graph.addEdge("6", "5");
		graph.addEdge("6", "10");
		graph.addEdge("6", "7");
		graph.addEdge("7", "4");
		graph.addEdge("7", "5");
		graph.addEdge("7", "2");
		graph.addEdge("8", "5");
		graph.addEdge("8", "7");
		graph.addEdge("9", "4");
		graph.addEdge("9", "1");
		graph.addEdge("10", "6");
	
		
		ShowGui(graph);
		
		StrongConnectivityAlgorithm<String, DefaultEdge> scAlg = new KosarajuStrongConnectivityInspector<>(graph);
		List<Graph<String, DefaultEdge>> stronglyConnectedSubgraphs = scAlg.getStronglyConnectedComponents();
		DijkstraShortestPath<String, DefaultEdge> dijkstraAlg =
	            new DijkstraShortestPath<>(graph);
		
		for (int i = 0; i < stronglyConnectedSubgraphs.size(); i++) {
            System.out.println(stronglyConnectedSubgraphs.get(i));
        }
		
		while (true) {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Calcul de chemin entre le point : ");
			String s1 = null;
			
			try {	
				s1 = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("et le point : ");
			String s2 = null;
			
			try {
				s2 = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	        SingleSourcePaths<String, DefaultEdge> cPaths = dijkstraAlg.getPaths(s1);
	        System.out.println(cPaths.getPath(s2));
		}
		
	}
		
	static void ShowGui(ListenableGraph<String, DefaultEdge> graph) {
        JFrame frame = new JFrame("DemoGraph");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JGraphXAdapter<String, DefaultEdge> graphAdapter = 
                new JGraphXAdapter<String, DefaultEdge>(graph);
        graphAdapter.getStylesheet().getDefaultEdgeStyle().put(mxConstants.STYLE_NOLABEL, "1");
        mxIGraphLayout layout = new mxCircleLayout(graphAdapter);
        layout.execute(graphAdapter.getDefaultParent());
        frame.add(new mxGraphComponent(graphAdapter));

        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
}
