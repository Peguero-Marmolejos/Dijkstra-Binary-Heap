import java.util.LinkedList;

public class Graph {
	int vertices;
	int count;
    LinkedList<Edge>[] adjacencylist;

    Graph(int vertices) {
        this.vertices = vertices;
        adjacencylist = new LinkedList[vertices];
        //initialize adjacency lists for all the vertices
        for (int i = 0; i <vertices ; i++) {
            adjacencylist[i] = new LinkedList<>();
        }
        count = 0;
    }

    public void addEdge(int source, int destination, int weight) {
        Edge edge = new Edge(source, destination, weight);
        adjacencylist[source].addFirst(edge);

        edge = new Edge(destination, source, weight);
        adjacencylist[destination].addFirst(edge); //for undirected graph
    }

    public void dijkstra_GetMinDistances(int sourceVertex){
        int INFINITY = Integer.MAX_VALUE;
        boolean[] SPT = new boolean[vertices];

//          //create heapNode for all the vertices
        HeapNode [] heapNodes = new HeapNode[vertices];
        for (int i = 0; i <vertices ; i++) {
            heapNodes[i] = new HeapNode();
            heapNodes[i].vertex = i;
            heapNodes[i].distance = INFINITY;
        }

        //decrease the distance for the first index
        heapNodes[sourceVertex].distance = 0;

        //add all the vertices to the MinHeap
        BinaryHeap minHeap = new BinaryHeap(vertices);
        for (int i = 0; i <vertices ; i++) {
            minHeap.insert(heapNodes[i]);
        }
        //while minHeap is not empty
        while(!minHeap.isEmpty()){
            //extract the min
            HeapNode extractedNode = minHeap.extractMin();

            //extracted vertex
            int extractedVertex = extractedNode.vertex;
            SPT[extractedVertex] = true;

            //iterate through all the adjacent vertices
            LinkedList<Edge> list = adjacencylist[extractedVertex];
            for (int i = 0; i <list.size() ; i++) {
                Edge edge = list.get(i);
                int destination = edge.destination;
                //only if  destination vertex is not present in SPT
                if(SPT[destination]==false ) {
                    ///check if distance needs an update or not
                    //means check total weight from source to vertex_V is less than
                    //the current distance value, if yes then update the distance
                    int newKey =  heapNodes[extractedVertex].distance + edge.weight ;
                    int currentKey = heapNodes[destination].distance;
                    if(currentKey>newKey){
                        decreaseKey(minHeap, newKey, destination);
                        heapNodes[destination].distance = newKey;
                    }
                    count++;
                }
            }
        }
        //print SPT
        printDijkstra(heapNodes, sourceVertex);
    }

    public void decreaseKey(BinaryHeap minHeap, int newKey, int vertex){

        //get the index which distance's needs a decrease;
        int index = minHeap.indexes[vertex];

        //get the node and update its value
        HeapNode node = minHeap.mH[index];
        node.distance = newKey;
        minHeap.bubbleUp(index);
    }

    public void printDijkstra(HeapNode[] resultSet, int sourceVertex){
        System.out.println("Dijkstra Algorithm: (Adjacency List + Binary Heap)");
        for (int i = 0; i <vertices ; i++) {
            System.out.println("Source Vertex: " + sourceVertex + " to vertex " +   + i +
                    " distance: " + resultSet[i].distance);
        }
    }
}
