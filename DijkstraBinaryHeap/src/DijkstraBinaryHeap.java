import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class DijkstraBinaryHeap {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//        graph.dijkstra_GetMinDistances(sourceVertex);
        
        long before = System.currentTimeMillis();
        int vertices = 10000;
        Graph graph = new Graph(vertices);
        ArrayList<String> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("data1(10000).txt"))) {
            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                data.add(sCurrentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(int i = 0; i < data.size(); i++) {
            String temp = data.get(i);
            int to = Integer.parseInt(temp.split(",")[1]);
            int from = Integer.parseInt(temp.split(",")[0]);
            int weight = Integer.parseInt(temp.split(",")[2]);
            graph.addEdge(from, to, weight);
        }
 
        graph.dijkstra_GetMinDistances(0);
        
        
        
        // get current time
        long time = System.currentTimeMillis() - before;
        System.out.println("Actual clock time : " + String.valueOf(time));
        System.out.println("Number of Operatiom: " + graph.count);
	}

}
