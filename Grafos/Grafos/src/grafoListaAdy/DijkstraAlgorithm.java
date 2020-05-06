package grafoListaAdy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

/**
 *
 * @author Nicolás
 */
public class DijkstraAlgorithm
{

    private int distancias[];
    private Set<Integer> nodosVisitados;
    private PriorityQueue<Node> pq;
    private int V; // Number of vertices
    private List<List<Node>> adj;

    public DijkstraAlgorithm(int V)
    {
        this.V = V;
        distancias = new int[V];
        nodosVisitados = new HashSet<>();
        pq = new PriorityQueue<>(V, Comparator.comparing(Node::getCost));
    }

    // Function for Dijkstra's Algorithm
    public void dijkstra(List<List<Node>> adj, int src)
    {
        this.adj = adj;

        for (int i = 0; i < V; i++)
            distancias[i] = Integer.MAX_VALUE;

        // Add source numeroVertice to the priority queue
        pq.add(new Node(src, 0));

        // Distance to the source is 0
        distancias[src] = 0;

        while (nodosVisitados.size() != V)
        {
            // remove the minimum distance numeroVertice
            // from the priority queue
            int numeroVertice = pq.remove().numeroVertice;

            // adding the numeroVertice whose distance is
            // finalized
            nodosVisitados.add(numeroVertice);

            e_Neighbours(numeroVertice);
        }
    }

    // Function to process all the neighbours
    // of the passed u
    private void e_Neighbours(int numeroVertice)
    {
        int edgeDistance;
        int newDistance;

        // All the neighbors of v
        for (int idx = 0; idx < adj.get(numeroVertice).size(); idx++)
        {
            Node vertice = adj.get(numeroVertice).get(idx);

            // If current numeroVertice hasn't already been processed
            if (!nodosVisitados.contains(vertice.numeroVertice))
            {
                edgeDistance = vertice.cost;
                newDistance = distancias[numeroVertice] + edgeDistance;

                // If new distance is cheaper in cost
                if (newDistance < distancias[vertice.numeroVertice])
                    distancias[vertice.numeroVertice] = newDistance;

                // Add the current numeroVertice to the queue
                pq.add(new Node(vertice.numeroVertice, distancias[vertice.numeroVertice]));
            }
        }
    }

    // Driver code
    public static void main(String arg[])
    {
        int V = 5;
        int source = 0;

        // Adjacency list representation of the
        // connected edges
        List<List<Node>> adj = new ArrayList<>();

        // Initialize list for every numeroVertice
        for (int i = 0; i < V; i++)
        {
            List<Node> item = new ArrayList<>();
            adj.add(item);
        }

        // Inputs for the DPQ graph
        adj.get(0).add(new Node(1, 9));
        adj.get(0).add(new Node(2, 6));
        adj.get(0).add(new Node(3, 5));
        adj.get(0).add(new Node(4, 3));

        adj.get(2).add(new Node(1, 2));
        adj.get(2).add(new Node(3, 4));

        // Calculate the single source shortest path
        DijkstraAlgorithm dpq = new DijkstraAlgorithm(V);
        dpq.dijkstra(adj, source);

        // Print the shortest path to all the nodes
        // from the source numeroVertice
        System.out.println("The shorted path from node :");
        for (int i = 0; i < dpq.distancias.length; i++)
            System.out.println(source + " to " + i + " is "
                    + dpq.distancias[i]);
    }
}

// Class to represent a numeroVertice in the graph
class Node
{

    public int numeroVertice;
    public int cost;

    public Node()
    {
    }

    public Node(int node, int cost)
    {
        this.numeroVertice = node;
        this.cost = cost;
    }

    public int getNode()
    {
        return numeroVertice;
    }

    public int getCost()
    {
        return cost;
    }

}
