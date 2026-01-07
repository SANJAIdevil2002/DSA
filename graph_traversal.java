public class graph_traversal {
    static class graph {
        int size;
        int adj[][];
        char[] ver;

        graph(int size) {
            this.size = size;
            this.adj = new int[size][size];
            this.ver = new char[size];
        }

        public void addvertex(int i, char v) {
            if (i < size && i >= 0) {
                ver[i] = v;
            }
        }

        public void addedge(int u, int v) {
            if (u < size && u >= 0 && v >= 0 && v < size) {
                adj[u][v] = 1;
                adj[v][u] = 1;
            }
        }

        public void printGraph() {
            for (int i = 0; i < size; i++) {
                System.out.println("vertex " + i + ":" + ver[i]);
            }
            System.out.println("adjacency matrix");
            for (int r[] : adj) {
                for (int i : r) {
                    System.out.print(i + " ");
                }
                System.out.println();
            }
        }

        // dfs
        public void dfs(char v) {
            System.out.println("dfs traversal .....");
            boolean[] visited = new boolean[size];
            int start = new String(ver).indexOf(v);
            dfsUtil(start, visited);
        }

        public void dfsUtil(int st, boolean[] visited) {
            visited[st] = true;
            System.out.print(ver[st] + " ");
            for (int i = 0; i < size; i++) {
                if (adj[st][i] == 1 && !visited[i]) {
                    dfsUtil(i, visited);
                }
            }

        }

    }
    public static void main(String[] args) {
        graph g=new graph(7);
        g.addvertex(0, 'A');
        g.addvertex(1, 'B');
        g.addvertex(2, 'C');
        g.addvertex(3, 'D');
        g.addvertex(4, 'E');
        g.addvertex(5, 'F');
        g.addvertex(6, 'G');

        g.addedge(0, 3);
        g.addedge(0, 2);
        g.addedge(0, 4);
        g.addedge(2, 4);
        g.addedge(2, 5);
        g.addedge(2, 1);
        g.addedge(2, 6);
        g.addedge(1, 5);

        g.printGraph();

        // traversal
        g.dfs('D');



        
    }
}
