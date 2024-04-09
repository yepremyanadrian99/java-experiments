package experiments.graph.dijkstra;

import java.util.*;

public class DijkstraAlgo {

    public static void main(String[] args) {
        final var dijkstraAlgo = new DijkstraAlgo();
        dijkstraAlgo.run();
    }

    private final Map<String, Map<String, Integer>> edges = new HashMap<>();
    private final Map<String, Node> shortest = new HashMap<>();
    private final Set<String> unvisited = new HashSet<>();
    private final Queue<String> minQueue = new PriorityQueue<>(
            (n1, n2) -> shortest.get(n1).weight - shortest.get(n2).weight
    );
    private String start;

    public void run() {
        inputGraph();
        initProperties();
        dijkstra();
        printShortest();
        printShortest("C");
    }

    private void dijkstra() {
        while (!minQueue.isEmpty()) {
            final var curr = minQueue.poll();
            final Map<String, Integer> neighbours = Objects.requireNonNullElse(edges.get(curr), Collections.emptyMap());
            final var currWeight = shortest.get(curr).weight;
            neighbours.entrySet().stream()
                    .filter(entry -> unvisited.contains(entry.getKey()))
                    .forEach(entry -> {
                        final var other = entry.getKey();
                        final var otherWeight = entry.getValue();
                        final var newWeight = currWeight + otherWeight;
                        final var currShortest = shortest.get(other);
                        if (currShortest == null || newWeight < currShortest.weight) {
                            shortest.put(other, new Node(newWeight, curr));
                            minQueue.add(other);
                        }
                    });
        }
    }

    private void inputGraph() {
        final var scanner = new Scanner(System.in);
        boolean directed = false;
        System.out.println("1.Directed or 2.Undirected graph?");
        while (true) {
            var input = scanner.nextInt();
            switch (input) {
                case 1:
                    directed = true;
                    break;
                case 2:
                    break;
                case 3: {
                    System.out.println("Unknown input, try again");
                    continue;
                }
            }
            break;
        }
        System.out.println("Input nodes");
        while (scanner.hasNext()) {
            String n = scanner.next();
            if (isTerminated(n)) {
                break;
            }
            unvisited.add(n);
        }
        System.out.println("Input starting node");
        while (scanner.hasNext()) {
            String n = scanner.next();
            if (!unvisited.contains(n)) {
                System.out.println("Unknown node entered");
                continue;
            }
            start = n;
            break;
        }
        System.out.println("Input edges in this format - v1 v2 weight");
        while (scanner.hasNext()) {
            String n1 = scanner.next();
            if (isTerminated(n1)) {
                break;
            }
            String n2 = scanner.next();
            int weight = scanner.nextInt();
            if (!(unvisited.contains(n1) && unvisited.contains(n2))) {
                System.out.println("Unknown nodes entered");
                continue;
            }
            edges.computeIfAbsent(n1, key -> new HashMap<>()).put(n2, weight);
            if (!directed) {
                edges.computeIfAbsent(n2, key -> new HashMap<>()).put(n1, weight);
            }
        }
    }

    private boolean isTerminated(String s) {
        return "end".equals(s);
    }

    private void initProperties() {
        shortest.put(start, new Node(0, null));
        minQueue.add(start);
    }

    private void printShortest() {
        for (var n : shortest.entrySet()) {
            final var other = n.getValue();
            System.out.println(n.getKey() + " - Dist: " + other.weight + ", Prev: " + other.prev);
        }
    }

    private void printShortest(String dest) {
        final List<String> path = new ArrayList<>();
        path.add(dest);

        Node curr = shortest.get(dest);
        do {
            path.add(curr.prev);
            curr = shortest.get(curr.prev);
        } while (curr.prev != null);
        Collections.reverse(path);
        int i = 0;
        for (; i < path.size() - 1; ++i) {
            System.out.print(path.get(i) + " -> ");
        }
        System.out.println(path.get(i));
    }

    private void printEdges() {
        for (var n : edges.entrySet()) {
            System.out.print(n.getKey() + ": [");
            for (var connect : n.getValue().entrySet()) {
                System.out.print(connect.getKey() + ":" + connect.getValue() + ", ");
            }
            System.out.println("]");
        }
    }

    private static class Node {
        public int weight;
        public String prev;

        public Node(int weight, String prev) {
            this.weight = weight;
            this.prev = prev;
        }
    }
}
/* Undirected example
2
A B C D E F
end
A
A B 2
A D 8
B D 5
B E 6
D E 3
D F 2
E F 1
E C 9
F C 3
end
*/

/* Directed example
1
A B C D E F
end
A
A B 10
A C 15
B F 15
B D 12
C E 10
D E 2
D F 1
F E 5
end
*/
